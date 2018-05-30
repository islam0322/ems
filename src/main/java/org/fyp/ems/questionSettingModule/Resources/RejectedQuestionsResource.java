package org.fyp.ems.questionSettingModule.Resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fyp.ems.questionSetterModule.RejectedQuestionServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.RejectedQuestion;

@Path("rejectedquestions")

public class RejectedQuestionsResource {
	
	RejectedQuestionServices rq;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRejectedQuestions()
	{
		rq = new RejectedQuestionServices();
		String r_questions = rq.getAllRejectedQuestions();

		if (!r_questions.equals("{}"))
			return Response.ok(r_questions).build();
		else
			return Response.status(Status.NO_CONTENT).entity(r_questions).build();
	}
	
	@GET
	@Path("/rejectedQuestion")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getRejectedQuestion(@QueryParam("id") int id)
	{
		rq = new RejectedQuestionServices();
		String r_question = rq.getRejectedQuestion(id);

		if (!r_question.equals("{}")) 
			return Response.ok(r_question).build();
		else
			return Response.status(Status.NO_CONTENT).entity(r_question).build();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUnseenQuestions(@QueryParam("username") String username)
	{
		rq = new RejectedQuestionServices();
		int count = rq.countRejectedQuestions(username);

		if (count > 0)
			 
			 return Response.status(Status.OK).entity(count).build();
		 
		 else
			 return Response.status(Status.NO_CONTENT).entity(count).build();
		
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/update")
	public Response updateRejectedQuestion(@QueryParam("id") int id,@QueryParam("comment") String comment)
	{
		rq = new RejectedQuestionServices();
		Boolean status =  rq.updateRejectedQuestion(id, comment);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/seenquestion")
	public Response makeQuestionSeen(@QueryParam("id") int id)
	{
		rq = new RejectedQuestionServices();
		Boolean status =  rq.makeQuestionSeen(id);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
		
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete")
	public Response deleteRejectedQuestion(@QueryParam("id") int id)
	{
		rq = new RejectedQuestionServices();
		int status =  rq.deleteRejectedQuestion(id);
		
		if(status > 0)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRejectedQuestion(RejectedQuestion rejected_question)
	{
		rq = new RejectedQuestionServices();
		int status =  rq.addRejectedQuestion(rejected_question);;
		
		if(status > 0)
			 return Response.status(Status.CREATED).entity(status).build();
		 else 
			 return Response.status(220).entity(status).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logout")
	public void logout()
	{
		rq = new RejectedQuestionServices();
		rq.logout();
	}

}
