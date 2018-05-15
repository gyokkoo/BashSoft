package org.io.commands;

import org.contracts.DirectoryManager;
import org.exceptions.InvalidInputException;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;
import org.staticData.SessionData;

import java.awt.*;
import java.io.File;

public class OpenFileCommand extends Command {

    public OpenFileCommand(String input,
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
            throw new InvalidInputException(this.getInput());
        }

        String fileName = this.getData()[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }
}