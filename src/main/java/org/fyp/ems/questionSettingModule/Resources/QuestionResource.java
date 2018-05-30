package org.fyp.ems.questionSettingModule.Resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.fyp.ems.questionSetterModule.QuestionServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;
import org.fyp.ems.questionSettingModule.EmsDbModel.Questions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
@SuppressWarnings({ "deprecation" })


@Path("questions")
public class QuestionResource {
			
	QuestionServices qs;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllQuestions()
	{
		qs = new QuestionServices();
		String questions = qs.getAllQuestions();
		if (!questions.isEmpty())
			return Response.ok(questions).build();
		else
			return Response.status(Status.NO_CONTENT).entity(questions).build();
	}
		
	@GET
	@Path("/question")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(@QueryParam("id") int id)
	{
		qs = new QuestionServices();
		String question = qs.getQuestion(id);
		
		if (question.isEmpty()) {
			return Response.status(Status.NO_CONTENT).entity(question).build();
		} else
			return Response.status(Status.OK).entity(question).build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateQuestion(Questions questions)
	{
		qs = new QuestionServices();
		Boolean status =  qs.updateQuestion(questions);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/disablequestion")
	public Response disableQuestion(@QueryParam("id") int id)
	{
		qs = new QuestionServices();
		Boolean status =  qs.disableQustion(id);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/enablequestion")
	public Response enableQuestion(@QueryParam("id") int id)
	{
		qs = new QuestionServices();
		Boolean status =  qs.enableQustion(id);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
		
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public int addQuestion(String questions,@HeaderParam ("image") String image)
	{
		qs = new QuestionServices();
		//int count =  qs.addQuestions(questions);
		
		System.out.println(questions+"   "+image);
		
		return 1;
	}
		
//		JSONParser parser = new JSONParser();
//		Questions qu  = new Questions();
//		
//		try {
//			JSONObject json = (JSONObject) parser.parse(questions);
//
//			qu.setSubject((String) json.get("subject"));
//			qu.setAuther((String) json.get("auther"));
//			qu.setTopic((String) json.get("topic"));
//			qu.setStatement((String) json.get("statement"));
//			qu.setSub_Topic((String) json.get("sub_topic"));
//			qu.setEstimated_time(Integer.parseInt(json.get("estimated_time").toString()));
//			qu.setComment((String) json.get("comment"));
//			qu.setStatus((String) json.get("status"));
//			qu.setDifficulty_level(Integer.parseInt(json.get("difficulty_level").toString()));
//			qu.setMarks(Integer.parseInt( json.get("marks").toString()));
////			System.out.println(sub + " "+ auther +""+ st + " "+ topic);
////			System.out.println(status + " "+ coment +""+ time + " "+ deff_level+ " " +marks);
//			System.out.println(qu.getComment());
//			JSONArray array = (JSONArray) json.get("options");
//			String opt,correct_status;
//			for(int i=0; i<array.size(); i++) {
//  
//				JSONObject obj = (JSONObject) array.get(i);
//				opt =  (String ) obj.get("option_statement");
//				correct_status = (String )obj.get("correct_status");
//				
//				System.out.println(opt + " "+ correct_status);
//        	}
//	
//		}catch(Exception e) {
//			
//			 System.out.println("exception occures2");
//		
//			 
//		}
//		
//		
//		if(count > 0)
//			 return Response.status(Status.CREATED).entity(count).build();
//		 else 
//			 return Response.status(220).entity(count).build();
		

	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCount(@QueryParam("user") String username)
	{
		qs = new QuestionServices();
		int count = qs.countQuestions();
		
		if(count > 0)
			 return Response.status(Status.OK).entity(count).build();
		 else 
			 return Response.status(220).entity(count).build();
		
	}
	@GET
	@Path("/approver")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getApproverInformation(@QueryParam("username") String username)
	{
		qs = new QuestionServices();
		String count = qs.getApproverInfo(username);
		
		if(count.isEmpty())
			return Response.status(220).entity(count).build();
			 
		 else 
			 return Response.status(Status.OK).entity(count).build();
		
	}
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logout")
	public void logout()
	{
		qs = new QuestionServices();
		qs.logout();
	}
}
