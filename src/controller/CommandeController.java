package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import model.ArticleModel;
import model.CommandeModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import entities.Articles;
import entities.Commandes;
@ManagedBean(name = "commandeController")
@SessionScoped

public class CommandeController {
	private CommandeModel commandeModel = new CommandeModel();
	private Commandes commande = new Commandes();
	private ArticleModel artModel = new ArticleModel();
	
	
	
	public ArticleModel getArtModel() {
		return artModel;
	}

	public void setArtModel(ArticleModel artModel) {
		this.artModel = artModel;
	}

	public Commandes getCommande() {
		return commande;
	}

	public void setCommande(Commandes commande) {
		this.commande = commande;
	}

	public List<Commandes> getCommandes() {
		
		return commandeModel.getCommandes();
	}
	
	//Lister tous les articles
	public List<Articles> getArts() {
		return artModel.getArticles();
	}
	
	//Ajouter une commande
	public String Add() {
		this.commandeModel.add(this.commande);
		return "indexcmd";
		
	}
	
	//Supprimer une commande
	public void delete(Commandes commande) {
		this.commandeModel.delete(commande);
	}
	
	//Envoyer l'àbjet commande à modifier
	public String update(Commandes commande) {
		this.commande = commande;
		return "updatecmd";
	} 
	
	//Modifier une commande
	public String update() {
		this.commandeModel.update(this.commande);
		return "indexcmd";
	}
	
	//Methode pour generer PDF pour une commande
	public void showReport(Commandes commande) throws JRException, IOException {
			this.commande = commande;
			String page = commande.getCodeCmd().toString();
			String date = commande.getDateCmd().toString();
			String client = commande.getClient();
			String ref = "fact"+page+date+client;
			List<Map<String,?>> result = new ArrayList<Map<String,?>>();
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("codeCmd", this.commande.getCodeCmd());
			m.put("client", this.commande.getClient());
			m.put("codeArt", this.commande.getCodeArt());
			m.put("qteCmd", this.commande.getQteCmd());
			m.put("dateCmd", this.commande.getDateCmd());
			result.add(m);
			
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(result);
			String jrxmlFile = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/commandereport.jrxml");
			InputStream input = new FileInputStream(new File(jrxmlFile));
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrDataSource);
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename="+ref+".pdf");
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
	}
	
}
