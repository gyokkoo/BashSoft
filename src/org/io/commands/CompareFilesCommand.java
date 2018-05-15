package org.io.commands;

import org.exceptions.InvalidCommandException;
import org.io.IOManager;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class CompareFilesCommand extends Command {

    public CompareFilesCommand(String input,
                               String[] data,
                               StudentsRepository repository,
                               Tester tester,
                               IOManager ioManager,
                               DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        String firstPath = this.getData()[1];
        String secondPath = this.getData()[2];
        this.getTester().compareContent(firstPath, secondPath);
    }
}
