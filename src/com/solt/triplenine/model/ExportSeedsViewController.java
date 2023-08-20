package com.solt.triplenine.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.ExportSeeds;
import com.solt.triplenine.service.ExportSeedsService;
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

public class ExportSeedsViewController implements Initializable, Reloader {

    @FXML
    private TextField srchname;

    @FXML
    private TableView<ExportSeeds> exportseedstableview;

    @FXML
    private TableColumn<ExportSeeds, String> exportseedscoldate;
    
    @FXML
    private TableColumn<ExportSeeds, String> exportseedscolname;

    @FXML
    private TableColumn<ExportSeeds, String> exportseedscolnrcno;

    @FXML
    private TableColumn<ExportSeeds, String> exportseedscolphno;

    @FXML
    private TableColumn<ExportSeeds, String> exportseedscolcategory;

    @FXML
    private TableColumn<ExportSeeds, String> exportseedscoltotal;

    @FXML
    private TableColumn<ExportSeeds, String> exportseedscolcarno;
    
    @SuppressWarnings("unused")
	private ExportSeedsService expseedssrv;
    private List<ExportSeeds>list;
    @SuppressWarnings("unused")
	private ExportSeeds expseeds;
    @SuppressWarnings("unused")
	private Reloader reloader;
    
    @FXML
    void exporteseedsadd() {
    	AddExportSeedsViewController.showView(this);
    }

    @FXML
    void exportseedsclear() {
    	exportseedstableview.getItems().clear();
 	   	cleardata();
 	   	dataFindAll();
    }

	private void dataFindAll() {
		// TODO Auto-generated method stub
		list = expseedssrv.findAll();
    	exportseedstableview.getItems().clear();
    	exportseedstableview.getItems().addAll(list);
	}

	private void cleardata() {
		// TODO Auto-generated method stub
		exportseedstableview.getItems().clear();
		exportseedstableview.getItems().addAll(list);    	
    	srchname.setText("");
	}

	@Override
	public void reloadView() {
		// TODO Auto-generated method stub
		reload();
	}

	private void reload() {
		// TODO Auto-generated method stub
		   list = expseedssrv.findAll();
		   exportseedstableview.getItems().clear();
		   exportseedstableview.getItems().addAll(list);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		exportseedscoldate.setCellValueFactory(new PropertyValueFactory<>("expseedsdate"));
		exportseedscolname.setCellValueFactory(new PropertyValueFactory<>("expseedsname"));
		exportseedscolnrcno.setCellValueFactory(new PropertyValueFactory<>("expseedsnrcno"));	
		exportseedscolcategory.setCellValueFactory(new PropertyValueFactory<>("expseedscategory"));
		exportseedscoltotal.setCellValueFactory(new PropertyValueFactory<>("expseedstotal"));
		exportseedscolcarno.setCellValueFactory(new PropertyValueFactory<>("expseedscarno"));
		exportseedscolphno.setCellValueFactory(new PropertyValueFactory<>("expseedsphno"));
		expseedssrv = new ExportSeedsService();
		list = new ArrayList<>();
		srchname.textProperty().addListener((a,b,c)->{
			search();
		});
		exportseedstableview.setRowFactory( tv -> {
		    TableRow<ExportSeeds> row = new TableRow<>();
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
		        		ExportSeeds expseeds = exportseedstableview.getSelectionModel().getSelectedItem();
		        		expseeds.setExpseedsnrcno(expseeds.getExpseedsnrcno());
		        		expseedssrv.delete(expseeds);
			            reloadView();
		        	}        	
		        }
		    });
		    return row ;
		});
		reloadView();
	}
	public void search() {
		   list = expseedssrv.find(srchname.getText());
		   exportseedstableview.getItems().clear();
		   exportseedstableview.getItems().addAll(list);
	}

}
