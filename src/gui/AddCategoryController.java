package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Category;
import services.CategoriesService;

public class AddCategoryController implements Initializable {

	@FXML
	private TextField txtCategoryName = new TextField();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
			
		
		
	}
	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtCategoryName, 30);
	}
	public void onConfirmButtonClick() {
		Category category = new Category(null,txtCategoryName.getText());
		CategoriesService.insertCategory(category);
	}

	
}
