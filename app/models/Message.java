package models;

import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message extends Model {
    @Id
    public Long id;
    @Constraints.Required
    public String name;
    public String mail;
    public String message;

    @Column(name = "postdate")
    @CreatedTimestamp
    public Date postdate;

    @Override
    public String toString(){
        return "id: " + id
                + ", name: " + name
                + ", mail: " + mail
                + ", message: " + message
                + ", postdate: " + postdate;
    }
}
