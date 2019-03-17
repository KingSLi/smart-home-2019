package testHandlers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.JsonHomeReader;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventHandlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.homeInsides.Light;

public class LightTest {
    @Test
    void lightOn() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        LightSensorEventHandler lightHandler = new LightSensorEventHandler();

        lightHandler.handleEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_ON, "1"));
        // check state
        smartHome.execute(object -> {
            if (!(object instanceof Light))
                return;
            Light light = (Light) object;
            if (light.getId().equals("1"))
                Assert.assertTrue(light.isOn());
        });
    }

    @Test
    void lightOff() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        LightSensorEventHandler lightHandler = new LightSensorEventHandler();

        lightHandler.handleEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_OFF, "2"));
        // check state
        smartHome.execute(object -> {
            if (!(object instanceof Light))
                return;
            Light light = (Light) object;
            if (light.getId().equals("2"))
                Assert.assertFalse(light.isOn());
        });
    }
}
