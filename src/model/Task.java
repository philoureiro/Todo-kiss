package model;

public class Task {

	private Integer id;
	private String description;
	private String subDescription;
	private String status;
	private String maxDate;
	private String category;
	
	public Task(Integer id, String description, String subDescription, String status, String maxDate, String category) {
		this.id = id;
		this.description = description;
		this.subDescription = subDescription;
		this.status = status;
		this.maxDate = maxDate;
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public String getSubDescription() {
		return subDescription;
	}

	public String getStatus() {
		return status;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public String getCategory() {
		return category;
	}

	public Integer getId() {
		return id;
	}
}
