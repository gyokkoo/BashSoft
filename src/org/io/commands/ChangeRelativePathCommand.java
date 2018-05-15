package org.io.commands;


import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class ChangeRelativePathCommand extends Command {

    public ChangeRelativePathCommand(String input,
                                     String[] data,
                                     StudentsRepository repository,
                                     Tester tester,
                                     DirectoryManager ioManager,
                                     DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String relativePath = this.getData()[1];
        this.getIoManager().changeCurrentDirRelativePath(relativePath);
    }
}
