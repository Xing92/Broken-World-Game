package xing.brokenworldserver.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	private static Transaction transaction = null;
	
	private static String hibernatecfgPath = "hibernate.cfg.xml";

	public static SessionFactory getFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure(hibernatecfgPath).buildSessionFactory();
		}
		return sessionFactory;
	}

	public static Session getSession() {
		if (session == null || !session.isOpen()) {
			session = getFactory().openSession();
		}
		return session;
	}

	public static Transaction getTransaction() {
		if (transaction == null || !transaction.isActive()) {
			transaction = getSession().beginTransaction();
		} else {
			transaction = getSession().getTransaction();
		}
		return transaction;
	}
	
	public void setTestPath(){
		hibernatecfgPath = "hibernatetest.cfg.xml";
	}
}
