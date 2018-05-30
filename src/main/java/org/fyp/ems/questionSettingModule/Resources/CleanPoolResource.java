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
import org.fyp.ems.questionSetterModule.CleanPoolServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.CleanPool;

@Path ("cleanquestions")
public class CleanPoolResource {
	
	CleanPoolServices cp;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCleanQuestions()
	{
		cp = new CleanPoolServices();
		String c_questions = cp.getAllCleanQuestions();

		if (!c_questions.equals("{}"))
			return Response.ok(c_questions).build();
		else
			return Response.status(Status.NO_CONTENT).entity(c_questions).build();
	}
	
	@GET
	@Path("/cleanQuestion")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCleanQuestion(@QueryParam("id") int id)
	{
		cp = new CleanPoolServices();
		String c_question = cp.getCleanQuestion(id);

		if (!c_question.equals("{}"))
			return Response.ok(c_question).build();
		else
			return Response.status(Status.NO_CONTENT).entity(c_question).build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/update")
	public Response updateCleanQuestion(@QueryParam("id") int id,@QueryParam("comment") String comment)
	{
		cp = new CleanPoolServices();
		Boolean status =  cp.updateCleanQuestion(id, comment);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete")
	public Response deleteCleanQuestion(@QueryParam("id") int id)
	{
		cp = new CleanPoolServices();
		int status =  cp.deleteCleanQuestion(id);
		
		if(status > 0)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCleanQuestion( CleanPool clean_question)
	{
		
		cp = new CleanPoolServices();
		int status =  cp.addCleanQuestion(clean_question);
		
		if(status > 0)
			 return Response.status(Status.CREATED).entity(status).build();
		 else 
			 return Response.status(220).entity(status).build();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response countUser(@QueryParam("username") String username)
	{
		cp = new CleanPoolServices();
		int count = cp.countApprovedQuestions(username);
		 if (count > 0)
		 
			 return Response.status(Status.OK).entity(count).build();
		 
		 else
			 return Response.status(Status.NO_CONTENT).entity(count).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logout")
	public void logout()
	{
		cp = new CleanPoolServices();;
		cp.logout();
	}

}
