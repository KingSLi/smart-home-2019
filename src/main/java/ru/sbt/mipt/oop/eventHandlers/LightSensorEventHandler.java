package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeInsides.Room;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightSensorEventHandler implements EventHandler {

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (!isLightEvent(sensorEvent)) {
            return;
        }
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(sensorEvent.getObjectId())) {
                    if (sensorEvent.getEventType() == LIGHT_ON) {
                        changeLightState(room, light, true, " was turned on.");
                    } else {
                        changeLightState(room, light, false, " was turned off.");
                    }
                }
            }
        }
    }

    private boolean isLightEvent(SensorEvent sensorEvent) {
        return sensorEvent.getEventType() == LIGHT_OFF || sensorEvent.getEventType() == LIGHT_ON;
    }

    private void changeLightState(Room room, Light light, boolean isOn, String message) {
        light.setOn(isOn);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + message);
    }
}