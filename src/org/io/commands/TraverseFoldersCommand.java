package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;

@Alias("ls")
public class TraverseFoldersCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public TraverseFoldersCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 1 && this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        if (this.getData().length == 1) {
            this.ioManager.traverseDirectory(0);
        }

        if (this.getData().length == 2) {
            this.ioManager.traverseDirectory(Integer.valueOf(this.getData()[1]));
        }
    }
}
