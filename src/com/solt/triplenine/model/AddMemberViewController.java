package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.Member;
import com.solt.triplenine.service.MemberService;
import com.solt.triplenine.util.Reloader;
import com.solt.triplenine.util.ShowAlert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddMemberViewController implements Initializable {

    @FXML
    private TextField addmemberposition;

    @FXML
    private TextField addmembername;

    @FXML
    private PasswordField addmemberpwd;

    @FXML
    private TextField addmemberphno;

    private Reloader reload;
    private MemberService msrv;
    
    @FXML
    void addmemberadded() {
    	 if (!addmembername.getText().isEmpty()&&addmembername.getText()!=null) {
    	   		Member member = new Member();
    	   		member.setMname(addmembername.getText());
    	   		member.setMposition(addmemberposition.getText());
    	   		member.setMphno(addmemberphno.getText());
    	   		member.setMpassword(addmemberpwd.getText());
    			msrv.add(member);
    			reload.reloadView();
    			addmemberclose();
    		}
    	   	else
    	   	{
    	   		ShowAlert.alert("Somtheing is wrong!", AlertType.WARNING);
    	   	}
    }
    @FXML
    void addmemberclose() {
    	addmembername.getScene().getWindow().hide();
    }
    
    public static void showView(Reloader reload) {
       	try {
       		FXMLLoader loader = new FXMLLoader(AddMemberViewController.class.getResource("AddMemberView.fxml"));
       		Parent view = loader.load();
       		AddMemberViewController controller = loader.getController();
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
			msrv = new MemberService();
			reload = (Reloader) new MemberViewController();
		}
}