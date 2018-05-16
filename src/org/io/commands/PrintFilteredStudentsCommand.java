package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.Database;
import org.exceptions.InvalidCommandException;

@Alias("filter")
public class PrintFilteredStudentsCommand extends Command {

    @Inject
    private Database repository;

    public PrintFilteredStudentsCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 5) {
            throw new InvalidCommandException(this.getInput());
        }

        String course = this.getData()[1];
        String filter = this.getData()[2].toLowerCase();
        String takeCommand = this.getData()[3].toLowerCase();
        String takeQuantity = this.getData()[4].toLowerCase();

        this.tryParseParametersForFilter(takeCommand, takeQuantity, course, filter);
    }

    private void tryParseParametersForFilter(
            String takeCommand, String takeQuantity,
            String courseName, String filter) {
        if (!takeCommand.equals("take")) {
            throw new InvalidCommandException(takeCommand);
        }

        if (takeQuantity.equals("all")) {
            this.repository.filterAndTake(courseName, filter);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.repository.filterAndTake(courseName, filter, studentsToTake);
        } catch (NumberFormatException nfe) {
            throw new InvalidCommandException(takeCommand);
        }
    }
}