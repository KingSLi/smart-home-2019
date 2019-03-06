package ru.sbt.mipt.oop;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();

        List<SensorEvent> events = generateSensorsEventsList(0.01);
        for (SensorEvent event: events) {
            System.out.println("Got event: " + event);
            event.processSensorEvent(smartHome);
        }
    }


    private static List<SensorEvent> generateSensorsEventsList(double flush) {
        List<SensorEvent> events = new ArrayList<>();
        while (Math.random() > flush) {
            SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
            String objectId = "" + ((int) (10 * Math.random()));
            switch (sensorEventType) {
                case DOOR_OPEN:
                case DOOR_CLOSED: events.add(new DoorSensorEvent(sensorEventType, objectId)); break;
                case LIGHT_ON:
                case LIGHT_OFF: events.add(new LightSensorEvent(sensorEventType, objectId)); break;
            }
        }

        return events;
    }
}
