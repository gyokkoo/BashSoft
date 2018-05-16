package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.Database;
import org.exceptions.InvalidCommandException;

@Alias("show")
public class ShowCourseCommand extends Command {

    @Inject
    private Database repository;

    public ShowCourseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2 && this.getData().length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        if (this.getData().length == 2) {
            String courseName = this.getData()[1];
            this.repository.getStudentsByCourse(courseName);
        }

        if (this.getData().length == 3) {
            String courseName = this.getData()[1];
            String userName = this.getData()[2];
            this.repository.getStudentMarkInCourse(courseName, userName);
        }
    }
}
