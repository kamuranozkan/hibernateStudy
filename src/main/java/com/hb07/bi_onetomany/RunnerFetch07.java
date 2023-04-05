package com.hb07.bi_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch07 {

    public static void main(String[] args) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student07.class).addAnnotatedClass(Book07.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();



        // !!! SQL ile öğrenci bilgilerini alalım ( öğrenci ismi ve kitap ismi )
        //kitabı olan öğrenciler kesişim inner join
        String sqlQuery =
                "SELECT s.student_name,b.name FROM student07 s INNER JOIN book07 b ON s.id=b.student_id";

        List<Object[]> resulList1 = session.createSQLQuery(sqlQuery).getResultList();
        for (Object[] object: resulList1) {
            System.out.println(Arrays.toString(object));
        }
        // !!! yukardaki sorguyu HQL ile yazalım
        String hqlQuery =
                "SELECT s.name,b.name FROM Student07 s INNER JOIN FETCH Book07 b ON s.id=b.student.id";
        List<Object[]> resultList2 = session.createQuery(hqlQuery).getResultList();
        resultList2.forEach(oa->System.out.println(Arrays.toString(oa)));




        // Kitap ismi "A Book" olan kitabı HQL ile siliniz
        String hqlQuery3 ="DELETE FROM Book07 b WHERE b.name='A Book'";
        int numOfDeletedRecords3 = session.createQuery(hqlQuery3).executeUpdate();
        System.out.println("Silinen kayit sayisi = "+numOfDeletedRecords3);
//



        // !!! book ismi içinde "Java" geçen student kayıtlarını alalım ( HQL )
        String hqlQuery4 =
                "SELECT s FROM Student07 s JOIN s.bookList b WHERE b.name LIKE '%Java%'";
        List<Student07> resulList4 = session.createQuery(hqlQuery4, Student07.class).getResultList();
        for (Student07 student07: resulList4) {
            System.out.println(student07);
        }

        tx.commit();
        session.close();
        sf.close();
    }
}
