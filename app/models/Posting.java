package models;

import play.db.jpa.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Posting extends Model {

	@ManyToOne
	public User creator;
	public String subject;
	public String description;
	public Date creationDate;

	public Posting(User creator, String subject, String description) {
		this.creator = creator;
		this.subject = subject;
		this.description = description;
		this.creationDate = new Date();
	}

}