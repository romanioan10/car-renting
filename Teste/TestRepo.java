package Teste;

import domeniu.Inchiriere;
import domeniu.Masina;
import org.junit.Test;
import repository.DuplicateEntityException;
import repository.MemoryRepository;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class TestRepo
{
    public TestRepo() {
    }

    @Test
    public void testAdd() throws ParseException, DuplicateEntityException, IOException {
        MemoryRepository<Masina> masinaMemoryRepository = new MemoryRepository<Masina>();
        MemoryRepository<Inchiriere> inchiriereMemoryRepository = new MemoryRepository<Inchiriere>();
        Masina masina = new Masina(1, "audi", "a4");
        Masina masina1 = new Masina(2, "dacia", "logan");
        Masina masina2 = new Masina(3, "lada", "niva");
        LocalDate dataInceput = LocalDate.of(2023,10,1);
        LocalDate dataSfarsit = LocalDate.of(2023,10,10);
        Inchiriere inchiriere = new Inchiriere(1, masina, dataInceput, dataSfarsit);
        masinaMemoryRepository.add(masina);
        masinaMemoryRepository.add(masina1);
        masinaMemoryRepository.add(masina2);
        inchiriereMemoryRepository.add(inchiriere);

        assert masinaMemoryRepository.size() == 3;
        assert masinaMemoryRepository.find(1) == masina;
        assert masinaMemoryRepository.find(2) == masina1;
        assert masinaMemoryRepository.find(3) == masina2;
        assert inchiriereMemoryRepository.size() == 1;
        assert inchiriereMemoryRepository.find(1) == inchiriere;
    }

    @Test
    public void testRemove() throws ParseException, DuplicateEntityException, IOException {
        MemoryRepository<Masina> masinaMemoryRepository = new MemoryRepository<Masina>();
        MemoryRepository<Inchiriere> inchiriereMemoryRepository = new MemoryRepository<Inchiriere>();
        Masina masina = new Masina(1, "audi", "a4");
        Masina masina1 = new Masina(2, "dacia", "logan");
        Masina masina2 = new Masina(3, "lada", "niva");
        LocalDate dataInceput = LocalDate.of(2023,10,1);
        LocalDate dataSfarsit = LocalDate.of(2023,10,10);
        Inchiriere inchiriere = new Inchiriere(1, masina, dataInceput, dataSfarsit);
        masinaMemoryRepository.add(masina);
        masinaMemoryRepository.add(masina1);
        masinaMemoryRepository.add(masina2);
        inchiriereMemoryRepository.add(inchiriere);
        assert masinaMemoryRepository.size() == 3;
        assert inchiriereMemoryRepository.size() == 1;
        masinaMemoryRepository.remove(3);
        inchiriereMemoryRepository.remove(1);
        assert masinaMemoryRepository.size() == 2;
        assert inchiriereMemoryRepository.size() == 0;
    }
    @Test
    public void testFind() throws ParseException, DuplicateEntityException, IOException {
        MemoryRepository<Masina> masinaMemoryRepository = new MemoryRepository<Masina>();
        MemoryRepository<Inchiriere> inchiriereMemoryRepository = new MemoryRepository<Inchiriere>();
        Masina masina = new Masina(1, "audi", "a4");
        Masina masina1 = new Masina(2, "dacia", "logan");
        Masina masina2 = new Masina(3, "lada", "niva");
        LocalDate dataInceput = LocalDate.of(2023,10,1);
        LocalDate dataSfarsit = LocalDate.of(2023,10,10);
        Inchiriere inchiriere = new Inchiriere(1, masina, dataInceput, dataSfarsit);
        masinaMemoryRepository.add(masina);
        masinaMemoryRepository.add(masina1);
        masinaMemoryRepository.add(masina2);
        inchiriereMemoryRepository.add(inchiriere);

        assert masinaMemoryRepository.find(1) == masina;
        assert inchiriereMemoryRepository.find(1) == inchiriere;

    }
}
