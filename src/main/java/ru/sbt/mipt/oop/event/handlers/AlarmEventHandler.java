package ru.sbt.mipt.oop.event.handlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.event.*;

public class AlarmEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public AlarmEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(Event event) {
        if (!isAlarmEvent(event)) {
            return;
        }
        AlarmEvent alarmEvent = (AlarmEvent) event;
        switch ((AlarmEventType) alarmEvent.getEventType()) {
            case ALARM_DEACTIVATE:
                smartHome.getAlarm().deActivate(alarmEvent.getCode());break;
            case ALARM_ACTIVATE:
                smartHome.getAlarm().activate(alarmEvent.getCode());break;
        }
    }

    private boolean isAlarmEvent(Event event) {
        return event instanceof AlarmEvent;
    }
}
