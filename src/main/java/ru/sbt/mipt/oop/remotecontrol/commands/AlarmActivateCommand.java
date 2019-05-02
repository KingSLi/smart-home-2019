package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmActivateCommand implements Command {
    private Alarm alarm;

    public AlarmActivateCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.activate("DEFAULT_CODE");
    }
}
