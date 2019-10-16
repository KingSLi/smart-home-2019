package ru.sbt.mipt.oop.remotecontrol;

import jdk.internal.net.http.common.Pair;
import rc.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleRemoteControl implements RemoteControl {
    private Map<String, Command> buttons = new HashMap<>();
    private String rcId;

    public ConsoleRemoteControl(String rcId) {
        this.rcId = rcId;
    }

    public void addButtonToCommand(String code, Command command) {
        buttons.put(code, command);
    }

    public void configureButtonsToCommands(List<Pair<String, Command>> buttonsList) {
        buttonsList.forEach(
                buttonCommandConfig -> buttons.put(buttonCommandConfig.first, buttonCommandConfig.second));

    }

    @Override
    public void onButtonPressed(String buttonCode) {
        if (buttons.containsKey(buttonCode)) {
            buttons.get(buttonCode).execute();
        }
    }
}
