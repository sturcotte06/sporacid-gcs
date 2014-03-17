package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product_comment")
@SequenceGenerator(name = "product_comment_id_seq", sequenceName = "product_comment_id_seq", allocationSize = 1)
public class ProductComment extends AbstractModelObject
{	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_comment_id_seq")
	@Column(name = "id")
	private int id;
	
	@Column(name = "product_id")
	private int productId;

	@Column(name = "appreciation")
	private Integer appreciation;
	
	@Column(name = "comment")
	private String comment;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productId)
	{
		this.productId = productId;
	}
	
	public Integer getAppreciation()
	{
		return appreciation;
	}

	public void setAppreciation(Integer appreciation)
	{
		this.appreciation = appreciation;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
