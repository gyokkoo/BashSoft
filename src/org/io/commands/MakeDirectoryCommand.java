package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidInputException;

@Alias("mkdir")
public class MakeDirectoryCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public MakeDirectoryCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String folderName = this.getData()[1];
        this.ioManager.createDirectoryInCurrentFolder(folderName);
    }
}
