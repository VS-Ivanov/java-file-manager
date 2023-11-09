package ru.otus.vadim.ivanov.java.file;

import java.util.Arrays;
import java.util.Scanner;

public class FileManager {

    private static String clientLine;
    public static void main(String[] args) {
        System.out.println("Welcome to console file manager!");

        FileSystemReceiver fileReceiver = new FileSystemReceiver();

        CommandInvoker invoker = new CommandInvoker();
        invoker.register("exit",new ExitCommand());
        invoker.register("pwd",new PwdCommand(fileReceiver));
        invoker.register("cd",new CdCommand(fileReceiver));
        invoker.register("ls",new LsCommand(fileReceiver));
        invoker.register("mkdir",new MkdirCommand(fileReceiver));
        invoker.register("rm",new RmCommand(fileReceiver));
        invoker.register("mv",new MvCommand(fileReceiver));
        invoker.register("cp",new CpCommand(fileReceiver));
        invoker.register("finfo",new FinfoCommand(fileReceiver));
        invoker.register("find",new FindCommand(fileReceiver));
        invoker.register("help",new HelpCommand(invoker));

        Scanner sc = new Scanner(System.in);

        while(true) {
            try {
                System.out.print("> ");
                String[] parseResult = CommandParser.parse(sc.nextLine());
                //System.out.println(String.format("You enter command: %s", parseResult[0]));
                invoker.execute(parseResult[0], Arrays.copyOfRange(parseResult, 1, parseResult.length));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
