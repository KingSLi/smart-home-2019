package rccontrol;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.ActivateState;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;
import ru.sbt.mipt.oop.remotecontrol.ConsoleRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RCManager;
import ru.sbt.mipt.oop.remotecontrol.commands.CommandsType;

import static org.testng.Assert.assertTrue;

public class TestAlarm {

    @Test
    void CheckAlarmActivate() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        RCManager rcManager = new RCManager(smartHome);
        ConsoleRemoteControl consoleRemoteControl = rcManager.createRController("123");

        consoleRemoteControl.addButtonToCommand("A", rcManager.createCommand(CommandsType.ALARM_ACTIVATE));
        consoleRemoteControl.onButtonPressed("A");

        assertTrue(smartHome.getAlarm().getState() instanceof ActivateState);
    }

    @Test
    void CheckAlarmState() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        RCManager rcManager = new RCManager(smartHome);
        ConsoleRemoteControl consoleRemoteControl = rcManager.createRController("123");

        consoleRemoteControl.addButtonToCommand("A", rcManager.createCommand(CommandsType.ALARM_ACTIVATE_ALARM_STATE));
        consoleRemoteControl.onButtonPressed("A");

        assertTrue(smartHome.getAlarm().getState() instanceof AlarmState);
    }
}
