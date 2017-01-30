package se.newton.flightbooking;

public class Util {

    public static String log(String fromClass, String method, String param, String value) {
        param = param == null ? "" : "with Param: " + param;
        value = (param == null) || (value == null) ? "" : "and Value: " + value;
        String msg = "%s Webservice receive a %s request %s %s";

        msg = String.format(msg, fromClass.toUpperCase(), method.toUpperCase(), param, value);
        System.out.println(msg);

        return msg;
    }
}
