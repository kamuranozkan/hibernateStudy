package com.hb12.caching;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RunnerFetch12 {

	public static void main(String[] args) {

		Configuration con = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student12.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		// !!! 1)  **************************************************
		System.out.println("First get for id 1");
		Student12 student1= session.get(Student12.class,1L);
		
		// !!! 2 ) clear metodu ile cache temizlendi.
		//session.clear();
		
		System.out.println("Second get for id 1");
		Student12 student2= session.get(Student12.class,1L);


		System.out.println("Second get for id 2");
		Student12 student3= session.get(Student12.class,2L);

		
		

		tx.commit();
		session.close();
	

		/*
		Session session2= sf.openSession();
		Transaction tx2= session2.beginTransaction();
		
		System.out.println("First get for id 1");
		Student12 student2= session2.get(Student12.class,1L);
		
		tx2.commit();
		session2.close();
		*/
		
		sf.close();
	}

}
