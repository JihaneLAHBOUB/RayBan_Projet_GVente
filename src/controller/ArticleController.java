package controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.ArticleModel;
import entities.Articles;
@ManagedBean(name = "articleController")
@SessionScoped

public class ArticleController {
	private ArticleModel articleModel = new ArticleModel();
	private Articles article = new Articles();
	
	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}
	//Lister tous les articles
	public List<Articles> getArticles() {
		
		return articleModel.getArticles();
	}
	//Ajouter un article
	public String Add() {
		this.articleModel.add(this.article);
		return "indexart";
	}
	//Supprimer un article
	public void delete(Articles article) {
		this.articleModel.delete(article);
	}
	//Envoyer l'objet d'article à modifier
	public String update(Articles article) {
		this.article = article;
		return "updateart";
	}
	//Modifier un article
	public String update() {
		this.articleModel.update(this.article);
		return "indexart";
	}
	//Chercher un article par codeArt
	public Articles search(int codeArt) {
		return articleModel.getArticleByID(codeArt);
	}
}
