package io.vertx.reactivex.ext.mongo;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionAction;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRCompletionConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRErrorConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRHolder;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRSubscribeConsumer;
import com.newrelic.instrumentation.labs.vertx.rxjava.NRTerminateAction;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.BulkOperation;
import io.vertx.ext.mongo.BulkWriteOptions;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.IndexOptions;
import io.vertx.ext.mongo.MongoClientBulkWriteResult;
import io.vertx.ext.mongo.MongoClientDeleteResult;
import io.vertx.ext.mongo.MongoClientUpdateResult;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.ext.mongo.WriteOption;

@Weave(type = MatchType.BaseClass)
public abstract class MongoClient {

	@Trace
	public Maybe<MongoClientBulkWriteResult> rxBulkWrite(String collection, List<BulkOperation> operations) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientBulkWriteResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxBulkWrite", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientBulkWriteResult> rxBulkWriteWithOptions(String collection, List<BulkOperation> operations, BulkWriteOptions bulkWriteOptions) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientBulkWriteResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxBulkWriteWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<Long> rxCount(String collection, JsonObject query) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Single<Long> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxBulkWriteWithOptions", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Completable rxCreateCollection(String collectionName) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collectionName);
		Completable completableResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxCreateCollection", NewRelic.getAgent().getTransaction());
		return completableResult.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doOnTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Completable rxCreateIndex(String collection, JsonObject key) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Completable completableResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxCreateIndex", NewRelic.getAgent().getTransaction());
		return completableResult.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doOnTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Completable rxCreateIndexWithOptions(String collection, JsonObject key, IndexOptions options) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Completable completableResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxCreateIndexWithOptions", NewRelic.getAgent().getTransaction());
		return completableResult.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doOnTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<JsonArray> rxDistinct(String collection, String fieldName, String resultClassname) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Single<JsonArray> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxBulkWriteWithOptions", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<String> rxSave(String collection, JsonObject document) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<String> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxSave", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<String> rxSaveWithOptions(String collection, JsonObject document, WriteOption writeOption) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<String> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxSaveWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<String> rxInsert(String collection, JsonObject document) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<String> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxInsert", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<String> rxInsertWithOptions(String collection, JsonObject document, WriteOption writeOption) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<String> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxInsertWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientUpdateResult> rxUpdateCollection(String collection, JsonObject query, JsonObject update) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientUpdateResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxUpdateCollection", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientUpdateResult> rxUpdateCollectionWithOptions(String collection, JsonObject query, JsonObject update, UpdateOptions options) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientUpdateResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxUpdateCollectionWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientUpdateResult> rxReplaceDocuments(String collection, JsonObject query, JsonObject replace) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientUpdateResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxReplaceDocuments", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientUpdateResult> rxReplaceDocumentsWithOptions(String collection, JsonObject query, JsonObject replace, UpdateOptions options) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientUpdateResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxReplaceDocumentsWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<List<JsonObject>> rxFind(String collection, JsonObject query) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Single<List<JsonObject>> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFind", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<List<JsonObject>> rxFindWithOptions(String collection, JsonObject query, FindOptions options) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Single<List<JsonObject>> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindWithOptions", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxFindOne(String collection, JsonObject query, JsonObject fields) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<JsonObject> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindOne", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxFindOneAndUpdate(String collection, JsonObject query, JsonObject update) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<JsonObject> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindOneAndUpdate", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxFindOneAndUpdateWithOptions(String collection, JsonObject query, JsonObject update, FindOptions findOptions, UpdateOptions updateOptions) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<JsonObject> mayBeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindOneAndUpdateWithOptions", NewRelic.getAgent().getTransaction());
		return mayBeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxFindOneAndReplace(String collection, JsonObject query, JsonObject replace) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<JsonObject> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindOneAndReplace", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxFindOneAndReplaceWithOptions(String collection, JsonObject query, JsonObject replace, FindOptions findOptions, UpdateOptions updateOptions) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<JsonObject> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindOneAndReplaceWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxFindOneAndDelete(String collection, JsonObject query) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<JsonObject> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindOneAndDelete", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxFindOneAndDeleteWithOptions(String collection, JsonObject query, FindOptions findOptions) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<JsonObject> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxFindOneAndDeleteWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientDeleteResult> rxRemoveDocuments(String collection, JsonObject query) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientDeleteResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxRemoveDocuments", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientDeleteResult> rxRemoveDocumentsWithOptions(String collection, JsonObject query, WriteOption writeOption) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientDeleteResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxRemoveDocumentsWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientDeleteResult> rxRemoveDocument(String collection, JsonObject query) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientDeleteResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxRemoveDocument", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<MongoClientDeleteResult> rxRemoveDocumentWithOptions(String collection, JsonObject query, WriteOption writeOption) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Maybe<MongoClientDeleteResult> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxRemoveDocumentWithOptions", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<List<String>> rxGetCollections() { 
		Single<List<String>> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxGetCollections", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Completable rxDropCollection(String collection) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Completable completableResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxDropCollection", NewRelic.getAgent().getTransaction());
		return completableResult.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doOnTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<JsonArray> rxListIndexes(String collection) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Single<JsonArray> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxListIndexes", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Completable rxDropIndex(String collection, String indexName) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Completable completableResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxDropIndex", NewRelic.getAgent().getTransaction());
		return completableResult.doOnComplete(new NRCompletionAction(holder)).doOnError(new NRErrorConsumer(holder)).doOnSubscribe(new NRSubscribeConsumer(holder)).doOnTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Maybe<JsonObject> rxRunCommand(String commandName, JsonObject command) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("CommandName", commandName);
		Maybe<JsonObject> maybeResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxRunCommand", NewRelic.getAgent().getTransaction());
		return maybeResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public Single<JsonArray> rxDistinctWithQuery(String collection, String fieldName, String resultClassname, JsonObject query) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		Single<JsonArray> singleResult = Weaver.callOriginal();
		NRHolder holder = new NRHolder("MongoClient", "rxDistinctWithQuery", NewRelic.getAgent().getTransaction());
		return singleResult.doOnSubscribe(new NRSubscribeConsumer(holder)).doOnError(new NRErrorConsumer(holder)).doOnSuccess(new NRCompletionConsumer<>(holder)).doAfterTerminate(new NRTerminateAction(holder));
	}

	@Trace
	public io.vertx.reactivex.core.streams.ReadStream<JsonObject> distinctBatch(String collection, String fieldName, String resultClassname) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		return Weaver.callOriginal();
	}

	@Trace
	public io.vertx.reactivex.core.streams.ReadStream<JsonObject> distinctBatchWithQuery(String collection, String fieldName, String resultClassname, JsonObject query) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		return Weaver.callOriginal();
	}

	@Trace
	public io.vertx.reactivex.core.streams.ReadStream<JsonObject> distinctBatchWithQuery(String collection, String fieldName, String resultClassname, JsonObject query, int batchSize) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		return Weaver.callOriginal();
	}

	@Trace
	public io.vertx.reactivex.core.streams.ReadStream<JsonObject> aggregate(String collection, JsonArray pipeline) { 
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Collection", collection);
		return Weaver.callOriginal();
	}

}
