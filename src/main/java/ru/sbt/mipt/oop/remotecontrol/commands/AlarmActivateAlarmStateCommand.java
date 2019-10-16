package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmActivateAlarmStateCommand implements Command {
    private Alarm alarm;

    public AlarmActivateAlarmStateCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.switchToAlarm();
    }
}
