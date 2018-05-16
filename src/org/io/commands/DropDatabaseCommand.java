package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.Database;
import org.exceptions.InvalidCommandException;
import org.io.OutputWriter;

@Alias("dropdb")
public class DropDatabaseCommand extends Command {

    @Inject
    private Database repository;

    public DropDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 1) {
            throw new InvalidCommandException(this.getInput());
        }

        this.repository.unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }
}