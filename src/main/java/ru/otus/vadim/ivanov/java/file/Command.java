package ru.otus.vadim.ivanov.java.file;

public interface Command {

    void execute(String[] args);

    String getUsage();

}
