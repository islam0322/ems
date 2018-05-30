package org.fyp.ems.questionSetterModule;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.fyp.ems.questionSettingModule.EmsDbModel.Images;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.EmsDbModel.Questions;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.fyp.ems.questionSettingModule.Security.password_incryptor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class QuestionServices {
	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <Questions>questions;
	JSONObject question = new JSONObject();
	JSONArray j_questions = new JSONArray();
	OptionServices os = new OptionServices();
	List<Options> options;

	public String getAllQuestions()
	{
		
		
		try {
			 	session = gs.getSession();
			 	tx = session.beginTransaction();
			 	questions = session.createQuery("From Questions").list();
				tx.commit();
				for(int j=0;j<questions.size();j++)
				{
					question = new JSONObject();
					
					Questions qu = questions.get(j);
					options = os.getAllOptions(qu.getId());
					if(qu.getStatement_image() != null)
					{
						Query query = session.createQuery("From Images m where m.question_id =:id");
						query.setParameter("id", qu.getId());
						Images image = (Images) query.getSingleResult();
						tx.commit();
						question.put("image", image.getImage());
						question.put("image_id", image.getId());
						
					}
					JSONObject opt = new JSONObject();
					JSONArray j_option = new JSONArray();
					
					
			  		for (int i = 1 ;i <= options.size();i++)
			  		{
			  			opt = new JSONObject();
			  			Options opt1 = options.get(i-1);
			  			opt.put("option"+i, opt1.getOption_statment());
			  			opt.put("id", opt1.getId());
			  			opt.put("correct_status", opt1.getCorrect_status());
			  			
			  			j_option.add(opt);			  			
			  		}

			  		question.put("options", j_option);
					question.put("subject", qu.getSubject());
					question.put("topic", qu.getTopic());
					question.put("sub_topic", qu.getSub_Topic());
					question.put("auther", qu.getAuther());
					question.put("difficulty_level", qu.getDifficulty_level());
					question.put("estimated_time", qu.getEstimated_time());
					question.put("question_id", qu.getId());
					question.put("marks", qu.getMarks());
					question.put("statement", qu.getStatement());
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = qu.getCreation_date();
					String today = dateFormat.format(date);
					question.put("Creation_date", today);
					
					j_questions.add(question);
				}

			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures1");
			
			} finally {
				session = null; 
			}
		JSONObject result = new JSONObject();
		result.put("questions", j_questions);
		return result.toJSONString();
	}
	public String getApproverInfo(String username)
	{
		int count = 0;
		JSONObject result = new JSONObject();
		try {			
				session = gs.getSession();	
				tx = session.beginTransaction();
				String sql = "select count(cp) from CleanPool cp where cp.approver_id=:id";
				Query query  = session.createQuery(sql);
				query.setParameter("id", username);
				Long res = (Long) query.uniqueResult();
				count = res.intValue();
				result.put("approved",count);
				sql = "select count(dp) from DirtyPool dp";
				query  = session.createQuery(sql);
				res = (Long) query.uniqueResult();
				count = res.intValue();
				result.put("pending",count);
				sql = "select count(rq) from RejectedQuestion rq where rq.approver_id=:id";
				query  = session.createQuery(sql);
				query.setParameter("id", username);
				res = (Long) query.uniqueResult();
				count = res.intValue();
				result.put("rejected",count);
				tx.commit();     	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
				
			
			} finally {
				session = null; 
			}
		return result.toJSONString();
	}
	
	public String getQuestion(int id)
	{
		Questions qu = new Questions();
		List <Options> opion_list;
		try {
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
			 	String hql = "FROM Questions q WHERE q.id = :id";
			 	Query query = session.createQuery(hql);
			 	query.setParameter("id",id);
			 	qu = (Questions)query.getSingleResult();
			 	tx.commit();
			 	opion_list=os.getAllOptions(qu.getId());
				JSONObject option = new JSONObject();
				JSONArray js_option = new JSONArray();
				
				for (int i = 1 ;i <= opion_list.size();i++)
		  		{
					option = new JSONObject();
		  			Options opt1 = opion_list.get(i-1);
		  			option.put("option"+i, opt1.getOption_statment());
		  			option.put("id", opt1.getId());
		  			if(opt1.getCorrect_status() != null)
		  			{
		  				option.put("correct_status", opt1.getCorrect_status());
		  			}
		  			js_option.add(option);			  			
		  		}
		  			  		
		  		question.put("options", js_option);
				question.put("subject", qu.getSubject());
				question.put("topic", qu.getTopic());
				question.put("sub_topic", qu.getSub_Topic());
				question.put("auther", qu.getAuther());
				question.put("difficulty_level", qu.getDifficulty_level());
				question.put("estimated_time", qu.getEstimated_time());
				question.put("question_id", qu.getId());
				question.put("marks", qu.getMarks());
				question.put("statement", qu.getStatement());
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = qu.getCreation_date();
				String today = dateFormat.format(date);
				question.put("creation_date", today);
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
			 
			} finally {
				session = null; 
			}
		return question.toJSONString();
	}
	
	public int addQuestions(Questions question) throws IOException
	{
		int count=0;
		String imageDataBytes = "";
		String imagePath = "";
		String statement = question.getStatement();
		
		int index = statement.indexOf("base64,");
		if (index > 0) {
			imageDataBytes = statement.substring(statement.indexOf("base64,")+7);
			password_incryptor pe = new password_incryptor();
			imageDataBytes = imageDataBytes.substring(0,imageDataBytes.indexOf("style")-2);
			String path = pe.generateimagePath();
			imagePath = "C:\\base64\\"+path+".jpg";
			try (FileOutputStream imageOutFile = new FileOutputStream(imagePath)) {
				byte[] imageByteArray = Base64.getDecoder().decode(imageDataBytes);
				imageOutFile.write(imageByteArray);
				question.setStatement_image(imagePath);
				
			} catch (FileNotFoundException e) {
				System.out.println("Image not found" + e);
			}
		
		}
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String today = dateFormat.format(date);
			question.setCreation_date(dateFormat.parse(today));
			session = gs.getSession(); 
		 	tx = session.beginTransaction();
		 	count = (int) session.save(question);
		 	System.out.println("count is :"+count);
		 	tx.commit();
		 	
		}catch(Exception e) {
			System.out.println("Json parsing problem");
			if (tx!=null) tx.rollback();
			System.out.println(count);
			return count;
		
		}
		finally {
			session = null; 
		}
		System.out.println("here"+count);
	
			return count;	
		
	}
//		JSONParser parser = new JSONParser();
//		
//		
//		Questions qu  = new Questions();
//		
//		try {
//				JSONObject json = (JSONObject) parser.parse(questions);
//	
//				qu.setSubject((String) json.get("subject"));
//				qu.setAuther((String) json.get("auther"));
//				qu.setTopic((String) json.get("topic"));
//				qu.setStatement((String) json.get("statement"));
//				qu.setSub_Topic((String) json.get("sub_topic"));
//				qu.setEstimated_time(Integer.parseInt(json.get("estimated_time").toString()));
//				qu.setComment((String) json.get("comment"));
//				qu.setStatus((String) json.get("status"));
//				qu.setDifficulty_level(Integer.parseInt(json.get("difficulty_level").toString()));
//				qu.setMarks(Integer.parseInt( json.get("marks").toString()));
//				String statement = qu.getStatement();
////				
//				int index = statement.indexOf("base64,");
//				if (index > 0) {
//					imageDataBytes = statement.substring(statement.indexOf("base64,")+7);
//					password_incryptor pe = new password_incryptor();
//					imageDataBytes = imageDataBytes.substring(0,imageDataBytes.indexOf("style")-2);
//					String path = pe.generateimagePath();
//					imagePath = "C:\\base64\\"+path+".jpg";
//					try (FileOutputStream imageOutFile = new FileOutputStream(imagePath)) {
//						byte[] imageByteArray = Base64.getDecoder().decode(imageDataBytes);
//						imageOutFile.write(imageByteArray);
//						qu.setStatement_image(imagePath);
//						
//					} catch (FileNotFoundException e) {
//						System.out.println("Image not found" + e);
//					}
//				
//				}
			
				
		
//					 	if (count > 0)
//					 	{
//					 		OptionServices os = new OptionServices();
//					 		int qid = count;
//					 		Options opt = new Options();
//					 		JSONArray array = (JSONArray) json.get("options");
//					 		String options = array.toJSONString();
//					 		System.out.println(options);
//					 		os.addOption(options, count);
//							for(int i=0; i<array.size(); i++) {
//								JSONObject obj = (JSONObject) array.get(i);
//								System.out.println(obj.get("option_statement").toString());
//								System.out.println(obj.get("correct_status").toString());
//								
//								opt.setOption_statment((String ) obj.get("option_statement"));
//								opt.setCorrect_status((String )obj.get("correct_status"));
//								
//								opt.setQuestion_id(qid);
//								//String opt_statement = opt.getOption_statment();
//			
////								index = opt_statement.indexOf("base64,");
////								if (index > 0) {
////									System.out.println("here ");
////									imageDataBytes = opt_statement.substring(opt_statement.indexOf("base64,")+7);
////									password_incryptor pe = new password_incryptor();
////									imageDataBytes = imageDataBytes.substring(0,imageDataBytes.indexOf("style")-2);
////									String path = pe.generateimagePath();
////									imagePath = "C:\\base64\\"+path+".jpg";
////									try (FileOutputStream imageOutFile = new FileOutputStream(imagePath)) {
////										byte[] imageByteArray = Base64.getDecoder().decode(imageDataBytes);
////										imageOutFile.write(imageByteArray);
////										opt.setImage_status(imagePath);
////										
////									} catch (FileNotFoundException e) {
////										System.out.println("Image not found" + e);
////									}
////								
////								}
//								System.out.println(opt.getCorrect_status()+""+opt.getImage_status()+""+opt.getOption_statment()+""+opt.getQuestion_id());
//								count = (int) session.save(opt);
//								tx.commit();
//				        	}
//					 	}
			         
//					}catch(Exception e) {
//						if (tx!=null) tx.rollback();
//						System.out.println("exception occures12");
//						System.out.println(count);
//						return count;
					 
			
//			}catch(Exception e) {
//				System.out.println("Json parsing problem");
//				if (tx!=null) tx.rollback();
//				System.out.println(count);
//				return count;
//			
//			}
//		finally {
//				session = null; 
//			}
//		System.out.println("here"+count);
//		
//		return count;	
	
	
//	public int addQuestions(String questions)
//	{
//		int count=0;
//		JSONParser parser = new JSONParser();
//		String imageDataBytes = "";
//		String imagePath = "";
//		Questions qu  = new Questions();
//		
//		try {
//				JSONObject json = (JSONObject) parser.parse(questions);
//	
//				qu.setSubject((String) json.get("subject"));
//				qu.setAuther((String) json.get("auther"));
//				qu.setTopic((String) json.get("topic"));
//				qu.setStatement((String) json.get("statement"));
//				qu.setSub_Topic((String) json.get("sub_topic"));
//				qu.setEstimated_time(Integer.parseInt(json.get("estimated_time").toString()));
//				qu.setComment((String) json.get("comment"));
//				qu.setStatus((String) json.get("status"));
//				qu.setDifficulty_level(Integer.parseInt(json.get("difficulty_level").toString()));
//				qu.setMarks(Integer.parseInt( json.get("marks").toString()));
//				String statement = qu.getStatement();
////				
////				int index = statement.indexOf("base64,");
////				if (index > 0) {
////					imageDataBytes = statement.substring(statement.indexOf("base64,")+7);
////					password_incryptor pe = new password_incryptor();
////					imageDataBytes = imageDataBytes.substring(0,imageDataBytes.indexOf("style")-2);
////					String path = pe.generateimagePath();
////					imagePath = "C:\\base64\\"+path+".jpg";
////					try (FileOutputStream imageOutFile = new FileOutputStream(imagePath)) {
////						byte[] imageByteArray = Base64.getDecoder().decode(imageDataBytes);
////						imageOutFile.write(imageByteArray);
////						qu.setStatement_image(imagePath);
////						
////					} catch (FileNotFoundException e) {
////						System.out.println("Image not found" + e);
////					}
////				
////				}
//			
//				try {
//						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//						Date date = new Date();
//						String today = dateFormat.format(date);
//						qu.setCreation_date(dateFormat.parse(today));
//						session = gs.getSession(); 
//					 	tx = session.beginTransaction();
//					 	count = (int) session.save(qu);
//					 	System.out.println("count is :"+count);
//					 	tx.commit();
//		
////					 	if (count > 0)
////					 	{
////					 		OptionServices os = new OptionServices();
////					 		int qid = count;
////					 		Options opt = new Options();
////					 		JSONArray array = (JSONArray) json.get("options");
////					 		String options = array.toJSONString();
////					 		System.out.println(options);
////					 		os.addOption(options, count);
////							for(int i=0; i<array.size(); i++) {
////								JSONObject obj = (JSONObject) array.get(i);
////								System.out.println(obj.get("option_statement").toString());
////								System.out.println(obj.get("correct_status").toString());
////								
////								opt.setOption_statment((String ) obj.get("option_statement"));
////								opt.setCorrect_status((String )obj.get("correct_status"));
////								
////								opt.setQuestion_id(qid);
////								//String opt_statement = opt.getOption_statment();
////			
//////								index = opt_statement.indexOf("base64,");
//////								if (index > 0) {
//////									System.out.println("here ");
//////									imageDataBytes = opt_statement.substring(opt_statement.indexOf("base64,")+7);
//////									password_incryptor pe = new password_incryptor();
//////									imageDataBytes = imageDataBytes.substring(0,imageDataBytes.indexOf("style")-2);
//////									String path = pe.generateimagePath();
//////									imagePath = "C:\\base64\\"+path+".jpg";
//////									try (FileOutputStream imageOutFile = new FileOutputStream(imagePath)) {
//////										byte[] imageByteArray = Base64.getDecoder().decode(imageDataBytes);
//////										imageOutFile.write(imageByteArray);
//////										opt.setImage_status(imagePath);
//////										
//////									} catch (FileNotFoundException e) {
//////										System.out.println("Image not found" + e);
//////									}
//////								
//////								}
////								System.out.println(opt.getCorrect_status()+""+opt.getImage_status()+""+opt.getOption_statment()+""+opt.getQuestion_id());
////								count = (int) session.save(opt);
////								tx.commit();
////				        	}
////					 	}
//			         
//					}catch(Exception e) {
//						if (tx!=null) tx.rollback();
//						System.out.println("exception occures12");
//						System.out.println(count);
//						return count;
//					 
//					} 
//			}catch(Exception e) {
//				System.out.println("Json parsing problem");
//				if (tx!=null) tx.rollback();
//				System.out.println(count);
//				return count;
//			
//			}
//		finally {
//				session = null; 
//			}
//		System.out.println("here"+count);
//		
//		return count;	
//	}
	
	public Boolean updateQuestion(Questions qu)
	{
		try {
				session = gs.getSession();	
			 	tx = session.beginTransaction();
			 	Questions question = (Questions)session.get(Questions.class, qu.getId()); 
			 	question.setStatement(qu.getStatement());
			 	question.setComment(qu.getComment());
			 	question.setSubject(qu.getSubject());
			 	question.setTopic(qu.getSub_Topic());
			 	question.setSub_Topic(qu.getSub_Topic());
			 	question.setEstimated_time(qu.getEstimated_time());
			 	question.setDifficulty_level(qu.getDifficulty_level());
			 	question.setMarks(qu.getMarks());
			 	//question.setEditor_id(qu.getEditor_id());
			 	session.update(question); 
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
	public Boolean disableQustion (int id)
	{
		try {
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
			 	Questions qu = (Questions)session.get(Questions.class, id); 
			 	qu.setStatus("disable");
			 	session.update(qu); 
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
	public Boolean enableQustion (int id)
	{
		try {
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
			 	Questions qu = (Questions)session.get(Questions.class, id); 
			 	qu.setStatus("enable");
			 	session.update(qu); 
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
	
	public int countQuestions ()
	{
		int count = 0;
		try {			
				session = gs.getSession();	
				tx = session.beginTransaction();
				String sql = "select count(q) from Questions q";
				Query query  = session.createQuery(sql);
				Long res = (Long) query.uniqueResult();
				count = res.intValue();
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
