package Teste;

import domeniu.Inchiriere;
import domeniu.Masina;
import org.junit.Test;
import repository.DuplicateEntityException;
import repository.MemoryRepository;
import service.InchiriereService;

import java.io.IOException;
import java.time.LocalDate;

public class TestServiceInchirieri
{
    public TestServiceInchirieri() {
    }

    @Test
    public void testAdd() throws DuplicateEntityException, IOException {
        MemoryRepository<Inchiriere> memoryRepository = new MemoryRepository<Inchiriere>();
        InchiriereService inchiriereService = new InchiriereService(memoryRepository);

        Masina masina = new Masina(1,"dacia", "logan");
        LocalDate dataInceput = LocalDate.of(2023,10,1);
        LocalDate dataSfarsit = LocalDate.of(2023,10,10);

        inchiriereService.add(1, masina, dataInceput, dataSfarsit);
        assert inchiriereService.size() == 1;
    }
    @Test

    public void testModify() throws DuplicateEntityException, IOException {
        MemoryRepository<Inchiriere> memoryRepository = new MemoryRepository<Inchiriere>();
        InchiriereService inchiriereService = new InchiriereService(memoryRepository);

        Masina masina = new Masina(1,"dacia", "logan");
        LocalDate dataInceput = LocalDate.of(2023,10,1);
        LocalDate dataSfarsit = LocalDate.of(2023,10,10);
        Masina masinaNou = new Masina(1,"lada", "niva");
        LocalDate dataInceputNou = LocalDate.of(2024,10,1);
        LocalDate dataSfarsitNou = LocalDate.of(2024,10,10);

        inchiriereService.add(1, masina, dataInceput, dataSfarsit);

        inchiriereService.modify(1, masinaNou, dataInceputNou, dataSfarsitNou);

        Inchiriere inchiriere = new Inchiriere(1, masinaNou, dataInceputNou, dataSfarsitNou);

        Inchiriere verif = inchiriereService.readInchiriere(1);

        assert inchiriere == verif;
    }

    public void testRemove() throws DuplicateEntityException, IOException {
        MemoryRepository<Inchiriere> memoryRepository = new MemoryRepository<Inchiriere>();
        InchiriereService inchiriereService = new InchiriereService(memoryRepository);

        Masina masina = new Masina(1,"dacia", "logan");
        LocalDate dataInceput = LocalDate.of(2023,10,1);
        LocalDate dataSfarsit = LocalDate.of(2023,10,10);

        inchiriereService.add(1, masina, dataInceput, dataSfarsit);
        inchiriereService.remove(1);
        assert inchiriereService.size() == 0;
    }

    public void testReadInchiriere() throws DuplicateEntityException, IOException {
        MemoryRepository<Inchiriere> memoryRepository = new MemoryRepository<Inchiriere>();
        InchiriereService inchiriereService = new InchiriereService(memoryRepository);

        Masina masina = new Masina(1,"dacia", "logan");
        LocalDate dataInceput = LocalDate.of(2023,10,1);
        LocalDate dataSfarsit = LocalDate.of(2023,10,10);

        inchiriereService.add(1, masina, dataInceput, dataSfarsit);
        Inchiriere verif = inchiriereService.readInchiriere(1);
        Inchiriere inchiriere = new Inchiriere(1, masina, dataInceput, dataSfarsit);

        assert inchiriere == verif;
    }
}
