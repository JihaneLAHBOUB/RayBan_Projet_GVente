package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Articles;
import util.HibernateUtil;

public class ArticleModel {
	//Lister tous les articles
	public  List<Articles> getArticles(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Articles");
			List<Articles> listArticles = query.list();
			transaction.commit();
			return listArticles;
			
		}
		catch (Exception ex) {
			if(!(transaction == null))
			transaction.rollback();
		}finally {
			session.close();
		}
		return null;
	}
	//Ajouter  un article
	public boolean add(Articles article) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(article);
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
	
	//Retourner un article par codeArt
	public Articles getArticleByID(int codeArt) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Articles article = (Articles) session.get(Articles.class, codeArt);
			transaction.commit();
			return article;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}
	
	//Modifier un article
	public boolean update(Articles article) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(article);
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
	
	//Supprimer un article
	public boolean delete(Articles article) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(article);
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
	
	//Chercher un article par code
	public  List<Articles> searchArticleByID(int code){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String qr = "FROM Articles WHERE codeArt ="+code;
			Query query = session.createQuery(qr);
			List<Articles> listArticles = query.list();
			transaction.commit();
			return listArticles;
			
		}
		catch (Exception ex) {
			if(!(transaction == null))
			transaction.rollback();
		}finally {
			session.close();
		}
		return null;
	}
	
}
