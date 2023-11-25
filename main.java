import Teste.TestAll;
import UI.Consola;
import domeniu.*;
import repository.*;
import service.InchiriereService;
import service.MasinaService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class main
{
    public static void main(String[] args) throws DuplicateEntityException, IOException, ParseException {
       TestAll testAll = new TestAll();
       testAll.testAll();
        IEntityFactory<Masina> masinaFactory = new MasinaFactory();
        IEntityFactory<Inchiriere> inchiriereFactory = new InchiriereFactory();
        IRepository<Masina> repositoryMasina= new MemoryRepository<>();
        IRepository<Inchiriere> repositoryInchiriere = new MemoryRepository<>();


        Settings setari = Settings.getInstance();
        if (Objects.equals(setari.getRepoType(), "memory")) {
            repositoryMasina = new MemoryRepository<>();
            repositoryInchiriere = new MemoryRepository<>();
        }
        if (Objects.equals(setari.getRepoType(), "text")){
            repositoryMasina = new FileRepository<>(setari.getRepoMasina(), masinaFactory);
            repositoryInchiriere = new FileRepository<>(setari.getRepoInchiriere(), inchiriereFactory);
        }
        if (Objects.equals(setari.getRepoType(), "binary")){
            repositoryMasina = new BinaryFileRepository<>(setari.getRepoMasina());
            repositoryInchiriere = new BinaryFileRepository<>(setari.getRepoInchiriere());
        }

        MasinaService masinaService = new MasinaService(repositoryMasina);
        InchiriereService inchiriereService = new InchiriereService(repositoryInchiriere);
        Consola consola = new Consola(inchiriereService, masinaService);

        consola.runMenu();
    }
}
