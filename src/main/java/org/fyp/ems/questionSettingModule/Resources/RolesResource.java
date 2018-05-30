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
import org.fyp.ems.questionSetterModule.RolesServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.Roles;

@Path("/roles")
public class RolesResource {
	RolesServices rs;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRoles()
	{
		rs = new RolesServices();
		String users = rs.getAllRoles();
	
		if (!users.equals("{}"))
			return Response.ok(users).build();
		else
			return Response.status(Status.NO_CONTENT).entity(users).build();
			
	}

	@GET
	@Path("/role")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRole(@QueryParam("id") int id)
	{
		rs = new RolesServices();
		Roles role = rs.getRole(id);
		
		if (rs.isEmpty(role)) {
			return Response.status(Status.NO_CONTENT).entity(role).build();
		} else
			return Response.status(Status.OK).entity(role).build();
	}
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response countRoles()
	{
		rs = new RolesServices();
		int count = rs.countRoles();
		 if (count > 0)
		 
			 return Response.status(Status.OK).entity(count).build();
		 
		 else
			 return Response.status(Status.NO_CONTENT).entity(count).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/update")
	public Response setRole(@QueryParam("id") int id,@QueryParam("name") String name)
	{
		rs = new RolesServices();
		Boolean status =  rs.updateRoles(id, name);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRole( Roles role)
	{
		rs = new RolesServices();
		int count =  rs.addRole(role);
		
		if(count > 0)
			 return Response.status(Status.CREATED).entity(count).build();
		 else 
			 return Response.status(220).entity(count).build();

	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/delete")
	public Response deleteRole(@QueryParam("id") int id)
	{
		rs = new RolesServices();
		int status =  rs.deleteRole(id);
		
		if(status > 0)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}

}
