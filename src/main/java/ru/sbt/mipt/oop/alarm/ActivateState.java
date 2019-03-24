package ru.sbt.mipt.oop.alarm;

public class ActivateState extends State {

    public ActivateState(Alarm alarm, String code) {
        super(alarm);
        this.alarm.setCode(code);
    }

    @Override
    public void activate(String code) {
        if (code.equals(alarm.getCode())) {
            System.out.println("[INFO]\t repeating activation");
        } else {
            switchToAlarm(alarm.getCode(), "trying activate when it already activate");
        }
    }

    @Override
    public void deActivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivateState(alarm));
            System.out.println("[INFO]\t deactivation allowed");
        } else {
            switchToAlarm(alarm.getCode(), "deActivate with wrong code");
        }
    }
}
