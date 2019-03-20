package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;
import ru.sbt.mipt.oop.homeInsides.Alarm;
import ru.sbt.mipt.oop.homeInsides.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        this(new ArrayList<>());
    }

    public SmartHome(Collection<Room> rooms) {
        this(rooms, new Alarm());
    }

    public SmartHome(Alarm alarm) {
        this(new ArrayList<>(), alarm);
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.alarm = alarm;
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        rooms.forEach(room -> room.execute(action));
    }
}
