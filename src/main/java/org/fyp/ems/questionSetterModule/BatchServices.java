package org.fyp.ems.questionSetterModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.fyp.ems.questionSettingModule.EmsDbModel.Batches;
import org.fyp.ems.questionSettingModule.EmsDbModel.Student;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
@SuppressWarnings({ "unchecked", "deprecation" })

public class BatchServices {
	
	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <Batches> batches;
	JSONObject batch ;
	JSONArray batch_array = new JSONArray();
	
	public String getAllBatches()
	{
		try {		
			session = gs.getSession();
			tx = session.beginTransaction();
			batches = session.createQuery("FROM Batches").list();
			
		    tx.commit();
		    for(int i=0;i < batches.size();i++)
		    {
		    	batch = new JSONObject();
		    	Batches b1 = batches.get(i); 
		    	batch.put("id", b1.getId());
		    	batch.put("name",b1.getName());
		    	
				batch_array.add(batch);
		    }
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			System.out.println("exception occures1");
				
		} finally {
			session = null; 
		}
		JSONObject result = new JSONObject();
		result.put("batches", batch_array);
		
		return result.toJSONString();
	}
	public int addBatch(String batch)
	{
		int count1=0;
		int count = 0;
		String batch_name;
		
		JSONParser parser = new JSONParser();
		try {
			session = gs.getSession(); 
			tx = session.beginTransaction();
			Batches bat = new Batches();
			JSONObject json = (JSONObject) parser.parse(batch);
			batch_name = json.get("batch_name").toString();
			bat.setName(batch_name);
			count1 = (int) session.save(bat);
	        tx.commit();

	        if (count1 > 0)
	        {
	        	String student_id = null;
	        	String pass = null;
	        	Student st ;
	        	JSONArray array = (JSONArray) json.get("students");
	       
	        	for(int i=0; i<array.size(); i++) {
	        		
	        		st = new Student();
	        		JSONObject obj = (JSONObject) array.get(i);
					student_id = obj.get("student_id").toString();
					pass = obj.get("password").toString();
					tx = session.beginTransaction();
					SessionImpl imp = (SessionImpl) session;
					Connection conn=imp.connection();
					String sql = "insert into students (student_id,password) values(?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);		
					ps.setString(1,student_id);	
					ps.setString(2,pass);
					count = ps.executeUpdate();
					tx.commit();
					
	        	}
	        	for(int i=0; i<array.size(); i++) {
	        		
	        		st = new Student();
	        		JSONObject obj = (JSONObject) array.get(i);
					student_id = obj.get("student_id").toString();
					tx = session.beginTransaction();
					SessionImpl imp = (SessionImpl) session;
					Connection conn=imp.connection();
					String sql = "insert into students_batches (batch_id,student_id) values(?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);		
					ps.setInt(1,count1);	
					ps.setString(2,student_id);
					count = ps.executeUpdate();
					tx.commit();
					
	        	}
	        }
	         
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 return count;
			 
		} finally {
			session = null; 
		}
		
		return count;
	}

}
