package testStates;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventHandlers.*;
import ru.sbt.mipt.oop.eventProdusers.ListConstEventProducer;
import ru.sbt.mipt.oop.homeInsides.Door;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeInsides.Room;
import ru.sbt.mipt.oop.states.ActivateState;
import ru.sbt.mipt.oop.states.AlarmState;
import ru.sbt.mipt.oop.states.DeactivateState;

import java.util.Arrays;


public class DeactivateTest {
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
    void deactivateWithRightCode() {
        SmartHome smartHome = generateDefaultHome();
        ListConstEventProducer events = new ListConstEventProducer(Arrays.asList(
                        new SensorEvent(SensorEventType.ALARM_ACTIVATE, "1"),
                        new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "1")));

        EventHandler handler = new AlarmEventHandler();
        while(events.hasNext()) {
            SensorEvent event = events.nextEvent();
            handler.handleEvent(smartHome, event);
        }
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof DeactivateState);
    }

    @Test
    void deactivateWithBadCode() {
        SmartHome smartHome = generateDefaultHome();
        ListConstEventProducer events = new ListConstEventProducer(Arrays.asList(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, "1"),
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "2")));
        EventHandler handler = new AlarmEventHandler();
        while(events.hasNext()) {
            SensorEvent event = events.nextEvent();
            handler.handleEvent(smartHome, event);
        }
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof AlarmState);
    }

    @Test
    void deactivateWhenAlarmState() {
        SmartHome smartHome = generateDefaultHome();
        ListConstEventProducer events = new ListConstEventProducer(Arrays.asList(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, "1"),
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "2"),
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "2"),
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "1")));

        EventHandler handler = new AlarmEventHandler();

        handler.handleEvent(smartHome, events.nextEvent());
        handler.handleEvent(smartHome, events.nextEvent());

        handler.handleEvent(smartHome, events.nextEvent());
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof AlarmState);

        handler.handleEvent(smartHome, events.nextEvent());
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof DeactivateState);
    }
}
