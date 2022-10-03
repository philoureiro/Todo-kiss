package gui;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Task;
import services.TasksService;

public class MainViewController implements Initializable {
	// START DECLARATIONS
	// START TOP BUTTONS DECLARATIONS
	@FXML
	private MenuBar menuBar = new MenuBar();
	@FXML
	private MenuItem tasksButton = new MenuItem("Tasks");

	@FXML
	private MenuItem addNewTaskButton = new MenuItem("Adicionar task");

	@FXML
	private MenuItem addNewCategoryButton = new MenuItem("Adicionar categoria");

	@FXML
	private MenuItem deleteCategoryButton = new MenuItem("Excluir categoria");
	// END TOP BUTTONS DECLARATIONS
	// START TABLEVIEW DECLARATIONS
	@FXML
	private TableView<Task> tasksTableView = new TableView<Task>();

	@FXML
	private TableColumn<Task, String> descriptionTableColumn = new TableColumn<Task, String>("Descrição");

	@FXML
	private TableColumn<Task, String> subDescriptionTableColumn = new TableColumn<Task, String>("Sub Descrição");

	@FXML
	private TableColumn<Task, String> statusTableColumn = new TableColumn<Task, String>("Status");

	@FXML
	private TableColumn<Task, String> maxDateTableColumn = new TableColumn<Task, String>("Data");

	@FXML
	private TableColumn<Task, String> categoryTableColumn = new TableColumn<Task, String>("Categoria");

	// END TABLEVIEW DECLARATIONS
	// END DECLARATIONS
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initNodes();

	}

	
	@SuppressWarnings("unchecked")
	public void initNodes() {
		descriptionTableColumn.setMinWidth(150);
		subDescriptionTableColumn.setMinWidth(200);
		statusTableColumn.setMinWidth(90);
		maxDateTableColumn.setMinWidth(90);
		categoryTableColumn.setMinWidth(90);
		tasksTableView.minWidth(800);
		descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
		subDescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("subDescription"));
		statusTableColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
		maxDateTableColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("maxDate"));
		categoryTableColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("category"));
		menuBar.setPrefWidth(1113);
		tasksTableView.getColumns().addAll(descriptionTableColumn, subDescriptionTableColumn,
		statusTableColumn, maxDateTableColumn, categoryTableColumn);
		updateTableViewData();
		addOptionsBoxToTable();
	}

	public void updateTableViewData() {
		ObservableList<Task> objList = FXCollections.observableArrayList(TasksService.findAll());
		tasksTableView.setItems(objList);
		tasksTableView.setMinHeight(400);
		
	}
	private void addOptionsBoxToTable() {
		TableColumn<Task, Void> optionsColumn = new TableColumn<Task, Void>("Opções");
		//optionsColumn.setMinWidth(380);
		Callback<TableColumn<Task, Void>, TableCell<Task, Void>> cellFactory = new Callback<TableColumn<Task, Void>, TableCell<Task, Void>>() {
			@Override
			public TableCell<Task, Void> call(final TableColumn<Task, Void> param) {
				final TableCell<Task, Void> cell = new TableCell<Task, Void>() {
					HBox optionsHBox = new HBox();
					HBox teste = new HBox();
					HBox testee = new HBox();
					
					ChoiceBox<String> choiceStatus = new ChoiceBox<String>();
					Button editTaskButton = new Button("Editar tarefa");
					Button deleteTaskButton = new Button("Excluir tarefa");
					{
						try {
							choiceStatus.getItems().addAll("Concluido", "Pendente", "Indefinido");
							choiceStatus.setMinWidth(160);
							teste.setMinWidth(10);
							testee.setMinWidth(10);
							editTaskButton.setStyle("-fx-background-color: blue");
							deleteTaskButton.setStyle("-fx-background-color: red");
							editTaskButton.setTextFill(Color.rgb(255,  255,  255));
							deleteTaskButton.setTextFill(Color.rgb(255, 255, 255));
							optionsHBox.getChildren().add(choiceStatus);
							optionsHBox.getChildren().add(teste);
							optionsHBox.getChildren().add(editTaskButton);
							optionsHBox.getChildren().add(testee);
							optionsHBox.getChildren().add(deleteTaskButton);						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					{
						editTaskButton.setOnAction((ActionEvent) -> {
							Task task = getTableView().getItems().get(getIndex());
							EditTaskController.setSelectedTask(task);
							loadView("/gui/EditTask.fxml");																
						});
						deleteTaskButton.setOnAction((ActionEvent) -> {
							Task task = getTableView().getItems().get(getIndex());
							TasksService.deleteTask(task);
							updateTableViewData();

						});
						choiceStatus.setOnAction((ActionEvent) -> {
							Task task = getTableView().getItems().get(getIndex());
							String status = choiceStatus.getValue();
							TasksService.changeTaskStatus(task, status);
							updateTableViewData();
						});
					}
					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							Task task = getTableView().getItems().get(getIndex());
							choiceStatus.setValue(task.getStatus());
							choiceStatus.setStyle(defineChoiceColor(task.getStatus()));
							setGraphic(optionsHBox);
						}
					}
				};
				return cell;
			}
		};

		optionsColumn.setCellFactory(cellFactory);
		tasksTableView.getColumns().add(optionsColumn);

	}
	private String defineChoiceColor(String status) {
		switch (status) {
		case "Pendente": {
			return "-fx-background-color: yellow";	
		}
		case "Concluído": {
			return "-fx-background-color: lightgreen";	
		}
		default:
			return "-fx-background-color: white";
		}
		
	}

	public void onTasksButtonClicked() {
		loadMainView();
	}

	public void onAddNewTaskButtonClicked() {
		loadView("/gui/AddTask.fxml");
	}

	public void onAddNewCategoryButtonClicked() {
		loadView("/gui/AddCategory.fxml");
	}

	public void onDeleteCategoryButtonClicked() {
		loadView("/gui/DeleteCategory.fxml");
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

	private void loadView(String absolutePath) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutePath));
			VBox addTaskVBox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(addTaskVBox.getChildren());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
