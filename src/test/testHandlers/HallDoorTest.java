package testHandlers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commands.SenderCommand;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.event.handlers.CloseHallDoorEventHandler;
import ru.sbt.mipt.oop.event.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;

public class HallDoorTest {
    @Test
    void checkAllOff() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        CloseHallDoorEventHandler handler = new CloseHallDoorEventHandler(smartHome, new SenderCommandToTrash());

        handler.handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "4"));
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
        CloseHallDoorEventHandler handler = new CloseHallDoorEventHandler(smartHome, new SenderCommandToTrash());
        LightSensorEventHandler lightHandler = new LightSensorEventHandler(smartHome);

        lightHandler.handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, "4"));
        handler.handleEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "3"));
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
