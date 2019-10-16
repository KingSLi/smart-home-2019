package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.coolcompanyadapters.CCHandlerToHandlerAdapter;
import ru.sbt.mipt.oop.event.handlers.AlarmEventHandler;
import ru.sbt.mipt.oop.event.handlers.DoorSensorEventHandler;
import ru.sbt.mipt.oop.event.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;

public class AdaptApplication {
    public static void main(String[] args) {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new CCHandlerToHandlerAdapter(
                new DoorSensorEventHandler(smartHome)));
        sensorEventsManager.registerEventHandler(new CCHandlerToHandlerAdapter(
                new LightSensorEventHandler(smartHome)));
        sensorEventsManager.registerEventHandler(new CCHandlerToHandlerAdapter(
                new AlarmEventHandler(smartHome)));

        sensorEventsManager.start();
    }
}
