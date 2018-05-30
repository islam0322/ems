//package org.fyp.ems.questionSettingModule.Security;
//
//
//import java.io.IOException;
//import java.util.List;
//import java.util.StringTokenizer;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//import org.glassfish.jersey.internal.util.Base64;
//
//@Provider
//public class loggingFilters implements ContainerRequestFilter{
//
//	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
//	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
//	@Override
//	public void filter(ContainerRequestContext requestContext) throws IOException {
//		// TODO Auto-generated method stub
//		
//		List <String> auth = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
//		
//		if (auth != null && auth.size() > 0)
//		{
//			String authTokken = auth.get(0);
//			authTokken = authTokken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
//			String decodStr = Base64.decodeAsString(authTokken);
//			StringTokenizer strTokeen = new StringTokenizer(decodStr,":");
//			String uname = strTokeen.nextToken();
//			String pwd = strTokeen.nextToken();
//			
//			if (uname.equals("username") && pwd.equals("password"))
//			{
//				return;
//			}
//		}
//		
//		Response unAuthorizeStatus = Response.status(Response.Status.UNAUTHORIZED)
//									.entity("User cannot access to Resource")
//									.build();
//		requestContext.abortWith(unAuthorizeStatus);
//		
//	}
//	
//	
//
//}
