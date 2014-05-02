package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "audits")
@SequenceGenerator(name = "audits_id_seq", sequenceName = "audits_id_seq", allocationSize = 1)
public class Audit extends AbstractModelObject
{
   @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audits_id_seq")
   @Column(name = "id") 
   @Getter @Setter
   private int id;

   @Column(name = "username")
   @Getter @Setter
   private String username;
   
   @Column(name = "timestamp")
   @Getter @Setter
   private Date timestamp;
   
   @Column(name = "message")
   @Getter @Setter
   private String message;
   
   /*public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public void setTimestamp(Date timestamp)
   {
      this.timestamp = timestamp;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public String getUsername()
   {
      return username;
   }

   public Date getTimestamp()
   {
      return timestamp;
   }

   public String getMessage()
   {
      return message;
   }*/
}
