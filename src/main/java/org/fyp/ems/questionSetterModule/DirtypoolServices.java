package org.fyp.ems.questionSetterModule;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.fyp.ems.questionSettingModule.EmsDbModel.DirtyPool;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.EmsDbModel.Questions;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class DirtypoolServices {
	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	
	public DirtypoolServices() {
				
	}
	
	static Transaction tx = null;
	JSONObject d_question = new JSONObject();
	JSONArray dirtyquestions = new JSONArray();
	List <DirtyPool> dirty_questions;

	public String getAllDirtyQuestions()
	{
		List<Options> options;
		Questions qu ;
		try {	
				session = gs.getSession();
			  	tx = session.beginTransaction();
			  	String queryText = "from DirtyPool";
			  	dirty_questions = session.createQuery(queryText).list();
			  	tx.commit();
			  	for (int i = 0 ;i < dirty_questions.size();i++)
			  	{
			  		d_question = new JSONObject();				  		
			  		DirtyPool dp = dirty_questions.get(i);
			  		Query query = session.createQuery("From Questions where id=:q_id");
			  		query.setParameter("q_id", dp.getQuestion_id());
			  		qu = (Questions) query.getSingleResult();
	
			  		Query query1 = session.createQuery("FROM Options op where op.question_id=:id");			  		
			  		query1.setParameter("id", qu.getId());
			  		options= query1.list();
			  		JSONObject opt = new JSONObject();
					JSONArray j_option = new JSONArray();
					
					for (int j = 1 ;j <= options.size();j++)
			  		{
			  			opt = new JSONObject();
			  			Options opt1 = options.get(j-1);
			  			opt.put("option"+j, opt1.getOption_statment());
			  			opt.put("id", opt1.getId());
			  			opt.put("correct_status", opt1.getCorrect_status());
			  			j_option.add(opt);			  			
			  		}
			  		d_question.put("options", j_option);
			  		d_question.put("question_id", qu.getId());
			  		d_question.put("marks", qu.getMarks());	  		
			  		d_question.put("statement", qu.getStatement());
			  		d_question.put("subject", qu.getSubject());
			  		d_question.put("topic", qu.getTopic());
			  		d_question.put("sub_topic", qu.getSub_Topic());
			  		d_question.put("auther", qu.getAuther());
			  		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = dp.getPending_date();
					String today = dateFormat.format(date);
			  		d_question.put("pending_date", today);
			  		d_question.put("difficulty_level", qu.getDifficulty_level());
			  		d_question.put("estimated_time", qu.getEstimated_time());
			  		dirtyquestions.add(d_question);

			  	}
			  	
				}catch(Exception e) {
					if (tx!=null) tx.rollback();
					System.out.println("exception occures1");
			
				} finally {
					session = null; 
				}
				JSONObject result = new JSONObject();
				result.put("questions", dirtyquestions);
		return result.toJSONString();
	}
	
	public String getDirtyQuestion(int id)
	{
//		String object = null;
		try {
				session = gs.getSession();				
			    tx = session.beginTransaction();
			    String queryText = "select dp, qu from DirtyPool dp , Questions qu where qu.id=dp.question_id and dp.id=:id";
			    Query query = session.createQuery(queryText);
			    query.setParameter("id", id);
			  	List <Object[]> rows = query.list();
			  	tx.commit();
			  	
			  	for (Object[] row: rows) 
			  	{
			  		Questions qu = (Questions) row[1];
			  		DirtyPool dp = (DirtyPool) row[0];
			  		d_question.put("question_id", qu.getId());
			  		d_question.put("marks", qu.getMarks());
			  		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = dp.getPending_date();
					String today = dateFormat.format(date);
			  		d_question.put("pending_date", today);
			  		d_question.put("statement", qu.getStatement());
			  		Query query1 = session.createQuery("FROM Options op where op.question_id=:id");
			  		query1.setParameter("id", qu.getId());
			  		
			  		List<Options> options = query1.list();
			  		JSONObject opt = new JSONObject();
					JSONArray j_option = new JSONArray();
					
					for (int j = 1 ;j <= options.size();j++)
			  		{
			  			opt = new JSONObject();
			  			Options opt1 = options.get(j-1);
			  			opt.put("option"+j, opt1.getOption_statment());
			  			opt.put("id", opt1.getId());
			  			opt.put("correct_status", opt1.getCorrect_status());
			  			j_option.add(opt);			  			
			  		}
			  		d_question.put("options", j_option);
			  		d_question.put("subject", qu.getSubject());
			  		d_question.put("topic", qu.getTopic());
			  		d_question.put("sub_topic", qu.getSub_Topic());
			  		d_question.put("auther", qu.getAuther());
			  		d_question.put("difficulty_level", qu.getDifficulty_level());
			  		d_question.put("estimated_time", qu.getEstimated_time());
			  		
//			  		object = d_question.toJSONString();
			  		
			  	}
			  	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
			 
			} finally {
				session = null; 
			}
		return d_question.toJSONString();
	}
	
	public Boolean updateDirtyQuestion(int id,Date p_date)
	{
		try {
				session = gs.getSession();		
				tx = session.beginTransaction();
				DirtyPool dq = (DirtyPool)session.get(DirtyPool.class, id); 
				dq.setPending_date(p_date);
				session.update(dq); 
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
	
	public int deleteDirtyQuestion(int id)
	{
		int count=0;
		try {
				session = gs.getSession();
				tx = session.beginTransaction();
				String hql = "delete FROM DirtyPool dp WHERE dp.question_id = :id";
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
	public int countPendingQuestions ()
	{
		int count = 0;
		try {			
				session = gs.getSession();	
				tx = session.beginTransaction();
				String sql = "select count(dp) from DirtyPool dp";
				Query query  = session.createQuery(sql);
				Long res = (Long) query.uniqueResult();
				count = res.intValue();
				tx.commit();     	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception dirty questions");
				return count;
			
			} finally {
				session = null; 
			}
		return count;
	}
	public int addDirtyQuestion(DirtyPool dirty_pool)
	{
		
		int count= 0;
			
		try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String today = dateFormat.format(date);
				dirty_pool.setPending_date(dateFormat.parse(today));
				session = gs.getSession();
			 	tx = session.beginTransaction();
			 	count = (int)session.save(dirty_pool);
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
