package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.event.handlers.EventHandler;

public interface EventManager {
    void registerEventHandler(EventHandler handler);
    void start();
}
