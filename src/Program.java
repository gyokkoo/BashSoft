import io.CommandInterpreter;
import io.IOManager;
import io.InputReader;
import io.OutputWriter;
import judge.Tester;
import network.DownloadManager;
import repository.RepositoryFilter;
import repository.RepositorySorter;
import repository.StudentsRepository;

public class Program {

    public static void main(String[] args) {
        Tester tester = new Tester();
        DownloadManager downloadManager = new DownloadManager();
        IOManager ioManager = new IOManager();
        RepositorySorter repositorySorter = new RepositorySorter();
        RepositoryFilter repositoryFilter = new RepositoryFilter();
        StudentsRepository repository = new StudentsRepository(repositoryFilter, repositorySorter);

        CommandInterpreter interpreter = new CommandInterpreter(
                tester, repository, downloadManager, ioManager);
        InputReader reader = new InputReader(interpreter);

        try {
            reader.readCommands();
        } catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }

    }
}
