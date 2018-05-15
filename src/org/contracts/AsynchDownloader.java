package org.contracts;

public interface AsynchDownloader extends Downloader {

    void downloadOnNewThread(String fileUrl);
}
