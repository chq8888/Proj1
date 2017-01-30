package se.newton.flightbooking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.newton.flightbooking.models.*;

public class FlightBookingApp extends Application {
    public static SearchParam TRIP = new SearchParam();
    public static SearchParam TRIPRETURN = new SearchParam();
    public static Booking BOOKING;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/se/newton/flightbooking/views/Search.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Boka resa");
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        launch(args);
    }
    
}
