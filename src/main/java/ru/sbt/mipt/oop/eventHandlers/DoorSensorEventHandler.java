package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Door;
import ru.sbt.mipt.oop.homeInsides.Room;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;


public class DoorSensorEventHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    if (sensorEvent.getEventType() == DOOR_OPEN) {
                        changeDoorState(room, door, true, " was opened.");
                    } else {
                        changeDoorState(room, door, false, " was closed.");
                    }
                }
            }
        }

    }

    private void changeDoorState(Room room, Door door, boolean isOpen, String message) {
        door.setOpen(isOpen);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + message);
    }
}
