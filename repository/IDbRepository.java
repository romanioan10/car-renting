package repository;

import domeniu.Entitate;

import java.io.IOException;

public interface IDbRepository<T extends Entitate> extends IRepository<T>
{
    void connectToDb();
    void closeConnection();
    void createTable();
    void insertRandomData() throws DuplicateEntityException, IOException;
}
