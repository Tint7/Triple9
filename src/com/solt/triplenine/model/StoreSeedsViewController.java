package com.solt.triplenine.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.StoreSeeds;
import com.solt.triplenine.service.StoreSeedsService;
import com.solt.triplenine.util.Reloader;

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

public class StoreSeedsViewController implements Initializable, Reloader {

    @FXML
    private TextField srchname;

    @FXML
    private TableView<StoreSeeds> storeseedstableview;

    @FXML
    private TableColumn<StoreSeeds, String> stseedscoldate;
    
    @FXML
    private TableColumn<StoreSeeds, String> stseedscolname;

    @FXML
    private TableColumn<StoreSeeds, String> stseedscoladdress;

    @FXML
    private TableColumn<StoreSeeds, String> stseedscolphno;

    @FXML
    private TableColumn<StoreSeeds, String> stseedscolcategory;

    @FXML
    private TableColumn<StoreSeeds, String> stseedscoltotal;
    
    @SuppressWarnings("unused")
	private StoreSeedsService stseedssrv;
    private List<StoreSeeds>list;
    @SuppressWarnings("unused")
	private StoreSeeds stseeds;
    @SuppressWarnings("unused")
	private Reloader reloader;
    
    @FXML
    void storeseedsadd() {
    	AddStoreSeedsViewController.showView(this);
    }

    @FXML
    void storeseedsclear() {
    	storeseedstableview.getItems().clear();
 	   	cleardata();
 	   	dataFindAll();
    }
    private void reload() {
		// TODO Auto-generated method stub
		   list = stseedssrv.findAll();
		   storeseedstableview.getItems().clear();
		   storeseedstableview.getItems().addAll(list);
	}

	@Override
	public void reloadView() {
		// TODO Auto-generated method stub
		reload();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stseedscoldate.setCellValueFactory(new PropertyValueFactory<>("stseedsdate"));
		stseedscolname.setCellValueFactory(new PropertyValueFactory<>("stseedsname"));
		stseedscolcategory.setCellValueFactory(new PropertyValueFactory<>("stseedscategory"));
		stseedscoltotal.setCellValueFactory(new PropertyValueFactory<>("stseedstotal"));
		stseedscoladdress.setCellValueFactory(new PropertyValueFactory<>("stseedsaddress"));	
		stseedscolphno.setCellValueFactory(new PropertyValueFactory<>("stseedsphno"));
		stseedssrv = new StoreSeedsService();
		list = new ArrayList<>();
		srchname.textProperty().addListener((a,b,c)->{
			search();
		});

		MenuItem edit =new MenuItem("Sell");
		
		edit.setOnAction(event->{
			StoreSeeds stseeds = storeseedstableview.getSelectionModel().getSelectedItem();
			EditStoreSeedsViewController.showView(this, stseeds);
		});
		storeseedstableview.setContextMenu(new ContextMenu(edit));
		storeseedstableview.setRowFactory( tv -> {
		    TableRow<StoreSeeds> row = new TableRow<>();
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
		        		StoreSeeds stseeds = storeseedstableview.getSelectionModel().getSelectedItem();
			        	stseeds.setStseedsphno(stseeds.getStseedsphno());
			        	stseedssrv.delete(stseeds);
			            reloadView();
		        	}
		        }
		    });
		    return row ;
		});
		
		reload();
	}
	public void search() {
		   list = stseedssrv.find(srchname.getText());
		   storeseedstableview.getItems().clear();
		   storeseedstableview.getItems().addAll(list);
	}
	private void cleardata() {
 	// TODO Auto-generated method stub
		storeseedstableview.getItems().clear();
		storeseedstableview.getItems().addAll(list);    	
    	srchname.setText("");
	}

	private void dataFindAll() {
 	// TODO Auto-generated method stub
    list = stseedssrv.findAll();
    storeseedstableview.getItems().clear();
    storeseedstableview.getItems().addAll(list);
	}

}
