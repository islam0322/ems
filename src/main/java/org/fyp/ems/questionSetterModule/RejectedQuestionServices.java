package org.fyp.ems.questionSetterModule;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.fyp.ems.questionSettingModule.EmsDbModel.RejectedQuestion;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.EmsDbModel.Questions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class RejectedQuestionServices {
	
	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	JSONObject r_question = new JSONObject();
	JSONArray rejectedquestions = new JSONArray();

	public String getAllRejectedQuestions()
	{
		List<Options> options;
		try {
			
				session = gs.getSession();
				tx = session.beginTransaction();
				String queryText = "select rq, qu from RejectedQuestion rq , Questions qu where qu.id=rq.question_id";
				List<Object[]> rows = session.createQuery(queryText).list();
				tx.commit();
			
				for (Object[] row: rows) 
				{
					r_question  = new JSONObject();
					Questions qu = (Questions) row[1];
					RejectedQuestion rq = (RejectedQuestion) row[0];
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = rq.getRejected_date();
					String today = dateFormat.format(date);
					r_question.put("rejected_date", today);
					r_question.put("Approver_id", rq.getApprover_id());
					r_question.put("approver_comment", rq.getApprover_comment());
					r_question.put("question_id", qu.getId());
					r_question.put("marks", qu.getMarks());
					r_question.put("statement", qu.getStatement());
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
			  		r_question.put("options", j_option);
					r_question.put("subject", qu.getSubject());
					r_question.put("topic", qu.getTopic());
					r_question.put("sub_topic", qu.getSub_Topic());
					r_question.put("auther", qu.getAuther());
					r_question.put("difficulty_level", qu.getDifficulty_level());
					r_question.put("estimated_time", qu.getEstimated_time());
	  		
					rejectedquestions.add(r_question);
				}
		
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures1");
			
			} finally {
				session = null; 
			}
		JSONObject result = new JSONObject();
		result.put("questions", rejectedquestions);
		
		return result.toJSONString();
	}
	
	public String getRejectedQuestion(int id)
	{
		List<Options> options;
		try {
				session = gs.getSession(); 
				tx = session.beginTransaction();
				String queryText = "select rq, qu from RejectedQuestion rq , Questions qu where qu.id=rq.question_id and rq.id=:id";
				Query query = session.createQuery(queryText);
				query.setParameter("id", id);
				List<Object[]> rows = query.list();
				tx.commit();
				for (Object[] row: rows) 
				{
					Questions qu = (Questions) row[1];
					RejectedQuestion rq = (RejectedQuestion) row[0];
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = rq.getRejected_date();
					String today = dateFormat.format(date);
					r_question.put("rejected_date", today);
					r_question.put("Approver_id", rq.getApprover_id());
					r_question.put("approver_comment", rq.getApprover_comment());
					r_question.put("question_id", qu.getId());
					r_question.put("marks", qu.getMarks());
					r_question.put("statement", qu.getStatement());
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
			  		r_question.put("options", j_option);
					r_question.put("subject", qu.getSubject());
					r_question.put("topic", qu.getTopic());
					r_question.put("sub_topic", qu.getSub_Topic());
					r_question.put("auther", qu.getAuther());
					r_question.put("difficulty_level", qu.getDifficulty_level());
					r_question.put("estimated_time", qu.getEstimated_time());
		  	
		        }
	         
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
			 
			} finally {
				session = null; 
			}
	
		return r_question.toJSONString();
	}
	
	public Boolean updateRejectedQuestion(int id,String comment)
	{
		try {
			
				session = gs.getSession();	
				tx = session.beginTransaction();
				RejectedQuestion rq = (RejectedQuestion)session.get(RejectedQuestion.class, id);
				rq.setApprover_comment(comment);;
				session.update(rq); 
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
	public Boolean makeQuestionSeen(int id)
	{
		try {
			
				session = gs.getSession();
				tx = session.beginTransaction();
				RejectedQuestion rq = (RejectedQuestion)session.get(RejectedQuestion.class, id);
				rq.setStatus("seen");
				session.update(rq); 
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
	public int deleteRejectedQuestion(int id)
	{
		int count=0;
		try {
				session = gs.getSession(); 
				tx = session.beginTransaction();
				String hql = "delete FROM RejectedQuestion rq WHERE rq.question_id = :id";
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
	
//	public String getUnseenQuestion()
//	{
//		String count=null;
//		try {
//				session = gs.getSession();
//				tx = session.beginTransaction();
//				String hql = "select count(*) FROM RejectedQuestion rq WHERE rq.status=:st";
//				Query query = session.createQuery(hql);
//				query.setParameter("st","unseen");
//				Object c =  query.uniqueResult();
//				count = c.toString();
//				tx.commit();
//
//			}catch(Exception e) {
//				if (tx!=null) tx.rollback();
//				System.out.println("exception occures2");
//				return count;
//			 
//			} finally {
//				session = null; 
//			}
//		return count;
//		
//	}
	public int countRejectedQuestions(String username)
	{
		int count = 0;
		try {			
				session = gs.getSession();	
				tx = session.beginTransaction();
				String sql = "select count(rq) from RejectedQuestion rq where approver_id=:id";
				Query query  = session.createQuery(sql);
				query.setParameter("id", username);
				Long res = (Long) query.uniqueResult();
				count = res.intValue();
				tx.commit();     	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception rejeted question");
				return count;
			
			} finally {
				session = null; 
			}
		return count;
	}
	public int addRejectedQuestion(RejectedQuestion rq)
	{
		int count= 0;
		int count1 = 0;
		try {
				session = gs.getSession(); 
			 	tx = session.beginTransaction();
			 	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 	Date date = new Date();
			 	String today = dateFormat.format(date);
			 	
			 	rq.setRejected_date(dateFormat.parse(today));
			 	System.out.println(dateFormat.format(date));
			 	String hql = "delete FROM DirtyPool dp WHERE dp.question_id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id",rq.getQuestion_id());
				count1 = query.executeUpdate();
				if (count1 > 0)
				{
					count = (int)session.save(rq);
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
	public void logout()
	{
		gs.CloseSession();
	}

}
