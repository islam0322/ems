package org.fyp.ems.questionSettingModule.Resources;

import java.sql.Date;
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
import org.fyp.ems.questionSetterModule.DirtypoolServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.DirtyPool;

@Path("dirtyquestions")
public class DirtypoolResource {

	DirtypoolServices dp;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDirtyQuestions()
	{
		dp = new DirtypoolServices();
		String d_questions = dp.getAllDirtyQuestions();

		if (!d_questions.equals("{}"))
			return Response.ok(d_questions).build();
		else
			return Response.status(Status.NO_CONTENT).entity(d_questions).build();
	}
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response countUser()
	{
		dp = new DirtypoolServices();
		int count = dp.countPendingQuestions();
		 if (count > 0)
		 
			 return Response.status(Status.OK).entity(count).build();
		 
		 else
			 return Response.status(Status.NO_CONTENT).entity(count).build();
	}
	
	@GET
	@Path("/dirtyquestion")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getdirtyQ(@QueryParam("id") int id)
	{
		dp = new DirtypoolServices();
		String d_question = dp.getDirtyQuestion(id);

		if (!d_question.equals("{}"))
			return Response.ok(d_question).build();
		else
			return Response.status(Status.NO_CONTENT).entity(d_question).build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/update")
	public Response updateDirtyQuestion(@QueryParam("id") int id,@QueryParam("date") Date date)
	{
		dp = new DirtypoolServices();
		Boolean status =  dp.updateDirtyQuestion(id, date);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete")
	public Response deleteDirtyQuestion(@QueryParam("id") int id)
	{
		dp = new DirtypoolServices();
		int status =  dp.deleteDirtyQuestion(id);
		
		if(status > 0)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDirtyQuestion(DirtyPool dirty_pool)
	{
		dp = new DirtypoolServices();
		int status =  dp.addDirtyQuestion(dirty_pool);
		
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
		dp = new DirtypoolServices();
		dp.logout();
	}
}
