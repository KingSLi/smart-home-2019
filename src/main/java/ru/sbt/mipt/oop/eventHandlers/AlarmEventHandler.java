package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.states.AlarmState;

public class AlarmEventHandler implements EventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        if (!isAlarmEvent(sensorEvent)) {
            return;
        }
        Long code = Long.parseLong(sensorEvent.getObjectId());
        switch (sensorEvent.getEventType()) {
            case ALARM_DEACTIVATE:
                smartHome.getAlarm().deActivate(code);break;
            case ALARM_ACTIVATE:
                smartHome.getAlarm().activate(code);break;
        }

        if (smartHome.getAlarm().getState() instanceof AlarmState) {
            alarmsall();
        }
    }

    private void alarmsall() {
        // todo: alalrm all
    }

    private boolean isAlarmEvent(SensorEvent sensorEvent) {
        return sensorEvent.getEventType() == SensorEventType.ALARM_ACTIVATE ||
        sensorEvent.getEventType() == SensorEventType.ALARM_DEACTIVATE;
    }
}
