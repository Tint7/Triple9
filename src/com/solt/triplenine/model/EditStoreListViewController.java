package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.StoreList;
import com.solt.triplenine.service.StoreListService;
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

public class EditStoreListViewController implements Initializable {

    @FXML
    private TextField editstlistname;

    @FXML
    private TextField editstlistaddress;

    @FXML
    private TextField editstlistnumber;

    @FXML
    private TextField editstlistphno;

    private StoreListService stlistsrv;
	private StoreList stlist;
	private Reloader reloader;
	
    @FXML
    void editstlistclose() {
    	editstlistname.getScene().getWindow().hide();
    }

    @FXML
    void editstlistupdated() {
    	try{
    		if (editstlistname.getText().isEmpty() || editstlistname.getText()==null) {
    		throw new ProjectException("Member Name Invlid!");}
    		stlist.setStlistdate(LocalDateTime.now());
    		stlist.setStlistname(editstlistname.getText());
    		stlist.setStlistaddress(editstlistaddress.getText());
    		stlist.setStlistnumber(editstlistnumber.getText());
       		stlist.setStlistphno(editstlistphno.getText());
       		stlistsrv.update(stlist);
       		reloader.reloadView();
       		editstlistclose();
       		
    	}catch (ProjectException e){
        	ShowAlert.alert(e.getMessage(), AlertType.WARNING);
        	
        }
    }
    public static void showView(Reloader load, StoreList stlist) {
    	try {
    		FXMLLoader loader = new FXMLLoader(EditStoreListViewController.class.getResource("EditStoreListView.fxml"));
			Parent	root = loader.load();
			EditStoreListViewController control = loader.getController();
			control.stlist = stlist;
			control.reloader = load;
			control.AccessStoreListData();
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
	private void AccessStoreListData() {
		// TODO Auto-generated method stub
		editstlistname.setText(stlist.getStlistname());
		editstlistaddress.setText(stlist.getStlistaddress());
		editstlistnumber.setText(stlist.getStlistnumber());
		editstlistphno.setText(stlist.getStlistphno());
		editstlistname.setEditable(false);
		editstlistaddress.setEditable(false);
		editstlistphno.setEditable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stlistsrv = new StoreListService();
		stlist = new StoreList();
		reloader = new StoreListViewController();
	}

}
