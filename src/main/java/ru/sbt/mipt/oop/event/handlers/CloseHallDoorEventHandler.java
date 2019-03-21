package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SenderCommand;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SoutSenderCommand;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.homeInsides.Door;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeInsides.Room;

public class CloseHallDoorEventHandler implements EventHandler {
    private final SmartHome smartHome;
    private final SenderCommand senderCommand;

    public CloseHallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.senderCommand = new SoutSenderCommand();
    }

    public CloseHallDoorEventHandler(SmartHome smartHome, SenderCommand senderCommand) {
        this.smartHome = smartHome;
        this.senderCommand = senderCommand;
    }

    @Override
    public void handleEvent(Event event) {
        if (!(event instanceof SensorEvent)) {
            return;
        }
        SensorEvent sensorEvent = (SensorEvent) event;

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
