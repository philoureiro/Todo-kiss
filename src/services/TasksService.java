package services;


import java.util.List;
import dao.DaoFactory;
import dao.TaskDao;
import model.Task;

public class TasksService {
	private static TaskDao taskDao = DaoFactory.createTaskDao();
	
	public static List<Task> findAll(){
		return taskDao.findAll();
	}
	public static void insertTask(Task task) {
		taskDao.insertTask(task);
		
	}
	public static void editTask(Task selectedTask, Task editedTask) {
		taskDao.editTask(selectedTask, editedTask);
	}
	public static void deleteTask(Task task) {
		taskDao.deleteTask(task);
	}
	public static void changeTaskStatus(Task task, String status) {
		taskDao.changeTaskStatus(task, status);
	}
}
