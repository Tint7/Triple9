package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.StoreSeeds;
import com.solt.triplenine.service.StoreSeedsService;
import com.solt.triplenine.util.Reloader;
import com.solt.triplenine.util.ShowAlert;

import javafx.event.ActionEvent;
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

public class AddStoreSeedsViewController implements Initializable {

    @FXML
    private TextField addstseedsname;

    @FXML
    private TextField addstseedscategory;

    @FXML
    private TextField addstseedstotal;

    @FXML
    private TextField addstseedsphno;

    @FXML
    private TextField addstseedsaddress;

    @SuppressWarnings("unused")
	private Reloader reload;
    @SuppressWarnings("unused")
	private StoreSeedsService stseedssrv;
    
    @FXML
    void addstseedsadded(ActionEvent event) {
    	if (!addstseedsname.getText().isEmpty()&&addstseedsname.getText()!=null) {
	   		StoreSeeds stseeds = new StoreSeeds();
	   		stseeds.setStseedsdate(LocalDateTime.now());
	   		stseeds.setStseedsname(addstseedsname.getText());
	   		stseeds.setStseedscategory(addstseedscategory.getText());
	   		stseeds.setStseedstotal(addstseedstotal.getText());
	   		stseeds.setStseedsaddress(addstseedsaddress.getText());
	   		stseeds.setStseedsphno(addstseedsphno.getText());
			stseedssrv.add(stseeds);
			reload.reloadView();
			addstseedsclose();
		}
	   	else
	   	{
	   		ShowAlert.alert("Somtheing is wrong!", AlertType.WARNING);
	   	}
    }

    @FXML
    void addstseedsclose() {
    	addstseedsname.getScene().getWindow().hide();
    }
    public static void showView(Reloader reload) {
       	try {
       		FXMLLoader loader = new FXMLLoader(AddStoreSeedsViewController.class.getResource("AddStoreSeedsView.fxml"));
       		Parent view = loader.load();
       		AddStoreSeedsViewController controller = loader.getController();
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
		stseedssrv = new StoreSeedsService();
		reload = (Reloader) new StoreSeedsViewController();
	}


}
