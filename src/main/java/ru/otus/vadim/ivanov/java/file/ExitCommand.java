package ru.otus.vadim.ivanov.java.file;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        //команда exit не поддерживает аргументы
        if(args.length > 0){
            System.out.println(String.format("Command not support arguments! Use:\n %s",getUsage()));
            return;
        }
        System.exit(0);
    }

    @Override
    public String getUsage() {
        return "exit - exit from application";
    }
}
