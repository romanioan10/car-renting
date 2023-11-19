package domeniu;

public abstract class Entitate {
    protected int id;


    public Entitate(int id)
    {
        this.id=id;
    }

    public int getId()
    {
        return id;
    }
}