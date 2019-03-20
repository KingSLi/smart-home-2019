package ru.sbt.mipt.oop.states;

import ru.sbt.mipt.oop.homeInsides.Alarm;

public class AlarmState extends State {
    public AlarmState(Alarm alarm, Long code) {
        super(alarm);
        this.alarm.setCode(code);
    }

    @Override
    public void activate(Long code) {
        System.out.println("[ALARM]\t ALARM is already active!!!");
    }

    @Override
    public void deActivate(Long code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivateState(alarm));
            System.out.println("[INFO]\t Deactivating Alarm state complete success");
        } else {
            System.out.println("[ALARM]\t WRONG code to deactivate alarm!!!");
        }
    }
}
