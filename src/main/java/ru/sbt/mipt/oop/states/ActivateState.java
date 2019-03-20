package ru.sbt.mipt.oop.states;

import ru.sbt.mipt.oop.homeInsides.Alarm;

public class ActivateState extends State {

    public ActivateState(Alarm alarm, Long code) {
        super(alarm);
        this.alarm.setCode(code);
    }

    @Override
    public void activate(Long code) {
        if (code.equals(alarm.getCode())) {
            System.out.println("[INFO]\t repeating activation");
        } else {
            switchToAlarm(alarm.getCode(), "trying activate when it already activate");
        }
    }

    @Override
    public void deActivate(Long code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivateState(alarm));
            System.out.println("[INFO]\t deactivation allowed");
        } else {
            switchToAlarm(alarm.getCode(), "deActivate with wrong code");
        }
    }
}
