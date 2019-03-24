package testHandlers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.handlers.DoorSensorEventHandler;
import ru.sbt.mipt.oop.homeInsides.Door;

public class DoorTest {
    @Test
    void DoorOn() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        DoorSensorEventHandler doorHandler = new DoorSensorEventHandler(smartHome);

        doorHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_OPEN, "1"));
        // check state
        smartHome.execute(object -> {
            if (!(object instanceof Door))
                return;
            Door Door = (Door) object;
            if (Door.getId().equals("1"))
                Assert.assertTrue(Door.isOpen());
        });
    }

    @Test
    void DoorOff() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        DoorSensorEventHandler DoorHandler = new DoorSensorEventHandler(smartHome);

        DoorHandler.handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "3"));
        // check state
        smartHome.execute(object -> {
            if (!(object instanceof Door))
                return;
            Door Door = (Door) object;
            if (Door.getId().equals("3"))
                Assert.assertFalse(Door.isOpen());
        });
    }
}
