package org.io.commands;

import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class PrintOrderedStudentsCommand extends Command {

    public PrintOrderedStudentsCommand(String input,
                                       String[] data,
                                       StudentsRepository repository,
                                       Tester tester,
                                       DirectoryManager ioManager,
                                       DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
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

//        StudentsRepository.printOrderedStudents(courseName, modifier, numberOfStudents);
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
            this.getRepository().orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.getRepository().orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            throw new InvalidCommandException(takeCommand);
            // OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }
}
