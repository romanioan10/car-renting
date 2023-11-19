package Teste;

import domeniu.Masina;
import org.junit.Test;
import repository.DuplicateEntityException;
import repository.MemoryRepository;
import service.MasinaService;

import java.io.IOException;


public class TestServiceMasini
{
    public TestServiceMasini() {
    }

    @Test
    public void testAdd() throws DuplicateEntityException, IOException {
        MemoryRepository<Masina> masinaMemoryRepository = new MemoryRepository<Masina>();
        MasinaService masinaService = new MasinaService(masinaMemoryRepository);
        masinaService.add(1, "audi", "a4");
        assert masinaService.size() == 1;
    }
    @Test
    public void testModify() throws DuplicateEntityException, IOException {
        MemoryRepository<Masina> masinaMemoryRepository = new MemoryRepository<Masina>();
        MasinaService masinaService = new MasinaService(masinaMemoryRepository);
        masinaService.add(1, "audi", "a4");
        masinaService.modify(1,"bmw", "m3");
        Masina masina = new Masina(1, "bmw", "m3");
        Masina masinaVerif = masinaService.readMasina(1);
        assert masinaVerif == masina;
    }

    public void testRemove() throws DuplicateEntityException, IOException {
        MemoryRepository<Masina> masinaMemoryRepository = new MemoryRepository<Masina>();
        MasinaService masinaService = new MasinaService(masinaMemoryRepository);
        masinaService.add(1, "audi", "a4");
        masinaService.remove(1);
        assert masinaService.size() == 0;
    }

    public void testReadMasina() throws DuplicateEntityException, IOException {
        MemoryRepository<Masina> masinaMemoryRepository = new MemoryRepository<Masina>();
        MasinaService masinaService = new MasinaService(masinaMemoryRepository);
        masinaService.add(1,"bmw", "m3");
        Masina masina = new Masina(1, "bmw", "m3");
        Masina masinaVerif = masinaService.readMasina(1);
        assert masinaVerif == masina;
    }



}
