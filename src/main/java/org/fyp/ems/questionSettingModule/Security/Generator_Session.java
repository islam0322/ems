package org.fyp.ems.questionSettingModule.Security;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Generator_Session {

	static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private static Session session = null;
	
	public Generator_Session()
	{
		
	}	
	public  Session getSession ()
	{
		if (session != null) {
			System.out.print("session is not closed");
			return session;
		}
		else {
			System.out.print("session is opened");
			session = sessionFactory.openSession();
			return session;
		}
	}
	public void CloseSession ()
	{
		
		if (session != null)
		{
			session.close();
			session = null;
		}
	}

}
