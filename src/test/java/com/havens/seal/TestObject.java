package com.havens.seal;

import com.havens.seal.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @desc: TODO
 * @author: havens
 * @date: 2016/8/24 16:12
 */
public class TestObject {
    private static MongoTemplate mongoTemplate;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;

    @BeforeClass
    public static void initSpring() {
        try {
            app = new ClassPathXmlApplicationContext("SpringConfig.xml");
            mongoTemplate = (MongoTemplate) app.getBean("mongoTemplate");
            collectionName = "users";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        User user = mongoTemplate.findOne(new Query(),User.class,collectionName);
        System.out.println("user===" + user);
    }
}
