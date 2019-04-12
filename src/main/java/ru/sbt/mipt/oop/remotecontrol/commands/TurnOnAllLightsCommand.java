package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Light;

public class TurnOnAllLightsCommand extends Command {
    public TurnOnAllLightsCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                ((Light) object).setOn(true);
            }
        });
    }
}
