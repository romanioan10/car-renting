package repository;

import domeniu.Entitate;

import java.io.IOException;
import java.util.Collection;

public interface IRepository<T extends Entitate> extends Iterable<T>
{
    public void add(T entitate) throws DuplicateEntityException, IOException;
    public void modify(int id, T entitate) throws IOException;
    public void remove(int id) throws IOException;
    public T find(int id);
    public Collection<T> getAll();
    public int size();
    public void setAll(Collection<T> entitati);

}
