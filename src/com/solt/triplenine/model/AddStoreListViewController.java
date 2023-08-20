package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.StoreList;
import com.solt.triplenine.service.StoreListService;
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

public class AddStoreListViewController implements Initializable {

    @FXML
    private TextField addstlistname;

    @FXML
    private TextField addstlistaddress;

    @FXML
    private TextField addstlistnumber;

    @FXML
    private TextField addstlistphno;

    @SuppressWarnings("unused")
	private Reloader reload;
    @SuppressWarnings("unused")
	private StoreListService stlistsrv;
    
    @FXML
    void addstlistadded() {
    	if (!addstlistname.getText().isEmpty()&&addstlistname.getText()!=null) {
	   		StoreList stlist = new StoreList();
	   		stlist.setStlistdate(LocalDateTime.now());
	   		stlist.setStlistname(addstlistname.getText());
	   		stlist.setStlistaddress(addstlistaddress.getText());
	   		stlist.setStlistnumber(addstlistnumber.getText());
	   		stlist.setStlistphno(addstlistphno.getText());
			stlistsrv.add(stlist);
			reload.reloadView();
			addstlistclose();
		}
	   	else
	   	{
	   		ShowAlert.alert("Somtheing is wrong!", AlertType.WARNING);
	   	}
    }

    @FXML
    void addstlistclose() {
    	addstlistname.getScene().getWindow().hide();
    }
    public static void showView(Reloader reload) {
       	try {
       		FXMLLoader loader = new FXMLLoader(AddStoreListViewController.class.getResource("AddStoreListView.fxml"));
       		Parent view = loader.load();
       		AddStoreListViewController controller = loader.getController();
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
		stlistsrv = new StoreListService();
		reload = (Reloader) new StoreListViewController();
		
	}

}
