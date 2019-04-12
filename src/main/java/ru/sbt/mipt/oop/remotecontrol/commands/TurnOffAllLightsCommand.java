package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Light;

public class TurnOffAllLightsCommand extends Command {
    public TurnOffAllLightsCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (!(object instanceof Light))
                return;
            Light light = (Light) object;
            light.setOn(false);
        });
    }
}
