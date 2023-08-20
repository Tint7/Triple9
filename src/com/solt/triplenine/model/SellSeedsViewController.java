package com.solt.triplenine.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.SellSeeds;
import com.solt.triplenine.service.SellSeedsService;
import com.solt.triplenine.util.Reloader;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SellSeedsViewController implements Initializable, Reloader {

    @FXML
    private TextField srchname;

    @FXML
    private TableView<SellSeeds> sellseedstableview;

    @FXML
    private TableColumn<SellSeeds, String> sellseedscoldate;
    
    @FXML
    private TableColumn<SellSeeds, String> sellseedscolname;

    @FXML
    private TableColumn<SellSeeds, String> sellseedscoladdress;

    @FXML
    private TableColumn<SellSeeds, String> sellseedscoltotalprice;

    @FXML
    private TableColumn<SellSeeds, String> sellseedscolcategory;

    @FXML
    private TableColumn<SellSeeds, String> sellseedscoltotal;

    @FXML
    private TableColumn<SellSeeds, String> sellseedscolprice;
    
    @SuppressWarnings("unused")
	private SellSeedsService sellseedssrv;
    private List<SellSeeds>list;
    @SuppressWarnings("unused")
	private SellSeeds sellseeds;
    @SuppressWarnings("unused")
	private Reloader reloader;

    @FXML
    void selleseedsadd() {
    	AddSellSeedsViewController.showView(this);
    }

    @FXML
    void sellseedsclear() {
    	sellseedstableview.getItems().clear();
 	   	cleardata();
 	   	dataFindAll();
    }
    private void dataFindAll() {
		// TODO Auto-generated method stub
    	list = sellseedssrv.findAll();
    	sellseedstableview.getItems().clear();
    	sellseedstableview.getItems().addAll(list);
	}

	private void cleardata() {
		// TODO Auto-generated method stub
		sellseedstableview.getItems().clear();
		sellseedstableview.getItems().addAll(list);    	
    	srchname.setText("");
	}

	private void reload() {
		// TODO Auto-generated method stub
		list = sellseedssrv.findAll();
    	sellseedstableview.getItems().clear();
    	sellseedstableview.getItems().addAll(list);
	}

	@Override
	public void reloadView() {
		// TODO Auto-generated method stub
		reload();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		sellseedscoldate.setCellValueFactory(new PropertyValueFactory<>("sellseedsdate"));
		sellseedscolname.setCellValueFactory(new PropertyValueFactory<>("sellseedsname"));
		sellseedscolcategory.setCellValueFactory(new PropertyValueFactory<>("sellseedscategory"));	
		sellseedscoltotal.setCellValueFactory(new PropertyValueFactory<>("sellseedstotal"));
		sellseedscolprice.setCellValueFactory(new PropertyValueFactory<>("sellseedsoneperprice"));
		sellseedscoladdress.setCellValueFactory(new PropertyValueFactory<>("sellseedsaddress"));
		sellseedscoltotalprice.setCellValueFactory(new PropertyValueFactory<>("sellseedstotalprice"));
		sellseedssrv = new SellSeedsService();
		list = new ArrayList<>();
		srchname.textProperty().addListener((a,b,c)->{
			search();
		});
		sellseedstableview.setRowFactory( tv -> {
		    TableRow<SellSeeds> row = new TableRow<>();
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
		        		SellSeeds sellseeds = sellseedstableview.getSelectionModel().getSelectedItem();
			        	sellseeds.setSellseedsname(sellseeds.getSellseedsname());
			        	sellseedssrv.delete(sellseeds);
			            reloadView();
		        	}
		        	
		        	
		        }
		    });
		    return row ;
		});
		 reloadView();
	}
	public void search() {
		   list = sellseedssrv.find(srchname.getText());
		   sellseedstableview.getItems().clear();
		   sellseedstableview.getItems().addAll(list);
	}
}
