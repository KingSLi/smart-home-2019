package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.states.ActivateState;
import ru.sbt.mipt.oop.states.AlarmState;

public class AlarmDecoratorSensorEventHandler implements EventHandler {
    private final EventHandler eventHandler;

    public AlarmDecoratorSensorEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (smartHome.getAlarm().getState() instanceof AlarmState) {
            System.out.println("[ALARM]\tSending message...");
        } else if (smartHome.getAlarm().getState() instanceof ActivateState) {
            smartHome.getAlarm().switchToAlarm();
            System.out.println("[ALARM]\tSending message...");
        } else {
            eventHandler.handleEvent(smartHome, sensorEvent);
        }
    }
}
