package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Where(clause = "parent_id is not null")
@Table(name = "product_category")
@SequenceGenerator(name = "product_category_id_seq", sequenceName = "product_category_id_seq", allocationSize = 1)
public class ProductCategory extends AbstractModelObject
{
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_id_seq")
	@Column(name = "id")
	private int id;
	
	/*@OneToOne @Fetch(FetchMode.JOIN)	
	@JoinColumn(name = "parent_id", 
		referencedColumnName = "id",
		nullable = true)
	private ProductCategory parentCategory;*/
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	/*public ProductCategory getParentCategory()
	{
		return parentCategory;
	}

	public void setParentCategory(ProductCategory parentCategory)
	{
		this.parentCategory = parentCategory;
	}*/
	
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
}
