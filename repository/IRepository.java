package repository;

import domeniu.Entitate;
import domeniu.Inchiriere;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public interface IRepository<T extends Entitate> extends Iterable<T>
{
    public void add(T entitate) throws DuplicateEntityException, IOException;
    public void remove(int id);
    public T find(int id);
    public Collection<T> getAll();

}
