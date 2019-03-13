package testHandlers;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.JsonHomeReader;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventHandlers.CloseHallDoorHandler;
import ru.sbt.mipt.oop.homeInsides.Light;

class CloseHallDoorHandlerTest  {

    @Test
    void checkAllOff() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        CloseHallDoorHandler handler = new CloseHallDoorHandler();

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        handler.handleEvent(smartHome, event);

        boolean someOn = false;
        smartHome.execute(object -> {
            if (!(object instanceof Light))
                return;
            Light light = (Light) object;
            Assert.assertTrue(light.isOn());
        });

    }
}