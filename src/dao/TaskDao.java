package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Task;
import services.CategoriesService;

public class TaskDao {
	
	private Connection conn;
	public TaskDao (Connection conn) {
		this.conn = conn;
	}
	public List<Task> findAll(){
		List<Task> list = new ArrayList<Task>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT id, description, sub_description, status, max_date, category FROM tasks LEFT JOIN categories on categoryId = categories.category_id left join status on statusId = status.status_id ORDER BY status DESC");
			rs = st.executeQuery();
			while(rs.next()) {
				Task task = new Task(rs.getInt("id"), rs.getString("description"), rs.getString("sub_description"), rs.getString("status"), rs.getString("max_date"), rs.getString("category"));
				list.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insertTask(Task task) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tasks (id, description, sub_description, statusId, max_date, categoryId) VALUES (?, ?, ?, ? ,?, ?)");
			st.setNull(1, 0);
			st.setString(2, task.getDescription());
			st.setString(3, task.getSubDescription());
			st.setInt(4, getConvertedStatusToInt(task.getStatus()));
			st.setString(5, task.getMaxDate());
			st.setInt(6, getConvertedCategoryToInt(task.getCategory()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void editTask(Task selectedTask, Task editedTask) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tasks SET description = ?, sub_description = ?, statusId = ?, max_date = ?, categoryId = ? WHERE id = ?");
			st.setString(1, editedTask.getDescription());
			st.setString(2, editedTask.getSubDescription());
			st.setInt(3, getConvertedStatusToInt(editedTask.getStatus()));
			st.setString(4, editedTask.getMaxDate());
			st.setInt(5, getConvertedCategoryToInt(editedTask.getCategory()));
			st.setInt(6, selectedTask.getId());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void deleteTask(Task task) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tasks WHERE id =?");
			st.setInt(1, task.getId());
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void changeTaskStatus(Task task, String newStatus) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tasks SET statusId = ? WHERE id = ?");
			st.setInt(1, getConvertedStatusToInt(newStatus));
			st.setInt(2, task.getId());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	private Integer getConvertedStatusToInt(String status) {
		switch (status) {
		case "Pendente": {
			
			return 2;
		}
		case "Concluido": {
			
			return 1;
		}
		case "Indefinido": {
			
			return 3;
		}
		default:
			return 2;
		}
		
	}
	private Integer getConvertedCategoryToInt(String category) {
		List<Category> allCategories = CategoriesService.findAll();
		Integer result = 1;
		for(int i = 0; i< allCategories.size(); i++) {
			if(allCategories.get(i).getCategory().equals(category)) {
				result = allCategories.get(i).getId();
			}
		}
		return result;	
	}
}
