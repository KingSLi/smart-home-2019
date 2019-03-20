package ru.sbt.mipt.oop.commands;

public class SoutSenderCommand implements SenderCommand {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
