package ru.otus.vadim.ivanov.java.file;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("exit");
        System.exit(0);
    }

    @Override
    public String getUsage() {
        return "exit command";
    }
}
