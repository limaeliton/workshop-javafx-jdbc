package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	@FXML // //@FXML para associar com a tela
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}
	
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	
		
	}
	// synchronized garante que o processamento não será interrompido.
	private  synchronized void loadView(String absoluteName)   {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newBox = loader.load();
			
			// pega a referência.
			Scene mainScene = Main.getMainScene();
			VBox  mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
			
			// Guarda um referência para o menu. inclui os filhos da janela aberta.
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newBox.getChildren());
			
		}
		catch(IOException error) {
			Alerts.showAlert("IO Exception", "Error loading view", error.getMessage(), AlertType.ERROR);
		}
		
	}
	
	
}
