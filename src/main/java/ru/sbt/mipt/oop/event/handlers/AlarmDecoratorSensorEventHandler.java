package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.alarm.ActivateState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.event.Event;

public class AlarmDecoratorSensorEventHandler implements EventHandler {
    private final EventHandler eventHandler;
    private final Alarm alarm;

    public AlarmDecoratorSensorEventHandler(EventHandler eventHandler, Alarm alarm) {
        this.eventHandler = eventHandler;
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(Event event) {
        if (alarm.getState() instanceof AlarmState) {
            System.out.println("[ALARM]\tSending message...");
        } else if (alarm.getState() instanceof ActivateState) {
            alarm.switchToAlarm();
            System.out.println("[ALARM]\tSending message...");
        } else {
            eventHandler.handleEvent(event);
        }
    }
}
