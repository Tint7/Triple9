package com.solt.triplenine.model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.Member;
import com.solt.triplenine.service.MemberService;
import com.solt.triplenine.util.ProjectException;
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

public class EditMemberViewController implements Initializable {

    @FXML
    private TextField editmembername;

    @FXML
    private TextField editmemberposition;

    @FXML
    private TextField editmemberphno;

    @FXML
    private PasswordField editmemberpwd;
    
    private MemberService msrv;
	private Member member;
	private Reloader reloader;
	
    @FXML
    void editmemberclose() {
    	editmembername.getScene().getWindow().hide();
    }

    @FXML
    void editmemberupdated() {
    	try{
    		if (editmembername.getText().isEmpty() || editmembername.getText()==null) {
    		throw new ProjectException("Member Name Invlid!");
			
		}
    		member.setMname(editmembername.getText());
    		member.setMposition(editmemberposition.getText());
    		member.setMphno(editmemberphno.getText());
       		member.setMpassword(editmemberpwd.getText());
       		msrv.update(member);
       		reloader.reloadView();
       		editmemberclose();
    	}catch (ProjectException e){
        	ShowAlert.alert(e.getMessage(), AlertType.WARNING);
        	
        }
    }
    public static void showView(Reloader load, Member member) {
    	try {
    		FXMLLoader loader = new FXMLLoader(EditMemberViewController.class.getResource("EditMemberView.fxml"));
			Parent	root = loader.load();
			EditMemberViewController control = loader.getController();
			control.member = member;
			control.reloader = load;
			control.AccessMemberData();
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
	private void AccessMemberData() {
		// TODO Auto-generated method stub
		editmembername.setText(member.getMname());
    	editmemberposition.setText(member.getMposition());
    	editmemberphno.setText(member.getMphno());
    	editmemberpwd.setText(member.getMpassword());
    	editmembername.setEditable(false);
    	editmemberposition.setEditable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		msrv = new MemberService();
		member = new Member();
		reloader = new MemberViewController();
	}

}
