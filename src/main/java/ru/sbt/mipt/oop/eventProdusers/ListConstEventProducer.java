package ru.sbt.mipt.oop.eventProdusers;

import ru.sbt.mipt.oop.event.Event;

import java.util.List;

public class ListConstEventProducer implements EventProducer {
    private List<Event> events;
    private Integer currEvent = 0;

    public ListConstEventProducer(List<Event> events) {
        this.events = events;
    }

    @Override
    public Event nextEvent() {
        if (!hasNext())
            return null;
        Event next = events.get(currEvent);
        currEvent++;
        return next;
    }

    @Override
    public boolean hasNext() {
        return currEvent < events.size();
    }
}
