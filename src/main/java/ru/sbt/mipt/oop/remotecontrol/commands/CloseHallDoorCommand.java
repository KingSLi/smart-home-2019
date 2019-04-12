package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Room;

public class CloseHallDoorCommand extends Command {
    public CloseHallDoorCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (!(object instanceof Room))
                return;
            Room room = (Room) object;
            room.getDoors().forEach(door -> door.setOpen(false));
        });
    }
}
