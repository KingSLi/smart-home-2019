package ru.sbt.mipt.oop.event;


import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.coolcompanyadapters.CCHandlerToHandlerAdapter;

public class AdaptEventManager implements EventManager {
    SensorEventsManager sensorEventsManager;

    public AdaptEventManager(SensorEventsManager sensorEventsManager) {
        this.sensorEventsManager = sensorEventsManager;
    }

    @Override
    public void registerEventHandler(ru.sbt.mipt.oop.event.handlers.EventHandler handler) {
        sensorEventsManager.registerEventHandler(new CCHandlerToHandlerAdapter(handler));

    }

    @Override
    public void start() {
        sensorEventsManager.start();
    }
}
