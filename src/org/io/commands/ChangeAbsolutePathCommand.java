package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;

@Alias("cdabs")
public class ChangeAbsolutePathCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public ChangeAbsolutePathCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String absolutePath = this.getData()[1];
        this.ioManager.changeCurrentDirAbsolute(absolutePath);
    }
}
