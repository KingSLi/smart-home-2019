package ru.sbt.mipt.oop.event;

public class AlarmEvent implements Event {
    private final String code;
    private final AlarmEventType type;

    public AlarmEvent(AlarmEventType type, String code) {
        this.code = code;
        this.type = type;
    }

    @Override
    public EventType getEventType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AlarmEvent{" +
                "typeS=" + type + '}';
    }
}
