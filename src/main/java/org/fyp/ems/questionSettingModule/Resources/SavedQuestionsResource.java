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

import org.fyp.ems.questionSetterModule.SavedQuestionServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.SavedQuestions;


@Path ("savedquestions")
public class SavedQuestionsResource {
	
	SavedQuestionServices sq;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSavedQuestions()
	{
		sq = new SavedQuestionServices(); 
		String s_questions = sq.getAllsavedquestions();

		if (!s_questions.equals("{}"))
			return Response.ok(s_questions).build();
		else
			return Response.status(Status.NO_CONTENT).entity(s_questions).build();
	}
	
	@GET
	@Path("/savedQuestion")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getSavedQ(@QueryParam("id") int id)
	{
		sq = new SavedQuestionServices();

		String s_question = sq.getSavedQuestion(id);

		if (!s_question.equals("{}"))
			return Response.ok(s_question).build();
		else
			return Response.status(Status.NO_CONTENT).entity(s_question).build();
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete")
	public Response deleteSavedQuestion(@QueryParam("id") int id)
	{
		sq = new SavedQuestionServices();
		int status =  sq.deleteSavedQuestion(id);
		
		if(status > 0)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuestion(String saved)
	{
		
		sq = new SavedQuestionServices();
		int index =  sq.addSavedQuestion(saved);
		System.out.println("index :"+index);
		if(index > 0)
			 return Response.status(Status.CREATED).entity(index).build();
		 else 
			 return Response.status(220).entity(index).build();
		
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logout")
	public void logout()
	{
		sq = new SavedQuestionServices();
		sq.logout();
	}

}
