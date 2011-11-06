package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Posting extends GenericModel {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String id;

    @ManyToOne
    public User creator;

    @Enumerated(EnumType.STRING)
    public Category category;

    public String subject;

    public String description;

    @Temporal(TemporalType.DATE)
    public Date creationDate = new Date();

    public boolean activated;

    public Posting(User creator, Category category, String subject, String description) {
        this.creator = creator;
        this.category = category;
        this.subject = subject;
        this.description = description;
        this.activated = false;
    }

}