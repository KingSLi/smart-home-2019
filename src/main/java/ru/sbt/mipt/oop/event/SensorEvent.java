package ru.sbt.mipt.oop.event;

public class SensorEvent implements Event {
    private String objectId;
    private EventType type;

    public SensorEvent(EventType type,  String objectId) {
        this.objectId = objectId;
        this.type = type;
    }

    public SensorEvent() {}

    @Override
    public EventType getEventType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "typeS=" + getEventType() +
                ", objectId='" + getObjectId() + '\'' +
                '}';
    }
}
