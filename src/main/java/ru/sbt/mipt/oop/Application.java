package ru.sbt.mipt.oop;


import jdk.internal.net.http.common.Pair;
import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.event.handlers.*;
import ru.sbt.mipt.oop.eventProdusers.EventProducer;
import ru.sbt.mipt.oop.eventProdusers.RandomEventProducer;
import ru.sbt.mipt.oop.homeinputoutput.JsonHomeReader;
import ru.sbt.mipt.oop.remotecontrol.ConsoleRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RCManager;
import ru.sbt.mipt.oop.remotecontrol.commands.CommandsType;
import ru.sbt.mipt.oop.remotecontrol.commands.TurnOffAllLightsCommand;
import ru.sbt.mipt.oop.remotecontrol.commands.TurnOnAllLightsCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = new JsonHomeReader("smart-home-1.js").readSmartHome();


        EventProducer eventProducer = new RandomEventProducer();
        ArrayList<EventHandler> handlers = createConfigHandlers(smartHome);

        RCManager rcManager = new RCManager(smartHome);
        ConsoleRemoteControl remoteControl = rcManager.createRController("42");
        remoteControl.addButtonToCommand("1", rcManager.createCommand(CommandsType.LIGHT_ON_ALL));
        remoteControl.addButtonToCommand("2", rcManager.createCommand(CommandsType.LIGHT_OFF_ALL));
        remoteControl.addButtonToCommand("3", rcManager.createCommand(CommandsType.HALL_LIGHT_ON));
        remoteControl.addButtonToCommand("4", rcManager.createCommand(CommandsType.ALARM_ACTIVATE));
        remoteControl.configureButtonsToCommands(
                Arrays.asList(
                        new Pair<>("11", rcManager.createCommand(CommandsType.LIGHT_ON_ALL)),
                        new Pair<>("2", new TurnOffAllLightsCommand(smartHome)),
                        new Pair<>("3", new TurnOnAllLightsCommand(smartHome))));


        while (eventProducer.hasNext()) {
            Event newEvent = eventProducer.nextEvent();
            System.out.println("Got event: " + newEvent);
            for (EventHandler handler : handlers) {
                handler.handleEvent(newEvent);
            }
        }
    }


    private static ArrayList<EventHandler> createConfigHandlers(SmartHome smartHome) {
        ArrayList<EventHandler> handlers = new ArrayList<>();
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new DoorSensorEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new LightSensorEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(new AlarmDecoratorSensorEventHandler(
                new CloseHallDoorEventHandler(smartHome), smartHome.getAlarm()));
        handlers.add(new AlarmEventHandler(smartHome));
        return handlers;
    }


//    RemoteController controller = new RemoteController("42");
//    SmartHome smartHome = context.getBean(SmartHome.class);
//        controller.linkButtonAndCommand("A", new TurnOnAllLights(smartHome, "42"));
//        controller.linkButtonAndCommand("B", new TurnOffAllLights(smartHome, "42"));
//    RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);
//        remoteControlRegistry.registerRemoteControl(controller,"42");
}
