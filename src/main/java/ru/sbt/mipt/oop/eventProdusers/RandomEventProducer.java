package ru.sbt.mipt.oop.eventProdusers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

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
    public SensorEvent nextEvent() {
        if (current_proba <= passLevel) {
            return  null;
        }
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    @Override
    public boolean hasNext() {
        current_proba = Math.random();
        return  current_proba > passLevel;
    }
}
