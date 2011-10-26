package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class User extends Model {

	public String eMail;
	public String displayName;

	public User(String eMail, String displayName) {
		this.eMail = eMail;
		this.displayName = displayName;
	}
}