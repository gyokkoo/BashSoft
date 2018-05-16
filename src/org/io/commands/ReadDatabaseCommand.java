package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.Database;
import org.exceptions.InvalidCommandException;

@Alias("readdb")
public class ReadDatabaseCommand extends Command {

    @Inject
    private Database repository;

    public ReadDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String fileName = this.getData()[1];
        this.repository.loadData(fileName);
    }
}
