package org.fyp.ems.questionSetterModule;

import java.util.List;
import org.fyp.ems.questionSettingModule.EmsDbModel.Images;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class ImageServices {

	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <Images>images;
	Images image;

	public List<Images> getStatementImages(int id)
	{
		try{
			 session = gs.getSession();
			 tx = session.beginTransaction();			 
			 Query query  = session.createQuery("FROM Images m where m.question_id=:id");
			 query.setParameter("id", id);
			 images = query.list();
	         tx.commit();
			
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			System.out.println("exception occures1");
			
		} finally {
			session = null; 
		}
		return images;
	}
	public Images getOptionsImages(int id,int opt_id)
	{
		 
		try{
			 session = gs.getSession();
			 tx = session.beginTransaction();			 
			 Query query  = session.createQuery("FROM Images m where m.option_id =:id1 and m.question_id=:id");
			 query.setParameter("id1", opt_id);
			 query.setParameter("id", id);
			 image = (Images) query.getSingleResult();
	         tx.commit();
			
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			System.out.println("exception occures1");
			
		} finally {
			session = null; 
		}
		return image;
	}
	
	public Boolean updateImage(Images image)
	{
		try{
			 session = gs.getSession();;	
			 tx = session.beginTransaction();
			 Images img = (Images)session.get(Images.class, image.getId()); 
	         img.setImage(image.getImage());
			 session.update(img); 
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
	
	public int AddImage(Images image)
	{
		int count=0;
		try{
			 session = gs.getSession();; 
			 tx = session.beginTransaction();
			 count =  (int) session.save(image);
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
	public int deleteStatementImage(int id)
	{
		int count=0;
		try{
			 session = gs.getSession();; 
			 tx = session.beginTransaction();
			 String hql = "delete FROM Images img WHERE img.question_id = :id";
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
	public int deleteOptionImage(int id,int opt)
	{
		int count=0;
		try{
			 session = gs.getSession();; 
			 tx = session.beginTransaction();
			 String hql = "delete FROM Images img WHERE img.question_id = :id and img.option_id = :oid";
			 Query query = session.createQuery(hql);
			 query.setParameter("id",id);
			 query.setParameter("oid",opt);
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
	
	public void logout()
	{
		gs.CloseSession();
	}
}
