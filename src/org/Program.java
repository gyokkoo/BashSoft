package org;

import org.contracts.DirectoryManager;
import org.contracts.Interpreter;
import org.io.CommandInterpreter;
import org.io.IOManager;
import org.io.InputReader;
import org.io.OutputWriter;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.RepositoryFilter;
import org.repository.RepositorySorter;
import org.repository.StudentsRepository;

public class Program {

    public static void main(String[] args) {
        Tester tester = new Tester();
        DownloadManager downloadManager = new DownloadManager();
        DirectoryManager ioManager = new IOManager();
        RepositorySorter repositorySorter = new RepositorySorter();
        RepositoryFilter repositoryFilter = new RepositoryFilter();
        StudentsRepository repository = new StudentsRepository(repositoryFilter, repositorySorter);

        Interpreter interpreter = new CommandInterpreter(
                tester, repository, downloadManager, ioManager);
        InputReader reader = new InputReader(interpreter);

        try {
            reader.readCommands();
        } catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}
