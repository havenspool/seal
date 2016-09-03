package com.havens.seal;

import com.havens.seal.model.User;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @desc: TODO
 * @author: havens
 * @date: 2016/8/24 16:12
 */
public class MongoDaoTest {
    AbstractApplicationContext ctx =new ClassPathXmlApplicationContext("SpringConfig.xml");
    MongoTemplate template = (MongoTemplate)ctx.getBean("mongoTemplate");
    private static String collectionName = "users";

    @Test
    public void testQuery() {
        User user = template.findOne(new Query(),User.class,collectionName);
        System.out.println("User===" + user);
    }

    @Test
    public void testAdd() {
        //添加一百个user
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setId("" + i);
            user.setAge(i);
            user.setName("zcy" + i);
            user.setPassword("zcy" + i);
            template.insert(user, collectionName);
        }
        List<User> users = template.findAll(User.class,collectionName);
        System.out.println("Users===" + users.toArray());
    }
}
