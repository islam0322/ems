package org.fyp.ems.questionSetterModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.fyp.ems.questionSettingModule.EmsDbModel.Subjects;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class SubjectServices {

	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <Subjects>subjects;

	public List<Subjects> getAllSubjects()
	{
		try{
			 session = gs.getSession();
			 tx = session.beginTransaction();
			 subjects = session.createQuery("FROM Subjects").list();
	         tx.commit();
			
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			System.out.println("exception occures1");
			
		} finally {
			session = null; 
		}
		return subjects;
	}
	
	public Subjects getSubject(int id)
	{
		Subjects sub = new Subjects();
		
		try{
			 session = gs.getSession();; 
			 tx = session.beginTransaction();
			 String hql = "FROM Subjects s WHERE s.subject_id = :id";
			 Query query = session.createQuery(hql);
			 query.setParameter("id",id);
			 sub = (Subjects)query.getSingleResult();
	         tx.commit();
	         
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 
		} finally {
			session = null; 
		}
		return sub;
	}
	
	public Boolean updateSubejct(int id,String name)
	{
		try{
			 session = gs.getSession();;	
			 tx = session.beginTransaction();
			 Subjects subject = (Subjects)session.get(Subjects.class, id); 
	         subject.setSubject_name(name);
			 session.update(subject); 
	         tx.commit();
	         	
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 return false;
			
		} finally {
			session = null; 
		}
		return true;
	}
	public int deleteSubject(int id)
	{
		int count=0;
		try{
			 session = gs.getSession();; 
			 tx = session.beginTransaction();
			 String hql = "delete FROM Subjects s WHERE s.id = :id";
			 Query query = session.createQuery(hql);
			 query.setParameter("id",id);
			 count = query.executeUpdate();
	         tx.commit();
	         
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 return count;
			 
		} finally {
			session = null; 
		}
		return count;
	}
	
	public int addSubject(Subjects subject)
	{
		int count=0;
		try{
			 session = gs.getSession();; 
			 tx = session.beginTransaction();
			 SessionImpl imp = (SessionImpl) session;
			 Connection conn=imp.connection();
			 String sql = "insert into subjects (subject_name) values(?)";
			 PreparedStatement ps = conn.prepareStatement(sql);		
			 ps.setString(1, subject.getSubject_name());				
			 count = ps.executeUpdate();
			 tx.commit();
	         
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 return count;
			 
		} finally {
			session = null; 
		}
		
		return count;
	}
	
	public Boolean isEmpty(Subjects subject)
	{
		if(subject.getSubject_id() != 0 && subject.getSubject_name() != null)
		{
			return false;
		}
		else 
			return true;
	}
	
	public void logout()
	{
		gs.CloseSession();
	}
}
