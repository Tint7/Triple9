package com.solt.triplenine;
	
import com.solt.triplenine.model.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TripleNineMain extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(LoginViewController.class.getResource("LoginView.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
		stage.setResizable(false);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
