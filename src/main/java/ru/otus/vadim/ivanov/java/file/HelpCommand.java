package ru.otus.vadim.ivanov.java.file;

public class HelpCommand implements Command{

    // в качестве ресивера используем Invoker
    // так как он знает все доступные команды
    private CommandInvoker commandReceiver;
    public HelpCommand(CommandInvoker commandReceiver) {
        this.commandReceiver = commandReceiver;
    }
    @Override
    public void execute(String[] args) {
        //проверяем что аргументов не больше одного
        if(args.length > 1){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        //выдаем инфу по конкретной команде
        if(args.length == 1){
            String commandUsage = commandReceiver.getCommandUsage(args[0]);
            if(commandUsage == null){
                System.out.println(String.format("No usage found for command %s !",args[0]));
                return;
            }
            System.out.println(String.format("%s usage:\n%s",args[0],commandUsage));
            return;
        }

        //без аргументов просто выводим список команд
        System.out.println("Available commands:");
        commandReceiver.getCommands().stream()
                .forEach(command -> System.out.println(command));
        System.out.println("Use > help [command] for detail");

    }

    @Override
    public String getUsage() {
        return "help - print all available commands\n" +
                "help [command] - print usage for command";
    }
}
