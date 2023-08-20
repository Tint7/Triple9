package com.solt.triplenine.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.Category;
import com.solt.triplenine.service.CategoryService;
import com.solt.triplenine.util.Reloader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryViewController implements Initializable,Reloader {

    @FXML
    private TextField srchname;

    @FXML
    private TableView<Category> categorytableview;

    @FXML
    private TableColumn<Category, String> ccoldate;
    
    @FXML
    private TableColumn<Category, String> ccolname;

    @FXML
    private TableColumn<Category, String> ccoloneperprice;

    private CategoryService csrv;
    private List<Category>list;
    
    
    @FXML
    void categoryadd(ActionEvent event) {
    	AddCategoryViewController.showView(this);
    }

    @FXML
    void categoryclear(ActionEvent event) {
    	categorytableview.getItems().clear();
 	   	cleardata();
 	   	dataFindAll();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ccoldate.setCellValueFactory(new PropertyValueFactory<>("cdate"));
		ccolname.setCellValueFactory(new PropertyValueFactory<>("cname"));
		ccoloneperprice.setCellValueFactory(new PropertyValueFactory<>("coneperprice"));
		csrv = new CategoryService();
		list = new ArrayList<>();
		srchname.textProperty().addListener((a,b,c)->{
		search();
		});

		MenuItem edit =new MenuItem("Edit");
		
		edit.setOnAction(event->{
			Category category = categorytableview.getSelectionModel().getSelectedItem();
			EditCategoryViewController.showView(this, category);
		});
		categorytableview.setContextMenu(new ContextMenu(edit));
		categorytableview.setRowFactory( tv -> {
		    TableRow<Category> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		        	alert.setTitle("မှတ်တမ်းဖျက်ခြင်း");
		        	alert.setHeaderText("Record Delete!");
		        	alert.setContentText("Do you want to delete!");
		        	Optional<ButtonType>option=alert.showAndWait();
		        	if (option.get()==ButtonType.CANCEL) {
						
					}
		        	else if(option.get()==ButtonType.OK){
		        		Category category = categorytableview.getSelectionModel().getSelectedItem();
			        	category.setCname(category.getCname());
			        	csrv.delete(category);
			            reloadView();
		        	}
		        	
		        	
		        }
		    });
		    return row ;
		});
		reload();
	}
	private void reload() {
		// TODO Auto-generated method stub
			list = csrv.findAll();
		   categorytableview.getItems().clear();
		   categorytableview.getItems().addAll(list);
	}
	public void search() {
		   list = csrv.find(srchname.getText());
		   categorytableview.getItems().clear();
		   categorytableview.getItems().addAll(list);
	}
	private void cleardata() {
    	// TODO Auto-generated method stub
       	categorytableview.getItems().clear();
       	categorytableview.getItems().addAll(list);    	
       	srchname.setText("");
    }

	@Override
	public void reloadView() {
		// TODO Auto-generated method stub
		reload();
	}
    private void dataFindAll() {
    	// TODO Auto-generated method stub
       list = csrv.findAll();
       categorytableview.getItems().clear();
       categorytableview.getItems().addAll(list);
    }
}
