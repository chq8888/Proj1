package se.newton.flightbooking.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.ws.rs.core.GenericType;
import se.newton.flightbooking.*;
import se.newton.flightbooking.models.*;

public class SearchResultController implements Initializable {

    @FXML private TableView<ResultSet> tblResult;
    @FXML private TableColumn<ResultSet, String> colcName, colfName;
    @FXML private TableColumn<ResultSet, Calendar> coldDate, coldTime, colaDate, colaTime;
    @FXML private TableColumn<ResultSet, Integer> colPrice;
    @FXML private TableColumn<ResultSet, Long> colSeatCount;
    @FXML private Label lblFrom, lblTo, lblClass, lblType;
    @FXML private Button btnPrev, btnNext;

    private ObservableList<ResultSet> lstTrip, lstTripReturn;
    private final GenericType<List<ResultSet>> collectionType;

    public SearchResultController() {

        collectionType = new GenericType<List<ResultSet>>() { };

        // check total available seat on trip relative to total passenger
        lstTrip = FXCollections.observableArrayList(Util.GetRequest("seats/search;" + FlightBookingApp.TRIP.toString(), collectionType))
                    .filtered((p -> p.getSeatCount() >= FlightBookingApp.TRIP.getNrOfPassenger() ));
 
        // check total available seat on return trip relative to total passenger
        if (FlightBookingApp.TRIPRETURN != null) {
            lstTripReturn = FXCollections.observableArrayList(Util.GetRequest("seats/search;" + FlightBookingApp.TRIPRETURN.toString(), collectionType))
                                  .filtered((p -> p.getSeatCount() >= FlightBookingApp.TRIPRETURN.getNrOfPassenger() ));

            // set TRIP and RETURNTRIP to equal size
            setEqualListSize();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lblFrom.setText(FlightBookingApp.TRIP.fromDest);
        lblTo.setText(FlightBookingApp.TRIP.toDest);
        lblClass.setText(FlightBookingApp.TRIP.sClass);
        lblType.setText(lstTripReturn == null ? Flight.FTYPE[0] : Flight.FTYPE[1]);

        colcName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colfName.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        coldDate.setCellValueFactory(new PropertyValueFactory<>("departure"));
        coldTime.setCellValueFactory(new PropertyValueFactory<>("departure"));
        colaDate.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        colaTime.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        colSeatCount.setCellValueFactory(new PropertyValueFactory<>("seatCount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        btnNext.setDisable(FlightBookingApp.BOOKING == null ? true : false);

        colcName.setCellFactory(column -> {
            return new TableCell<ResultSet, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {                        
                        String text = item;
                        
                        if (lstTripReturn != null) 
                            text += "\n" + lstTripReturn.get( this.getTableRow().getIndex() ).getCompanyName();
                        
                        setText(text);
                    }
                }
            };
        });

        colfName.setCellFactory(column -> {
            return new TableCell<ResultSet, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {                        
                        String text = item;
                        
                        if (lstTripReturn != null) 
                            text += "\n" + lstTripReturn.get( this.getTableRow().getIndex() ).getFlightName();
                        
                        setText(text);
                    }
                }
            };
        });

        // set date format according to yy-mm-dd
        coldDate.setCellFactory(column -> {
            return new TableCell<ResultSet, Calendar>() {
                @Override
                protected void updateItem(Calendar item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {                        
                        String text = new SimpleDateFormat("yyyy-MM-dd").format(item.getTime());
                        
                        if (lstTripReturn != null) {
                            Calendar cal = lstTripReturn.get( this.getTableRow().getIndex() ).getDeparture();
                            text += "\n" + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
                        } 
                        
                        setText(text);
                    }
                }
            };
        });

        // set time format according to 00:mm - 24:mm
        coldTime.setCellFactory(column -> {
            return new TableCell<ResultSet, Calendar>() {
                @Override
                protected void updateItem(Calendar item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        String text = new SimpleDateFormat("HH:mm").format(item.getTime());
                        
                        if (lstTripReturn != null) {
                            Calendar cal = lstTripReturn.get( this.getTableRow().getIndex() ).getDeparture();
                            text += "\n" + new SimpleDateFormat("HH:mm").format(cal.getTime());
                        } 
                        
                        setText(text);                        
                    }
                }
            };
        });

        // set date format according to yy-mm-dd
        colaDate.setCellFactory(column -> {
            return new TableCell<ResultSet, Calendar>() {
                @Override
                protected void updateItem(Calendar item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        String text = new SimpleDateFormat("yyyy-MM-dd").format(item.getTime());
                        
                        if (lstTripReturn != null) {
                            Calendar cal = lstTripReturn.get( this.getTableRow().getIndex() ).getArrival();
                            text += "\n" + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
                        } 
                            
                         setText(text);
                    }
                }
            };
        });
        
        // set time format according to 00:mm - 24:mm
        colaTime.setCellFactory(column -> {
            return new TableCell<ResultSet, Calendar>() {
                @Override
                protected void updateItem(Calendar item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        String text = new SimpleDateFormat("HH:mm").format(item.getTime());
                        
                        if (lstTripReturn != null) {
                            Calendar cal = lstTripReturn.get( this.getTableRow().getIndex() ).getArrival();
                            text += "\n" + new SimpleDateFormat("HH:mm").format(cal.getTime());
                        }
                            
                        setText(text);
                    }
                }
            };
        });

        colSeatCount.setCellFactory(column -> {
            return new TableCell<ResultSet, Long>() {
                @Override
                protected void updateItem(Long item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {                        
                        String text = item.toString(item);
                        
                        if (lstTripReturn != null) 
                            text += "\n" + Long.toString(lstTripReturn.get( this.getTableRow().getIndex() ).getSeatCount());

                        setText(text);
                    }
                }
            };
        });

        // set price according to nr of passenger
        colPrice.setCellFactory(column -> {
            return new TableCell<ResultSet, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty)
                        setText(null);
                    else 
                        setText(Integer.toString(getTotalPrice(this.getTableRow().getIndex())));
                }
            };
        });

        tblResult.getSelectionModel().selectedItemProperty().addListener(
                (obsList, oldSelection, newSelection) -> {
            
            int index = tblResult.getSelectionModel().getSelectedIndex();

            FlightBookingApp.BOOKING = new Booking(lstTrip.get(index).getfId());
            
            if (lstTripReturn != null)
                FlightBookingApp.BOOKING.setFrId(lstTripReturn.get(index).getfId());
            
            FlightBookingApp.BOOKING.setTotalPrice(getTotalPrice(index));
            
            btnNext.setDisable(false);
        });

        tblResult.setEditable(true);
        tblResult.setItems(lstTrip);

        btnPrev.setOnAction(ev -> {
            Util.switchScene(ev, getClass().getResource("/se/newton/flightbooking/views/Search.fxml"));
        });

        btnNext.setOnAction(ev -> {
            Util.switchScene(ev, getClass().getResource("/se/newton/flightbooking/views/Booking.fxml"));
        });
    }

    private void setEqualListSize() {

        if (lstTrip.size() > lstTripReturn.size()) {
            
            lstTrip = FXCollections.observableArrayList( Arrays.copyOf(lstTrip.toArray(), lstTripReturn.size(), ResultSet[].class) );
            
        } else if (lstTrip.size() < lstTripReturn.size()) {
            
            lstTripReturn = FXCollections.observableArrayList( Arrays.copyOf(lstTripReturn.toArray(), lstTrip.size(), ResultSet[].class) );
        }
    }
    
    private int getTotalPrice(int index) {
        int trPrice = 0, 
            tPrice = lstTrip.get(index).getPrice() * FlightBookingApp.TRIP.getNrOfPassenger();

        if (lstTripReturn != null) {
            trPrice = lstTripReturn.get(index).getPrice();
            trPrice = trPrice * FlightBookingApp.TRIPRETURN.getNrOfPassenger();
        }
        
        return tPrice + trPrice;
    }
}
