package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.AsynchDownloader;
import org.exceptions.InvalidCommandException;

@Alias("downloadasynch")
public class DownloadAsynchCommand extends Command {

    @Inject
    private AsynchDownloader downloadManager;

    public DownloadAsynchCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String fileUrl = this.getData()[1];
        this.downloadManager.downloadOnNewThread(fileUrl);
    }
}
