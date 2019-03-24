package ru.sbt.mipt.oop.alarm;

public class AlarmState extends State {
    public AlarmState(Alarm alarm, String code) {
        super(alarm);
        this.alarm.setCode(code);
    }

    @Override
    public void activate(String code) {
        System.out.println("[ALARM]\t ALARM is already active!!!");
    }

    @Override
    public void deActivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivateState(alarm));
            System.out.println("[INFO]\t Deactivating Alarm state complete success");
        } else {
            System.out.println("[ALARM]\t WRONG code to deactivate alarm!!!");
        }
    }
}
