package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Category;
import model.Task;
import services.CategoriesService;
import services.TasksService;
import javafx.event.ActionEvent;

public class AddTaskController implements Initializable{
	@FXML
	private TextField txtDescription = new TextField();
	
	@FXML
	private TextField txtSubDescription = new TextField();
	
	@FXML
	private ChoiceBox<String> choiceStatus = new ChoiceBox<String>();
	
	@FXML 
	private DatePicker datePicker = new DatePicker();
	
	@FXML
	private ChoiceBox<String> choiceCategory = new ChoiceBox<String>();
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
	}
	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtDescription, 60);
		Constraints.setTextFieldMaxLength(txtSubDescription, 150);
		choiceStatus.getItems().addAll("Pendente", "Concluido", "Indefinido");
		List<Category> categories = CategoriesService.findAll();
		for(int i = 0; i < categories.size(); i++) {
			choiceCategory.getItems().add(categories.get(i).getCategory());
		}
		
	
	}
	
	public void onSelectStatus(ActionEvent event) {
		
	}
	public void onSelectCategory(ActionEvent event) {
		
	}
	
	public void onConfirmButtonClicked() {
		Task task = new Task(null, txtDescription.getText(), txtSubDescription.getText(), choiceStatus.getValue().toString(), datePicker.getValue().toString(), choiceCategory.getValue().toString());
		TasksService.insertTask(task);
		
	}
	

}
