package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.event.AdaptEventManager;
import ru.sbt.mipt.oop.event.EventManager;
import ru.sbt.mipt.oop.event.handlers.*;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;

public class AdaptApplication {
    public static void main(String[] args) {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        EventManager sensorEventsManager = new AdaptEventManager(new SensorEventsManager());
        sensorEventsManager.registerEventHandler(
                        new AlarmDecoratorSensorEventHandler(
                                new DoorSensorEventHandler(smartHome), smartHome.getAlarm()));
        sensorEventsManager.registerEventHandler(
                        new AlarmDecoratorSensorEventHandler(
                                new LightSensorEventHandler(smartHome), smartHome.getAlarm()));
        sensorEventsManager.registerEventHandler(
                        new AlarmDecoratorSensorEventHandler(
                                new CloseHallDoorEventHandler(smartHome), smartHome.getAlarm()));
        sensorEventsManager.registerEventHandler(
                        new AlarmEventHandler(smartHome));
        sensorEventsManager.start();
    }
}
