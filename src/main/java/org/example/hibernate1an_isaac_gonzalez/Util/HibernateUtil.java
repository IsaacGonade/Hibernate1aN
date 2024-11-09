package org.example.hibernate1an_isaac_gonzalez.Util;

import org.example.hibernate1an_isaac_gonzalez.Model.Coche;
import org.example.hibernate1an_isaac_gonzalez.Model.Multa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static SessionFactory factory;
    static {
        Configuration cfg = new Configuration();
        //obtengo los datos de la conexion desde el fichero de configuracion
        cfg.configure("configuration/hibernate.cfg.xml");

        cfg.addAnnotatedClass(Coche.class);
        cfg.addAnnotatedClass(Multa.class);

        factory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }
}
