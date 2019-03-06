package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightSensorEvent extends SensorEvent{

    public LightSensorEvent(SensorEventType eventType, String objectId) {
        super(eventType, objectId);
    }


    @Override
    public void processSensorEvent(SmartHome smartHome) {
        // событие от источника света
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(getObjectId())) {
                    if (getEventType() == LIGHT_ON) {
                        changeLightState(room, light, true, " was turned on.");
                    } else {
                        changeLightState(room, light, false, " was turned off.");
                    }
                }
            }
        }
    }

    private void changeLightState(Room room, Light light, boolean isOn, String message) {
        light.setOn(isOn);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + message);
    }
}