package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.eventHandlers.CloseHallDoorHandler;
import ru.sbt.mipt.oop.eventHandlers.DoorSensorEventHandler;
import ru.sbt.mipt.oop.eventHandlers.EventHandler;
import ru.sbt.mipt.oop.eventHandlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.eventProdusers.EventProducer;
import ru.sbt.mipt.oop.eventProdusers.RandomEventProducer;

import java.io.IOException;
import java.util.ArrayList;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();


        EventProducer eventProducer = new RandomEventProducer();
        ArrayList<EventHandler> handlers = createConfigHandlers();


        while (eventProducer.hasNext()) {
            SensorEvent newEvent = eventProducer.nextEvent();
            System.out.println("Got event: " + newEvent);
            for (EventHandler handler : handlers) {
                handler.handleEvent(smartHome, newEvent);
            }
        }
    }


    private static ArrayList<EventHandler> createConfigHandlers() {
        ArrayList<EventHandler> handlers = new ArrayList<>();
        handlers.add(new DoorSensorEventHandler());
        handlers.add(new LightSensorEventHandler());
        handlers.add(new CloseHallDoorHandler());
        return handlers;
    }
}
