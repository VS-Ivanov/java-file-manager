package ru.otus.vadim.ivanov.java.file;

import java.util.*;

public class CommandInvoker {

    private final HashMap<String,Command> commandMap = new HashMap();

    public void register(String commandName, Command command) {
        commandMap.put(commandName,command);
    }

    public void execute(String commandName, String[] args) {
        Command command = commandMap.get(commandName);
        if(command == null) {
            //throw new IllegalStateException("no command registered for " + commandName);
            System.out.println(String.format("Command %s not found! Use > help for view all available commands.",commandName));
            return;
        }
        command.execute(args);
    }

    //возвращаем список доступных команд
    public Set<String> getCommands() {
        return commandMap.keySet();
    }

    //возвращаем usage по команде
    public String getCommandUsage(String commandName){
        Command command = commandMap.get(commandName);
        if(command != null) {
            return command.getUsage();
        }
        return null;
    }
}
