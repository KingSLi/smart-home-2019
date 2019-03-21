package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.event.handlers.*;
import ru.sbt.mipt.oop.eventProdusers.EventProducer;
import ru.sbt.mipt.oop.eventProdusers.RandomEventProducer;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;

import java.util.ArrayList;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();


        EventProducer eventProducer = new RandomEventProducer();
        ArrayList<EventHandler> handlers = createConfigHandlers(smartHome);


        while (eventProducer.hasNext()) {
            Event newEvent = eventProducer.nextEvent();
            System.out.println("Got event: " + newEvent);
            for (EventHandler handler : handlers) {
                handler.handleEvent(newEvent);
            }
        }
    }


    private static ArrayList<EventHandler> createConfigHandlers(SmartHome smartHome) {
        ArrayList<EventHandler> handlers = new ArrayList<>();
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new DoorSensorEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new LightSensorEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new CloseHallDoorEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(new AlarmEventHandler(smartHome));
        return handlers;
    }
}
