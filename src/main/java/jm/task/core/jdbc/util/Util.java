package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class Util {

    private static final SessionFactory sessionFactory;  // статичное поле, которое хранит фабрику сессий. фабрика сессий необходима для порождения сессий
    // сессии необходимы для общения с базами данных

    static // блок, в котором инициализируются статичные элементы
    {
        try
        {
             sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        catch(Throwable th){
            System.err.println("Enitial SessionFactory creation failed"+th);
            throw new ExceptionInInitializerError(th);
        }
    }
    public static Session createSession(){
        return sessionFactory.openSession();
    }

}
