package rccontrol;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.homeInsides.Door;
import ru.sbt.mipt.oop.homeInsides.Light;
import ru.sbt.mipt.oop.homeInsides.Room;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;
import ru.sbt.mipt.oop.remotecontrol.ConsoleRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RCManager;
import ru.sbt.mipt.oop.remotecontrol.commands.CommandsType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestHallEvent {
    @Test
    void CheckTurnOnHallLightCommand() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        RCManager rcManager = new RCManager(smartHome);
        ConsoleRemoteControl consoleRemoteControl = rcManager.createRController("123");

        consoleRemoteControl.addButtonToCommand("A", rcManager.createCommand(CommandsType.HALL_LIGHT_ON));
        consoleRemoteControl.onButtonPressed("A");

        smartHome.execute(obj -> {
            if (obj instanceof Room) {
                Room room = (Room) obj;
                if (room .getName().equals("hall")) {
                    room.execute(obj1 -> {
                        if (obj1 instanceof Light) {
                            assertFalse(((Light) obj1).isOn());
                        }
                    });
                }
            }
        });
    }


    @Test
    void CheckCloseHallLightCommand() {
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();
        RCManager rcManager = new RCManager(smartHome);
        ConsoleRemoteControl consoleRemoteControl = rcManager.createRController("123");

        consoleRemoteControl.addButtonToCommand("A", rcManager.createCommand(CommandsType.CLOSE_HALL_DOOR));
        consoleRemoteControl.onButtonPressed("A");

        smartHome.execute(obj -> {
            if (obj instanceof Room) {
                Room room = (Room) obj;
                if (room .getName().equals("hall")) {
                    room.execute(obj1 -> {
                        if (obj1 instanceof Door) {
                            assertTrue(((Door) obj1).isOpen());
                        }
                    });
                }
            }
        });
    }
}
