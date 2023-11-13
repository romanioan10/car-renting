package domeniu;

public class Masina extends Entitate
{
    String marca;
    String model;


    public Masina(int id)
    {
        super(id);
    }

    public Masina(int id, String marca, String model)
    {
        super(id);
        this.marca=marca;
        this.model=model;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca=marca;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    @Override
    public String toString()
    {
        return id +"," + marca + "," + model;
    }

}



