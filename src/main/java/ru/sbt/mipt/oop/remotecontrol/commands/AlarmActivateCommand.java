package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;

public class AlarmActivateCommand extends Command {
    public AlarmActivateCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate("DEFAULT_CODE");
    }
}
