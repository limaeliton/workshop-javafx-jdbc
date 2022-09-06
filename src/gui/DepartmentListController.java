package gui;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable , DataChangeListener {
	
	//Depência
	private DepartmentService service;
	
	
	@FXML //@FXML para associar com a tela
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	// carrega os departamentos
	private ObservableList<Department> obsList;
	
	@FXML
	// para ter referência para o controle que recebeu o evento.
	public void onBtNewAction(javafx.event.ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Department obj = new Department();
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
	}
	
	// injetar dependência
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}



	// inicia o comportamento das colunas
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// faz com que a janela abra completa
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		// recupera os departamentos do serviço
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
	}
	
	// função para carregar a janela do formulário e preencher um novo departamento.
	private void createDialogForm(Department obj, String absoluteName, Stage parentStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			DepartmetFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.setDepartmentService(new DepartmentService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane)); // o elemento raiz da Scene
			dialogStage.setResizable(false); // a janela não vai ser redmensionada
			dialogStage.initOwner(parentStage); // o Stage pai da janela
			dialogStage.initModality(Modality.WINDOW_MODAL); // a janela vai ficar travada, enquanto não fechar não acessa a janela anterior
			dialogStage.showAndWait();
			
			
		}
		catch(IOException error) {
			Alerts.showAlert("IO Exception", "Error loading view", error.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}

}
