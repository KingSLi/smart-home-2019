package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;

public class AlarmActivateAlarmStateCommand extends Command {
    public AlarmActivateAlarmStateCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute() {
        smartHome.getAlarm().switchToAlarm();
    }
}
