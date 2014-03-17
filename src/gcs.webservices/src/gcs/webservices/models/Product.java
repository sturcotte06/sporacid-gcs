package gcs.webservices.models;

import gcs.webapp.utils.hibernate.AbstractModelObject;

import javax.persistence.CascadeType;
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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "product_product")
@Where(clause = "(select pt.categ_id from product_template pt where pt.id = product_tmpl_id) " +
				"!= (select pc.id from product_category pc where pc.name = 'Autres')")
@SequenceGenerator(name = "product_product_id_seq", sequenceName = "product_product_id_seq", allocationSize = 1)
public class Product extends AbstractModelObject
{	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_product_id_seq")
	@Column(name = "id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "product_tmpl_id", 
		referencedColumnName = "id",
		nullable = false)
	private ProductTemplate template;
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "product_id", 
		nullable = false)
	private Set<ProductComment> comments;*/

	@Formula("(select avg(pc.appreciation) from product_comment pc where pc.product_id = id)")
	private Double meanAppreciation;
	
	@Column(name = "default_code")
	private String code;
	
	@Column(name = "price_extra")
	private double priceExtra;

	@Column(name = "active")
	private boolean active;
	
	@Column(name = "product_img_url")
	private String productImageUrl;
	
	@Column(name = "amazon_product_id")
	private String amazonProductId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public ProductTemplate getTemplate()
	{
		return template;
	}

	public void setTemplate(ProductTemplate template)
	{
		this.template = template;
	}

	public Double getMeanAppreciation()
	{
		return meanAppreciation;
	}

	public void setMeanAppreciation(Double meanAppreciation)
	{
		this.meanAppreciation = meanAppreciation;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public double getPriceExtra()
	{
		return priceExtra;
	}

	public void setPriceExtra(double priceExtra)
	{
		this.priceExtra = priceExtra;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public String getProductImageUrl()
	{
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl)
	{
		this.productImageUrl = productImageUrl;
	}

	public String getAmazonProductId()
	{
		return amazonProductId;
	}

	public void setAmazonProductId(String amazonProductId)
	{
		this.amazonProductId = amazonProductId;
	}
}
