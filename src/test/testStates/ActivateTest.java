package testStates;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.handlers.AlarmEventHandler;
import ru.sbt.mipt.oop.event.handlers.EventHandler;
import ru.sbt.mipt.oop.eventProdusers.ListConstEventProducer;
import ru.sbt.mipt.oop.homeInsides.Door;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeInsides.Room;
import ru.sbt.mipt.oop.alarm.ActivateState;
import ru.sbt.mipt.oop.alarm.AlarmState;

import java.util.Arrays;

public class ActivateTest {
    SmartHome generateDefaultHome() {
        SmartHome smartHome = new SmartHome();
        Room one = new Room(
                Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "one");
        smartHome.addRoom(one);
        return smartHome;
    }

    @Test
    void activateWhenDeactivateState() {
        SmartHome smartHome = generateDefaultHome();
        ListConstEventProducer events = new ListConstEventProducer(Arrays.asList(
                new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "1")));

        EventHandler handler = new AlarmEventHandler(smartHome);
        while(events.hasNext()) {
            Event event = events.nextEvent();
            handler.handleEvent(event);
        }
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof ActivateState);
    }

    @Test
    void activateWhenAlarmState() {
        SmartHome smartHome = generateDefaultHome();
        EventHandler handler = new AlarmEventHandler(smartHome);
        ListConstEventProducer events = new ListConstEventProducer(Arrays.asList(
                new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "1"),
                new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "2"),
                new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "1")));

        while(events.hasNext()) {
            Event event = events.nextEvent();
            handler.handleEvent(event);
        }
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof AlarmState);
    }

    @Test
    void activateWhenActivateState() {
        SmartHome smartHome = generateDefaultHome();
        ListConstEventProducer events = new ListConstEventProducer(Arrays.asList(
                new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "1"),
                new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "1"),
                new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "2")));
        EventHandler handler = new AlarmEventHandler(smartHome);

        handler.handleEvent(events.nextEvent());

        handler.handleEvent(events.nextEvent());
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof ActivateState);

        handler.handleEvent(events.nextEvent());
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof AlarmState);
    }
}