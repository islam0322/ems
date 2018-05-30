package org.fyp.ems.questionSetterModule;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.fyp.ems.questionSettingModule.EmsDbModel.CleanPool;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.EmsDbModel.Questions;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class CleanPoolServices {

	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	JSONObject c_question = new JSONObject();
	JSONArray cleanquestions = new JSONArray();

	public String getAllCleanQuestions()
	{
		List<Options> options;
		try {			
				session = gs.getSession();
				tx = session.beginTransaction();
				String queryText = "select cp, qu from CleanPool cp , Questions qu where qu.id=cp.question_id";
				List<Object[]> rows = session.createQuery(queryText).list();
				tx.commit();
				
				for (Object[] row: rows) 
				{
					c_question = new JSONObject();
					Questions qu = (Questions) row[1];
					CleanPool cp = (CleanPool) row[0];
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = cp.getApproval_date();
					String today = dateFormat.format(date);
					c_question.put("approval_date", today);
					c_question.put("Approver_id", cp.getApprover_id());
					c_question.put("statement",qu.getStatement());
					c_question.put("approver_comment", cp.getApprover_comment());
					c_question.put("question_id", qu.getId());
					c_question.put("marks", qu.getMarks());
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
			  		c_question.put("options", j_option);
					c_question.put("subject", qu.getSubject());
					c_question.put("topic", qu.getTopic());
					c_question.put("sub_topic", qu.getSub_Topic());
					c_question.put("auther", qu.getAuther());
					c_question.put("difficulty_level", qu.getDifficulty_level());
					c_question.put("estimated_time", qu.getEstimated_time());
		  		
					cleanquestions.add(c_question);
				}
		
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures1");
			
			} finally {
				session = null; 
			}
		JSONObject result = new JSONObject();
		result.put("questions", cleanquestions);
		
		return result.toJSONString();
	}
	
	public String getCleanQuestion(int id)
	{
		List<Options> options;
		try {
				session = gs.getSession(); 
				tx = session.beginTransaction();
				String queryText = "select cp, qu from CleanPool cp , Questions qu where qu.id=cp.question_id and cp.id=:id";
				Query query = session.createQuery(queryText);
				query.setParameter("id", id);
				List<Object[]> rows = query.list();
				tx.commit();
				for (Object[] row: rows) 
				{
					Questions qu = (Questions) row[1];
					CleanPool cp = (CleanPool) row[0];
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = cp.getApproval_date();
					String today = dateFormat.format(date);
					c_question.put("approval_date", today);
					c_question.put("Approver_id", cp.getApprover_id());
					c_question.put("approver_comment", cp.getApprover_comment());
					c_question.put("question_id", qu.getId());
					c_question.put("marks", qu.getMarks());
					c_question.put("statement", qu.getStatement());
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
			  		c_question.put("options", j_option);
					c_question.put("subject", qu.getSubject());
					c_question.put("topic", qu.getTopic());
					c_question.put("sub_topic", qu.getSub_Topic());
					c_question.put("auther", qu.getAuther());
					c_question.put("difficulty_level", qu.getDifficulty_level());
					c_question.put("estimated_time", qu.getEstimated_time());
	  	
				}
	         
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
			 
			} finally {
				session = null; 
			}
	
		return c_question.toJSONString();
	}
	
	public Boolean updateCleanQuestion(int id,String comment)
	{
		try {
			
				session = gs.getSession();	
				tx = session.beginTransaction();
				CleanPool cq = (CleanPool)session.get(CleanPool.class, id);
				cq.setApprover_comment(comment);;
				session.update(cq); 
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
	public int deleteCleanQuestion(int id)
	{
		int count=0;
		try {
				session = gs.getSession(); 
				tx = session.beginTransaction();
				String hql = "delete FROM CleanPool cp WHERE cp.question_id = :id";
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
	public int addCleanQuestion(CleanPool clean_pool)
	{	
		int count= 0;
		int count1 = 0;
			
		try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String today = dateFormat.format(date);
				clean_pool.setApproval_date(dateFormat.parse(today));
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
			 	String hql = "delete FROM DirtyPool dp WHERE dp.question_id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id",clean_pool.getQuestion_id());
				count1 = query.executeUpdate();
				if (count1 > 0)
				{
					count = (int) session.save(clean_pool);
				 	tx.commit();
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
	public int countApprovedQuestions(String username)
	{
		int count = 0;
		try {			
				session = gs.getSession();	
				tx = session.beginTransaction();
				String sql = "select count(cp) from CleanPool cp where approver_id=:id";
				Query query  = session.createQuery(sql);
				query.setParameter("id", username);
				Long res = (Long) query.uniqueResult();
				count = res.intValue();
				tx.commit();     	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception clean pool");
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
