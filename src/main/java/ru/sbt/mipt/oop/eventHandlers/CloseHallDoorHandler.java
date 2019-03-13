package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.homeInsides.Door;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeInsides.Room;

public class CloseHallDoorHandler implements EventHandler {
    private final SenderCommand senderCommand;

    public CloseHallDoorHandler() {
        this.senderCommand = new SoutSenderCommand();
    }

    public CloseHallDoorHandler(SenderCommand senderCommand) {
        this.senderCommand = senderCommand;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(sensorEvent.getObjectId()) && room.getName().equals("hall")) {
                    offAllLights(smartHome);
                }
            }
        }

    }

    private void offAllLights(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                senderCommand.sendCommand(command);
            }
        }
    }




}
