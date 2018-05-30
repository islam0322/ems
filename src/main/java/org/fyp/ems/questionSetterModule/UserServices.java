package org.fyp.ems.questionSetterModule;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.fyp.ems.questionSettingModule.EmsDbModel.Roles;
import org.fyp.ems.questionSettingModule.EmsDbModel.Users;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.fyp.ems.questionSettingModule.Security.password_incryptor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class UserServices {
	
	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <Users>users;
	JSONArray user_array = new JSONArray();
	JSONObject user = new JSONObject();
	public String getAllUsers()
	{
		try {		
			session = gs.getSession();
			tx = session.beginTransaction();
			users = session.createQuery("FROM Users").list();
		    tx.commit();
		    for(int i=0;i < users.size();i++)
		    {
		    	user = new JSONObject();
		    	Users us = users.get(i); 
		    	user.put("username", us.getUsername());
		    	user.put("password",us.getPassword());
		    	user.put("role", us.getRole());
		    	String hql = "FROM Roles r WHERE r.id=:id";
				Query query = session.createQuery(hql);	
				query.setParameter("id",us.getRole());
				Roles role = (Roles)query.getSingleResult();
				
				user.put("role_name", role.getName());
		    	user.put("status", us.getStatus());
		    	user.put("parent_id",us.getParent_id());
		    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = us.getJoining_date();
				String today = dateFormat.format(date);
		    	user.put("joining_date",today);
		    	user.put("Name",us.getName());
		    	user.put("user_id",us.getId());
		    	
		    	user_array.add(user);
		    }
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			System.out.println("exception occures1");
				
		} finally {
			session = null; 
		}
		JSONObject result = new JSONObject();
		result.put("users", user_array);
		
		return result.toJSONString();
	}
	public String getAllUsersByName(String username)
	{
			
		try {		
			session = gs.getSession();
			tx = session.beginTransaction();
			users = session.createQuery("FROM Users where username= '"+username+"'").list();
		    tx.commit();
		    for(int i=0;i < users.size();i++)
		    {
		    	user = new JSONObject();
		    	Users us = users.get(i);
		    	user.put("password",us.getPassword());
		    	String hql = "FROM Roles r WHERE r.id=:id";
				Query query = session.createQuery(hql);	
				query.setParameter("id",us.getRole());
				Roles role = (Roles)query.getSingleResult();
				user.put("role_name", role.getName());
		    	user.put("role", us.getRole());
		    	user.put("status",us.getStatus());
		    	user.put("parent_id",us.getParent_id());
		    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = us.getJoining_date();
				String today = dateFormat.format(date);
		    	user.put("joining_date",today);
		    	user.put("Name",us.getName());
		    	user_array.add(user);
		    }	
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			System.out.println("exception occures1");
				
		} finally {
			session = null; 
		}
		JSONObject result = new JSONObject();
		result.put("users", user_array);
		
		return result.toJSONString();
	}
	
	public int updateUser(String username,String pwd,int role)
	{
		int count = 0;
		try {
			
				session = gs.getSession();	
				tx = session.beginTransaction();
				password_incryptor pr = new password_incryptor();
				String salt = pr.generateSalt();
				String pass = pr.md5(pwd+salt);
				String hql = "update Users us set us.password=:ps,us.salt=:salt where us.username=:user and role=:rl";
				Query query = session.createQuery(hql);
				query.setParameter("ps",pass);
				query.setParameter("salt",salt);
				query.setParameter("user",username);
				query.setParameter("rl",role);
				count = query.executeUpdate();
				tx.commit();
	         	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
				return count;
			
			} finally {
				session = null; 
			}
		return count;
	}
	public Users getUser(String uname)
	{
		Users user = new Users();
		try {
			session = gs.getSession(); 
			tx = session.beginTransaction();
			 String hql = "FROM Users u WHERE u.username=:user";
			 Query query = session.createQuery(hql);
			 query.setParameter("user",uname);
			 user = (Users)query.getSingleResult();
	         tx.commit();
	         
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 
		} finally {
			session = null; 
		}
		
		return user;
	}
	public Users checkLogin(String uname , int role)
	{
		System.out.print(role);
		Users user = new Users();
		try {
				session = gs.getSession(); 
				tx = session.beginTransaction();
				String hql = "FROM Users u WHERE u.username = :username and u.role = :rl";
				Query query = session.createQuery(hql);
				query.setParameter("username",uname);
				query.setParameter("rl",role);
				user = (Users)query.getSingleResult();
				tx.commit();
	                
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
				return user;
			 
			} finally {
				session = null; 
			}
		
		return user;
	}
	public int addUser(Users user)
	{
		int count=0;
		try {
			session = gs.getSession(); 
			tx = session.beginTransaction();
			password_incryptor pr = new password_incryptor();
			String salt = pr.generateSalt();
			String pass = pr.md5(user.getPassword()+salt);
			System.out.println(pass.length());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String today = dateFormat.format(date);
			user.setJoining_date(dateFormat.parse(today));
			user.setSalt(salt);
			user.setPassword(pass);
			count = (int) session.save(user);
	        tx.commit();
	         
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 return count;
			 
		} finally {
			session = null; 
		}
		
		return count;
	}
	public Boolean disableUser (String uname)
	{
		try {
				session = gs.getSession();	
				tx = session.beginTransaction();
				Users user = (Users)session.get(Users.class, uname); 
				user.setStatus("disable");
				session.update(user); 
				tx.commit();
	         	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
				return false;
			
			} finally {
				session = null; 
			}
		return true;
	}
	public Boolean enableUser (String uname)
	{
		try {
			
				session = gs.getSession();	
				tx = session.beginTransaction();
				Users user = (Users)session.get(Users.class, uname); 
				user.setStatus("enable");
				session.update(user); 
				tx.commit();
	         	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
				return false;
			
			} finally {
				session = null; 
			}
		return true;
	}
	public int countUsers ()
	{
		int count = 0;
		try {			
				session = gs.getSession();	
				tx = session.beginTransaction();
				String sql = "select count(u) from Users u";
				Query query  = session.createQuery(sql);
				System.out.println("here");
				Long res = (Long) query.uniqueResult();
				count = res.intValue();
				tx.commit();     	
			}catch(Exception e) {
				if (tx!=null) tx.rollback();
				System.out.println("exception occures2");
				return count;
			
			} finally {
				session = null; 
			}
		return count;
	}
	public Boolean checkUsername(String uname)
	{
		Users user;
		Boolean status= false;
		try {
			session = gs.getSession();	
			tx = session.beginTransaction();
			String hql = "FROM Users u WHERE u.username = :username";
			 Query query = session.createQuery(hql);
			 query.setParameter("username",uname);
			 user = (Users) query.getSingleResult();
			 if (!user.getUsername().equals(null))
			 {
				 status= true;
			 }
			 
	         tx.commit();
	         	
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 return status;
			
		} finally {
			session = null; 
		}
		return status;
	}
	public Boolean isEmpty(Users user)
	{
		if(user.getUsername() != null && user.getPassword() != null && user.getRole() != 0 && user.getStatus() != null && user.getSalt() != null)
		{
			return false;
		}
		else return true;
		
	}
	
	public void logout()
	{
		gs.CloseSession();
	}
	
}
