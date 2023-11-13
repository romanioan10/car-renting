import UI.Consola;
import domeniu.Inchiriere;
import domeniu.InchiriereFactory;
import domeniu.Masina;
import domeniu.MasinaFactory;
import repository.DuplicateEntityException;
import repository.FileRepository;
import repository.IRepository;
import repository.MemoryRepository;
import service.InchiriereService;
import service.MasinaService;

import java.io.FileNotFoundException;
import java.io.IOException;

public class main
{
    public static void main(String[] args) throws DuplicateEntityException, IOException {
//        IRepository<Masina> repositoryMasina = new MemoryRepository<>();
        IRepository<Masina> repositoryMasina = new FileRepository<>("masini.txt", new MasinaFactory());
//        IRepository<Inchiriere> repositoryInchiriere = new MemoryRepository<>();
        IRepository<Inchiriere> repositoryInchiriere = new FileRepository<>("inchirieri.txt", new InchiriereFactory());
        MasinaService masinaService = new MasinaService(repositoryMasina);
        InchiriereService inchiriereService = new InchiriereService(repositoryInchiriere);
        Consola consola = new Consola(inchiriereService, masinaService);

        consola.runMenu();
    }
}
