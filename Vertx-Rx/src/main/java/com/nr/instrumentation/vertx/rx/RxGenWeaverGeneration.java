package com.nr.instrumentation.vertx.rx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;

import com.newrelic.api.agent.NewRelic;

import io.vertx.lang.rx.RxGen;

public class RxGenWeaverGeneration extends BaseGenerateUtil {

	protected static enum RxType {NOTRX,SINGLE,OBSERVABLE,FLOWABLE,COMPLETEABLE,MAYBE};

	private static RxGenWeaverGeneration instance = null;
	private static final List<String> ignoredMethods;
	private static final String handlerDirectory = "com/nr/instrumentation/vertx/rx";
	private static final String HAS_HANDLER_CHECK = "io.vertx.core.Handler<io.vertx.core.AsyncResult<";

	static {

		ignoredMethods = new ArrayList<String>();
		ignoredMethods.add("toString");
		ignoredMethods.add("equals");
		ignoredMethods.add("getDelegate");
		ignoredMethods.add("hashCode");

		instance = new RxGenWeaverGeneration();
		try {
			instance.init();
		} catch (IOException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to create RxGenWeaverGeneration instance");
		}
	}

	private static final String MANIFEST = "Manifest-Version: 1.0\n" + 
			"Implementation-Title: com.newrelic.instrumentation.Vertx-Rx-Custom\n" + 
			"Implementation-Version: 1.0\n" + 
			"Implementation-Vendor-Id: Field Instrumentation\n" + 
			"Implementation-Vendor: New Relic\n" + 
			"\n" + 
			"";

	protected void addGeneratedFile(File generated) {
		generatedJavaFiles.add(generated);
	}

	private static final String NRImports = "import com.newrelic.api.agent.NewRelic;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.weaver.Weave;\n" + 
			"import com.newrelic.api.agent.weaver.Weaver;\n" + 
			"import com.nr.instrumentation.vertx.rx.*;\n";

	private static final String handlerCode = "package com.nr.instrumentation.vertx.rx;\n" + 
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

	private static List<String> instrumented = new ArrayList<String>();

	protected void init() throws IOException {
		NewRelic.getAgent().getLogger().log(Level.FINE, "Initializing RxGenWeaverGeneration");
		String name = getAppName() + "-NewRelic-RxGen-Custom";
		extensionJarName = name + ".jar";
		super.init(name);
		File handlerJavaFile = generateHandler();
		generatedJavaFiles.add(handlerJavaFile);

		List<File> rxFiles = generateLifts();
		generatedJavaFiles.addAll(rxFiles);
	}

	protected File generateHandler() throws IOException {
		File packageDirectory = new File(srcDirectory,handlerDirectory);
		packageDirectory.mkdirs();

		File handlerJavaFile = new File(packageDirectory,"NRHandlerWrapper.java");
		PrintWriter writer = new PrintWriter(handlerJavaFile);
		writer.print(handlerCode);
		writer.close();

		return handlerJavaFile;
	}

	protected List<File> generateLifts() {
		File packageDirectory = new File(srcDirectory,handlerDirectory);
		packageDirectory.mkdirs();
		List<File> files = new ArrayList<File>();

		// Completable
		try {
			File completableObs = new File(packageDirectory,"NRCompletableObserver.java");
			PrintWriter writer = new PrintWriter(completableObs);
			writer.print(RXCode.COMPLETABLEOBSERVER);
			writer.close();
			files.add(completableObs);

			File completableOper = new File(packageDirectory,"NRCompletableOperator.java");
			writer = new PrintWriter(completableOper);
			writer.print(RXCode.COMPLETABLEOPERATOR);
			writer.close();
			files.add(completableOper);


		} catch (FileNotFoundException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to generate Completable files");
		}

		// Flowable
		try {
			File flowableObs = new File(packageDirectory,"NRFlowableObserver.java");
			PrintWriter writer = new PrintWriter(flowableObs);
			writer.print(RXCode.FLOWABLEOBSERVER);
			writer.close();
			files.add(flowableObs);

			File flowableOper = new File(packageDirectory,"NRFlowableOperator.java");
			writer = new PrintWriter(flowableOper);
			writer.print(RXCode.FLOWABLEOPERATOR);
			writer.close();
			files.add(flowableOper);


		} catch (FileNotFoundException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to generate Flowable files");
		}

		// Maybe
		try {
			File maybeObs = new File(packageDirectory,"NRMaybeObserver.java");
			PrintWriter writer = new PrintWriter(maybeObs);
			writer.print(RXCode.MAYBEOBSERVER);
			writer.close();
			files.add(maybeObs);

			File maybeOper = new File(packageDirectory,"NRMaybeOperator.java");
			writer = new PrintWriter(maybeOper);
			writer.print(RXCode.MAYBEOPERATOR);
			writer.close();
			files.add(maybeOper);


		} catch (FileNotFoundException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to generate Maybe files");
		}

		// Observable
		try {
			File observableObs = new File(packageDirectory,"NRObservableObserver.java");
			PrintWriter writer = new PrintWriter(observableObs);
			writer.print(RXCode.OBSERVABLEOBSERVER);
			writer.close();
			files.add(observableObs);

			File observableOper = new File(packageDirectory,"NRObservableOperator.java");
			writer = new PrintWriter(observableOper);
			writer.print(RXCode.OBSERVABLEOPERATOR);
			writer.close();
			files.add(observableOper);


		} catch (FileNotFoundException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to generate Observable files");
		}

		// Single
		try {
			File singleObs = new File(packageDirectory,"NRSingleObserver.java");
			PrintWriter writer = new PrintWriter(singleObs);
			writer.print(RXCode.SINGLEOBSERVER);
			writer.close();
			files.add(singleObs);

			File singleOper = new File(packageDirectory,"NRSingleOperator.java");
			writer = new PrintWriter(singleOper);
			writer.print(RXCode.SINGLEOPERATOR);
			writer.close();
			files.add(singleOper);


		} catch (FileNotFoundException e) {
			NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to generate Single files");
		}


		return files;
	}

	public static boolean isInstrumented(Class<?> rxClass) {
		return instrumented.contains(rxClass.getName());
	}

	public static void generateWeaver(Class<?> rxClass) {

		// will not instrument framework RxGen classes
		String packageName = rxClass.getPackage().getName();
		if(packageName.startsWith("io.vertx.reactivex")) return;

		RxGen rxgen = rxClass.getAnnotation(RxGen.class);

		String valueClassName = null;
		if(rxgen != null) {
			valueClassName = rxgen.value() != null ? rxgen.value().getName() : null;
		}
		String classToInstrument = rxClass.getName();

		if(!instrumented.contains(classToInstrument)) {
			NewRelic.getAgent().getLogger().log(Level.FINE, "Generating RxGen Weaver class for class {0}, value is {1}", rxClass, valueClassName);
			instance.getJars(rxClass.getClassLoader());

			try {
				File weaverFile = instance.generateWeaverFile(rxClass,valueClassName);
				instance.addGeneratedFile(weaverFile);
				Boolean result = instance.compileJavaFiles();
				NewRelic.getAgent().getLogger().log(Level.FINE, "Result of compiling Vertx Rx generated weaver classes is {0}", result);
				instance.copyClassFiles();

				instance.createJar();

			} catch (IOException e) {
				NewRelic.getAgent().getLogger().log(Level.FINE, e, "Failed to generate RxGen Weaver classes due to error");
			}
		}
	}

	protected File generateWeaverFile(Class<?> rxClass,String valueClassName) throws IOException {
		String classname = rxClass.getSimpleName();
		Package rxClassPackage = rxClass.getPackage();
		String packageName = rxClassPackage.getName();
		String packageDirName = packageName.replace(".", File.separator);
		File packageDir = new File(srcDirectory,packageDirName);
		if(!packageDir.exists()) {
			packageDir.mkdirs();
		}
		File weaverFile = new File(packageDir, classname +".java");
		PrintWriter writer = new PrintWriter(weaverFile);

		// write package header
		writer.println("package "+packageName+";");
		writer.println();

		HashMap<String, String> classImports = new HashMap<String, String>();
		writer.println();
		writer.println(NRImports);
		writer.println();

		String tmp = null;
		int modifiers = rxClass.getModifiers();
		if(Modifier.isPublic(modifiers)) {
			tmp = "public ";
		} else if(Modifier.isProtected(modifiers)) {
			tmp = "protected ";
		} else {
			tmp = "private ";
		}

		writer.println("@Weave");
		writer.println(tmp+" class " + classname +" {");

		Method[] methods = rxClass.getDeclaredMethods();

		for(Method method : methods) {
			String methodName = method.getName();

			if(ignoredMethods.contains(methodName)) continue;
			int methodModifiers = method.getModifiers();
			if(Modifier.isStatic(methodModifiers)) continue;
			if(!Modifier.isPrivate(methodModifiers)) {
				String genericMethodString = method.toGenericString();
				int methodMods = method.getModifiers();
				if(Modifier.isStatic(methodMods) || Modifier.isPrivate(methodMods)) continue;
				writer.println("\t@Trace(dispatcher=true)");

				boolean voidReturn = genericMethodString.contains(" void ");
				Class<?> returnClass = method.getReturnType();
				RxType rxClassType = isRx(returnClass);

				String modGeneric = modifyGeneric(genericMethodString,packageName,rxClass.getName(),classImports);
				writer.println("\t"+modGeneric+" {");
				boolean hasHandler = modGeneric.contains(HAS_HANDLER_CHECK);
				if(hasHandler) {
					int index = modGeneric.indexOf(HAS_HANDLER_CHECK);
					if(index > -1) {
						int index2 = modGeneric.indexOf("> handler", index);
						if(index2 > -1) {
							String cn = modGeneric.substring(index+HAS_HANDLER_CHECK.length(), index2-1);
							writer.println(getAddHandlerCode(cn));
						}
					}
				}

				String returnType = null;
				StringTokenizer st = new StringTokenizer(modGeneric);
				while(st.hasMoreTokens() && returnType == null) {
					String token = st.nextToken();
					if(!RxGenWeaverGeneration.modifiers.contains(token) && !token.startsWith("<")) {
						returnType = token;
					}
				}

				String paramType = null;
				if(rxClassType != RxType.NOTRX) {
					int index1 = returnType.indexOf('<');
					if(index1 > -1) {
						int index2 = returnType.length();
						if(index2 > -1) {

							paramType = returnType.substring(index1+1, index2-1);
						}
					}
				}
				if(voidReturn) {
					writer.println("\t\tWeaver.callOriginal();");
				} else {
					switch(rxClassType) {
					case SINGLE:
						writer.print("\t\t");
						writer.print(returnType);
						writer.println(" result = Weaver.callOriginal();");
						if(paramType == null) {
							writer.println("\t\treturn result.lift(new NRSingleOperator());");
						} else {
							writer.println("\t\treturn result.lift(new NRSingleOperator<"+paramType+">());");
						}
						break;
					case OBSERVABLE:
						writer.print("\t\t");
						writer.print(returnType);
						writer.println(" result = Weaver.callOriginal();");
						if(paramType == null) {
							writer.println("\t\treturn result.lift(new NROserverableOperator());");
						} else {
							writer.println("\t\treturn result.lift(new NROserverableOperator<"+paramType+">());");
						}
						break;
					case COMPLETEABLE:
						writer.print("\t\t");
						writer.print(returnType);
						writer.println(" result = Weaver.callOriginal();");
						writer.println("\t\treturn result.lift(new NRCompletableOperator());");
						break;
					case FLOWABLE:
						writer.print("\t\t");
						writer.print(returnType);
						writer.println(" result = Weaver.callOriginal();");
						if(paramType == null) {
							writer.println("\t\treturn result.lift(new NRFlowableOperator());");
						} else {
							writer.println("\t\treturn result.lift(new NRFlowableOperator<"+paramType+">());");
						}
						break;
					case MAYBE:
						writer.print("\t\t");
						writer.print(returnType);
						writer.println(" result = Weaver.callOriginal();");
						if(paramType == null) {
							writer.println("\t\treturn result.lift(new NRMaybeOperator());");
						} else {
							writer.println("\t\treturn result.lift(new NRMaybeOperator<"+paramType+">());");
						}
						break;
					case NOTRX:
						writer.println("\t\t return Weaver.callOriginal();");

					}
				}
				writer.println("\t}");
				writer.println();

			}
		}


		writer.println("\t}");
		writer.close();

		return weaverFile;
	}

	private String getAddHandlerCode(String cn) {
		StringBuffer code = new StringBuffer();

		code.append("\t\tToken token = NewRelic.getAgent().getTransaction().getToken();\n");
		code.append("\t\tNRHandlerWrapper<"+cn+"> wrapper = ");
		code.append("new NRHandlerWrapper<"+cn+">(handler,token);\n");
		code.append("\t\thandler = wrapper;\n");
		return code.toString();
	}

	protected String modifyGeneric(String generic, String packageName, String fullclassname, HashMap<String, String> classImports) {
		String tmp = generic.trim();
		for(String key : classImports.keySet()) {
			tmp = tmp.replace(key, classImports.get(key));
		}
		for(String pn : ignoredPackages) {
			tmp = tmp.replace(pn+".", "");
		}
		//		tmp = tmp.replace(packageName+".", "");

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
					isHandler = token.startsWith(HAS_HANDLER_CHECK);


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
			} else if(classImports.containsKey(token)) {
				String classname = getClassName(token);
				sb.append(classname+ " ");
			} else if(!ignoredModifiers.contains(token)){
				sb.append(token);
				sb.append(' ');
			}

		}
		return sb.toString();
	}

	@Override
	protected String getManifest() {
		return MANIFEST;
	}

	@Override
	protected void checkForIgnored(HashMap<String, String> importsMap, String fullClassName) {

	}

	private RxType isRx(Class<?> classToCheck) {
		String classname = classToCheck.getName();
		if(classname.startsWith("io.reactivex")) {
			if(classname.equals("io.reactivex.Single")) {
				return RxType.SINGLE;
			}
			if(classname.equals("io.reactivex.Observable")) {
				return RxType.OBSERVABLE;
			}
			if(classname.equals("io.reactivex.Completable")) {
				return RxType.COMPLETEABLE;
			}
			if(classname.equals("io.reactivex.Flowable")) {
				return RxType.FLOWABLE;
			}
			if(classname.equals("io.reactivex.Maybe")) {
				return RxType.MAYBE;
			}
		}
		return RxType.NOTRX;
	}

}
