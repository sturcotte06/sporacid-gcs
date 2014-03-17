package gcs.webservices.services;


/*@Component
@Path("/products")*/
public class ProductsService
{
	/**
	 * Data access object for products CRUD operations.
	 */
	/*@InjectParam
	private IProductsDAO productsDao;*/
	
	/**
	 * Returns all products for the category
	 * @param categoryId	Id for the category
	 * @return	A collection of all products for the category
	 */
	/*@GET @Path("/get-all-products")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllProducts(@InjectParam GetAllProductRequest request)
	{
		GetAllProductsResponse responseEntity = new GetAllProductsResponse();
		Collection<Product> products = null;
		
		if (request != null && request.getCategoryId() > 0) {
			// Category id is defined, return all products for that category
			products = productsDao.getAllProducts(request.getCategoryId());
		} else {
			// Category id is not defined, return all products
			products = productsDao.getAllProducts();
		}
		
		if (products != null) {
			responseEntity.setProducts(products);
			responseEntity.getMessages().add("All products were fetched successfully.");
			responseEntity.setSuccess(true);
		} else {
			responseEntity.getMessages().add("The products could not be fetched.");
			responseEntity.setSuccess(false);
		}
		
		return Response.ok().entity(responseEntity).build();
	}*/
	
	/**
	 * Returns the product with the specified id.
	 * @param productId	Id of the product
	 * @return			The product with the specified id
	 */
	/*@GET @Path("/get-product")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProduct(@InjectParam GetProductRequest request)
	{
		GetProductResponse responseEntity = new GetProductResponse();
		Product product = null;
		
		if(request != null) {
			product = productsDao.getProduct(request.getProductId());
		}
		
		if (product != null) {
			responseEntity.setProduct(product);
			responseEntity.getMessages().add("The product was fetched successfully.");
			responseEntity.setSuccess(true);
		} else {
			responseEntity.getMessages().add("The product could not be fetched.");
			responseEntity.setSuccess(false);
		}
		
		return Response.ok().entity(responseEntity).build();
	}*/
	
	/**
	 * 
	 * @param categoryId
	 * @param skip
	 * @param take
	 * @return
	 */
	/*@GET @Path("/get-products")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProducts(@InjectParam GetProductsRequest request)
	{
		GetProductsResponse responseEntity = new GetProductsResponse();
		Collection<Product> products = null;
		
		if (request != null && request.getCategoryId() > 0) {
			// Category id is defined, return products for that category
			products = productsDao.getProducts(request.getCategoryId(), request.getSkip(), request.getTake());
		} else {
			// Category id is not defined, return products
			products = productsDao.getProducts(request.getSkip(), request.getTake());
		}
		
		if (products != null) {
			responseEntity.setProducts(products);
			responseEntity.getMessages().add("All products were fetched successfully.");
			responseEntity.setSuccess(true);
		} else {
			responseEntity.getMessages().add("The products could not be fetched.");
			responseEntity.setSuccess(false);
		}
		
		return Response.ok().entity(responseEntity).build();
	}*/
	
	/*@GET @Path("/get-product-count")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductCount(@InjectParam GetProductCountRequest request)
	{
		GetProductCountResponse responseEntity = new GetProductCountResponse();
		int productCount = -1;
		
		if (request != null && request.getCategoryId() > 0) {
			productCount = productsDao.getProductCount(request.getCategoryId());
		} else {
			productCount = productsDao.getProductCount();
		}
		
		if (productCount > 0) {
			responseEntity.setProductCount(productCount);
			responseEntity.getMessages().add("The product count was fetched successfully.");
			responseEntity.setSuccess(true);
		} else {
			responseEntity.getMessages().add("The product count could not be fetched.");
			responseEntity.setSuccess(false);
		}
		
		return Response.ok().entity(responseEntity).build();
	}*/
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	/*@GET @Path("/get-product-comments")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductComments(@InjectParam GetProductCommentsRequest request)
	{
		GetProductCommentsResponse responseEntity = new GetProductCommentsResponse();
		Collection<ProductComment> comments = null;

		if (request != null) {
			// Category id is defined, return products for that category
			comments = productsDao.getProductComments(request.getProductId(), request.getSkip(), request.getTake());
		}
		
		if (comments != null) {
			responseEntity.setComments(comments);
			responseEntity.getMessages().add("All comments were fetched successfully.");
			responseEntity.setSuccess(true);
		} else {
			responseEntity.getMessages().add("The comments could not be fetched.");
			responseEntity.setSuccess(false);
		}
		
		return Response.ok().entity(responseEntity).build();
	}
	*/
	/**
	 * 
	 * @param request
	 * @return
	 */
/*	@POST @Path("/add-product-comment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addProductComment(AddProductCommentRequest request)
	{
		webservices.services.beans.responses.Response responseEntity = 
				new webservices.services.beans.responses.Response();
		Integer addedCommentId = null;
		
		if (request != null) {
			addedCommentId = productsDao.addProductComment(request.getComment());
		}
		
		if (addedCommentId != null) {
			responseEntity.getMessages().add("The comment was added successfully.");
			responseEntity.setSuccess(true);
		} else {
			responseEntity.getMessages().add("The comment could not be added.");
			responseEntity.setSuccess(false);
		}
		
		return Response.ok().entity(responseEntity).build();
	}
*/	
	/**
	 * 
	 * @param request
	 * @return
	 */
	/*@GET @Path("/get-product-categories")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProductCategories(@InjectParam GetProductCategoriesRequest request)
	{
		GetProductCategoriesResponse responseEntity = new GetProductCategoriesResponse();
		Collection<ProductCategory> categories = null;

		if (request != null) {
			// Category id is defined, return products for that category
			categories = productsDao.getProductCategories(request.getParentCategoryId());
		}
		
		if (categories != null) {
			responseEntity.setCategories(categories);
			responseEntity.getMessages().add("All categories were fetched successfully.");
			responseEntity.setSuccess(true);
		} else {
			responseEntity.getMessages().add("The categories could not be fetched.");
			responseEntity.setSuccess(false);
		}
		
		return Response.ok().entity(responseEntity).build();
	}*/
}
