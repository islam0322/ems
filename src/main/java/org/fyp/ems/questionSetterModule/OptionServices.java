package org.fyp.ems.questionSetterModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class OptionServices {

	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <Options>options;

	public List<Options> getAllOptions(int id)
	{
		try {
			
			 	session = gs.getSession();
			 	tx = session.beginTransaction();
			 	Query query = session.createQuery("FROM Options op where op.question_id=:id");			 	
			 	query.setParameter("id", id);
			 	System.out.println("id:"+id);
			 	options = query.list();
			 	tx.commit();
			
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures1");
			
			} finally {
				session = null; 
			}
		return options;
	}
	
	public Options getCorrectOption (int id)
	{
		Options op = new Options();
		try {
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
			 	String hql = "FROM Options op WHERE op.question_id=:id and op.correct_status=:st";
			 	Query query = session.createQuery(hql);
			 	query.setParameter("id",id);
			 	query.setParameter("st","correct");
			 	op = (Options)query.getSingleResult();
//			 	System.out.println("-1");
//			 	File file = new File("C:\\temp\\output.jpg");
//			 	System.out.println("0");
//			     FileOutputStream outputStream = new FileOutputStream(file);
//			     System.out.println("1");
//			     InputStream input = op.getImage().getBinaryStream();
//			     System.out.println("2");
//			     BufferedImage bufferedImage = ImageIO.read(input);
//			     System.out.println("3");
//			         ImageIO.write(bufferedImage, "jpg", outputStream);
//			         System.out.println("4");
//			 	//op.setImage(null);
			 	tx.commit();
	         
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
			 
			} finally {
				session = null; 
			}
		return op;
	}
	
	public int addOption(Options op)
	{
		int count=0;
		
		try {
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
//			 	SessionImpl imp = (SessionImpl) session;
//			 	Connection conn=imp.connection();
//			 	String sql = "insert into options (question_id,option_statment,image_status,correct_status) values(?,?,?,?)";
//			 	PreparedStatement ps = conn.prepareStatement(sql);
//			 	ps.setInt(1, op.getQuestion_id());
//			 	ps.setString(2, op.getOption_statment());
//			 	ps.setString(3, op.getImage_status());
//			 	ps.setString(4, op.getCorrect_status());
//			 	count = ps.executeUpdate();
//			 	System.out.println("call here");
			 	count = (int) session.save(op);
			 	tx.commit();
			 	session.flush();
			 	
			 
//			 	File file=new File("C:\\Users\\I.Ali\\Desktop\\RMS.jpg");
//			    BufferedImage bufferedImage=ImageIO.read(file);
//	            ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
//	            ImageIO.write(bufferedImage, "jpg", byteOutStream);
//			    System.out.println("image is : "+byteOutStream);
//			 	op.setImage(BlobProxy.generateProxy(byteOutStream.toByteArray()));
//			 	count = (int) session.save(op);

	         
			}catch(Exception e) {
//				if (tx!=null) tx.rollback();
				System.out.println("exception occures1222");
				return count;
			 
			} finally {
//				session = null; 
			}		
		return count;	
	}
	
	public int deleteOption(int id)
	{
		int count=0;
		
		try {
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
			 	String hql = "delete FROM Options op WHERE op.id = :id";
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
	public Boolean updateOption(Options op)
	{
		try {
				session = gs.getSession();	
			 	tx = session.beginTransaction();
			 	Options option = (Options)session.get(Options.class, op.getId()); 
			 	option.setOption_statment(op.getOption_statment());
			 	option.setCorrect_status(op.getCorrect_status());
			 	session.update(option); 
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
	public Boolean isEmpty(Options op)
	{
		if(op.getId() != 0 && op.getQuestion_id() != 0)
			return false;
		else
		return true;
	}
	
	public void logout()
	{
		gs.CloseSession();
	}
}
