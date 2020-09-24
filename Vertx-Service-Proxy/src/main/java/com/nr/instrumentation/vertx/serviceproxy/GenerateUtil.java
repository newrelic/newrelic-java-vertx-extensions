package com.nr.instrumentation.vertx.serviceproxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;

import com.newrelic.agent.instrumentation.classmatchers.ExactClassMatcher;
import com.newrelic.agent.instrumentation.methodmatchers.ExactMethodMatcher;
import com.newrelic.agent.instrumentation.methodmatchers.MethodMatcher;
import com.newrelic.agent.instrumentation.methodmatchers.OrMethodMatcher;
import com.newrelic.api.agent.NewRelic;

public class GenerateUtil extends BaseGenerateUtil {
	
	protected static GenerateUtil instance = null;
	protected static final String handlerDirectory = "com/nr/instrumentation/vertx/serviceproxy";

	static {
		instance = new GenerateUtil();
		try {
			instance.init();
		} catch (IOException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to initialize GenerateUtil");
		}
	}
	
	protected static final String MANIFEST = "Manifest-Version: 1.0\n" + 
			"Implementation-Title: com.newrelic.instrumentation.Vertx-ServiceProxy-Custom\n" + 
			"Implementation-Version: 1.0\n" + 
			"Implementation-Vendor-Id: Field Instrumentation\n" + 
			"Implementation-Vendor: New Relic\n" + 
			"\n" + 
			"";
	
	private static final String PROXY_Imports = 
			"import io.vertx.core.json.JsonObject;\n" +
			"import io.vertx.core.eventbus.Message;\n";
	
	private static final String NRImports = "import com.newrelic.api.agent.NewRelic;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.weaver.Weave;\n" + 
			"import com.newrelic.api.agent.weaver.Weaver;\n" + 
			"import com.nr.instrumentation.vertx.serviceproxy.NRHandlerWrapper;\n";

	private static final String handlerCode = "package com.nr.instrumentation.vertx.serviceproxy;\n" + 
			"\n" + 
			"import com.newrelic.agent.bridge.AgentBridge;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"\n" + 
			"import io.vertx.core.AsyncResult;\n" + 
			"import io.vertx.core.Handler;\n" + 
			"\n" + 
			"public class NRHandlerWrapper<T> implements Handler<AsyncResult<T>> {\n" + 
			"	\n" + 
			"	private Handler<AsyncResult<T>> delegate = null;\n" + 
			"	private Token token = null;\n" + 
			"	private static boolean isTransformed = false;\n" + 
			"	\n" + 
			"	public NRHandlerWrapper(Handler<AsyncResult<T>> d, Token t) {\n" + 
			"		delegate = d;\n" + 
			"		token = t;\n" + 
			"		if(!isTransformed) {\n" + 
			"			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());\n" + 
			"			isTransformed = true;\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true,excludeFromTransactionTrace=true)\n" + 
			"	public void handle(AsyncResult<T> event) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		delegate.handle(event);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";
	
	protected File generateHandler() throws IOException {
		File packageDirectory = new File(srcDirectory,handlerDirectory);
		if(!packageDirectory.exists()) {
			packageDirectory.mkdirs();
		}
		
		File handlerJavaFile = new File(packageDirectory,"NRHandlerWrapper.java");
		PrintWriter writer = new PrintWriter(handlerJavaFile);
		writer.print(handlerCode);
		writer.close();
		
		return handlerJavaFile;
	}
	

	
	public void init() throws IOException {
		extensionJarName = getAppName()+"-Vertx-ServiceProxy-Custom.jar";

		super.init("NewRelic-ServiceProxy-"+getAppName());
		File handlerJavaFile = generateHandler();
		generatedJavaFiles.add(handlerJavaFile);
	}
	
	protected void checkForIgnored(HashMap<String,String> importsMap, String fullClassName) {
		Set<String> keys = importsMap.keySet();
		String simpleClassName = getClassName(fullClassName);
		
		for(String key : keys) {
			// never include the class itself
			if(key.equals(fullClassName)) {
				importsMap.remove(key);
				continue;
			}
			// never include an RxGen class that has its name
			String pn = getPackageName(key);
			String cn = getClassName(key);
			if(pn.contains(".reactivex.") && cn.equals(simpleClassName)) {
				importsMap.remove(key);
			}
		}
	}
	
	public File generateEBProxy(Class<?> proxyInterface) throws FileNotFoundException {
		
		
		Method[] declaredMethods = proxyInterface.getMethods();
		String packageName = proxyInterface.getPackage().getName();
		File packageDirectory = new File(srcDirectory, packageName.replace('.', '/'));
		if(!packageDirectory.exists()) {
			packageDirectory.mkdirs();
		}

		String ebClassName = proxyInterface.getSimpleName() +"VertxEBProxy";
		File ebProxyJavaFile = new File(packageDirectory, ebClassName+".java");
		PrintWriter writer = new PrintWriter(ebProxyJavaFile);
		writer.println("package "+packageName+";");
		writer.println();

		HashMap<String,String> classesToImport = generateImports(proxyInterface);
		
		// produce import statements
		
		for(String importClass : classesToImport.keySet()) {
			writer.println("import "+importClass+";");
		}
		
		writer.println(NRImports);
		
		writer.println();
		
		writer.println("@Weave");
		int interfaceModifiers = proxyInterface.getModifiers();
		if(Modifier.isPublic(interfaceModifiers)) {
			writer.print("public ");
		} 
		writer.println("abstract class "+ebClassName + "{");
		
		writer.println();
		
		// generate weaved methods
		for(Method method : declaredMethods) {
			int methodMods = method.getModifiers();
			if(Modifier.isStatic(methodMods)) continue;
			writer.println("\t@Trace");
			
			String genericString = method.toGenericString();
			boolean voidReturn = genericString.contains(" void ");
			String modGeneric = modifyGeneric(genericString,packageName,proxyInterface.getName(),classesToImport);
			writer.println("\t"+modGeneric+" {");
			boolean hasHandler = modGeneric.contains("Handler<AsyncResult");
			if(hasHandler) {
				int index = modGeneric.indexOf("Handler<AsyncResult<");
				if(index > -1) {
					int index2 = modGeneric.indexOf("> handler", index);
					if(index2 > -1) {
						String cn = modGeneric.substring(index+"Handler<AsyncResult<".length(), index2-1);
						writer.println(getAddHandlerCode(cn));
					}
				}
			}
			if(voidReturn) {
				writer.println("\t\tWeaver.callOriginal();");
			} else {
				writer.println("\t\t return Weaver.callOriginal();");
			}
			writer.println("\t}");
			writer.println();
		}

		writer.println("}");
		writer.close();
		return ebProxyJavaFile;
	}
	
	protected String getAddHandlerCode(String cn) {
			StringBuffer code = new StringBuffer();
			
			code.append("\t\tToken token = NewRelic.getAgent().getTransaction().getToken();\n");
			code.append("\t\tNRHandlerWrapper<"+cn+"> wrapper = ");
			code.append("new NRHandlerWrapper<"+cn+">(handler,token);\n");
			code.append("\t\thandler = wrapper;\n");
			return code.toString();
	}
	
	@Override
	protected String modifyGeneric(String generic, String packageName, String fullclassname, HashMap<String,String> classesToImport) {
		String tmp = generic.trim();
		for(String key : classesToImport.keySet()) {
			tmp = tmp.replace(key, classesToImport.get(key));
		}
		for(String pn : ignoredPackages) {
			tmp = tmp.replace(pn+".", "");
		}
		tmp = tmp.replace(packageName+".", "");
		
		StringTokenizer st = new StringTokenizer(tmp);
		StringBuffer sb = new StringBuffer();
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			// if contains parns it's the method signature
			if(token.contains("(") && token.contains(")")) {
				int index = token.indexOf('(');
				if(index > -1) {
					String tmp2 = token.substring(0, index);
					String methodName = getClassName(tmp2);
					sb.append(methodName);
					sb.append('(');
				}
				int index2 = token.indexOf(')');
				// this should be the comma separated list of parameters
				String parmList = token.substring(index+1,index2);
				StringTokenizer st3 = new StringTokenizer(parmList, ",");
				int paramCount = 0;
				int tokenCount = st3.countTokens();
				while(st3.hasMoreTokens()) {
					paramCount++;
					token = st3.nextToken();
					boolean isHandler = false;
					sb.append(token);
					isHandler = token.startsWith("Handler<AsyncResult");
					
					
					if(isHandler) {
						sb.append(" handler");
					} else {
						sb.append(" param"+paramCount);
					}
					if(paramCount < tokenCount) {
						sb.append(',');
					}
				}
				sb.append(") ");
			} else if(classesToImport.containsKey(token)) {
				String classname = getClassName(token);
				sb.append(classname+ " ");
			} else if(!ignoredModifiers.contains(token)){
				int index = token.indexOf('.');
				if(index > -1) {
					sb.append(getClassName(token)+" ");
				} else {
					sb.append(token);
				}
				sb.append(' ');
			}
			
		}
		return sb.toString();
	}
	
	public static void createPointcuts(Class<?> interfaceclass, Class<?> serviceClass, Class<?> proxyHandlerClass) {
		if(interfaceclass != null) {
			instance.trackEBProxy(interfaceclass);
		}
	}
	
	protected void trackEBProxy(Class<?> proxyInterface) {
		Method[] declaredMethods = proxyInterface.getMethods();
		String packageName = proxyInterface.getPackage().getName();

		String ebClassName = packageName + "." +proxyInterface.getSimpleName() +"VertxEBProxy";
		ExactClassMatcher classmatcher = new ExactClassMatcher(ebClassName);
		
		List<ExactMethodMatcher> methodMatchers = new ArrayList<ExactMethodMatcher>();
		for(Method method : declaredMethods) {
			com.newrelic.agent.deps.org.objectweb.asm.commons.Method nrMethod = com.newrelic.agent.deps.org.objectweb.asm.commons.Method.getMethod(method);
			ExactMethodMatcher methodMatcher = new ExactMethodMatcher(nrMethod.getName(), nrMethod.getDescriptor());
			methodMatchers.add(methodMatcher);
		}
		
		ExactMethodMatcher[] array = new ExactMethodMatcher[methodMatchers.size()];
		methodMatchers.toArray(array);
		MethodMatcher methodMatcher = OrMethodMatcher.getMethodMatcher(array);
		
		
	}
	
	public static void createWeaverClasses(Class<?> interfaceclass, Class<?> serviceClass, Class<?> proxyHandlerClass) {
		NewRelic.getAgent().getLogger().log(Level.FINE, "Call to build Service Proxy Weaver classes for interface: {0}, service: {1}, proxy handler: {2}", interfaceclass.getName(),serviceClass.getName(),proxyHandlerClass.getName());
		try {
			instance.generate(interfaceclass,serviceClass, proxyHandlerClass);
			instance.getJars(interfaceclass.getClassLoader());
			Boolean result = instance.compileJavaFiles();
			NewRelic.getAgent().getLogger().log(Level.FINE, "Result of compiling Vertx Service Proxy generated weaver classes is {0}", result);
			instance.copyClassFiles();
			
			instance.createJar();
		} catch (IOException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to create Service Proxy Weaver classes");
		}
	}
	
	protected File generateServiceWeaver(Class<?> serviceClass) throws FileNotFoundException {
		Method[] declaredMethods = serviceClass.getDeclaredMethods();
		
		String packageName = serviceClass.getPackage().getName();
		File packageDirectory = new File(srcDirectory, packageName.replace('.', '/'));
		if(!packageDirectory.exists()) {
			packageDirectory.mkdirs();
		}

		String serviceClassName = serviceClass.getSimpleName();
		File serviceJavaFile = new File(packageDirectory, serviceClassName+".java");
		PrintWriter writer = new PrintWriter(serviceJavaFile);
		writer.println("package "+packageName+";");
		writer.println();

		HashMap<String,String> classesToImport = generateImports(serviceClass);
		// produce import statements
		
		for(String importClass : classesToImport.keySet()) {
			writer.println("import "+importClass+";");
		}
		
		writer.println(NRImports);
		
		writer.println();
		
		writer.println("@Weave");
		int interfaceModifiers = serviceClass.getModifiers();
		if(Modifier.isPublic(interfaceModifiers)) {
			writer.print("public ");
		} 
		writer.println("abstract class "+serviceClassName + "{");
		
		writer.println();
		
		// generate weaved methods
		for(Method method : declaredMethods) {
			int methodMods = method.getModifiers();
			if(Modifier.isStatic(methodMods) || Modifier.isPrivate(methodMods)) continue;
			writer.println("\t@Trace");
			
			String genericString = method.toGenericString();
			boolean voidReturn = genericString.contains(" void ");
			String modGeneric = modifyGeneric(genericString,packageName,serviceClass.getName(),classesToImport);
			writer.println("\t"+modGeneric+" {");
			boolean hasHandler = modGeneric.contains("Handler<AsyncResult");
			if(hasHandler) {
				int index = modGeneric.indexOf("Handler<AsyncResult<");
				if(index > -1) {
					int index2 = modGeneric.indexOf("> handler", index);
					if(index2 > -1) {
						String cn = modGeneric.substring(index+"Handler<AsyncResult<".length(), index2-1);
						writer.println(getAddHandlerCode(cn));
					}
				}
			}
			if(voidReturn) {
				writer.println("\t\tWeaver.callOriginal();");
			} else {
				writer.println("\t\t return Weaver.callOriginal();");
			}
			writer.println("\t}");
			writer.println();
		}

		writer.println("}");
		writer.close();
		return serviceJavaFile;
	}
	
	protected File generateProxyHandlerWeaver(Class<?> proxyHandlerClass) throws FileNotFoundException {
		String packageName = proxyHandlerClass.getPackage().getName();
		File packageDirectory = new File(srcDirectory, packageName.replace('.', '/'));
		if(!packageDirectory.exists()) {
			packageDirectory.mkdirs();
		}

		String proxyClassName = proxyHandlerClass.getSimpleName();
		File proxyJavaFile = new File(packageDirectory, proxyClassName+".java");
		PrintWriter writer = new PrintWriter(proxyJavaFile);
		writer.println("package "+packageName+";");
		writer.println();

		// produce import statements
		writer.println(PROXY_Imports);
		
		writer.println(NRImports);
		
		writer.println();
		
		writer.println("@Weave");
		int interfaceModifiers = proxyHandlerClass.getModifiers();
		if(Modifier.isPublic(interfaceModifiers)) {
			writer.print("public ");
		} 
		writer.println("abstract class "+proxyClassName + "{");
		
		writer.println();
		
	    writer.println("\t@Trace");
	    
	    writer.println("\tpublic void handle(Message<JsonObject> param1)  {");
	    writer.println("\t\tWeaver.callOriginal();");
	    writer.println("\t}");

		
			writer.println();

		writer.println("}");
		writer.close();
		return proxyJavaFile;
	}

	
	public void generate(Class<?> interfaceClass, Class<?> serviceClass, Class<?> proxyHandlerClass) {
		try {
			if (interfaceClass != null) {				
				File generated = generateEBProxy(interfaceClass);
				generatedJavaFiles.add(generated);
			}
			if(serviceClass != null) {
				File generated2 = generateServiceWeaver(serviceClass);
				generatedJavaFiles.add(generated2);
			}
			if(proxyHandlerClass != null) {
				File generated3 = generateProxyHandlerWeaver(proxyHandlerClass);
				generatedJavaFiles.add(generated3);
			}
		} catch (IOException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to generate classes");
		}
	}
	
	public static void main(String[] args) {
		try {
			String theClassName = "com.dream11.wallet.dao.UserWalletTransactionDao";
			Class<?> iClass = Class.forName(theClassName);
			ClassLoader cl = iClass.getClassLoader();
			if(cl instanceof URLClassLoader) {
				URLClassLoader uCL = (URLClassLoader)cl;
				URL[] urls = uCL.getURLs();
				for(URL url : urls) {
					String urlFile = url.getFile();
					System.out.println(urlFile);
				}
				uCL.close();
			} else {
				System.out.println("classloader is not a URLClassloader");
			}
//			createWeaverClasses(iClass,null, null);
//			
//			theClassName = "com.dream11.wallet.dao.WalletAggregationDAO";
//			iClass = Class.forName(theClassName);
//			createWeaverClasses(iClass,null, null);
//			
//			theClassName = "com.dream11.wallet.dao.WalletDAO";
//			iClass = Class.forName(theClassName);
//			createWeaverClasses(iClass,null, null);
//			
//			// com.dream11.redis.RedisService
//			theClassName = "com.dream11.redis.RedisService";
//			iClass = Class.forName(theClassName);
//			createWeaverClasses(iClass,null,null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getManifest() {
		return MANIFEST;
	}
	
}
