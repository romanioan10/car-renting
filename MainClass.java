import Teste.TestAll;
import UI.Consola;
import domeniu.*;
import gui.JavaFXApplication;
import repository.*;
import service.InchiriereService;
import service.MasinaService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;


public class MainClass
{
    public static void main(String[] args) throws repository.DuplicateEntityException, IOException, ParseException, SQLException {

       TestAll testAll = new TestAll();
       testAll.testAll();
        IEntityFactory<Masina> masinaFactory = new MasinaFactory();
        IEntityFactory<Inchiriere> inchiriereFactory = new InchiriereFactory();
        repository.IRepository<Masina> repositoryMasina= new MemoryRepository<>();
        repository.IRepository<Inchiriere> repositoryInchiriere = new MemoryRepository<>();

        IRepository<domeniu.Masina> dbrepositoryMasina = new MasiniDbRepository();

        ((MasiniDbRepository) dbrepositoryMasina).connectToDb();


        Settings setari = Settings.getInstance();
        if (Objects.equals(setari.getRepoType(), "memory")) {
            repositoryMasina = new MemoryRepository<>();
            repositoryInchiriere = new MemoryRepository<>();
        }
        if (Objects.equals(setari.getRepoType(), "text")){
            repositoryMasina = new FileRepository<>(setari.getRepoMasina(), masinaFactory);
            repositoryInchiriere = new FileRepository<>(setari.getRepoInchiriere(), inchiriereFactory);
        }
        if (Objects.equals(setari.getRepoType(), "binary"))
        {
            repositoryMasina = new BinaryFileRepository<>(setari.getRepoMasina());
            repositoryInchiriere = new BinaryFileRepository<>(setari.getRepoInchiriere());
        }

        if (Objects.equals(setari.getRepoType(), "database"))
        {
            repositoryMasina = new MasiniDbRepository();
            repositoryInchiriere = new InchirieriDbRepository();
            ((MasiniDbRepository) repositoryMasina).connectToDb();
            ((InchirieriDbRepository) repositoryInchiriere).connectToDb();
        }

        repositoryMasina.setAll(dbrepositoryMasina.getAll());
        MasinaService masinaService = new MasinaService(repositoryMasina);
        InchiriereService inchiriereService = new InchiriereService(repositoryInchiriere);
        Consola consola = new Consola(inchiriereService, masinaService);
        JavaFXApplication javaFXApplication = new JavaFXApplication();

        javaFXApplication.main(args);
        consola.runMenu();

    }
}
