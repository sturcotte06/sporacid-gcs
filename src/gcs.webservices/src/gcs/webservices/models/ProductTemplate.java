package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "product_template")
@SequenceGenerator(name = "product_template_id_seq", sequenceName = "product_template_id_seq", allocationSize = 1)
public class ProductTemplate extends AbstractModelObject
{
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_template_id_seq")
	@Column(name = "id")
	private int id;
	
	@OneToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "categ_id", 
		referencedColumnName = "id",
		nullable = false)
	private ProductCategory category;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "list_price")
	private double price;
	
	public ProductCategory getCategory()
	{
		return category;
	}

	public void setCategory(ProductCategory category)
	{
		this.category = category;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
}
