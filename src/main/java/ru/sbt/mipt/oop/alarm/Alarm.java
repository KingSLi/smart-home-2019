package ru.sbt.mipt.oop.alarm;

public class Alarm{
    private State state;
    private String code;
    // код для деактивации сигнализации при попытке взломать систему
    private final String ALARM_DEACTIVATION_CODE;


    public Alarm() {
        this("BestCodeHere");
    }

    public Alarm(String deactivationCode) {
        this.state = new DeactivateState(this);
        ALARM_DEACTIVATION_CODE = deactivationCode;
    }

    void changeState(State state) {
        this.state = state;
    }



    public State getState() {
        return state;
    }

    void setCode(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }



    public void activate(String code) {
        state.activate(code);
    }

    public void deActivate(String code) {
        state.deActivate(code);
    }

    public void switchToAlarm() {
        state.switchToAlarm(ALARM_DEACTIVATION_CODE, "Some Event when alarm is on");
    }
}
