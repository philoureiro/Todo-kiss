package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Category;
import model.Task;
import services.CategoriesService;
import services.TasksService;

public class EditTaskController implements Initializable{
	private static Task selectedTask = new Task(0, "", "", "2022-01-01", "Pendente", "Geral");
	
	@FXML
	private TextField txtDescription = new TextField();
	@FXML
	private TextField txtSubDescription = new TextField();
	@FXML
	private ChoiceBox<String> choiceStatus = new ChoiceBox<String>();
	@FXML
	private DatePicker maxDate = new DatePicker();
	@FXML
	private ChoiceBox<String> choiceCategory = new ChoiceBox<String>();
	@FXML
	private Button confirmButton = new Button("Confirmar");
	@FXML
	private Button cancelButton = new Button("Cancelar");


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
	}
	private void initializeNodes() {
		choiceStatus.getItems().addAll("Pendente", "Concluído", "Indefinido");
		List<Category> all = CategoriesService.findAll();	
		all.forEach((Consumer) -> {
			choiceCategory.getItems().add(Consumer.getCategory());
		});
		Constraints.setTextFieldMaxLength(txtDescription, 60);
		Constraints.setTextFieldMaxLength(txtSubDescription, 150);
		txtDescription.setText(selectedTask.getDescription());
		txtSubDescription.setText(selectedTask.getSubDescription());
		choiceStatus.setValue(selectedTask.getStatus());
		LocalDate s = LocalDate.parse(selectedTask.getMaxDate());
		maxDate.setValue(s);
		choiceCategory.setValue(selectedTask.getCategory());
	}
	public static void setSelectedTask(Task task) {
		selectedTask = task;
	}
	
	public void onConfirmButtonClicked() {
		Task editedTask = new Task(selectedTask.getId(), txtDescription.getText(), txtSubDescription.getText(), choiceStatus.getValue().toString(),maxDate.getValue().toString(), choiceCategory.getValue().toString());
		TasksService.editTask(selectedTask, editedTask);
		loadMainView();
	}
	public void onCancelButtonClicked() {
		loadMainView();
	}
	public void loadMainView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			VBox mainVBox = (VBox) scrollPane.getContent();

			Scene mainScene = Main.getMainScene();
			VBox actualVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			actualVBox.getChildren().clear();
			actualVBox.getChildren().addAll(mainVBox.getChildren());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
