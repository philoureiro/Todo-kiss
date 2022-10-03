package services;

import java.util.List;

import dao.CategoryDao;
import dao.DaoFactory;
import model.Category;

public class CategoriesService {
	private static CategoryDao categoryDao = DaoFactory.createCategoryDao();
	
	public static void insertCategory(Category category) {
		categoryDao.insertCategory(category);
	}
	
	public static List<Category> findAll(){
		return categoryDao.getAll();
	}
	
	public static boolean deleteCategory(String name) {
		return categoryDao.deleteCategory(name);
	}
}
