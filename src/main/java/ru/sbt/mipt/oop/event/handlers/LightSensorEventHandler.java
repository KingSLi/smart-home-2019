package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.homeInsides.Light;

import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_ON;

public class LightSensorEventHandler implements EventHandler {
    private SmartHome smartHome;

    public LightSensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event event) {
        if (!isSensortEvent(event)) {
            return;
        }
        SensorEvent currEvent = (SensorEvent) event;

        if (!isLightEvent(currEvent)) {
            return;
        }
        smartHome.execute(object -> {
            if (!(object instanceof Light))
                return;
            Light light = (Light) object;
            if (light.getId().equals(currEvent.getObjectId())) {
                if (currEvent.getEventType() == LIGHT_ON) {
                    changeLightState(light, true, " was turned on.");
                } else {
                    changeLightState(light, false, " was turned off.");
                }
            }
        });
    }

    private boolean isSensortEvent(Event event) {
        return event instanceof SensorEvent;
    }

    private boolean isLightEvent(SensorEvent sensorEvent) {
        return sensorEvent.getEventType() == LIGHT_OFF || sensorEvent.getEventType() == LIGHT_ON;
    }

    private void changeLightState(Light light, boolean isOn, String message) {
        light.setOn(isOn);
        System.out.println("Light " + light.getId() + message);
    }
}