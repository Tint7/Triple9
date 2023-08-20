package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.ExportSeeds;
import com.solt.triplenine.service.ExportSeedsService;
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

public class AddExportSeedsViewController implements Initializable {

    @FXML
    private TextField addexportseedsname;

    @FXML
    private TextField addexportseedscategory;

    @FXML
    private TextField addexportseedstotal;

    @FXML
    private TextField addexportseedsphno;

    @FXML
    private TextField addexportseedscarno;

    @FXML
    private TextField addexportseedsnrcno;
    
    @SuppressWarnings("unused")
	private Reloader reload;
    @SuppressWarnings("unused")
	private ExportSeedsService expseedssrv;
    
    @FXML
    void addexportseedsadded() {
    	if (!addexportseedsname.getText().isEmpty()&&addexportseedsname.getText()!=null) {
	   		ExportSeeds expseeds = new ExportSeeds();
	   		expseeds.setExpseedsdate(LocalDateTime.now());
	   		expseeds.setExpseedsname(addexportseedsname.getText());
	   		expseeds.setExpseedsnrcno(addexportseedsnrcno.getText());
	   		expseeds.setExpseedscategory(addexportseedscategory.getText());
	   		expseeds.setExpseedstotal(addexportseedstotal.getText());
	   		expseeds.setExpseedscarno(addexportseedscarno.getText());
	   		expseeds.setExpseedsphno(addexportseedsphno.getText());
	   		expseedssrv.add(expseeds);
			reload.reloadView();
			addexportseedsclose();
		}
	   	else
	   	{
	   		ShowAlert.alert("Somtheing is wrong!", AlertType.WARNING);
	   	}
    }

    @FXML
    void addexportseedsclose() {
    	addexportseedsname.getScene().getWindow().hide();
    }
    public static void showView(Reloader reload) {
       	try {
       		FXMLLoader loader = new FXMLLoader(AddExportSeedsViewController.class.getResource("AddExportSeedsView.fxml"));
       		Parent view = loader.load();
       		AddExportSeedsViewController controller = loader.getController();
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
		expseedssrv = new ExportSeedsService();
		reload = (Reloader) new ExportSeedsViewController();
	}
}
