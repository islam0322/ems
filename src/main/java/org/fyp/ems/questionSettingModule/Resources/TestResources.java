package org.fyp.ems.questionSettingModule.Resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.fyp.ems.questionSetterModule.TestService;



// this method will get the user id and password and pass it to test service class
@Path("/student")
public class TestResources {
	TestService ts;
	@Path("/auth")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
   public Response StudentLogin(@HeaderParam("student_id") String studentid, @HeaderParam("password") String password) {
   	 ts = new TestService();
   	
       return ts.StudentLogin(studentid, password);
      
    }
	@Path("/test")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response StudentTest(@HeaderParam("student_id") String studentid ,@HeaderParam("testid") int tid) {
		 ts = new TestService();
		 String q = ts.StudentTest(studentid, tid);
		
		 if (q.length() > 0)
		 return Response.ok(q.toString()).build();
		 else
		 return Response.status(Status.NO_CONTENT).build();
	}
	@Path("/computeResult")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ComputeResult(String data) {
	
		System.out.println(data);
		
		ts = new TestService();
		return ts.ComputeResult(data);
	}
}

