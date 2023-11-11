package ru.otus.vadim.ivanov.java.file;

public class FindCommand implements Command{
    private FileSystemReceiver fileReceiver;
    public FindCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        // команда find требует обязательный аргумент
        if(args.length != 1){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        fileReceiver.find(args[0]);
    }

    @Override
    public String getUsage() {

        return "find [filename] - find filename in current working directory or subdirectory";
    }
}
