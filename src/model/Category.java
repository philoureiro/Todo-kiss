package model;

public class Category {

	private Integer category_id;
	private String category;
	
	public Category(Integer id,String category) {
		this.category_id = id;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
	public Integer getId(){
		return category_id;
	}
}
