package ru.otus.vadim.ivanov.java.file;

import java.util.Arrays;
import java.util.Scanner;

public class FileManager {

    private static String clientLine;
    public static void main(String[] args) {
        System.out.println("Start");

        FileSystemReceiver fileReceiver = new FileSystemReceiver();

        CommandInvoker invoker = new CommandInvoker();
        invoker.register("exit",new ExitCommand());
        invoker.register("pwd",new PwdCommand(fileReceiver));
        invoker.register("cd",new CdCommand(fileReceiver));

        Scanner sc = new Scanner(System.in);

        while(true) {
            String[] parseResult = CommandParser.parse(sc.nextLine());
            System.out.println(String.format("You enter command: %s",parseResult[0]));
            invoker.execute(parseResult[0], Arrays.copyOfRange(parseResult,1,parseResult.length));
        }
    }
}
