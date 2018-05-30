package org.fyp.ems.questionSettingModule.Resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fyp.ems.questionSetterModule.BatchServices;


@Path("batch")
public class BatchResources {

	
	BatchServices sb;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBatches()
	{
		sb= new BatchServices();
		String users = sb.getAllBatches();
	
		if (!users.equals("{}"))
			return Response.ok(users).build();
		else
			return Response.status(Status.NO_CONTENT).entity(users).build();
			
	}
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public int addBatch(String batch)
	{
		sb = new BatchServices();
		
		return sb.addBatch(batch);
	}
	
}
