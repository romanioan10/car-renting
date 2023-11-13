package repository;

import domeniu.Entitate;
import domeniu.IEntityFactory;

import java.io.*;
import java.util.Scanner;

public class FileRepository<T extends Entitate> extends MemoryRepository<T> implements IRepository<T>
{
    private String fileName;
    private IEntityFactory<T> entityFactory;

    public FileRepository(String fileName, IEntityFactory<T> entityFactory) throws IOException, DuplicateEntityException {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        readFromFile();
    }

    private void readFromFile() throws IOException, DuplicateEntityException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            T entity = entityFactory.createEntity(line);
            super.add(entity);
        }
    }

    public void add(T entitate) throws DuplicateEntityException, IOException
    {
        super.add(entitate);
        Writer wr = new FileWriter(this.fileName, true);
        wr.write(String.valueOf(entitate));
        wr.write("\r\n");
        wr.flush();
        wr.close();
    }

}
