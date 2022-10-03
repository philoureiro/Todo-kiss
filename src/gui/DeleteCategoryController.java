package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import model.Category;
import services.CategoriesService;

public class DeleteCategoryController implements Initializable{
	
	@FXML
	private ChoiceBox<String> choiceCategory;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<Category> categories = CategoriesService.findAll();
		for(int i = 0; i < categories.size(); i++) {
			choiceCategory.getItems().add(categories.get(i).getCategory());
		}
		
		
	}
	public void onConfirmButtonClicked() {
		String categoryName = choiceCategory.getValue().toString();
		CategoriesService.deleteCategory(categoryName);// retorna boolean
	
	}
	

}
