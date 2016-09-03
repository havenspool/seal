package com.havens.seal.proxy;

import com.havens.seal.model.User;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @desc: TODO
 * @author: havens
 * @date: 2016/8/25 11:46
 */
public class MongodbDynamicProxyTest {
    AbstractApplicationContext ctx =new ClassPathXmlApplicationContext("SpringConfig.xml");
    MongoTemplate template = (MongoTemplate)ctx.getBean("mongoTemplate");
    private static String collectionName = "tb_player";

    @Test
    public void testInsert() {
        MethodInterceptor methodInterceptor = new SubjectInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteSubject.class);
        enhancer.setCallback(methodInterceptor);
        ISubject subject = (ISubject) enhancer.create();
        System.out.println(subject);
        template.insert(subject,collectionName);


        User user = template.findOne(new Query(),User.class,collectionName);
        System.out.println("User===" + user);
    }

}
