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
        if (sensorEvent.getEventType() != SensorEventType.DOOR_CLOSED) {
            return;
        }

        smartHome.execute(object -> {
            if (!(object instanceof Room))
                return;
            Room room = (Room) object;
            if (!room.getName().equals("hall"))
                return;
            boolean isDoorInHall = false;
            for (Door door : room.getDoors()) {
                if (sensorEvent.getObjectId().equals(door.getId())) {
                    isDoorInHall = true;
                }
            }
            if (isDoorInHall) {
                smartHome.execute(object1 -> {
                    if (!(object1 instanceof Light)) {
                        return;
                    }
                    Light light = (Light) object1;
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    senderCommand.sendCommand(command);
                });
            }
        });
    }
}
