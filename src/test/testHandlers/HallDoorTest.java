package testHandlers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.eventHandlers.CloseHallDoorHandler;
import ru.sbt.mipt.oop.eventHandlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.homeInsides.Light;

public class HallDoorTest {
    @Test
    void checkAllOff() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        CloseHallDoorHandler handler = new CloseHallDoorHandler(new SenderCommandToTrash());

        handler.handleEvent(smartHome, new SensorEvent(SensorEventType.DOOR_CLOSED, "4"));
        // check state
        smartHome.execute(object -> {
            if (!(object instanceof Light))
                return;
            Light light = (Light) object;
            Assert.assertFalse(light.isOn());
        });
    }
    @Test
    void checkNoHallDoorClosed() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        CloseHallDoorHandler handler = new CloseHallDoorHandler(new SenderCommandToTrash());
        LightSensorEventHandler lightHandler = new LightSensorEventHandler();

        lightHandler.handleEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_ON, "4"));
        handler.handleEvent(smartHome, new SensorEvent(SensorEventType.DOOR_CLOSED, "3"));
        // check state
        smartHome.execute(object -> {
            if (!(object instanceof Light))
                return;
            Light light = (Light) object;
            if (light.getId().equals("4"))
                Assert.assertTrue(light.isOn());
        });

    }
}

class SenderCommandToTrash implements SenderCommand {
    @Override
    public void sendCommand(SensorCommand command) {
        //System.out.println("Pretent we're sending command " + command);
    }
}
