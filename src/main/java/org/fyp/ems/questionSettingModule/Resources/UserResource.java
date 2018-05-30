package org.fyp.ems.questionSettingModule.Resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.fyp.ems.questionSetterModule.UserServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.Users;
import org.fyp.ems.questionSettingModule.Security.password_incryptor;

@Path("users")

public class UserResource {
	
	UserServices us;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers()
	{
		us = new UserServices();
		String users = us.getAllUsers();
	
		if (!users.equals("{}"))
			return Response.ok(users).build();
		else
			return Response.status(Status.NO_CONTENT).entity(users).build();
			
	}
	
	@Path("/byUserName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsersByUserName(@QueryParam("username") String username)
	{
		us = new UserServices();
		String users = us.getAllUsersByName(username);
		if (!users.equals("{}"))
			return Response.ok(users).build();
		else
			return Response.status(Status.NO_CONTENT).entity(users).build();
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam("username") String username)
	{
		us = new UserServices();
		Users user = us.getUser(username);
		
		if (us.isEmpty(user)) {
			return Response.status(Status.NO_CONTENT).entity(user).build();
		} else
			return Response.status(Status.OK).entity(user).build();
	}
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response countUser()
	{
		us = new UserServices();
		int count = us.countUsers();
		 if (count > 0)
		 
			 return Response.status(Status.OK).entity(count).build();
		 
		 else
			 return Response.status(Status.NO_CONTENT).entity(count).build();
	}
	@GET
	@Path("/checkusername")
	@Produces(MediaType.TEXT_PLAIN)
	public Response checkUser(@QueryParam("username") String username)
	{
		us = new UserServices();
		Boolean status = us.checkUsername(username);
		 if (status)
		 
			 return Response.status(Status.OK).entity(status).build();
		 
		 else
			 return Response.status(Status.NO_CONTENT).entity(status).build();
	}	
	@GET
	@Path("/checklogin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkLogin(@QueryParam("username") String username,@QueryParam("password") String password,@QueryParam("role") int role)
	{
		us = new UserServices();
		Users user = us.checkLogin(username,role);
		password_incryptor pr = new password_incryptor();
		String pass = pr.md5(password+user.getSalt());
		 if (us.isEmpty(user))
			 return Response.status(Status.NO_CONTENT).entity(user).build();
		 else if (!pass.equals(user.getPassword())) {
			 user = new Users();
			 return Response.status(Status.PRECONDITION_FAILED).entity(user).build();
		 }
		 else if(user.getStatus().equals("disable")) {
			 user = new Users();
			 return Response.status(Status.METHOD_NOT_ALLOWED).entity(user).build(); 
		 }
		 else
			 return Response.status(Status.OK).entity(user).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/update")
	public Response setUser(@QueryParam("username") String username,@QueryParam("password") String password,@QueryParam("role") int role)
	{
		us= new UserServices();
		int  count =  us.updateUser(username,password,role);
		
		if(count > 0)
			 return Response.status(Status.OK).entity(count).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(count).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/disableuser")
	public Response disableUser(@QueryParam("username") String username)
	{
		us= new UserServices();
		Boolean status =  us.disableUser(username);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();

	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/enableuser")
	public Response enableUser(@QueryParam("username") String username)
	{
		us= new UserServices();
		Boolean status =  us.enableUser(username);
		
		if(status)
			 return Response.status(Status.OK).entity(status).build();
		 else 
			 return Response.status(Status. NO_CONTENT).entity(status).build();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser( Users user)
	{
		us= new UserServices();
		int count =  us.addUser(user);
		
		if(count > 0)
			 return Response.status(Status.CREATED).entity(count).build();
		 else 
			 return Response.status(220).entity(count).build();

	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logout")
	public void logout()
	{
		us= new UserServices();
		us.logout();
	}
	
}
