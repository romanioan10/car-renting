package domeniu;

public interface IEntityFactory <T extends Entitate>
{
    public T createEntity(String line);
}
