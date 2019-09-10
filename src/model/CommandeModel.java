package model;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Commandes;
import util.HibernateUtil;

public class CommandeModel {
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	//Lister tous les commandes
	public  List<Commandes> getCommandes(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Commandes");
			List<Commandes> listCommandes = query.list();
			transaction.commit();
			return listCommandes;
			
		}
		catch (Exception ex) {
			if(!(transaction == null))
			transaction.rollback();
		}finally {
			session.close();
		}
		return null;
	}
	
	//Ajouter une commande
	public boolean add(Commandes commande) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(commande);
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
	
	//Retourner une commande par codeCmd
	public Commandes getCommandeByID(int codeCmd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Commandes commande = (Commandes) session.get(Commandes.class, codeCmd);
			transaction.commit();
			return commande;
		} catch (Exception e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}
	
	//Modifier une commande
	public boolean update(Commandes commande) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(commande);
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
	
	//Supprimer une commande
	public boolean delete(Commandes commande) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(commande);
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
	
	//LChercher une commande par code
	public List<Commandes> searchCommandeByID(int code){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String qr = "FROM Commandes WHERE codeCmd ="+code;
			Query query = session.createQuery(qr);
			List<Commandes> listCommandes = query.list();
			transaction.commit();
			return listCommandes;
			
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
