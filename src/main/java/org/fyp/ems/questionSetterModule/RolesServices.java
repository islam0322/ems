package org.fyp.ems.questionSetterModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.fyp.ems.questionSettingModule.EmsDbModel.Roles;
import org.fyp.ems.questionSettingModule.Security.Generator_Session;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })

public class RolesServices {

	private static Generator_Session gs = new Generator_Session();
	private static Session session = null;
	static Transaction tx = null;
	List <Roles>roles;
	JSONArray roles_array = new JSONArray();
	JSONObject role = new JSONObject();
	
	public String getAllRoles()
	{
		try {		
			session = gs.getSession();
			tx = session.beginTransaction();
			roles = session.createQuery("FROM Roles").list();
		    tx.commit();
		    for(int i=0;i < roles.size();i++)
		    {
		    	role = new JSONObject();
		    	Roles rl = roles.get(i); 
		    	role.put("id", rl.getId());
		    	role.put("name",rl.getName());
		    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = rl.getCreated_date();
				String today = dateFormat.format(date);
				role.put("Created_date",today);
				roles_array.add(role);
		    }
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			System.out.println("exception occures1");
				
		} finally {
			session = null; 
		}
		JSONObject result = new JSONObject();
		result.put("roles", roles_array);
		
		return result.toJSONString();
	}
	public Boolean updateRoles(int id,String name)
	{
		try {
				session = gs.getSession();	
				tx = session.beginTransaction();
				Roles role = (Roles)session.get(Roles.class, id); 
				role.setName(name);;
				session.update(role); 
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
	
	//problem for consective calles
	public Roles getRole(int id)
	{
		Roles role = new Roles();
		try {
			session = gs.getSession(); 
			tx = session.beginTransaction();
			String hql = "FROM Roles r WHERE r.id=:id";
			Query query = session.createQuery(hql);	
			query.setParameter("id",id);
			role = (Roles)query.getSingleResult();
			tx.commit();
	         
		}catch(Exception e) {
			 if (tx!=null) tx.rollback();
			 System.out.println("exception occures2");
			 
		} finally {
			session = null; 
		}
		
		return role;
	}
	public int addRole(Roles role)
	{
		int count=0;
		try {
			session = gs.getSession(); 
			tx = session.beginTransaction();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String today = dateFormat.format(date);
			role.setCreated_date(dateFormat.parse(today));
			count = (int) session.save(role);
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
	public int deleteRole(int id)
	{
		int count=0;
		try {
				session = gs.getSession();
				tx = session.beginTransaction();
				String hql = "delete FROM Roles r WHERE r.id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id",id);
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
	public int countRoles ()
	{
		int count = 0;
		try {			
				session = gs.getSession();	
				tx = session.beginTransaction();
				String sql = "select count(r) from Roles r";
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
	public Boolean isEmpty(Roles role)
	{
		if(role.getId() !=  0 && role.getName() != null )
		{
			return false;
		}
		else return true;
		
	}
}
