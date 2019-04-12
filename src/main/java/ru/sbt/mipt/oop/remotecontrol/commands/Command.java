package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.RCManager;

public abstract class Command {
    SmartHome smartHome;

    public Command(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public abstract void execute();
}
