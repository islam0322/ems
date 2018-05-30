package org.fyp.ems.questionSetterModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List; 
import org.fyp.ems.questionSettingModule.EmsDbModel.SavedQuestions;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.EmsDbModel.Questions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class SavedQuestionServices {

	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <SavedQuestions>savedQuestions;
	JSONObject s_question = new JSONObject();
	JSONArray s_questions = new JSONArray();
	public String getAllsavedquestions()
	{
		List<Options> options;
		try {
			
				session = gs.getSession();
				tx = session.beginTransaction();
				String queryText = "select sq, qu from SavedQuestions sq , Questions qu where qu.id=sq.question_id";
				List<Object[]> rows = session.createQuery(queryText).list();
				tx.commit();

				for (Object[] row: rows) 
				{
					s_question = new JSONObject();
					Questions qu = (Questions) row[1];
					SavedQuestions sq = (SavedQuestions) row[0];
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = sq.getSaved_date();
					String today = dateFormat.format(date);
					s_question.put("saved_date", today);
					s_question.put("question_id", qu.getId());
					s_question.put("marks", qu.getMarks());
					s_question.put("statement", qu.getStatement());
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
			  		s_question.put("options", j_option);
					s_question.put("subject", qu.getSubject());
					s_question.put("topic", qu.getTopic());
					s_question.put("sub_topic", qu.getSub_Topic());
					s_question.put("auther", qu.getAuther());
					s_question.put("difficulty_level", qu.getDifficulty_level());
					s_question.put("estimated_time", qu.getEstimated_time());
	  		
					s_questions.add(s_question);	
					}

		
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures1");
			
			} finally {
				session = null; 
			}
			JSONObject result = new JSONObject();
			result.put("questions", s_questions);
			
		return result.toJSONString();
	}
	
	public String getSavedQuestion(int id)
	{
		List<Options> options;
		try {
				session = gs.getSession(); 
				tx = session.beginTransaction();
				String queryText = "select sq, qu from SavedQuestions sq , Questions qu where qu.id=sq.question_id and sq.id=:id";				
				Query query = session.createQuery(queryText);
				query.setParameter("id", id);
				List<Object[]> rows = query.list();
				tx.commit();
		
				for (Object[] row: rows) 
				{
					Questions qu = (Questions) row[1];
					SavedQuestions sq = (SavedQuestions) row[0];
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = sq.getSaved_date();
					String today = dateFormat.format(date);
					s_question.put("saved_date", today);
					s_question.put("question_id", qu.getId());
					s_question.put("marks", qu.getMarks());
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
			  		s_question.put("options",j_option);
					s_question.put("subject", qu.getSubject());
					s_question.put("topic", qu.getTopic());
					s_question.put("sub_topic", qu.getSub_Topic());
					s_question.put("auther", qu.getAuther());
					s_question.put("difficulty_level", qu.getDifficulty_level());
					s_question.put("estimated_time", qu.getEstimated_time());
  		
				}
	         
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
			 
			} finally {
				session = null; 
			}
	
		return s_question.toJSONString();
	}
	
	public int deleteSavedQuestion(int id)
	{
		int count=0;
		try {
			session = gs.getSession(); 
			tx = session.beginTransaction();
			 String hql = "delete FROM SavedQuestions sq WHERE sq.id = :id";
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
	
	public int addSavedQuestion(String  qu)
	{
		int count= 0;
		int index = 0;
		SavedQuestions sq = new SavedQuestions();
		
		try {
				QuestionServices qs = new QuestionServices();
				//index  =  qs.addQuestions(qu);

//				if (index > 0)
//				{
//					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//					Date date = new Date();
//					String today = dateFormat.format(date);
//					sq.setSaved_date(dateFormat.parse(today));
//					sq.setQuestion_id(index);
//					System.out.println("id is :"+sq.getQuestion_id());
//					System.out.println("date  is :"+sq.getSaved_date());
//				 	session = gs.getSession(); 
//				 	tx = session.beginTransaction();
//				 	count = (int) session.save(sq);
//				 	tx.commit();
//				}
	         
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
				return count;
			 
			} finally {
				session  = null; 
			}		
		return count;	
	}
	
	public void logout()
	{
		gs.CloseSession();
	}


}
