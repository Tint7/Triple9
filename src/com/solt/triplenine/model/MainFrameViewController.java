package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainFrameViewController implements Initializable{

    @FXML
    private Label title;

    @FXML
    private StackPane stackpane;
    
    @FXML
    private HBox bkmember;

    @FXML
    private HBox bkcategory;

    @FXML
    private HBox bkstorelist;

    @FXML
    private HBox bkstoreseeds;

    @FXML
    private HBox bksellseeds;

    @FXML
    private HBox bkexportseeds;
    private final Background focusBackground= new Background(new BackgroundFill(Color.web("#b3dff9"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background unfocusBackground= new Background(new BackgroundFill(Color.web("#9cd7fb"), CornerRadii.EMPTY, Insets.EMPTY));
  
    @FXML
    void category() {
    	loadView("CategoryView.fxml", "ကုန်အမျိးုအစားများ");
    	bkmember.setBackground(unfocusBackground);
    	bkcategory.setBackground(focusBackground);
    	bkstorelist.setBackground(unfocusBackground);
    	bkstoreseeds.setBackground(unfocusBackground);
    	bksellseeds.setBackground(unfocusBackground);
    	bkexportseeds.setBackground(unfocusBackground);
    }
    @FXML
    void exit() {
    	Platform.exit();
    }
    @FXML
    void exportseeds() {
    	loadView("ExportSeedsView.fxml", "တင်ပို ့ကုန်များ");
    	bkmember.setBackground(unfocusBackground);
    	bkcategory.setBackground(unfocusBackground);
    	bkstorelist.setBackground(unfocusBackground);
    	bkstoreseeds.setBackground(unfocusBackground);
    	bksellseeds.setBackground(unfocusBackground);
    	bkexportseeds.setBackground(focusBackground);
    }
    @FXML
    void member() {
    	loadView("MemberView.fxml", "ဝန်ထမ်းများ");
    	bkmember.setBackground(focusBackground);
    	bkcategory.setBackground(unfocusBackground);
    	bkstorelist.setBackground(unfocusBackground);
    	bkstoreseeds.setBackground(unfocusBackground);
    	bksellseeds.setBackground(unfocusBackground);
    	bkexportseeds.setBackground(unfocusBackground);
    }
    @FXML
    void sellseeds() {
    	loadView("SellSeedsView.fxml", "‌ရောင်းကုန်များ");
    	bkmember.setBackground(unfocusBackground);
    	bkcategory.setBackground(unfocusBackground);
    	bkstorelist.setBackground(unfocusBackground);
    	bkstoreseeds.setBackground(unfocusBackground);
    	bksellseeds.setBackground(focusBackground);
    	bkexportseeds.setBackground(unfocusBackground);
    }
    @FXML
    void storelist() {
    	loadView("StoreListView.fxml", "အိတ်ခွင်စာရင်းများ");
    	bkmember.setBackground(unfocusBackground);
    	bkcategory.setBackground(unfocusBackground);
    	bkstorelist.setBackground(focusBackground);
    	bkstoreseeds.setBackground(unfocusBackground);
    	bksellseeds.setBackground(unfocusBackground);
    	bkexportseeds.setBackground(unfocusBackground);
    }
    @FXML
    void storeseeds() {
    	loadView("StoreSeedsView.fxml", "အပ်ကုန်များ");
    	bkmember.setBackground(unfocusBackground);
    	bkcategory.setBackground(unfocusBackground);
    	bkstorelist.setBackground(unfocusBackground);
    	bkstoreseeds.setBackground(focusBackground);
    	bksellseeds.setBackground(unfocusBackground);
    	bkexportseeds.setBackground(unfocusBackground);
    }
    public static void showview() {
    	try {
    		Parent root = FXMLLoader.load(MainFrameViewController.class.getResource("MainFrameView.fxml"));
        	Stage stage = new Stage();
        	stage.setScene(new Scene(root));
        	stage.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public void loadView(String viewname, String title) {
    	
		try {
			Parent view = FXMLLoader.load(getClass().getResource(viewname));
			stackpane.getChildren().clear();
	    	stackpane.getChildren().add(view);
	    	this.title.setText("");
	    	this.title.setText(title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadView("MemberView.fxml", "ဝန်ထမ်းများ");
		bkmember.setBackground(focusBackground);
		
	}
}
