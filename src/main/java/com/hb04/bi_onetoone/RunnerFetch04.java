package com.hb04.bi_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class RunnerFetch04 {

    public static void main(String[] args) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student04.class).addAnnotatedClass(Diary04.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        // belli id li Studentı getirelim
        Student04 student = session.get(Student04.class, 1001);
        //bu method student04 tablosuna gider 1001 idli studentı bulur o bir record. hiberante'e helir hibernate
        //bize bunu Student04 class data türüne yani student04 objesine çevirir.

        //!!! diary get edelim
        Diary04 diary = session.get(Diary04.class, 101);
        System.out.println(student); //toStringini getirir.
        System.out.println("************************");
        System.out.println(diary);//toStringini getirir.

        System.out.println("*****************");
        System.out.println(diary.getStudent());//diary üzerinden studenta ulaştım
        System.out.println("***************************");
        System.out.println(student.getDiary());//student üzerinden diarye ulaştım
        //çift taraflı ilişkide bunu sağlıyor.

        // !!! Kesişim kümesini getirelim ( Inner Join )*************************

        String hqlQuery ="SELECT s.name,d.name FROM Student04 s INNER JOIN FETCH Diary04 d on s.id=d.student";

        // üstteki HQL kodun SQL hali :
        // select s.std_name,d.name from student04 s inner join diary04 d on s.id=d.std_id ;

        List<Object[]>  resultList = session.createQuery(hqlQuery).getResultList();


        for (Object[] object: resultList) {
            System.out.println(Arrays.toString(object));
        }

        // Lambda expression resultList collectiondı biz collectionlarda lambda kullanabilirz
//       resultList.forEach(oa->{
//           System.out.println(Arrays.toString(oa));
//      });

        // !!! HQL LEFT JOIN *************************************
        // Kısaca bütün öğrenciler ve kitabı olan öğrencileri istiyorum

        String hqlQuery2 =
                "SELECT s.name,d.name FROM Student04 s LEFT JOIN FETCH Diary04 d on s.id= d.student";

        List<Object[]> resultList2 = session.createQuery(hqlQuery2).getResultList();
        resultList2.forEach(oa->{
            System.out.println(Arrays.toString(oa));
        });

        // !!! HQL RIGHT JOIN******************************************

        String hqlQuery3 =
                "SELECT s.name,d.name FROM Student04 s RIGHT JOIN FETCH Diary04 d on s.id=d.student";
        List<Object[]> resultList3 = session.createQuery(hqlQuery3).getResultList();
        resultList3.forEach(oa->{
            System.out.println(Arrays.toString(oa));
        });

        // !!! HQL FULL JOIN**********************************************

        String hqlQuery4 =
                "SELECT s.name,d.name FROM Student04 s FULL JOIN FETCH Diary04 d on s.id=d.student.id";
        List<Object[]> resultList4 = session.createQuery(hqlQuery4).getResultList();
        resultList4.forEach(oa->{
            System.out.println(Arrays.toString(oa));
        });



        tx.commit();
        session.close();
        sf.close();
    }
}
