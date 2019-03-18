package ru.sbt.mipt.oop.eventProdusers;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventProducer {
    SensorEvent nextEvent();
    boolean hasNext();
}
