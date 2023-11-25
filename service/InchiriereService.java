package service;

import domeniu.Inchiriere;
import domeniu.Masina;
import repository.DuplicateEntityException;
import repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.time.LocalDate;

public class InchiriereService
{
    IRepository<Inchiriere> repository;

    public InchiriereService(IRepository<Inchiriere> repository)
    {
        this.repository=repository;
    }

    public int size()
    {
        return repository.size();
    }

    public void add(int id, Masina masina, LocalDate dataInceput, LocalDate dataSfarsit) throws DuplicateEntityException, IOException {
        repository.add(new Inchiriere(id, masina, dataInceput, dataSfarsit));
    }

//    public void modify(int id, Masina masinaNou, LocalDate dataInceputNou, LocalDate dataSfarsitNou) throws DuplicateEntityException
//    {
//        Inchiriere inchiriere = readInchiriere(id);
//        if(readInchiriere(id) != null)
//        {
//            inchiriere.setMasina(masinaNou);
//            inchiriere.setDataInceput(dataInceputNou);
//            inchiriere.setDataSfarsit(dataSfarsitNou);
//        }
//        else
//            throw new IllegalArgumentException("inchirierea nu exista");
//    }

    public void modify(int id, Inchiriere inchiriere) throws IOException
    {

        if(readInchiriere(id) != null)
            repository.modify(id, inchiriere);
        else
            throw new IllegalArgumentException("inchirierea nu exista");
    }



    public void remove(int id) throws IOException {
        if(readInchiriere(id) != null)
            repository.remove(id);
        else
            throw new IllegalArgumentException("inchirierea nu exista");
    }

    public Inchiriere readInchiriere(int id)
    {
        return repository.find(id);
    }

    public Collection<Inchiriere> getAll()
    {
        return repository.getAll();
    }

}
