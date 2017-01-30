package se.newton.flightbooking;

import java.util.List;
import java.net.URL;
import java.io.IOException;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import org.glassfish.jersey.jackson.JacksonFeature;

public class Util {

    public static String HOST = "localhost";
    public static String PORT = "8080";
    public static String APPNAME = "FlightBooking-BackEnd-Maven-Jersey-Webapp";
    public static String BASE_URI = String.format("http://%s:%s/%s/api", HOST, PORT, APPNAME);
    public static String CONTENT_TYPE = MediaType.APPLICATION_JSON;
    public static boolean LOG_REQUEST = true;
    public static final Client CLIENT = ClientBuilder.newClient();
//    public final static Client CLIENT = ClientBuilder.newBuilder()
//        //.register(MyObjectMapperProvider.class)  // No need to register this provider if no special configuration is required.
//        .register(JacksonFeature.class)
//        .build();
    
    public static <T> T GetRequest(String resourcePath, Class<T> typeofClass) {
        
        WebTarget service = CLIENT.target(BASE_URI);

        T object = service.path(resourcePath).request(CONTENT_TYPE).get(typeofClass);
        if (LOG_REQUEST)    System.out.println(object);
        return object;
    }

    public static <T> List<T> GetRequest(String resourcePath, GenericType<List<T>> typeofCollection) {
        
        WebTarget service = CLIENT.target(BASE_URI);

        List<T> list = service.path(resourcePath).request(CONTENT_TYPE).get(typeofCollection);
        if (LOG_REQUEST)    System.out.println(list);
        return list;
    }
    
    public static <T> T PostRequest(String resourcePath, Class<T> typeofClass, Entity<?> entity) {

        WebTarget service = CLIENT.target(BASE_URI);
        T object = service.path(resourcePath).request(CONTENT_TYPE).post(entity, typeofClass);
        if (LOG_REQUEST)    System.out.println(object);
        return object;
    }
    
    public static <T> void PostRequest(String resourcePath, GenericType<List<T>> typeofCollection, Entity<?> entity) {

        WebTarget service = CLIENT.target(BASE_URI);
        service.path(resourcePath).request(CONTENT_TYPE).post(entity, typeofCollection);
    }

    public static Response PutRequest(String resourcePath, Entity<?> entity) {

        WebTarget service = CLIENT.target(BASE_URI);
        Response response = service.path(resourcePath).request(CONTENT_TYPE).put(entity);
        if (LOG_REQUEST)    System.out.println(response);
        return response;
    }

    public static <T> T PutRequest(String resourcePath, Class<T> typeofClass, Entity<?> entity) {

        WebTarget service = CLIENT.target(BASE_URI);
        T object = service.path(resourcePath).request(CONTENT_TYPE).put(entity, typeofClass);
        if (LOG_REQUEST)    System.out.println(object);
        return object;
    }
    
    public static <T> void PutRequest(String resourcePath, GenericType<List<T>> typeofCollection, Entity<?> entity) {

        WebTarget service = CLIENT.target(BASE_URI);
        service.path(resourcePath).request(CONTENT_TYPE).put(entity, typeofCollection);
    }
    
    public static Response DeleteRequest(String resourcePath) {
        
        WebTarget service = CLIENT.target(BASE_URI);

        Response response = service.path(resourcePath).request(CONTENT_TYPE).delete();
        if (LOG_REQUEST)    System.out.println(response);
        return response;
    }
    
    public static void switchScene(ActionEvent ev, URL url) {
        
        try {
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("[selectScene}: File not found");
            System.exit(-1);
        }
    }
    
    public static void showMessage(String title, String msg) {
        Alert dialog = new Alert(AlertType.INFORMATION);

        dialog.setHeaderText(null);
        dialog.setTitle(title);
        dialog.setContentText(msg);
        dialog.showAndWait();
    }
}
