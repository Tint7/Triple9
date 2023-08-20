package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.Category;
import com.solt.triplenine.service.CategoryService;
import com.solt.triplenine.util.ProjectException;
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

public class EditCategoryViewController implements Initializable {

    @FXML
    private TextField editcategoryname;

    @FXML
    private TextField editcategoryprice;

    private CategoryService csrv;
	private Category category;
	private Reloader reloader;
	
    @FXML
    void editcategoryclose() {
    	editcategoryname.getScene().getWindow().hide();
    }

    @FXML
    void editcategoryupdate() {
    	try{
    		if (editcategoryname.getText().isEmpty() || editcategoryname.getText()==null) {
    		throw new ProjectException("Category Name Invlid!");
			
		}
    		category.setCdate(LocalDateTime.now());
    		category.setCname(editcategoryname.getText());
    		category.setConeperprice(editcategoryprice.getText());
       		csrv.update(category);
       		reloader.reloadView();
       		editcategoryclose();
    	}catch (ProjectException e){
        	ShowAlert.alert(e.getMessage(), AlertType.WARNING);
        	
        }
    }
    
    public static void showView(Reloader load, Category category) {
    	try {
    		FXMLLoader loader = new FXMLLoader(EditCategoryViewController.class.getResource("EditCategoryView.fxml"));
			Parent	root = loader.load();
			EditCategoryViewController control = loader.getController();
			control.category = category;
			control.reloader = load;
			control.AccessCategoryData();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
	    	stage.setScene(new Scene(root));
	    	stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void AccessCategoryData() {
		// TODO Auto-generated method stub
		editcategoryname.setText(category.getCname());
    	editcategoryprice.setText(category.getConeperprice());
    	editcategoryname.setEditable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		csrv = new CategoryService();
		category = new Category();
		reloader = new CategoryViewController();
	}


}
