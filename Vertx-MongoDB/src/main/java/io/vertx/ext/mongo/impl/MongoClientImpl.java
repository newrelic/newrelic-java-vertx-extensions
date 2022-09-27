package io.vertx.ext.mongo.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
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

import com.mongodb.async.SingleResultCallback;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
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
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient distinctBatch(String collection, String fieldName, String resultClassname, Handler<AsyncResult<JsonObject>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "FieldName", fieldName);
		VertxMongoDBUtils.addAttribute(attributes, "ResultClassname", resultClassname);
		
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
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient findBatchWithOptions(String collection, JsonObject query, FindOptions options, Handler<AsyncResult<JsonObject>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "FindOptions", options);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","findBatchWithOptions"});
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
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","findWithOptions"});
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient getCollections(Handler<AsyncResult<List<String>>> resultHandler) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","MongoDBClient","getCollections"});
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
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient listIndexes(String collection, Handler<AsyncResult<JsonArray>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","listIndexes"});
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
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public MongoClient replaceDocumentsWithOptions(String collection, JsonObject query, JsonObject replace, UpdateOptions options, Handler<AsyncResult<MongoClientUpdateResult>> resultHandler) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Vertx","MongoDBClient","replaceDocuments"});
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
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher=true)
	public io.vertx.ext.mongo.MongoClient updateCollectionWithOptions(String collection, JsonObject query, JsonObject update, UpdateOptions options,Handler<AsyncResult<MongoClientUpdateResult>> resultHandler) {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		VertxMongoDBUtils.addAttribute(attributes, "Collection", collection);
		VertxMongoDBUtils.addAttribute(attributes, "Query", query);
		VertxMongoDBUtils.addAttribute(attributes, "UpdateOption", options);
		
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttributes(attributes);
		traced.setMetricName(new String[] {"Custom","Vertx","MongoDBClient","updateCollection"});
		return Weaver.callOriginal();
	}
	
	@Trace
	 private <T, R> SingleResultCallback<T> convertCallback(Handler<AsyncResult<R>> resultHandler, Function<T, R> converter) {
		 SingleResultCallback<T> callback = Weaver.callOriginal();
		 callback.token = NewRelic.getAgent().getTransaction().getToken();
		 return callback;
	 }
	
}
