package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.eventHandlers.*;
import ru.sbt.mipt.oop.eventProdusers.EventProducer;
import ru.sbt.mipt.oop.eventProdusers.RandomEventProducer;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;

import java.util.ArrayList;

public class Application {

    public static void main(String... args) {
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
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new DoorSensorEventHandler()));
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new LightSensorEventHandler()));
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new CloseHallDoorEventHandler()));
        handlers.add(new AlarmEventHandler());
        return handlers;
    }
}
