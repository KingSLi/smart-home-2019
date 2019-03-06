package ru.sbt.mipt.oop;

public abstract class SensorEvent {
    private final String objectId;
    private final SensorEventType eventType;

    public SensorEvent(SensorEventType eventType, String objectId) {
        this.objectId = objectId;
        this.eventType = eventType;
    }

    public SensorEventType getEventType() {
        return eventType;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "typeS=" + eventType +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public abstract void processSensorEvent(SmartHome smartHome);

}
