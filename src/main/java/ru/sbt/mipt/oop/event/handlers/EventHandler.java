package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.event.Event;

public interface EventHandler {
    void handleEvent(Event sensorEvent);
}
