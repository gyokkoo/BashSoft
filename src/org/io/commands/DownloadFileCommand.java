package org.io.commands;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.AsynchDownloader;
import org.exceptions.InvalidCommandException;

@Alias("download")
public class DownloadFileCommand extends Command {

    @Inject
    private AsynchDownloader downloadManager;

    public DownloadFileCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String fileUrl = data[1];
        this.downloadManager.download(fileUrl);
    }
}
