package testHandlers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.handlers.LightSensorEventHandler;
import ru.sbt.mipt.oop.homeInsides.Light;

public class LightTest {
    @Test
    void lightOn() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        LightSensorEventHandler lightHandler = new LightSensorEventHandler(smartHome);

        lightHandler.handleEvent(new SensorEvent(SensorEventType.LIGHT_ON, "1"));
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
        LightSensorEventHandler lightHandler = new LightSensorEventHandler(smartHome);

        lightHandler.handleEvent(new SensorEvent(SensorEventType.LIGHT_OFF, "2"));
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
