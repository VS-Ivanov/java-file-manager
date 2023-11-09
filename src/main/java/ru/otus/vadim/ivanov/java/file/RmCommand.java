package ru.otus.vadim.ivanov.java.file;

public class RmCommand implements Command{

    private FileSystemReceiver fileReceiver;

    public RmCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        // команда rm требует обязательный аргумент
        if(args.length != 1){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        fileReceiver.rm(args[0]);

    }

    @Override
    public String getUsage() {
        return "rm [filename] - remove file or directory (with content)";
    }
}
