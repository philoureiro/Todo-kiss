package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.Category;

public class CategoryDao {

	private Connection conn = null;
	public CategoryDao(Connection conn) {
		this.conn = conn;
	}
	
	public void insertCategory(Category category) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO categories (category_id, category) VALUES (?, ?)");
			st.setNull(1, 0);
			st.setString(2, category.getCategory());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
		}
	}
	public List<Category> getAll(){
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Category> all = new ArrayList<Category>();
		try {
			st = conn.prepareStatement("SELECT * FROM categories");
			rs = st.executeQuery();
			while(rs.next()) {
				Category category = new Category(rs.getInt("category_id"),rs.getString("category"));
				all.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return all;
	}
	public boolean deleteCategory(String name) {
		PreparedStatement stFindExists = null;
		ResultSet rsFindExists = null;
		PreparedStatement stExecuteDelete = null;
		List<Category> isCategoryAlreadyExists = new ArrayList<Category>();
		try {
			stFindExists = conn.prepareStatement("SELECT * from tasks LEFT JOIN categories on categoryId = categories.category_id WHERE category =?");
			stFindExists.setString(1, name);
			rsFindExists = stFindExists.executeQuery();
			while(rsFindExists.next()) {
				Category category = new Category(rsFindExists.getInt("category_id"),rsFindExists.getString("category"));
				isCategoryAlreadyExists.add(category);
			}
			if(isCategoryAlreadyExists.size() == 0) {
				stExecuteDelete = conn.prepareStatement("DELETE FROM categories WHERE category =?");
				stExecuteDelete.setString(1, name);
				stExecuteDelete.executeUpdate();
				return true;
			}else {
				return false;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(stFindExists);
			DB.closeResultSet(rsFindExists);
			DB.closeStatement(stExecuteDelete);
		}
		return false;
	}


}
