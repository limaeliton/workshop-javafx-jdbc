package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	// retorna um número inteiro válido.
public static Integer tryParseToInt(String str) {
	try {
		return Integer.parseInt(str);
	}
	catch(NumberFormatException error) {
		return null;
	}
}

}