package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.UploadErrorException;

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

public class RegisterController implements Initializable{

	@FXML
	private TextField regUser;
	@FXML
	private PasswordField regPass;
	@FXML
	private PasswordField regPassChk;
	
	@FXML
	public void onRegister(ActionEvent e) throws IOException, InterruptedException, UploadErrorException, DbxException {
		
		makeFiles();
		TimeUnit.SECONDS.sleep(1);
		pushValues();
		DbxServer.filesUpload(regUser.getText());
		
			Parent Parent = FXMLLoader.load(getClass().getResource("login.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(Parent));
			stage.setResizable(false);
			stage.alwaysOnTopProperty();
			stage.show();
			
			((Node) (e.getSource())).getScene().getWindow().hide();
	}
	
	public void makeFiles() throws IOException {
		String fileName = regUser.getText();
		File file = new File("" + fileName);

		file.createNewFile();
		
	}
	
	public void pushValues() {
		String ourNodeName = "/com/" + regUser.getText();
		Preferences pref = Preferences.userRoot().node(ourNodeName);
		
		String user = regUser.getText();
		String pass = regPass.getText();
		String passChk = regPassChk.getText();
		
		if (pass.equals(passChk)) {
			pref.put("Username", user);
			pref.put("Password", pass);
			
			try {
	            pref.exportNode(new FileOutputStream(user));
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (BackingStoreException e) {
	            e.printStackTrace();
	        }
			
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
