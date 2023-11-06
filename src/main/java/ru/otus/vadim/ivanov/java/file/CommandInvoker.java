package ru.otus.vadim.ivanov.java.file;

import java.util.HashMap;

public class CommandInvoker {

    private final HashMap<String,Command> commandMap = new HashMap();

    public void register(String commandName, Command command) {
        commandMap.put(commandName,command);
    }

    public void execute(String commandName, String[] args) {
        Command command = commandMap.get(commandName);
        if(command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute(args);
    }
}
