package ru.sbt.mipt.oop.states;

import ru.sbt.mipt.oop.homeInsides.Alarm;

public class DeactivateState extends State {
    public DeactivateState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(Long code) {
        alarm.changeState(new ActivateState(alarm, code));
        System.out.println("[INFO]\t activating is ok");
    }

    @Override
    public void deActivate(Long code) {
        System.out.println("[INFO]\t repeating deActivate");
    }
}
