package org.fyp.ems.questionSettingModule.Resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Random;
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
import org.fyp.ems.questionSetterModule.ImageServices;
import org.fyp.ems.questionSettingModule.EmsDbModel.Images;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path ("images")
public class ImagesResource {

	ImageServices imgsrv;
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getStatementImages(@QueryParam("id") int id)
		{
			imgsrv = new ImageServices();
			List<Images> imgsrvs = imgsrv.getStatementImages(id);
			GenericEntity<List<Images>> entity = new GenericEntity<List<Images>>(imgsrvs) {};
			if (imgsrvs.size() > 0)
				return Response.ok(entity).build();
			else
				return Response.status(Status.NO_CONTENT).entity(entity).build();
				
		}
		
		@GET
		@Path("/optionImage")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getOptionImage( @QueryParam("id") int id,@QueryParam("opt") int opt_id)
		{
			imgsrv = new ImageServices();
			Images img = imgsrv.getOptionsImages(id, opt_id);
			
			if (img.equals("")) {
				return Response.status(Status.NO_CONTENT).entity(img).build();
			} else
				return Response.status(Status.OK).entity(img).build();
		}
		
		@PUT
		@Produces(MediaType.TEXT_PLAIN)
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateimage(Images image)
		{
			imgsrv= new ImageServices();
			Boolean status =  imgsrv.updateImage(image);
			
			if(status)
				 return Response.status(Status.OK).entity(status).build();
			 else 
				 return Response.status(Status. NO_CONTENT).entity(status).build();
		}
		
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public String addimage(String image)
		{
			String imageDataBytes = "";
			String im = "";
			@SuppressWarnings("deprecation")
			JSONParser parser = new JSONParser();
			
			try {
				@SuppressWarnings("deprecation")
				JSONObject json = (JSONObject) parser.parse(image);
				
				System.out.println("here we are :");
				im = (String) json.get("statement");
				System.out.println("here we are :"+im);
				
			} catch (@SuppressWarnings("deprecation") ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String imagePath = "";
			int index = image.indexOf("base64,");
			if (index > 0) {
				imageDataBytes = image.substring(image.indexOf("base64,")+7);
				
				imageDataBytes = imageDataBytes.substring(0,imageDataBytes.indexOf("style")-2);
				int ind = image.indexOf(imageDataBytes);
				
				image = image.replace(imageDataBytes, "islam@0322");
				System.out.println("Image :"+imageDataBytes);
				System.out.println("origional is :"+image);
				System.out.println("origional ison esid sji :"+im);
				System.out.println("Index is  :"+ind);
				String path = generateSalt();
				imagePath = "C:\\base64\\"+path+".jpg";
//				try (FileOutputStream imageOutFile = new FileOutputStream(imagePath)) {
//
//					byte[] imageByteArray = Base64.getDecoder().decode(imageDataBytes);
//					imageOutFile.write(imageByteArray);
//				} catch (FileNotFoundException e) {
//					System.out.println("Image not found" + e);
//				} catch (IOException ioe) {
//					System.out.println("Exception while reading the Image " + ioe);
//				}

			}
			
//			System.out.println(imageDataBytes);
			
//			System.out.println("=================Encoder Image to Base 64!=================");
//		String base64ImageString = encoder(imagePath);
//			System.out.println("Base64ImageString = " + base64ImageString);
//	 
//			System.out.println("=================Decoder Base64ImageString to Image!=================");
//			decoder(base64ImageString, "C:\\base64\\decoderimage.jpg");
			
	
			return imagePath;

		}
//		public static String encoder(String imagePath) {
//			String base64Image = "";
//			File file = new File(imagePath);
//			try (FileInputStream imageInFile = new FileInputStream(file)) {
//				// Reading a Image file from file system
//				byte imageData[] = new byte[(int) file.length()];
//				imageInFile.read(imageData);
//				base64Image = Base64.getEncoder().encodeToString(imageData);
//			} catch (FileNotFoundException e) {
//				System.out.println("Image not found" + e);
//			} catch (IOException ioe) {
//				System.out.println("Exception while reading the Image " + ioe);
//			}
//			return base64Image;
//		}
//		public static void decoder(String base64Image, String pathFile) {
//			try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
//				// Converting a Base64 String into Image byte array
//				byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
//				imageOutFile.write(imageByteArray);
//			} catch (FileNotFoundException e) {
//				System.out.println("Image not found" + e);
//			} catch (IOException ioe) {
//				System.out.println("Exception while reading the Image " + ioe);
//			}
//		}
		public String generateSalt()
		{
			final Random RANDOM = new SecureRandom();
			byte[] salt = new byte[16];
			byte[] salt2 = new byte[16];
			byte[] salt3 = new byte[16];
			byte[] salt4 = new byte[16];
		    RANDOM.nextBytes(salt);
		    System.out.println(salt);
		    String random = salt.toString();
		    RANDOM.nextBytes(salt2);
		    System.out.println(salt2);
		    random =  random + salt2.toString();
		    RANDOM.nextBytes(salt3);
		    System.out.println(salt3);
		    random = random + salt3.toString();
		    RANDOM.nextBytes(salt4);
		    System.out.println(salt4);
		    random = random + salt4.toString();

		    return random.replace("[", "");
		}
		@DELETE
		@Produces(MediaType.TEXT_PLAIN)
		@Path("/deleteStatement")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response deleteiStatementmage(@QueryParam("id") int id )
		{
			imgsrv= new ImageServices();
			int count = imgsrv.deleteStatementImage(id);
			
			if(count > 0)
				 return Response.status(Status.OK).entity(count).build();
			 else 
				 return Response.status(Status.NO_CONTENT).entity(count).build();

		}
		@DELETE
		@Produces(MediaType.TEXT_PLAIN)
		@Path("/deleteOption")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response deleteiOptionmage(@QueryParam("id") int id,@QueryParam("opt") int opt )
		{
			imgsrv= new ImageServices();
			int count = imgsrv.deleteOptionImage(id, opt);
			
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
			imgsrv= new ImageServices();
			imgsrv.logout();
		}
		
}


