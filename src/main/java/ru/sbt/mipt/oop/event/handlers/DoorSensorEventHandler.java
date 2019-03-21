package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.homeInsides.Door;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;


public class DoorSensorEventHandler implements EventHandler {
    private SmartHome smartHome;

    public DoorSensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event event) {
        if (!isSensorEvent(event)) {
            return;
        }
        SensorEvent sensorEvent = (SensorEvent) event;

        if (!isDoorEvent(sensorEvent)) {
            return;
        }

        smartHome.execute(object -> {
            if (!(object instanceof Door))
                return;
            Door door = (Door) object;
            if (door.getId().equals(sensorEvent.getObjectId())) {
                if (sensorEvent.getEventType() == DOOR_OPEN) {
                    changeDoorState(door, true, " was opened.");
                } else {
                    changeDoorState(door, false, " was closed.");
                }
            }
        });
    }

    private boolean isDoorEvent(SensorEvent sensorEvent) {
        return sensorEvent.getEventType() == DOOR_OPEN || sensorEvent.getEventType() == DOOR_CLOSED;
    }

    private boolean isSensorEvent(Event event) {
        return event instanceof SensorEvent;
    }

    private void changeDoorState(Door door, boolean isOpen, String message) {
        door.setOpen(isOpen);
        System.out.println("Door " + door.getId() + message);
    }
}
