package org.fyp.ems.questionSettingModule.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fyp.ems.questionSetterModule.SubjectServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.Subjects;

@Path("subjects")
public class SubjectResource {
	
	
	SubjectServices sb;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers()
	{
		sb = new SubjectServices();
		List<Subjects> subjects = sb.getAllSubjects();
		
		GenericEntity<List<Subjects>> entity = new GenericEntity<List<Subjects>>(subjects) {};
		if (subjects.size() > 0)
			return Response.ok(entity).build();
		else
			return Response.status(Status.NO_CONTENT).entity(entity).build();
	}
	
	@GET
	@Path("/subject")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubject(@QueryParam("id") int id)
	{
		sb = new SubjectServices();
		Subjects subject = sb.getSubject(id);
		
		if (sb.isEmpty(subject)) {
			return Response.status(Status.NO_CONTENT).entity(subject).build();
		} else
			return Response.status(Status.OK).entity(subject).build();
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/update")
	public Response updateSubject(@QueryParam("id") int id,@QueryParam("name") String name)
	{
		sb = new SubjectServices();
		Boolean status =  sb.updateSubejct(id, name);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete")
	public Response deleteStaff(@QueryParam("id") int id)
	{
		sb = new SubjectServices();
		int status =  sb.deleteSubject(id);
		
		if(status > 0)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSubject(Subjects subject)
	{
		sb = new SubjectServices();
		int status =  sb.addSubject(subject);
		
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
		sb = new SubjectServices();
		sb.logout();
	}
}
