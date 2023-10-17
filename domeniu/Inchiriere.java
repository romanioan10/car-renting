package domeniu;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Date;

class Inchiriere extends Entitate
{
    Masina masina;
    Date dataInceput;
    Date dataSfarsit;

    public Inchiriere(int id, Masina masina, Date dataInceput, Date dataSfarsit)
    {
        super(id);
        this.masina=masina;
        this.dataInceput=dataInceput;
        this.dataSfarsit=dataSfarsit;
    }

    public Date getDataInceput()
    {
        return dataInceput;
    }
    public Date getDataSfarsit()
    {
        return dataSfarsit;
    }

    public void setDataInceput(Date dataInceput)
    {
        this.dataInceput=dataInceput;
    }

    public void setDataSfarsit(Date dataSfarsit)
    {
        this.dataSfarsit=dataSfarsit;
    }

}
