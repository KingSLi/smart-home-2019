package ru.sbt.mipt.oop.coolcompanyadapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

public class CCSensorToEventAdapter {
    private CCSensorEvent ccSensorEvent;

    public CCSensorToEventAdapter(CCSensorEvent ccSensorEvent) {
        this.ccSensorEvent = ccSensorEvent;
    }

    public Event getEvent() {
        switch (ccSensorEvent.getEventType()) {
            case "LightIsOn":       return new SensorEvent(SensorEventType.LIGHT_ON, ccSensorEvent.getObjectId());
            case "LightIsOff":      return new SensorEvent(SensorEventType.LIGHT_OFF, ccSensorEvent.getObjectId());
            case "DoorIsOpen":      return new SensorEvent(SensorEventType.DOOR_OPEN, ccSensorEvent.getObjectId());
            case "DoorIsClosed":    return new SensorEvent(SensorEventType.DOOR_CLOSED, ccSensorEvent.getObjectId());
            case "DoorIsLocked":    return new SensorEvent(SensorEventType.DOOR_LOCKED, ccSensorEvent.getObjectId());
            default:/*DoorUnlocked*/return new SensorEvent(SensorEventType.DOOR_UNLOCKED, ccSensorEvent.getObjectId());
        }
    }
}
