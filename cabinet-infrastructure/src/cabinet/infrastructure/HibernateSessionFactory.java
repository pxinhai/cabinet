package cabinet.infrastructure;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static org.hibernate.SessionFactory sessionFactory;

	private HibernateSessionFactory() {
	}

	public static Session getSession() throws Exception {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}

	public static void rebuildSessionFactory() throws Exception {
		try {
		 Configuration configuration = new Configuration();
			configuration.configure("/hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}
}