package org.fyp.ems.questionSetterModule;

import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.EmsDbModel.Questions;
import org.fyp.ems.questionSettingModule.EmsDbModel.TestInfo;
import org.fyp.ems.questionSettingModule.EmsDbModel.TestResult;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

// this class will handle the student request
public class TestService {

	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	JSONObject test_info = new JSONObject();
	JSONArray questions = new JSONArray();
	String studentId;
	int batchId;
	List<TestInfo> test;
	List questionIds;
//	List<Questions> questions;
	String Error;
	List<Options> options;
	OptionServices os = new OptionServices();

	public TestService() {

	}

	// This method will take user credentials and find the test questions against
	// his batch and return it in json object
	public Response StudentLogin(String userid, String password) {
		// to store the table route info
		studentId = userid;
		try {
			session = gs.getSession();
			tx = session.beginTransaction();
			String hql = "FROM Student s WHERE s.student_id = :student_id and s.password = :password";
			Query query = session.createQuery(hql);
			query.setParameter("student_id", userid);
			query.setParameter("password", password);
			List results = query.list();
			tx.commit();
			if (!results.isEmpty()) {
				batchId = getBatchId(studentId);
				if (batchId != 0) {
					// now get the test id and info against that batch and have a status live for
					// test
					
					if(getTest(batchId)) {
						return Response.ok(test_info.toJSONString()).build();
					}else {
						return Response.status(Status.NO_CONTENT).build();
					}
				}
			}else {
			
			}
		}catch(Exception e) {
			session = null;
		}
		return Response.status(Status.NO_CONTENT).build();
}
	public String StudentTest(String studentId, int testId) {
		
		session = gs.getSession();
		questionIds = getQuestionIds(testId);
		
		if (!questionIds.isEmpty()) {
		// after getting all questions ids , get all question data agains that id's and
		
			
		for (int i = 0; i < questionIds.size(); i++) {
			System.out.println("Question Id is: "+ questionIds.get(i));
			setTestQuestions((int) questionIds.get(i));
		}
		
		}
		JSONObject result = new JSONObject();
		result.put("questions", questions);
		return result.toString();		
	}


	// this function is to send the batch is of student
	public int getBatchId(String stdid) {
		int batchId = 0;
		tx = session.beginTransaction();
		String hql = "SELECT b.batchId FROM StudentBatches b Where b.studentId = :student_id";
		Query query = session.createQuery(hql);
		query.setParameter("student_id", stdid);
		List results = query.list();
		tx.commit();
		if (!results.isEmpty()) {
			batchId = (int) results.get(0);
			// System.out.println("found batch Id " + results.get(0));
			return batchId;
		} else {
			return batchId;
		}

	}

	// this function is to send the test id whose status is live against batch id
	public boolean getTest(int batchId) {
		int testId = 0;
		tx = session.beginTransaction();
		String hql = "FROM TestInfo tf WHERE tf.batchId = :assign_batch_id and tf.testStatus = :test_status";
		Query query = session.createQuery(hql);
		query.setParameter("assign_batch_id", batchId);
		query.setParameter("test_status", "live");
		List results = query.list();
		tx.commit();
		if (!results.isEmpty()) {
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				TestInfo tf = (TestInfo) iterator.next();
				testId = tf.getTestId();
				test_info.put("studentId", studentId);
				test_info.put("testId", testId);
				test_info.put("testMarks", tf.getTestMarks());
				test_info.put("totalQuestions", tf.getTotalQuestions());
				test_info.put("testTotalTime", tf.getTestMaxTime());
				test_info.put("testBatchId", tf.getBatchId());
				test_info.put("testTitle", tf.getName());

			}
			return true;
		} else {
			
		}
		return false;
   
	}

	// this function will send all qid's against the specific test
	public List getQuestionIds(int testId) {
		tx = session.beginTransaction();
		String hql = "Select tq.question_id FROM TestQuestions tq WHERE tq.test_id = :test_id ";
		Query query = session.createQuery(hql);
		query.setParameter("test_id", testId);
		List results = query.list();
		tx.commit();
		if (!results.isEmpty()) {
			System.out.println("found question Ids " + results.get(0) + " " + results.get(1));
			return results;
		} else {
			return results;
		}
	}

	// this function will take qid and set all question data in json object
	public void setTestQuestions(int qid) {
		tx = session.beginTransaction();
		String hql = "FROM Questions q WHERE q.id = :qid ";
		Query query = session.createQuery(hql);
		query.setParameter("qid", qid);

		List results = query.list();
		tx.commit();
		if (!results.isEmpty()) {
	
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				Questions q = (Questions) iterator.next();
				JSONObject qst = new JSONObject();
				
				options = os.getAllOptions(q.getId());
				JSONObject opt = new JSONObject();
				JSONArray j_option = new JSONArray();
				
		  		for (int i = 1 ;i <= options.size();i++)
		  		{
		  			opt = new JSONObject();
		  			Options opt1 = options.get(i-1);
		  			opt.put("option"+i, opt1.getOption_statment());
		  			opt.put("option_id", opt1.getId());
		  			
		  			j_option.add(opt);			  			
		  		}
		  		
		  		qst.put("question_id", q.getId());
		  		qst.put("marks", q.getMarks());
				qst.put("statement", q.getStatement());
		  		qst.put("options", j_option);
				
				questions.add(qst);


			}
		}
	
//		if (!results.isEmpty()) {
//			
//			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
//				Questions q = (Questions) iterator.next();
//				questions.add(q);
//			}
//		}
	}
	
	
	public Response ComputeResult(String data) {
		String studentId;
		int testId;
		int corrected =0;
		double marksObtained=0;
		double total_marks=0;
		int qid = 0;
		int marks = 0;
		int unattempt = 0;
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(data);
			testId = Integer.valueOf(json.get("test_id").toString());
			studentId = json.get("student_id").toString();
			total_marks = Double.valueOf(json.get("total_marks").toString());
			JSONArray array = (JSONArray) json.get("questions");
			Options op = new Options();
			session = gs.getSession();
			for(int i=0; i<array.size(); i++) {
				op = new Options();
				JSONObject obj = (JSONObject) array.get(i);
				qid = Integer.valueOf(obj.get("select_id").toString());
				marks = Integer.valueOf(obj.get("marks").toString());
				if (qid != 0)
				{
					tx = session.beginTransaction();
					String hql = "FROM Options op WHERE op.id = :id";
					Query query = session.createQuery(hql);
					query.setParameter("id",qid);
					op = (Options)query.getSingleResult();
					tx.commit();
					if (!isEmpty(op))
					{

						if(op.getCorrect_status() !=  null)
						{
							corrected++;
							marksObtained = marksObtained + marks;
						}

					}
				}else {
					unattempt++;
				}
				
			}
			JSONObject result = new JSONObject();
			TestResult tr = new TestResult();
			tr.setCorrected(corrected);
			tr.setMarksObtained(marksObtained);
			tr.setStudentId(studentId);
			tr.setTestId(testId);
			tr.setTotalMarks(total_marks);
			tr.setUnattempted(unattempt);
			tx = session.beginTransaction();
			int row = (int) session.save(tr);
			tx.commit();
			
			if (row > 0)
			{
				result.put("corrected", corrected);
				result.put("marks_obtained", marksObtained);
				result.put("Total_marks", total_marks);
				result.put("Unattempted", unattempt);
			}
			
			return Response.ok(result.toString()).build();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			session = null;
			e.printStackTrace();
		}
		
		return Response.status(Status.NO_CONTENT).build();
		
	}
	public Boolean isEmpty(Options op)
	{
		if(op.getId() != 0 && op.getQuestion_id() != 0)
			return false;
		else
		return true;
	}
}