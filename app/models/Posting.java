package models;

import controllers.Application;
import play.db.jpa.Model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Posting extends Model {

	@ManyToOne
	public User creator;
	@Enumerated(EnumType.STRING)
	public Category category;
	public String subject;
	public String description;
	public Date creationDate;
	public UUID token;
	public boolean activated;

	public Posting(User creator, Category category, String subject, String description) {
		this.creator = creator;
		this.category = category;
		this.subject = subject;
		this.description = description;
		this.token = UUID.randomUUID();
		this.creationDate = new Date();
		this.activated = false;
	}

}