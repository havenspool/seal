package com.havens.seal.main;

import java.net.UnknownHostException;

import com.havens.seal.dao.PersonDAO;
import com.havens.seal.model.Person;
import com.mongodb.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class SpringDataMongoDBMain {

	public static final String DB_NAME = "seal";
	public static final String PERSON_COLLECTION = "Person";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;

	private static MongoClientOptions getConfOptions() {
		return new MongoClientOptions.Builder().socketKeepAlive(true) // 是否保持长链接
				.connectTimeout(5000) // 链接超时时间
				.socketTimeout(5000) // read数据超时时间
				.readPreference(ReadPreference.primary()) // 最近优先策略
				.connectionsPerHost(30) // 每个地址最大请求数
				.maxWaitTime(1000 * 60 * 2) // 长链接的最大等待时间
				.threadsAllowedToBlockForConnectionMultiplier(50) // 一个socket最大的等待请求数
				.writeConcern(WriteConcern.NORMAL).build();
	}

	public static void main(String[] args) {
		try {
			Mongo mongo = new MongoClient(new ServerAddress(MONGO_HOST, MONGO_PORT), getConfOptions());
			MongoOperations mongoOps = new MongoTemplate(mongo, DB_NAME);

			Person p = new Person("113", "PankajKr", "Bangalore, India");
			mongoOps.insert(p, PERSON_COLLECTION);

			Person p1 = mongoOps.findOne(
					new Query(Criteria.where("name").is("PankajKr")),
					Person.class, PERSON_COLLECTION);

			System.out.println(p1);
			
			mongoOps.dropCollection(PERSON_COLLECTION);
			mongo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
