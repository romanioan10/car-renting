import Teste.TestAll;
import UI.Consola;
import domeniu.Inchiriere;
import domeniu.InchiriereFactory;
import domeniu.Masina;
import domeniu.MasinaFactory;
import repository.*;
import service.InchiriereService;
import service.MasinaService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class main
{
    public static void main(String[] args) throws DuplicateEntityException, IOException, ParseException {
       TestAll testAll = new TestAll();
       testAll.testAll();
//        IRepository<Masina> repositoryMasina = new MemoryRepository<>();
//       IRepository<Masina> repositoryMasina = new FileRepository<>("masini.txt", new MasinaFactory());
       IRepository<Masina> repositoryMasina = new BinaryFileRepository<>("masini.bin");
//        IRepository<Inchiriere> repositoryInchiriere = new MemoryRepository<>();
//       IRepository<Inchiriere> repositoryInchiriere = new FileRepository<>("inchirieri.txt", new InchiriereFactory());
       IRepository<Inchiriere> repositoryInchiriere = new BinaryFileRepository<>("inchirieri.bin");

        MasinaService masinaService = new MasinaService(repositoryMasina);
        InchiriereService inchiriereService = new InchiriereService(repositoryInchiriere);
        Consola consola = new Consola(inchiriereService, masinaService);

        consola.runMenu();
    }
}
