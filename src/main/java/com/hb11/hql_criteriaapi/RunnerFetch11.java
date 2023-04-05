package com.hb11.hql_criteriaapi;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RunnerFetch11 {

	public static void main(String[] args) {



		Configuration con = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student11.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		Student11 student = session.get(Student11.class, 1L);
		student.setName("Updated "+student.getName());
		session.update(student);


		int sMathGrade=80;
		int lMathGrade=75;

		String hqlQuery3="UPDATE Student11 s SET s.mathGrade=:a where s.mathGrade<:lMath";

		Query query= session.createQuery(hqlQuery3);

		query.setParameter("a", sMathGrade);
		query.setParameter("lMath", lMathGrade);

		int numOfRec2= query.executeUpdate();
		System.out.println("Effected Row Count:"+ numOfRec2);

	// Criteria
		CriteriaBuilder cb= session.getCriteriaBuilder();
	 	CriteriaQuery<Student11> criteriaQuery= cb.createQuery(Student11.class);
		Root<Student11> root = criteriaQuery.from(Student11.class);
	 	

		// 1.örnek
		criteriaQuery.select(root);
		Query<Student11> query1 = session.createQuery(criteriaQuery);
		List<Student11> resultList2= query1.getResultList();

		resultList2.forEach(s->System.out.println(s));

	 	//2.örnek
		criteriaQuery.select(root).  // SELECT * FROM Student11
				      where(cb.equal(root.get("name"),"Student Name 6"));
		Query<Student11> query2 = session.createQuery(criteriaQuery);
		List<Student11> resultList3= query2.getResultList();

		resultList3.forEach(System.out::println);

		// 3.örnek
		criteriaQuery.select(root).where(cb.greaterThan(root.get("mathGrade"),80));
		Query<Student11> query3 = session.createQuery(criteriaQuery);
		List<Student11> resultList4= query3.getResultList();
		resultList4.forEach(System.out::println);

		// 4.örnek --> MathGrade puanı 95 den küçük olan
		criteriaQuery.select(root).where(cb.lessThan(root.get("mathGrade"),95));
		Query<Student11> query4 = session.createQuery(criteriaQuery);
		List<Student11> resultList5= query4.getResultList();
		resultList5.forEach(System.out::println);

		//  5.örnek : id si 1 veya mathGrade i 75 den büyük olan recordu bulalım

		Long id = 1L ;

		Predicate predicateForId = cb.equal(root.get("id"), id);
		Predicate predicateForMathGrade = cb.greaterThan(root.get("mathGrade"), 75);

		Predicate predicateQuery = cb.or(predicateForId , predicateForMathGrade );

		criteriaQuery.where(predicateQuery);
		Query<Student11> query5 = session.createQuery(criteriaQuery);
		List<Student11> resultList6 = query5.getResultList();
		resultList6.forEach(System.out::println);





		
	 	
		tx.commit();
		session.close();
		sf.close();
	}

}
