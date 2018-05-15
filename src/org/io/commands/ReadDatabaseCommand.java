package org.io.commands;


import org.exceptions.InvalidCommandException;
import org.io.IOManager;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class ReadDatabaseCommand extends Command {

    public ReadDatabaseCommand(String input,
                               String[] data,
                               StudentsRepository repository,
                               Tester tester,
                               IOManager ioManager,
                               DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String fileName = this.getData()[1];
        this.getRepository().loadData(fileName);
    }
}
