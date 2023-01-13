package com.hb01.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RunnerFetch01 {
    public static void main(String[] args) {

        Configuration con=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);
        SessionFactory sf=con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

        //1.yol
//        Student01 student01=session.get(Student01.class,1001);
//        Student01 student02=session.get(Student01.class,1002);
//        Student01 student03=session.get(Student01.class,1003);
//        System.out.println(student01);
//        System.out.println(student02);
//        System.out.println(student03);

//        //2.yol sql
//        String sqlQuery="SELECT * FROM t_student01";
//       List<Objects[]>resultList= session.createSQLQuery(sqlQuery).getResultList();
//       for(Object[]r:resultList){
//           System.out.println(Arrays.toString(r));
//
//       }
//       //3.yol hql
//        String hqlQuery="FROM Student01";
//       List<Student01>resultList2=session.createQuery(hqlQuery,Student01.class).getResultList();
//       for(Student01 student01:resultList2){
//           System.out.println(student01);
//       }
       //UniqueResult() sql
//        String sqlQuery2="SELECT*FROM t_student01 WHERE student_name='Tarık'";
//      Object[]uniqueResult1= (Object[]) session.createSQLQuery(sqlQuery2).uniqueResult();
//        System.out.println(Arrays.toString(uniqueResult1));
//        System.out.println(uniqueResult1[0]);

//        //uniqueResuly() hql
//        String hqlQuery2="FROM Student01 WHERE name='Tarık'";
//        Student01 uniqueResult2=session.createQuery(hqlQuery2,Student01.class).uniqueResult();
//        System.out.println(uniqueResult2);
//
        //Alias ile
        String hqlQuery3="FROM Student01 std WHERE std.name='Miraç'";
        Student01 uniqueResult3=session.createQuery(hqlQuery3,Student01.class).uniqueResult();
        System.out.println(uniqueResult3);

        //grade değeri 90 olan öğrencileri getir
        String hqlQuery4="SELECT s.id,s.name FROM Student01 s WHERE s.grade=90";//Student01'e s diyoruz
        List<Object[]> resulList4 = session.createQuery(hqlQuery4).getResultList();
        for (Object[] object : resulList4) {
            System.out.println(Arrays.toString(object));
        }


        tx.commit();
            session.close();
            sf.close();
        }

    }

