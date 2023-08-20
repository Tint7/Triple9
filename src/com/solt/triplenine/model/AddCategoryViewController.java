package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.Category;
import com.solt.triplenine.service.CategoryService;
import com.solt.triplenine.util.Reloader;
import com.solt.triplenine.util.ShowAlert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddCategoryViewController implements Initializable {

    @FXML
    private TextField addcategoryname;

    @FXML
    private TextField addcategoryprice;

    @FXML
    private Reloader reload;
    private CategoryService csrv;
       
    @FXML
    void addcategoryadded() {
    	 if (!addcategoryname.getText().isEmpty()&&addcategoryname.getText()!=null) {
 	   		Category category = new Category();
 	   		category.setCdate(LocalDateTime.now());
 	   		category.setCname(addcategoryname.getText());
 	   		category.setConeperprice(addcategoryprice.getText());
 			csrv.add(category);
 			reload.reloadView();
 			addcategoryclose();
 		}
 	   	else
 	   	{
 	   		ShowAlert.alert("Somtheing is wrong!", AlertType.WARNING);
 	   	}
    }

    @FXML
    void addcategoryclose() {
    	addcategoryname.getScene().getWindow().hide();
    }
    
    public static void showView(Reloader reload) {
       	try {
       		FXMLLoader loader = new FXMLLoader(AddCategoryViewController.class.getResource("AddCategoryView.fxml"));
       		Parent view = loader.load();
       		AddCategoryViewController controller = loader.getController();
       		controller.reload = reload;
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			stage.initStyle(StageStyle.UNDECORATED);
    			stage.setScene(new Scene(view));
    			stage.show();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
       	
       }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		csrv = new CategoryService();
		reload = (Reloader) new CategoryViewController();
		
	}

}