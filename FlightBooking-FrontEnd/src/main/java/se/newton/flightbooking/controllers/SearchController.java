package se.newton.flightbooking.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import javax.ws.rs.core.GenericType;

import se.newton.flightbooking.*;
import se.newton.flightbooking.models.*;

public class SearchController implements Initializable {

    @FXML private ComboBox<String> cbFrom, cbTo, cbType, cbClass;
    @FXML private DatePicker dpFromDate, dpToDate;
    @FXML private ComboBox<Integer> cbAdult, cbChild;
    @FXML private Button btnSearch, btnFindOrder;
    
    private final String redBorder = "-fx-border-color: #ff0000;";
    private final String redBackground = "-fx-background-color: #ffc0cb;";
    private final String grayBorder = "-fx-border-color: #bbbbbb;";
    private String[] arrDest;

    public SearchController() {
        List<Destination> ds = Util.GetRequest("destinations", new GenericType<List<Destination>>() { });
        arrDest = Arrays.stream(ds.toArray()).map(Object::toString).toArray(String[]::new);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbFrom.setItems(FXCollections.observableArrayList(arrDest));
        cbTo.setItems(FXCollections.observableArrayList(arrDest));
        cbAdult.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        cbChild.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5));

        cbType.setItems(FXCollections.observableArrayList(Flight.FTYPE[0], Flight.FTYPE[1]));
        cbClass.setItems(FXCollections.observableArrayList(Seat.SCLASS[0], Seat.SCLASS[1]));

        cbFrom.getSelectionModel().select(FlightBookingApp.TRIP.fromDest);
        cbTo.getSelectionModel().select(FlightBookingApp.TRIP.toDest);
        cbClass.getSelectionModel().select(FlightBookingApp.TRIP.sClass);
        cbAdult.getSelectionModel().select(FlightBookingApp.TRIP.adult);
        cbChild.getSelectionModel().select(FlightBookingApp.TRIP.child);
        cbType.getSelectionModel().select(FlightBookingApp.TRIPRETURN == null ? Flight.FTYPE[0] : Flight.FTYPE[1]);

        if (FlightBookingApp.TRIP.date != null) 
            dpFromDate.setValue(LocalDate.parse(FlightBookingApp.TRIP.date));

        if (FlightBookingApp.TRIPRETURN == null) 
            dpToDate.setDisable(true);
        else if (FlightBookingApp.TRIPRETURN.date != null) 
            dpToDate.setValue(LocalDate.parse(FlightBookingApp.TRIPRETURN.date));

        cbType.setOnAction(ev -> {
            boolean isTripReturn = cbType.getSelectionModel().getSelectedItem().equalsIgnoreCase(Flight.FTYPE[1]);
            
            dpToDate.setDisable(isTripReturn ? false : true);
            if (isTripReturn) 
                FlightBookingApp.TRIPRETURN = new SearchParam();         
            else {
                dpToDate.setStyle(grayBorder);
                FlightBookingApp.TRIPRETURN = null;
            }
        });

        btnFindOrder.setOnAction(ev -> {

        });

        btnSearch.setOnAction(ev -> {
            //if (!isValidForm()) return;

            //setQueryParameter();
            //Util.switchScene(ev, getClass().getResource("/se/newton/flightbooking/views/SearchResult.fxml"));
            //String json = Util.GetRequest("flights", String.class);

            Child f = Util.GetRequest("flights/1001", Child.class);
            System.out.println("json");
            
        });

        btnFindOrder.setOnAction(ev -> {

        });
        
        // disable dpFromDate before today
        dpFromDate.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle(redBackground);
                        }
                    }
                };
            }
        });
        
        // disable dpToDate before today + 1
        dpToDate.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now().plusDays(1))) {
                            setDisable(true);
                            setStyle(redBackground);
                        }
                    }
                };
            }
        });
    }

    private void setQueryParameter() {

        FlightBookingApp.TRIP.fromDest = cbFrom.getSelectionModel().getSelectedItem();
        FlightBookingApp.TRIP.toDest = cbTo.getSelectionModel().getSelectedItem();
        FlightBookingApp.TRIP.date = dpFromDate.getValue().toString();
        FlightBookingApp.TRIP.adult = cbAdult.getSelectionModel().getSelectedItem();
        FlightBookingApp.TRIP.child = cbChild.getSelectionModel().getSelectedItem();
        FlightBookingApp.TRIP.sClass = cbClass.getSelectionModel().getSelectedItem();

        if (!dpToDate.isDisable()) {
            FlightBookingApp.TRIPRETURN.fromDest = cbTo.getSelectionModel().getSelectedItem();
            FlightBookingApp.TRIPRETURN.toDest = cbFrom.getSelectionModel().getSelectedItem();
            FlightBookingApp.TRIPRETURN.date = dpToDate.getValue().toString();
            FlightBookingApp.TRIPRETURN.adult = cbAdult.getSelectionModel().getSelectedItem();
            FlightBookingApp.TRIPRETURN.child = cbChild.getSelectionModel().getSelectedItem();
            FlightBookingApp.TRIPRETURN.sClass = cbClass.getSelectionModel().getSelectedItem();
        }
    }

    private boolean isValidForm() {
        String from, to;
        boolean isValid = true;

        cbFrom.setStyle(grayBorder);
        cbTo.setStyle(grayBorder);
        dpFromDate.setStyle(grayBorder);
        dpToDate.setStyle(grayBorder);

        from = cbFrom.getSelectionModel().getSelectedItem();
        to = cbTo.getSelectionModel().getSelectedItem();
        if (from != null && to != null && from.equals(to)) {
            cbFrom.setStyle(redBorder);
            cbTo.setStyle(redBorder);
            isValid = false;
        }

        if (cbFrom.getSelectionModel().getSelectedItem() == null) {
            cbFrom.setStyle(redBorder);
            isValid = false;
        }

        if (cbTo.getSelectionModel().getSelectedItem() == null) {
            cbTo.setStyle(redBorder);
            isValid = false;
        }

        if (dpFromDate.getValue() == null) {
            dpFromDate.setStyle(redBorder);
            isValid = false;
        }

        if (!dpToDate.isDisable() && dpToDate.getValue() == null) {
            dpToDate.setStyle(redBorder);
            isValid = false;
        }

        if (dpFromDate.getValue() == dpToDate.getValue()) {
            dpFromDate.setStyle(redBorder);
            dpToDate.setStyle(redBorder);
            isValid = false;
        }
        
        if (dpFromDate.getValue() != null && dpToDate.getValue() != null) {
            if(dpFromDate.getValue().isAfter(dpToDate.getValue())) {
                dpToDate.setStyle(redBorder);
                isValid = false;
            }
        }

        return isValid;
    }
}
