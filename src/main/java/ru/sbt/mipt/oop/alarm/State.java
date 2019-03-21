package ru.sbt.mipt.oop.alarm;

public abstract class State {
    Alarm alarm;

    State(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void activate(String code);
    public abstract void deActivate(String code);

    public void switchToAlarm(String activationCode, String errorMessage) {
        alarm.changeState(new AlarmState(alarm, activationCode));
        System.out.println("[ALARM]\t " + errorMessage);
    }
}
