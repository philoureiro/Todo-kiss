package dao;

import db.DB;

public class DaoFactory {

	public static TaskDao createTaskDao() {
		return new TaskDao(DB.getConnection());
	}
	public static CategoryDao createCategoryDao() {
		return new CategoryDao(DB.getConnection());
	}
}
