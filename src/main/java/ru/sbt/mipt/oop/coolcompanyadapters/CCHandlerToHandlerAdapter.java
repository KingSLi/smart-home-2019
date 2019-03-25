package ru.sbt.mipt.oop.coolcompanyadapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;

public class CCHandlerToHandlerAdapter implements EventHandler {

    private final ru.sbt.mipt.oop.event.handlers.EventHandler eventHandler;

    public CCHandlerToHandlerAdapter(ru.sbt.mipt.oop.event.handlers.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventHandler.handleEvent(new CCSensorToEventAdapter(event));
    }
}
