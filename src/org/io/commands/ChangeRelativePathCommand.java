package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidInputException;

@Alias("cdrel")
public class ChangeRelativePathCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public ChangeRelativePathCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String relativePath = data[1];
        this.ioManager.changeCurrentDirRelativePath(relativePath);
    }
}
