package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private TextField getUsername;
	@FXML
	private PasswordField getPassword;
	
	@FXML
	public void onLogin(ActionEvent e) {
		checkFiles();
	}
	
	@FXML
	public void onRegister(ActionEvent e) throws IOException {
		Parent Parent = FXMLLoader.load(getClass().getResource("register.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(Parent));
		stage.setResizable(false);
		stage.alwaysOnTopProperty();
		stage.show();
		
		((Node) (e.getSource())).getScene().getWindow().hide();
	}
	
	public void checkFiles() {
		String ourNodeName = "/com/" + getUsername.getText();
		Preferences pref = Preferences.userRoot().node(ourNodeName);
		
		String xmlUser = pref.get("Username", "root");
		String xmlPass = pref.get("Password", "root");
		
		if (xmlUser.equals(getUsername.getText())) {
			System.out.print("Username Correct");
			if (xmlPass.equals(getPassword.getText())) {
				System.out.println("Password Correct");
			}
		}
}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
