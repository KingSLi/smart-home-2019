package ru.sbt.mipt.oop.coolcompanyadapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.EventType;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

public class CCSensorToEventAdapter extends SensorEvent {
    private CCSensorEvent ccSensorEvent;

    public CCSensorToEventAdapter(CCSensorEvent ccSensorEvent) {
        this.ccSensorEvent = ccSensorEvent;
    }

    @Override
    public EventType getEventType() {
        switch (ccSensorEvent.getEventType()) {
            case "LightIsOn":       return SensorEventType.LIGHT_ON;
            case "LightIsOff":      return SensorEventType.LIGHT_OFF;
            case "DoorIsOpen":      return SensorEventType.DOOR_OPEN;
            case "DoorIsClosed":    return SensorEventType.DOOR_CLOSED;
            case "DoorIsLocked":    return SensorEventType.DOOR_LOCKED;
            default:/*DoorIsUnlocked*/ return SensorEventType.DOOR_UNLOCKED;
        }
    }

    @Override
    public String getObjectId() {
        return ccSensorEvent.getObjectId();
    }
}
