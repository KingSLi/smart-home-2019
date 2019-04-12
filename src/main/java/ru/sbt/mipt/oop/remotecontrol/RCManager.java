package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.commands.*;

public class RCManager {
    public final SmartHome smartHome;
    private String rcId = "0";

    public RCManager(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public ConsoleRemoteControl createRController(String rcid) {
        System.out.println("Created controller with ID: " + rcid);
        this.rcId = rcid;
        return new ConsoleRemoteControl(rcId);
    }

    public Command createCommand(CommandsType commandType) {
        switch (commandType) {
            case LIGHT_ON_ALL:
                return new TurnOnAllLightsCommand(smartHome);
            case LIGHT_OFF_ALL:
                return new TurnOffAllLightsCommand(smartHome);
            case ALARM_ACTIVATE:
                return new AlarmActivateCommand(smartHome);
            case ALARM_ACTIVATE_ALARM_STATE:
                return new AlarmActivateAlarmStateCommand(smartHome);
            case HALL_LIGHT_ON:
                return new TurnOnHallLightsCommand(smartHome);
            case CLOSE_HALL_DOOR:
                return new CloseHallDoorCommand(smartHome);
            default:
                return null;
        }
    }
}
