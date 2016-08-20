package com.havens.seal.main;

import com.havens.seal.dao.PersonDAO;
import com.havens.seal.model.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringMongoDBXMLMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("SpringConfig.xml");
		PersonDAO personDAO = ctx.getBean("personDAO", PersonDAO.class);
		
		Person p = new Person(null, "PankajKr", "Bangalore, India");
		
		//create
		personDAO.create(p);
		System.out.println("Generated ID="+p.getId());
		
		//read
		Person p1 = personDAO.readById(p.getId());
		System.out.println("Retrieved Person="+p1);
		
		//update
		p1.setName("David");p1.setAddress("SFO, USA");
		personDAO.update(p1);
		Person temp = personDAO.readById(p1.getId());
		System.out.println("Retrieved Person after update="+temp);
		
		//delete
		int count = personDAO.deleteById(p1.getId());
		System.out.println("Number of records deleted="+count);
		
		ctx.close();
	}

}
