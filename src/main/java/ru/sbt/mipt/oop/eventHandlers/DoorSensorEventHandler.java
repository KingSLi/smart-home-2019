package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Door;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;


public class DoorSensorEventHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {

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

    private void changeDoorState(Door door, boolean isOpen, String message) {
        door.setOpen(isOpen);
        System.out.println("Door " + door.getId() + message);
    }
}
