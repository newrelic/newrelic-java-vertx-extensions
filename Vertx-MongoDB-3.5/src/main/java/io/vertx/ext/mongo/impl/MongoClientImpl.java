package io.vertx.ext.mongo.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.streams.ReadStream;
import io.vertx.ext.mongo.BulkOperation;
import io.vertx.ext.mongo.BulkWriteOptions;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.IndexOptions;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.MongoClientBulkWriteResult;
import io.vertx.ext.mongo.MongoClientDeleteResult;
import io.vertx.ext.mongo.MongoClientUpdateResult;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.ext.mongo.WriteOption;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;

import com.mongodb.async.SingleResultCallback;
import com.newrelic.api.agent.DatastoreParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.vertx.mongodb.NRAsyncResultHandler;
import com.newrelic.instrumentation.vertx.mongodb.VertxMongoDBUtils;

@Weave
public abstract class MongoClientImpl {

	@Trace(dispatcher=true)
	public MongoClient bulkWriteWithOptions(String collection, List<BulkOperation> operations,BulkWriteOptions bulkWriteOptions, Handler<AsyncResult<MongoClientBulkWriteResult>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","bulkWrite"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("bulkWrite").build();
			Segment segment = txn.startSegment("MongoDB-BulkWrite");
			NRAsyncResultHandler<MongoClientBulkWriteResult> wrapper = new NRAsyncResultHandler<MongoClientBulkWriteResult>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient count(String collection, JsonObject query, Handler<AsyncResult<Long>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","count"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("count").build();
			Segment segment = txn.startSegment("MongoDB-Count");
			NRAsyncResultHandler<Long> wrapper = new NRAsyncResultHandler<Long>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient createIndexWithOptions(String collection, JsonObject key, IndexOptions options, Handler<AsyncResult<Void>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Key", key);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","createIndex"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("createIndex").build();
			Segment segment = txn.startSegment("MongoDB-CreateIndex");
			NRAsyncResultHandler<Void> wrapper = new NRAsyncResultHandler<Void>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient distinct(String collection, String fieldName, String resultClassname, Handler<AsyncResult<JsonArray>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "FieldName", fieldName);
		VertxMongoDBUtils.addAttribute(attributes, "ResultClassname", resultClassname);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","distinct"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("distinct").build();
			Segment segment = txn.startSegment("MongoDB-Distinct");
			NRAsyncResultHandler<JsonArray> wrapper = new NRAsyncResultHandler<JsonArray>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public ReadStream<JsonObject> distinctBatchWithQuery(String collection, String fieldName, String resultClassname, JsonObject query, int batchSize)  {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "FieldName", fieldName);
		VertxMongoDBUtils.addAttribute(attributes, "ResultClassname", resultClassname);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","distinctBatch"});
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient dropCollection(String collection, Handler<AsyncResult<Void>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","dropCollection"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("dropCollection").build();
			Segment segment = txn.startSegment("MongoDB-DropCollection");
			NRAsyncResultHandler<Void> wrapper = new NRAsyncResultHandler<Void>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public MongoClient dropIndex(String collection, String indexName, Handler<AsyncResult<Void>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "IndexName", indexName);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","dropIndex"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("dropIndex").build();
			Segment segment = txn.startSegment("MongoDB-DropIndex");
			NRAsyncResultHandler<Void> wrapper = new NRAsyncResultHandler<Void>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public ReadStream<JsonObject> findBatchWithOptions(String collection, JsonObject query, FindOptions options) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","findBatch"});
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient findOne(String collection, JsonObject query, JsonObject fields, Handler<AsyncResult<JsonObject>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "Fields", fields);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","findOne"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("findOne").build();
			Segment segment = txn.startSegment("MongoDB-FindOne");
			NRAsyncResultHandler<JsonObject> wrapper = new NRAsyncResultHandler<JsonObject>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient findOneAndDeleteWithOptions(String collection, JsonObject query, FindOptions findOptions, Handler<AsyncResult<JsonObject>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "FindOptions", findOptions);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","findOneAndDelete"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("findOneAndDelete").build();
			Segment segment = txn.startSegment("MongoDB-FindOneAndDelete");
			NRAsyncResultHandler<JsonObject> wrapper = new NRAsyncResultHandler<JsonObject>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient findOneAndReplaceWithOptions(String collection, JsonObject query, JsonObject replace, FindOptions findOptions, UpdateOptions updateOptions, Handler<AsyncResult<JsonObject>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "FindOptions", findOptions);
		VertxMongoDBUtils.addAttribute(attributes, "UpdateOptions", updateOptions);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","findOneAndReplace"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("findOneAndReplace").build();
			Segment segment = txn.startSegment("MongoDB-FindOneAndReplace");
			NRAsyncResultHandler<JsonObject> wrapper = new NRAsyncResultHandler<JsonObject>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient findOneAndUpdateWithOptions(String collection, JsonObject query, JsonObject update, FindOptions findOptions, UpdateOptions updateOptions, Handler<AsyncResult<JsonObject>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "FindOptions", findOptions);
		VertxMongoDBUtils.addAttribute(attributes, "UpdateOptions", updateOptions);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","findOneAndUpdate"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("findOneAndUpdate").build();
			Segment segment = txn.startSegment("MongoDB-FindOneAndUpdate");
			NRAsyncResultHandler<JsonObject> wrapper = new NRAsyncResultHandler<JsonObject>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient findWithOptions(String collection, JsonObject query, FindOptions options, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "FindOptions", options);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		Exception e = new Exception("in MongoClientImpl.findWithOptions");
		NewRelic.getAgent().getLogger().log(Level.FINE, e, "invoking findWithOptions({0},{1},{2},{3})",collection,query,options,resultHandler);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","find"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("find").build();
			Segment segment = txn.startSegment("MongoDB-Find");
			NRAsyncResultHandler<List<JsonObject>> wrapper = new NRAsyncResultHandler<List<JsonObject>>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient getCollections(Handler<AsyncResult<List<String>>> resultHandler) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","MongoDBClient","getCollections"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection("collections").operation("getCollections").build();
			Segment segment = txn.startSegment("MongoDB-GetCollections");
			NRAsyncResultHandler<List<String>> wrapper = new NRAsyncResultHandler<List<String>>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient insertWithOptions(String collection, JsonObject document, WriteOption writeOption, Handler<AsyncResult<String>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "WriteOption", writeOption);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","insert"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("insert").build();
			Segment segment = txn.startSegment("MongoDB-Insert");
			NRAsyncResultHandler<String> wrapper = new NRAsyncResultHandler<String>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient listIndexes(String collection, Handler<AsyncResult<JsonArray>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","listIndexes"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("listIndexes").build();
			Segment segment = txn.startSegment("MongoDB-ListIndexes");
			NRAsyncResultHandler<JsonArray> wrapper = new NRAsyncResultHandler<JsonArray>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public MongoClient removeDocumentsWithOptions(String collection, JsonObject query, WriteOption writeOption, Handler<AsyncResult<MongoClientDeleteResult>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "WriteOption", writeOption);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","removeDocuments"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("removeDocuments").build();
			Segment segment = txn.startSegment("MongoDB-RemoveDocuments");
			NRAsyncResultHandler<MongoClientDeleteResult> wrapper = new NRAsyncResultHandler<MongoClientDeleteResult>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public MongoClient removeDocumentWithOptions(String collection, JsonObject query, WriteOption writeOption, Handler<AsyncResult<MongoClientDeleteResult>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "WriteOption", writeOption);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","removeDocument"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("removeDocument").build();
			Segment segment = txn.startSegment("MongoDB-RemoveDocument");
			NRAsyncResultHandler<MongoClientDeleteResult> wrapper = new NRAsyncResultHandler<MongoClientDeleteResult>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient removeOneWithOptions(String collection, JsonObject query, WriteOption writeOption, Handler<AsyncResult<Void>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "WriteOption", writeOption);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","removeOne"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("removeOne").build();
			Segment segment = txn.startSegment("MongoDB-RemoveOne");
			NRAsyncResultHandler<Void> wrapper = new NRAsyncResultHandler<Void>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public MongoClient replaceDocumentsWithOptions(String collection, JsonObject query, JsonObject replace, UpdateOptions options, Handler<AsyncResult<MongoClientUpdateResult>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "UpdateOptions", options);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","replaceDocuments"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("replaceDocuments").build();
			Segment segment = txn.startSegment("MongoDB-ReplaceDocuments");
			NRAsyncResultHandler<MongoClientUpdateResult> wrapper = new NRAsyncResultHandler<MongoClientUpdateResult>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient saveWithOptions(String collection, JsonObject document, WriteOption writeOption, Handler<AsyncResult<String>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "WriteOption", writeOption);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","save"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("save").build();
			Segment segment = txn.startSegment("MongoDB-Save");
			NRAsyncResultHandler<String> wrapper = new NRAsyncResultHandler<String>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient updateCollectionWithOptions(String collection, JsonObject query, JsonObject update, UpdateOptions options,Handler<AsyncResult<MongoClientUpdateResult>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "UpdateOptions", options);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","updateCollection"});
		if(resultHandler != null) {
			Transaction txn = NewRelic.getAgent().getTransaction();
			DatastoreParameters params = DatastoreParameters.product("MongoDB").collection(collection).operation("updateCollection").build();
			Segment segment = txn.startSegment("MongoDB-UpdateCollection");
			NRAsyncResultHandler<MongoClientUpdateResult> wrapper = new NRAsyncResultHandler<MongoClientUpdateResult>(resultHandler,segment,params);
			resultHandler = wrapper;
		}
		return Weaver.callOriginal();
	}
	
	@Trace
	 private <T, R> SingleResultCallback<T> convertCallback(Handler<AsyncResult<R>> resultHandler, Function<T, R> converter) {
		 SingleResultCallback<T> callback = Weaver.callOriginal();
		 callback.token = NewRelic.getAgent().getTransaction().getToken();
		 return callback;
	 }
	
}
