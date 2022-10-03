package model;

public class Status {
	private Integer status_id = null;
	private String status;
	
	
	public Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public Integer getId() {
		return status_id;
	}

}
