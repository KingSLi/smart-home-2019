package ru.sbt.mipt.oop.eventProdusers;

import ru.sbt.mipt.oop.SensorEvent;

import java.util.Collection;
import java.util.List;

public class ListConstEventProducer implements EventProducer {
    private List<SensorEvent> events;
    private Integer currEvent = 0;

    public ListConstEventProducer(List<SensorEvent> events) {
        this.events = events;
    }

    @Override
    public SensorEvent nextEvent() {
        if (!hasNext())
            return null;
        SensorEvent next = events.get(currEvent);
        currEvent++;
        return next;
    }

    @Override
    public boolean hasNext() {
        return currEvent < events.size();
    }
}
