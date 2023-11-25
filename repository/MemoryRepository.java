package repository;

import domeniu.Entitate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MemoryRepository<T extends Entitate> implements IRepository<T>
{

    public int size()
    {
        return entitati.size();
    }
    List<T> entitati =  new ArrayList<T>();
    @Override
    public void add(T entitate) throws DuplicateEntityException, IOException {
        if(entitate == null)
        {
            throw new IllegalArgumentException("entitatea nu poate fi null");
        }

        if(find(entitate.getId()) != null)
        {
            throw new DuplicateEntityException("entitatea deja exista");
        }

        entitati.add(entitate);
    }

    @Override
    public void modify(int id, T entitate) throws IOException {
        if(entitate == null)
        {
            throw new IllegalArgumentException("entitatea nu poate fi null");
        }

        if(find(entitate.getId()) == null)
        {
            throw new IllegalArgumentException("entitatea nu exista");
        }

        for(int i=0; i<entitati.size(); i++)
        {
            if(entitati.get(i).getId()==id)
            {
                entitati.set(i, entitate);
                break;
            }
        }
    }


    @Override
    public void remove(int id) throws IOException {

        boolean ok = entitati.removeIf(e -> e.getId()==id);
//        for(T entitate: entitati)
//        {
//            if(entitate.getId()==id)
//            {
//                entitati.remove(entitate);
//                ok=1;
//            }
//        }
        if(!ok)
            throw new IllegalArgumentException("entitatea nu exista");
    }

    @Override
    public T find(int id) {
        for(T entitate : entitati)
        {
            if(entitate.getId()==id)
            {
                return entitate;
            }
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entitati);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entitati).iterator();
    }

}
