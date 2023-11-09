package ru.otus.vadim.ivanov.java.file;

public class FinfoCommand implements Command{
    private FileSystemReceiver fileReceiver;
    public FinfoCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        // команда finfo требует обязательный аргумент
        if(args.length != 1){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        fileReceiver.finfo(args[0]);
    }

    @Override
    public String getUsage() {

        return "finfo [filename] - print detailed information about file or directory";
    }
}
