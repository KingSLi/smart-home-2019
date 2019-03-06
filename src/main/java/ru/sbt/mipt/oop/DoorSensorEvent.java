package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;


public class DoorSensorEvent extends SensorEvent {
    public DoorSensorEvent(SensorEventType eventType, String objectId) {
        super(eventType, objectId);
    }

    @Override
    public void processSensorEvent(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(getObjectId())) {
                    if (getEventType() == DOOR_OPEN) {
                        changeDoorState(room, door, true, " was opened.");
                    } else {
                        changeDoorState(room, door, false, " was closed.");
                        if (room.getName().equals("hall")) {
                            closeHallDoorOffLights(smartHome);
                        }
                    }
                }
            }
        }

    }

    private void closeHallDoorOffLights(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private void changeDoorState(Room room, Door door, boolean isOpen, String message) {
        door.setOpen(isOpen);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + message);
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

}
