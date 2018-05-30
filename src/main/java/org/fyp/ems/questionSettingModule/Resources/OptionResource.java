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

import org.fyp.ems.questionSetterModule.OptionServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.Options;


@Path ("options")
public class OptionResource {

OptionServices op;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOptions(@QueryParam("id") int id)
	{
		op = new OptionServices();
		List<Options> options = op.getAllOptions(id);
		GenericEntity<List<Options>> entity = new GenericEntity<List<Options>>(options) {};
		if (options.size() > 0)
			return Response.ok(entity).build();
		else
			return Response.status(Status.NO_CONTENT).entity(entity).build();
			
	}
	
	@GET
	@Path("/correctoption")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCorrectOption( @QueryParam("id") int id)
	{
		op = new OptionServices();
		Options option = op.getCorrectOption(id);
		
		if (op.isEmpty(option)) {
			return Response.status(Status.NO_CONTENT).entity(option).build();
		} else
			return Response.status(Status.OK).entity(option).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOption(Options option)
	{
		op= new OptionServices();
		Boolean status =  op.updateOption(option);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOption( Options option)
	{
		op= new OptionServices();
		int count = op.addOption(option);
		
		if(count > 0)
			 return Response.status(Status.CREATED).entity(count).build();
		 else 
			 return Response.status(220).entity(count).build();

	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteOption(@QueryParam("id") int id )
	{
		op= new OptionServices();
		int count = op.deleteOption(id);
		
		if(count > 0)
			 return Response.status(Status.OK).entity(count).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(count).build();

	}
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logout")
	public void logout()
	{
		op= new OptionServices();
		op.logout();
	}
	
}
