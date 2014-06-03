package gcs.webservices.client.requests.commandites;

public class DeleteRequest 
{
	private Integer id;
	
	public DeleteRequest(){}
	
	public DeleteRequest(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
