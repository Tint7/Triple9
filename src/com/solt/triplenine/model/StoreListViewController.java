package com.solt.triplenine.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.StoreList;
import com.solt.triplenine.service.StoreListService;
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

public class StoreListViewController implements Initializable, Reloader {

    @FXML
    private TextField srchname;

    @FXML
    private TableView<StoreList> storelisttableview;

    @FXML
    private TableColumn<StoreList, String> stlistcoldate;

    @FXML
    private TableColumn<StoreList, String> stlistcolname;

    @FXML
    private TableColumn<StoreList, String> stlistcoladdress;

    @FXML
    private TableColumn<StoreList, String> stlistcolphno;

    @FXML
    private TableColumn<StoreList, String> stlistcolnumber;
    
    @SuppressWarnings("unused")
	private StoreListService stlistsrv;
    private List<StoreList>list;
    @SuppressWarnings("unused")
	private StoreList stlist;
    @SuppressWarnings("unused")
	private Reloader reloader;
    @FXML
    void storelistadd(ActionEvent event) {
    	AddStoreListViewController.showView(this);
    }

    @FXML
    void storelistclear(ActionEvent event) {
    	storelisttableview.getItems().clear();
 	   	cleardata();
 	   	dataFindAll();
    }

	@Override
	public void reloadView() {
		// TODO Auto-generated method stub
		reload();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stlistcoldate.setCellValueFactory(new PropertyValueFactory<>("stlistdate"));
		stlistcolname.setCellValueFactory(new PropertyValueFactory<>("stlistname"));
		stlistcoladdress.setCellValueFactory(new PropertyValueFactory<>("stlistaddress"));
		stlistcolnumber.setCellValueFactory(new PropertyValueFactory<>("stlistnumber"));
		stlistcolphno.setCellValueFactory(new PropertyValueFactory<>("stlistphno"));		
		stlistsrv = new StoreListService();
		list = new ArrayList<>();
		srchname.textProperty().addListener((a,b,c)->{
			search();
		});

		MenuItem edit =new MenuItem("Edit");
		
		edit.setOnAction(event->{
			StoreList stlist = storelisttableview.getSelectionModel().getSelectedItem();
			EditStoreListViewController.showView(this, stlist);
		});
		storelisttableview.setContextMenu(new ContextMenu(edit));
		storelisttableview.setRowFactory( tv -> {
		    TableRow<StoreList> row = new TableRow<>();
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
		        		StoreList stlist = storelisttableview.getSelectionModel().getSelectedItem();
			        	stlist.setStlistphno(stlist.getStlistphno());
			        	stlistsrv.delete(stlist);
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
		   list = stlistsrv.findAll();
		   storelisttableview.getItems().clear();
		   storelisttableview.getItems().addAll(list);
	}
	public void search() {
		   list = stlistsrv.find(srchname.getText());
		   storelisttableview.getItems().clear();
		   storelisttableview.getItems().addAll(list);
	}
	private void cleardata() {
    	// TODO Auto-generated method stub
		storelisttableview.getItems().clear();
       	storelisttableview.getItems().addAll(list);    	
       	srchname.setText("");
    }

    private void dataFindAll() {
    	// TODO Auto-generated method stub
       list = stlistsrv.findAll();
       storelisttableview.getItems().clear();
       storelisttableview.getItems().addAll(list);
    }
	
}
