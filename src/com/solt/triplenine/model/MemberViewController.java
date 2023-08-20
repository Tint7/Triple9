package com.solt.triplenine.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.solt.triplenine.entity.Member;
import com.solt.triplenine.service.MemberService;
import com.solt.triplenine.util.Reloader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberViewController implements Initializable,Reloader {

    @FXML
    private TextField srchname;

    @FXML
    private TableView<Member> membertableview;

    @FXML
    private TableColumn<Member, String> mcolname;

    @FXML
    private TableColumn<Member, String> mposition;

    @FXML
    private TableColumn<Member, String> mcolphno;

    @FXML
    private TableColumn<Member, String> mcolpwd;

    private MemberService msrv;
    private List<Member>list;
    @SuppressWarnings("unused")
	private Member member;
    @SuppressWarnings("unused")
	private Reloader reload;
    
    @FXML
    void memberadd(ActionEvent event) {
    	AddMemberViewController.showView(this);
    }

    @FXML
    void memberclear(ActionEvent event) {
    	membertableview.getItems().clear();
 	   	cleardata();
 	   	dataFindAll();
    }
    private void cleardata() {
    	// TODO Auto-generated method stub
       	membertableview.getItems().clear();
       	membertableview.getItems().addAll(list);    	
       	srchname.setText("");
    }

    private void dataFindAll() {
    	// TODO Auto-generated method stub
       list = msrv.findAll();
       membertableview.getItems().clear();
       membertableview.getItems().addAll(list);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		mcolname.setCellValueFactory(new PropertyValueFactory<>("mname"));
		mposition.setCellValueFactory(new PropertyValueFactory<>("mposition"));
		mcolphno.setCellValueFactory(new PropertyValueFactory<>("mphno"));
		mcolpwd.setCellValueFactory(new PropertyValueFactory<>("mpassword"));		
		msrv = new MemberService();
		list = new ArrayList<>();
		srchname.textProperty().addListener((a,b,c)->{
			search();
		});

		MenuItem edit =new MenuItem("Edit");
		
		edit.setOnAction(event->{
			Member member = membertableview.getSelectionModel().getSelectedItem();
			EditMemberViewController.showView(this, member);
		});
		membertableview.setContextMenu(new ContextMenu(edit));
		membertableview.setRowFactory( tv -> {
		    TableRow<Member> row = new TableRow<>();
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
		        		Member member = membertableview.getSelectionModel().getSelectedItem();
			        	member.setMposition(member.getMposition());
			        	msrv.delete(member);
			            reloadView();
		        	}
		        	
		        	
		        }
		    });
		    return row ;
		});
		
		reload();
	}

	@Override
	public void reloadView() {
		// TODO Auto-generated method stub
		reload();
		
	}
	private void reload() {
		// TODO Auto-generated method stub
		   list = msrv.findAll();
		   membertableview.getItems().clear();
		   membertableview.getItems().addAll(list);
		   
	}
	public void search() {
		   list = msrv.find(srchname.getText());
		   membertableview.getItems().clear();
		   membertableview.getItems().addAll(list);
	}	
}