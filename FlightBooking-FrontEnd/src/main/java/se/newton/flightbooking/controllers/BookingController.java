package se.newton.flightbooking.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import se.newton.flightbooking.FlightBookingApp;
import se.newton.flightbooking.Util;
import se.newton.flightbooking.models.*;

public class BookingController implements Initializable {

    @FXML private TableView<Passenger> tblPassenger;
    @FXML private TableColumn<Passenger, String> colfName, collName, colEmail, colPhone;
    @FXML private TableColumn<Passenger, Integer> colAge;
    @FXML private Label lblFrom, lblTo, lblClass, lblType, lblNrOfPassenger, lblTotalPrice;
    @FXML private TextField txtfName, txtlName, txtAge, txtEmail, txtPhone;
    @FXML private ComboBox<String> cbPayment;
    @FXML private Button btnHome, btnBooking, btnNew, btnSave;

    private ObservableList<Passenger> lstPassenger;
        
    private final SearchParam trip, tripReturn;
    private final Booking booking;
    private final String redBorder = "-fx-border-color: #ff0000;";
    private final String grayBorder = "-fx-border-color: #bbbbbb;";
private Long xxx = 1L;
    public BookingController() {
        trip = FlightBookingApp.TRIP;
        tripReturn = FlightBookingApp.TRIPRETURN;
        booking= FlightBookingApp.BOOKING;
        
        lstPassenger = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblFrom.setText(trip.fromDest);
        lblTo.setText(trip.toDest);
        lblClass.setText(trip.sClass);
        lblType.setText(tripReturn == null ? Flight.FTYPE[0] : Flight.FTYPE[1]);
        lblNrOfPassenger.setText(Integer.toString(trip.getNrOfPassenger()));
        lblTotalPrice.setText(Integer.toString(booking.getTotalPrice()) + " kr");

        colfName.setCellValueFactory(new PropertyValueFactory<>("pfName"));
        collName.setCellValueFactory(new PropertyValueFactory<>("plName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        cbPayment.setItems(FXCollections.observableArrayList(Booking.PAYMENT));
        tblPassenger.setItems(lstPassenger);
        
        // select row event handler
        tblPassenger.getSelectionModel().selectedItemProperty().addListener(
                        (obsList, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Passenger p = (Passenger) newSelection;
                txtfName.setText(p.getPfName());
                txtlName.setText(p.getPlName());
                txtEmail.setText(p.getEmail());
                txtPhone.setText(p.getPhone());
                txtAge.setText(Integer.toString(p.getAge()));
            }
        });
                
        btnHome.setOnAction( ev -> {
            Util.switchScene(ev, getClass().getResource("/se/newton/flightbooking/views/Search.fxml"));
        });
        
        btnBooking.setOnAction( ev -> {
            if (!isValidForm())    return;
            
            booking.setPassengers(new ArrayList<>(lstPassenger));
            Booking b = Util.PostRequest("/bookings/create", Booking.class, Entity.entity(booking, MediaType.APPLICATION_JSON));
            
            String s = "xxx";
        });
        
        btnNew.setOnAction( ev -> {
            tblPassenger.getSelectionModel().clearSelection();
        });
        
        btnSave.setOnAction( ev -> {
            if (!isValidInput())    return;

            int idx = tblPassenger.getSelectionModel().getSelectedIndex();
            if ( idx == -1 && lstPassenger.size() < trip.getNrOfPassenger()) {
                Passenger p = new Passenger(txtfName.getText(), txtfName.getText(), Integer.parseInt(txtAge.getText()), txtEmail.getText(), txtPhone.getText());
                p.setBooking(booking);
                booking.setbId(1L);
                lstPassenger.add(p);
                p.setpId(xxx);
                xxx += 1;
            }  else if (idx != -1) {
                Passenger p = tblPassenger.getSelectionModel().getSelectedItem();

                p.setPfName(txtfName.getText());
                p.setPlName(txtlName.getText());
                p.setEmail(txtEmail.getText());
                p.setPhone(txtPhone.getText());
                p.setAge(Integer.parseInt(txtAge.getText()));

                lstPassenger.set(idx, p);
            }  
        });
        
        cbPayment.setOnAction( ev -> {
           booking.setPayment(cbPayment.getSelectionModel().getSelectedItem());
        });
    }   
    
    private boolean isValidInput() {
        boolean ok = true;

        txtAge.setStyle(grayBorder);
        txtfName.setStyle(grayBorder);
        txtlName.setStyle(grayBorder);
        
        try {
            Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException e) {
            txtAge.setStyle(redBorder);
            ok = false;
        }
        
        if (txtfName.getText().isEmpty()) {
            txtfName.setStyle(redBorder);
            ok = false;            
        }

        if (txtlName.getText().isEmpty()) {
            txtlName.setStyle(redBorder);
            ok = false;            
        }
        
        return ok;
    }
    
    private boolean isValidForm() {
        boolean ok = true;

        cbPayment.setStyle(grayBorder);
        tblPassenger.setStyle(grayBorder);
        
        if (cbPayment.getSelectionModel().getSelectedIndex() == -1) {
            ok = false;
            cbPayment.setStyle(redBorder);
        }

        if (lstPassenger.size() != trip.getNrOfPassenger()) {
            ok = false;
            tblPassenger.setStyle(redBorder);
        }
        
        return ok;
    }
}
