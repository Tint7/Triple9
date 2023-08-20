package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.SellSeeds;
import com.solt.triplenine.entity.StoreSeeds;
import com.solt.triplenine.service.SellSeedsService;
import com.solt.triplenine.service.StoreSeedsService;
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

public class EditStoreSeedsViewController implements Initializable {

    @FXML
    private TextField editstseedsname;

    @FXML
    private TextField editstseedscategory;

    @FXML
    private TextField editstseedstotal;

    @FXML
    private TextField editstseedsphno;

    @FXML
    private TextField editstseedsaddress;
    
    @FXML
    private TextField sellstseeds;
    
    @FXML
    private TextField sellprice;

    private StoreSeedsService stseedssrv;
    private SellSeedsService sellseedssrv;
    
   	private StoreSeeds stseeds;
   	private SellSeeds sellseeds;
   	private Reloader reloader;
   	Double total=0.0,total1=0.0,total2=0.0,t,p,tp;
    @FXML
    void editstseedsclose() {
    	editstseedsname.getScene().getWindow().hide();
    }

    @FXML
    void editstseedsupdated() {
    	try{
    		total1 = Double.valueOf(stseeds.getStseedstotal());
    		total2 = Double.valueOf(sellstseeds.getText());
    		total = total1-total2;
    		System.out.println(total);
    		if (editstseedsname.getText().isEmpty() || editstseedsname.getText()==null) {
    		throw new ProjectException("Something is wrong!");}
    		stseeds.setStseedsdate(LocalDateTime.now());
    		stseeds.setStseedsname(editstseedsname.getText());
    		stseeds.setStseedscategory(editstseedscategory.getText());
    		stseeds.setStseedstotal(total.toString());
       		stseeds.setStseedsaddress(editstseedsaddress.getText());
       		stseeds.setStseedsphno(editstseedsphno.getText());
       		
       		p = Double.valueOf(sellprice.getText());
	   		t= Double.valueOf(sellstseeds.getText());
	   		tp= t*p;
       		
			sellseeds.setSellseedsdate(LocalDateTime.now());
	   		sellseeds.setSellseedsname(editstseedsname.getText());
	   		sellseeds.setSellseedscategory(editstseedscategory.getText());
	   		sellseeds.setSellseedstotal(sellstseeds.getText());
	   		sellseeds.setSellseedsoneperprice(sellprice.getText());
	   		sellseeds.setSellseedsaddress(editstseedsaddress.getText());
	   		sellseeds.setSellseedstotalprice(tp.toString());
	   		sellseedssrv.add(sellseeds);
       		
       		
	   		stseedssrv.update(stseeds);
       		reloader.reloadView();
       		editstseedsclose();
       		
    	}catch (ProjectException e){
        	ShowAlert.alert(e.getMessage(), AlertType.WARNING);
        	
        }
    }
    public static void showView(Reloader load, StoreSeeds stseeds) {
    	try {
    		FXMLLoader loader = new FXMLLoader(EditStoreSeedsViewController.class.getResource("EditStoreSeedsView.fxml"));
			Parent	root = loader.load();
			EditStoreSeedsViewController control = loader.getController();
			control.stseeds = stseeds;
			control.reloader = load;
			control.AccessStoreSeedsData();
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
	private void AccessStoreSeedsData() {
		// TODO Auto-generated method stub
		editstseedsname.setText(stseeds.getStseedsname());
		editstseedscategory.setText(stseeds.getStseedscategory());
		editstseedstotal.setText(stseeds.getStseedstotal());
		editstseedsaddress.setText(stseeds.getStseedsaddress());
		editstseedsphno.setText(stseeds.getStseedsphno());
		editstseedscategory.setEditable(false);
		editstseedsaddress.setEditable(false);
		editstseedsname.setEditable(false);
		editstseedstotal.setEditable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stseedssrv = new StoreSeedsService();
		stseeds = new StoreSeeds();
		sellseedssrv = new SellSeedsService();
		sellseeds = new SellSeeds();
		reloader = new StoreSeedsViewController();
	}

}
