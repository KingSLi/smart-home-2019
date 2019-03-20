package ru.sbt.mipt.oop.homeInsides;

import ru.sbt.mipt.oop.states.DeactivateState;
import ru.sbt.mipt.oop.states.State;

public class Alarm{
    private State state;
    private Long code;
    // код для деактивации сигнализации при попытке взломать систему
    private final Long ALARM_DEACTIVATION_CODE;


    public Alarm() {
        this(42L);
    }

    public Alarm(Long deactivationCode) {
        this.state = new DeactivateState(this);
        ALARM_DEACTIVATION_CODE = deactivationCode;
    }

    public void changeState(State state) {
        this.state = state;
    }



    public State getState() {
        return state;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }



    public void activate(Long code) {
        state.activate(code);
    }

    public void deActivate(Long code) {
        state.deActivate(code);
    }

    public void switchToAlarm() {
        state.switchToAlarm(ALARM_DEACTIVATION_CODE, "Some Event when alarm is on");
    }
}
