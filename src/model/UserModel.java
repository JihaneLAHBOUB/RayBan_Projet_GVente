package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Users;
import util.HibernateUtil;

public class UserModel {
	//Lister les utilisateurs
	public  List<Users> getUsers(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Users");
			List<Users> listUsers = query.list();
			transaction.commit();
			return listUsers;
			
		}
		catch (Exception ex) {
			if(!(transaction == null))
			transaction.rollback();
		}finally {
			session.close();
		}
		return null;
	}
	
	//ajouter un utilisateur
	public boolean add(Users user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			
			return true;
			
		}
		catch (Exception ex) {
			if(!(transaction == null))
			transaction.rollback();
		}finally {
			session.close();
		}
		return false;
	}
	
	//Modifier un utilisateur
	public boolean update(Users user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}
	
	//Supprimer un utilisateur
	public boolean delete(Users user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}
	
}
