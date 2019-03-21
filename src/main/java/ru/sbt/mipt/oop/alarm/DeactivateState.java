package ru.sbt.mipt.oop.alarm;

public class DeactivateState extends State {
    public DeactivateState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String code) {
        alarm.changeState(new ActivateState(alarm, code));
        System.out.println("[INFO]\t activating is ok");
    }

    @Override
    public void deActivate(String code) {
        System.out.println("[INFO]\t repeating deActivate");
    }
}
