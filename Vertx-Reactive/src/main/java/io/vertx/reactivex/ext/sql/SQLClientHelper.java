package io.vertx.reactivex.ext.sql;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.vertx.reactive.NRCompletableOperator;
import com.nr.instrumentation.vertx.reactive.NRFlowableOperator;
import com.nr.instrumentation.vertx.reactive.NRMaybeOperator;
import com.nr.instrumentation.vertx.reactive.NRObservableOperator;
import com.nr.instrumentation.vertx.reactive.NRSingleOperator;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

@Weave
public abstract class SQLClientHelper {

//	@Trace
//	public static <T> Flowable<T> usingConnectionFlowable(SQLClient client,Function<SQLConnection, Flowable<T>> sourceSupplier) {
//		Flowable<T> result = Weaver.callOriginal();
//		return result.lift(new NRFlowableOperator<T>());
//	}
//
//	@Trace
//	public static <T> Observable<T> usingConnectionObservable(SQLClient client,Function<SQLConnection, Observable<T>> sourceSupplier) {
//		Observable<T> result = Weaver.callOriginal();
//		return result.lift(new NRObservableOperator<T>());
//	}
//
//	@Trace
//	public static <T> Single<T> usingConnectionSingle(SQLClient client,Function<SQLConnection, Single<T>> sourceSupplier) {
//		Single<T> result = Weaver.callOriginal();
//		return result.lift(new NRSingleOperator<T>());
//	}
//
//	@Trace
//	public static <T> Maybe<T> usingConnectionMaybe(SQLClient client,Function<SQLConnection, Maybe<T>> sourceSupplier) {
//		Maybe<T> result = Weaver.callOriginal();
//		return result.lift(new NRMaybeOperator<T>());
//	}
//
//	@Trace
//	public static Completable usingConnectionCompletable(SQLClient client,Function<SQLConnection, Completable> sourceSupplier) {
//		Completable result = Weaver.callOriginal();
//		return result.lift(new NRCompletableOperator());
//	}

}
