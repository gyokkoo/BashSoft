package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.Database;
import org.exceptions.InvalidCommandException;

@Alias("order")
public class PrintOrderedStudentsCommand extends Command {

    @Inject
    private Database repository;

    public PrintOrderedStudentsCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 5) {
            throw new InvalidCommandException(this.getInput());
        }

        String courseName = this.getData()[1];
        String orderType = this.getData()[2].toLowerCase();
        String takeCommand = this.getData()[3].toLowerCase();
        String takeQuantity = this.getData()[4].toLowerCase();

//        Database.printOrderedStudents(courseName, modifier, numberOfStudents);
        this.tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);
    }

    private void tryParseParametersForOrder(
            String takeCommand, String takeQuantity,
            String courseName, String orderType) {
        if (!takeCommand.equals("take")) {
            throw new InvalidCommandException(takeCommand);
            //  OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_COMMAND);
        }

        if (takeQuantity.equals("all")) {
            this.repository.orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.repository.orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            throw new InvalidCommandException(takeCommand);
            // OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }
}
