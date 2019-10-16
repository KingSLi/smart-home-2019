package rccontrol;


import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;
import ru.sbt.mipt.oop.remotecontrol.ConsoleRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RCManager;
import ru.sbt.mipt.oop.remotecontrol.commands.CommandsType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLight {

    @Test
    void CheckTurnOnCommand() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        RCManager rcManager = new RCManager(smartHome);
        ConsoleRemoteControl consoleRemoteControl = rcManager.createRController("123");

        consoleRemoteControl.addButtonToCommand("A", rcManager.createCommand(CommandsType.LIGHT_ON_ALL));
        consoleRemoteControl.onButtonPressed("A");

        smartHome.execute(obj -> {
            if (obj instanceof Light) {
                assertFalse(((Light) obj).isOn());
            }
        });
    }

    @Test
    void CheckTurnOffCommand() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        RCManager rcManager = new RCManager(smartHome);
        ConsoleRemoteControl consoleRemoteControl = rcManager.createRController("123");

        consoleRemoteControl.addButtonToCommand("A", rcManager.createCommand(CommandsType.LIGHT_OFF_ALL));
        consoleRemoteControl.onButtonPressed("A");

        smartHome.execute(obj -> {
            if (obj instanceof Light) {
                assertTrue(((Light) obj).isOn());
            }
        });
    }
}
