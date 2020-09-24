package com.nr.instrumentation.vertx.rx;

class RXCode {

	protected static final String SINGLEOBSERVER = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import com.newrelic.agent.bridge.AgentBridge;\n" + 
			"import com.newrelic.api.agent.NewRelic;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"\n" + 
			"import io.reactivex.SingleObserver;\n" + 
			"import io.reactivex.disposables.Disposable;\n" + 
			"\n" + 
			"public class NRSingleObserver<T> implements SingleObserver<T>, Disposable {\n" + 
			"	\n" + 
			"	private SingleObserver<T> downstream;\n" + 
			"	\n" + 
			"	private Disposable upstream;\n" + 
			"	\n" + 
			"	private Token token = null;\n" + 
			"	private static boolean isTransformed = false;\n" + 
			"\n" + 
			"	public NRSingleObserver(SingleObserver<T> downstream) {\n" + 
			"		this.downstream = downstream;\n" + 
			"		if(!isTransformed) {\n" + 
			"			isTransformed = true;\n" + 
			"			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void dispose() {\n" + 
			"		upstream.dispose();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public boolean isDisposed() {\n" + 
			"		return upstream.isDisposed();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void onSubscribe(Disposable d) {\n" + 
			"		if(token == null) {\n" + 
			"			token = NewRelic.getAgent().getTransaction().getToken();\n" + 
			"		}\n" + 
			"		if(upstream != null) {\n" + 
			"			d.dispose();\n" + 
			"		} else {\n" + 
			"			upstream = d;\n" + 
			"			downstream.onSubscribe(this);\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onSuccess(T t) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onSuccess(t);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onError(Throwable e) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onError(e);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";
	
	protected static final String SINGLEOPERATOR = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import io.reactivex.SingleObserver;\n" + 
			"import io.reactivex.SingleOperator;\n" + 
			"\n" + 
			"public class NRSingleOperator<T> implements SingleOperator<T,T> {\n" + 
			"\n" + 
			"	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n" + 
			"	@Override\n" + 
			"	public SingleObserver<? super T> apply(SingleObserver<? super T> observer) throws Exception {\n" + 
			"		SingleObserver<? super T> upstream = new NRSingleObserver(observer);\n" + 
			"		return upstream;\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";
		
	protected static final String COMPLETABLEOBSERVER = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import com.newrelic.agent.bridge.AgentBridge;\n" + 
			"import com.newrelic.api.agent.NewRelic;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"\n" + 
			"import io.reactivex.CompletableObserver;\n" + 
			"import io.reactivex.disposables.Disposable;\n" + 
			"\n" + 
			"public class NRCompletableObserver implements CompletableObserver, Disposable {\n" + 
			"	\n" + 
			"	private CompletableObserver downstream;\n" + 
			"	\n" + 
			"	private Disposable upstream;\n" + 
			"	\n" + 
			"	private Token token = null;\n" + 
			"	private static boolean isTransformed = false;\n" + 
			"\n" + 
			"	public NRCompletableObserver(CompletableObserver downstream) {\n" + 
			"		this.downstream = downstream;\n" + 
			"		if(!isTransformed) {\n" + 
			"			isTransformed = true;\n" + 
			"			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void dispose() {\n" + 
			"		upstream.dispose();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public boolean isDisposed() {\n" + 
			"		return upstream.isDisposed();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void onSubscribe(Disposable d) {\n" + 
			"		if(token == null) {\n" + 
			"			token = NewRelic.getAgent().getTransaction().getToken();\n" + 
			"		}\n" + 
			"		if(upstream != null) {\n" + 
			"			d.dispose();\n" + 
			"		} else {\n" + 
			"			upstream = d;\n" + 
			"			downstream.onSubscribe(this);\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onComplete() {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onComplete();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onError(Throwable e) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onError(e);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

	protected static final String COMPLETABLEOPERATOR = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import io.reactivex.CompletableObserver;\n" + 
			"import io.reactivex.CompletableOperator;\n" + 
			"\n" + 
			"public class NRCompletableOperator implements CompletableOperator {\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public CompletableObserver apply(CompletableObserver observer) throws Exception {\n" + 
			"		return new NRCompletableObserver(observer);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

	protected static final String FLOWABLEOBSERVER = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import org.reactivestreams.Subscriber;\n" + 
			"import org.reactivestreams.Subscription;\n" + 
			"\n" + 
			"import com.newrelic.agent.bridge.AgentBridge;\n" + 
			"import com.newrelic.api.agent.NewRelic;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"\n" + 
			"import io.reactivex.FlowableSubscriber;\n" + 
			"\n" + 
			"public class NRFlowableObserver<T> implements FlowableSubscriber<T>, Subscription {\n" + 
			"\n" + 
			"	private Subscriber<? super T> downstream;\n" + 
			"	\n" + 
			"	Subscription upstream;\n" + 
			"	private Token token = null;\n" + 
			"	private static boolean isTransformed = false;\n" + 
			"	\n" + 
			"	public NRFlowableObserver(Subscriber<? super T> s) {\n" + 
			"		downstream = s;\n" + 
			"		if(!isTransformed) {\n" + 
			"			isTransformed = true;\n" + 
			"			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());\n" + 
			"		}\n" + 
			"	}\n" + 
			"	\n" + 
			"	\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onNext(T t) {\n" + 
			"		if(token != null) {\n" + 
			"			token.link();\n" + 
			"		}\n" + 
			"		downstream.onNext(t);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onError(Throwable t) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onError(t);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onComplete() {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onComplete();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void request(long n) {\n" + 
			"		upstream.request(n);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void cancel() {\n" + 
			"		upstream.cancel();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void onSubscribe(Subscription s) {\n" + 
			"		if(upstream != null) {\n" + 
			"			s.cancel();\n" + 
			"		} else {\n" + 
			"			upstream = s;\n" + 
			"			downstream.onSubscribe(this);\n" + 
			"		}\n" + 
			"		if(token == null) {\n" + 
			"			token = NewRelic.getAgent().getTransaction().getToken();\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

	protected static final String FLOWABLEOPERATOR = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import org.reactivestreams.Subscriber;\n" + 
			"\n" + 
			"import io.reactivex.FlowableOperator;\n" + 
			"\n" + 
			"public class NRFlowableOperator<T> implements FlowableOperator<T, T> {\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public Subscriber<? super T> apply(Subscriber<? super T> subscriber) throws Exception {\n" + 
			"		\n" + 
			"		return new NRFlowableObserver<T>(subscriber);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

	protected static final String MAYBEOBSERVER = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import com.newrelic.agent.bridge.AgentBridge;\n" + 
			"import com.newrelic.api.agent.NewRelic;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"\n" + 
			"import io.reactivex.MaybeObserver;\n" + 
			"import io.reactivex.disposables.Disposable;\n" + 
			"\n" + 
			"public class NRMaybeObserver<T> implements MaybeObserver<T>, Disposable {\n" + 
			"	\n" + 
			"	private MaybeObserver<T> downstream;\n" + 
			"	\n" + 
			"	private Disposable upstream;\n" + 
			"	\n" + 
			"	private Token token = null;\n" + 
			"	private static boolean isTransformed = false;\n" + 
			"\n" + 
			"	public NRMaybeObserver(MaybeObserver<T> downstream) {\n" + 
			"		this.downstream = downstream;\n" + 
			"		if(!isTransformed) {\n" + 
			"			isTransformed = true;\n" + 
			"			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void dispose() {\n" + 
			"		upstream.dispose();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public boolean isDisposed() {\n" + 
			"		return upstream.isDisposed();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void onSubscribe(Disposable d) {\n" + 
			"		if(token == null) {\n" + 
			"			token = NewRelic.getAgent().getTransaction().getToken();\n" + 
			"		}\n" + 
			"		if(upstream != null) {\n" + 
			"			d.dispose();\n" + 
			"		} else {\n" + 
			"			upstream = d;\n" + 
			"			downstream.onSubscribe(this);\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onSuccess(T t) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onSuccess(t);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onError(Throwable e) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onError(e);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onComplete() {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onComplete();\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

	protected static final String MAYBEOPERATOR = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import io.reactivex.MaybeObserver;\n" + 
			"import io.reactivex.MaybeOperator;\n" + 
			"\n" + 
			"public class NRMaybeOperator<T> implements MaybeOperator<T, T> {\n" + 
			"\n" + 
			"	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n" + 
			"	@Override\n" + 
			"	public MaybeObserver<? super T> apply(MaybeObserver<? super T> observer) throws Exception {\n" + 
			"		return new NRMaybeObserver(observer);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";
	
	protected static final String OBSERVABLEOBSERVER = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import com.newrelic.agent.bridge.AgentBridge;\n" + 
			"import com.newrelic.api.agent.NewRelic;\n" + 
			"import com.newrelic.api.agent.Token;\n" + 
			"import com.newrelic.api.agent.Trace;\n" + 
			"\n" + 
			"import io.reactivex.Observer;\n" + 
			"import io.reactivex.disposables.Disposable;\n" + 
			"\n" + 
			"public class NRObservableObserver<T> implements Observer<T>, Disposable {\n" + 
			"\n" + 
			"	private Observer<T> downstream;\n" + 
			"	\n" + 
			"	private Disposable upstream;\n" + 
			"	\n" + 
			"	private Token token = null;\n" + 
			"	private static boolean isTransformed = false;\n" + 
			"\n" + 
			"	public NRObservableObserver(Observer<T> downstream) {\n" + 
			"		this.downstream = downstream;\n" + 
			"		if(!isTransformed) {\n" + 
			"			isTransformed = true;\n" + 
			"			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void dispose() {\n" + 
			"		upstream.dispose();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public boolean isDisposed() {\n" + 
			"		return upstream.isDisposed();\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	public void onSubscribe(Disposable d) {\n" + 
			"		if(token == null) {\n" + 
			"			token = NewRelic.getAgent().getTransaction().getToken();\n" + 
			"		}\n" + 
			"		if(upstream != null) {\n" + 
			"			d.dispose();\n" + 
			"		} else {\n" + 
			"			upstream = d;\n" + 
			"			downstream.onSubscribe(this);\n" + 
			"		}\n" + 
			"	}\n" + 
			"\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onError(Throwable e) {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onError(e);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onNext(T t) {\n" + 
			"		if(token != null) {\n" + 
			"			token.link();\n" + 
			"		}\n" + 
			"		downstream.onNext(t);\n" + 
			"	}\n" + 
			"\n" + 
			"	@Override\n" + 
			"	@Trace(async=true)\n" + 
			"	public void onComplete() {\n" + 
			"		if(token != null) {\n" + 
			"			token.linkAndExpire();\n" + 
			"			token = null;\n" + 
			"		}\n" + 
			"		downstream.onComplete();\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

	protected static final String OBSERVABLEOPERATOR = "package com.nr.instrumentation.vertx.rx;\n" + 
			"\n" + 
			"import io.reactivex.ObservableOperator;\n" + 
			"import io.reactivex.Observer;\n" + 
			"\n" + 
			"public class NRObservableOperator<T> implements ObservableOperator<T, T> {\n" + 
			"\n" + 
			"	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n" + 
			"	@Override\n" + 
			"	public Observer<? super T> apply(Observer<? super T> observer) throws Exception {\n" + 
			"		return new NRObservableObserver(observer);\n" + 
			"	}\n" + 
			"\n" + 
			"}\n" + 
			"";

}
