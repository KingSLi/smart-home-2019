package ru.sbt.mipt.oop.eventProdusers;

import ru.sbt.mipt.oop.event.*;

public class RandomEventProducer implements EventProducer {
    private Double passLevel;
    private Double current_proba;

    public RandomEventProducer() {
        passLevel = 0.05;
    }

    public RandomEventProducer(Double passLevel) {
        this.passLevel = passLevel;
    }

    @Override
    public Event nextEvent() {
        if (current_proba <= passLevel) {
            return  null;
        }
        if (Math.random() > 0.35) {
            SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.values().length * Math.random())];
            String objectId = "" + ((int) (10 * Math.random()));
            return new SensorEvent(sensorEventType, objectId);
        }
        AlarmEventType alarmEventType = AlarmEventType.values()[(int) (AlarmEventType.values().length * Math.random())];
        String code = "" + ((int) (100 * Math.random()));
        return new SensorEvent(alarmEventType, code);

    }

    @Override
    public boolean hasNext() {
        current_proba = Math.random();
        return  current_proba > passLevel;
    }
}
