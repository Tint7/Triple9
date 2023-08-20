package com.solt.triplenine.model;

import java.net.URL;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.Member;
import com.solt.triplenine.service.MemberService;
import com.solt.triplenine.util.ProjectException;
import com.solt.triplenine.util.Security;
import com.solt.triplenine.util.ShowAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;
    private MemberService srv;
    public void clear() {
    	name.clear();
    	password.clear();
    }

    public void submit() {
    	try {
        	String n = name.getText();
        	String pass = password.getText();
        	Member member = srv.findLoginname(n);
        	if (member == null) {
    			throw new ProjectException("Login Name is invalid!");
    		}
        	member.getMpassword();
        	if (!pass.equals(member.getMpassword())) {
    				throw new ProjectException("Password is invalid!");
        	}
        	
        	Security.setMember(member);
        	MainFrameViewController.showview();
        	name.getScene().getWindow().hide();
        	}catch(ProjectException e)
        	{
        		ShowAlert.alert(e.getMessage(), AlertType.WARNING);
        	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		srv = new MemberService();
		
	}

}

