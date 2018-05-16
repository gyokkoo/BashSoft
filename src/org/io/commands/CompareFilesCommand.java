package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.ContentComparer;
import org.exceptions.InvalidCommandException;

@Alias("cmp")
public class CompareFilesCommand extends Command {

    @Inject
    private ContentComparer tester;

    public CompareFilesCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        String firstPath = this.getData()[1];
        String secondPath = this.getData()[2];
        this.tester.compareContent(firstPath, secondPath);
    }
}
