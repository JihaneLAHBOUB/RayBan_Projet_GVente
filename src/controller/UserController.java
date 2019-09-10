package controller;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entities.Users;
import model.UserModel;
import util.EncryptUtil;
import util.SessionUtil;
@ManagedBean(name = "userController")
@SessionScoped

public class UserController {
	
	private UserModel userModel = new UserModel();
	private Users user = new Users();
	private String login;
	private String pass;
	private String msg = "";
	
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	//Lister tous les utilisateurs
	public List<Users> getUsers(){
		return this.userModel.getUsers();
	}
	//Ajouter un utilisateur
	public String Add() {
		//Encrepter le mot de pass
		String encrypt = EncryptUtil.md5(this.user.getPass());
		this.user.setPass(encrypt);
		this.userModel.add(this.user);
		return "indexuser";
	}
	//Envoyer l'objet utilisateur à modifier
	public String update(Users user) {
		this.user = user;
		return "updateuser";
	}
	//Modifier un utilisateur
	public String update() {
		//Encrepter le mot de pass
		String encrypt = EncryptUtil.md5(this.user.getPass());
		this.user.setPass(encrypt);
		this.userModel.update(this.user);
		return "indexuser";
	}
	//Supprimer un utilisateur
	public void delete(Users user) {
		this.userModel.delete(user);
	}
	//Envoyer vers la page LOGIN
	public void login(Users user) throws IOException {
		for(Users u : this.getUsers()) {
			if(this.user.getLogin().equals(u.getLogin())) {
				if(u.getPass().equals(EncryptUtil.md5(this.user.getPass()))) {
					HttpSession hs = SessionUtil.getSession();
					hs.setAttribute("login", this.user.getLogin());
					FacesContext.getCurrentInstance().
					getExternalContext().redirect("indexart.xhtml");
					
				} else {
					this.msg = "Password not correct";
				}
			} else {
				this.msg = "Account Invalid";

			}
		}
	}
	//Envoyer vers la page LOGOUT
    public void logout() throws IOException {
		HttpSession hs = SessionUtil.getSession();
		hs.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		
	}
}
