package com.solt.triplenine.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowAlert {
 public static void alert(String message, AlertType type) {
	Alert alert = new Alert(type,message);
	alert.showAndWait();
}
}
