package se.newton.flightbooking.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class FindBookingController implements Initializable {

    @FXML private TextField txtBookingId;
    @FXML private TableColumn<?, String> colfName, collName, colEmail;
    @FXML private Button btnFind, btnRemoveOrder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnFind.setOnAction( ev -> {
        
        });
        
        btnRemoveOrder.setOnAction( ev -> {
        
        });
    }    
    
}
