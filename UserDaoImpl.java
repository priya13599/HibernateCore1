package com.nucleus.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nucleus.model.User;
@Repository
public class UserDaoImpl implements IUserDao{
	@Autowired
	SessionFactory sessionfactory;
	
	
	@Override
	public void SaveUser(User user) {
		Session session = sessionfactory.getCurrentSession();
		session.persist(user);
		
	}


	@Override
	public int retrieveRoleId(String rolename) {
		Query q = (Query) sessionfactory.getCurrentSession().createQuery("select roleId from Role where roleName=?");
		q.setParameter(0, rolename);
		System.out.println(q.list());
		List<Integer> list = q.list();
		return list.get(0);
		
	}


	@Override
	public int checkuserid(String userid) {
		Query q = (Query) sessionfactory.getCurrentSession().createQuery("select userId from User");
		
		System.out.println(q.list());
		List<String> list = q.list();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).equals(userid))
			{
				return 0;
			}
		}
		return 1;
	}

}
