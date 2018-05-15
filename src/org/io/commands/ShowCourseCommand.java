package org.io.commands;

import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class ShowCourseCommand extends Command {
    public ShowCourseCommand(String input,
                             String[] data,
                             StudentsRepository repository,
                             Tester tester,
                             DirectoryManager ioManager,
                             DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2 && this.getData().length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        if (this.getData().length == 2) {
            String courseName = this.getData()[1];
            this.getRepository().getStudentsByCourse(courseName);
        }

        if (this.getData().length == 3) {
            String courseName = this.getData()[1];
            String userName = this.getData()[2];
            this.getRepository().getStudentMarkInCourse(courseName, userName);
        }
    }
}
