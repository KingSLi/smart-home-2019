package ru.sbt.mipt.oop.states;

import ru.sbt.mipt.oop.homeInsides.Alarm;

public abstract class State {
    Alarm alarm;

    State(Alarm alarm) {
        this.alarm = alarm;
    }

    public abstract void activate(Long code);
    public abstract void deActivate(Long code);

    public void switchToAlarm(Long activationCode, String errorMessage) {
        alarm.changeState(new AlarmState(alarm, activationCode));
        System.out.println("[ALARM]\t " + errorMessage);
    }
}
