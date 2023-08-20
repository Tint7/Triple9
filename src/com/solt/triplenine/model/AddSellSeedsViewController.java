package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.SellSeeds;
import com.solt.triplenine.service.SellSeedsService;
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

public class AddSellSeedsViewController implements Initializable{

    @FXML
    private TextField addsellseedsname;

    @FXML
    private TextField addsellseedscategory;

    @FXML
    private TextField addsellseedstotal;

    @FXML
    private TextField addsellseedsaddress;

    @FXML
    private TextField addsellseedsprice;
    
    Double tp,p,t;
    @SuppressWarnings("unused")
	private Reloader reload;
    @SuppressWarnings("unused")
	private SellSeedsService sellseedssrv;

    @FXML
    void addsellseedsadded() {
    	if (!addsellseedsname.getText().isEmpty()&&addsellseedsname.getText()!=null) {
	   		SellSeeds sellseeds = new SellSeeds();
	   		p = Double.valueOf(addsellseedsprice.getText());
	   		t= Double.valueOf(addsellseedstotal.getText());
	   		tp= t*p;
	   		sellseeds.setSellseedsdate(LocalDateTime.now());
	   		sellseeds.setSellseedsname(addsellseedsname.getText());
	   		sellseeds.setSellseedscategory(addsellseedscategory.getText());
	   		sellseeds.setSellseedstotal(addsellseedstotal.getText());
	   		sellseeds.setSellseedsoneperprice(addsellseedsprice.getText());
	   		sellseeds.setSellseedsaddress(addsellseedsaddress.getText());
	   		sellseeds.setSellseedstotalprice(tp.toString());
	   		sellseedssrv.add(sellseeds);
			reload.reloadView();
			addsellseedsclose();
		}
	   	else
	   	{
	   		ShowAlert.alert("Somtheing is wrong!", AlertType.WARNING);
	   	}
    }

    @FXML
    void addsellseedsclose() {
    	addsellseedsname.getScene().getWindow().hide();
    }
    public static void showView(Reloader reload) {
       	try {
       		FXMLLoader loader = new FXMLLoader(AddSellSeedsViewController.class.getResource("AddSellSeedsView.fxml"));
       		Parent view = loader.load();
       		AddSellSeedsViewController controller = loader.getController();
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
		sellseedssrv = new SellSeedsService();
		reload = (Reloader) new SellSeedsViewController();
	}

}
