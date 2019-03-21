package ru.sbt.mipt.oop.eventProdusers;

import ru.sbt.mipt.oop.event.Event;

public interface EventProducer {
    Event nextEvent();
    boolean hasNext();
}
