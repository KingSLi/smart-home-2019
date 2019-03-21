package ru.sbt.mipt.oop.event;

public class SensorEvent implements Event {
    private final String objectId;
    private final EventType type;

    public SensorEvent(EventType type,  String objectId) {
        this.objectId = objectId;
        this.type = type;
    }

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
                "typeS=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
