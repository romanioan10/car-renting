package UI;

import domeniu.Inchiriere;
import domeniu.Masina;
import repository.DuplicateEntityException;
import service.InchiriereService;
import service.MasinaService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Consola
{
    InchiriereService inchiriereService;
    MasinaService masinaService;

    public Consola(InchiriereService inchiriereService, MasinaService masinaService)
    {
        this.inchiriereService=inchiriereService;
        this.masinaService=masinaService;
    }

    private void printMenu()
    {
        System.out.println("1. Adaugare masina");
        System.out.println("2. Modificare masina");
        System.out.println("3. Stergere masina");
        System.out.println("4. Adaugare inchiriere");
        System.out.println("5. Modificare inchiriere");
        System.out.println("6. Stergere inchiriere");
        System.out.println("7. Cele mai des inchiriate masini");
        System.out.println("8. Numarul de inchirieri efectuate in fiecare luna");
        System.out.println("9. Masinile care au fost inchiriate cel mai mult timp");
        System.out.println("m. Afisare masini");
        System.out.println("i. Afisare inchirieri");
        System.out.println("0. Iesire");
    }

    public void runMenu() throws DuplicateEntityException, IOException{
        while (true)
        {
            printMenu();
            String option;
            Scanner scanner = new Scanner(System.in);
            option=scanner.next();
            switch (option)
            {
                case "1":
                {
                    try
                    {
                        System.out.println("da id-ul masinii: ");
                        int id = scanner.nextInt();
                        System.out.println("da marca masinii: ");
                        String marca = scanner.next();
                        System.out.println("da modelul masinii: ");
                        String model = scanner.next();
                        masinaService.add(id, marca, model);
                    }
                    catch(DuplicateEntityException ex)
                    {
                        System.out.println(ex.toString());
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.toString());
                    }
                    break;
                }
                case "4":
                {
                    System.out.println("dati id-ul inchirierii: ");
                    int idInchiriere = scanner.nextInt();
                    System.out.println("Selectati perioada de inchiriere: ");
                    System.out.println("dati ziua in care incepeti inchirierea: ");
                    int ziInceput = scanner.nextInt();
                    System.out.println("dati luna in care incepeti inchirierea: ");
                    int lunaInceput = scanner.nextInt();
                    System.out.println("dati anul in care incepeti inchirierea: ");
                    int anInceput = scanner.nextInt();
                    System.out.println("dati ziua in care terminati inchirierea: ");
                    int ziSfarsit = scanner.nextInt();
                    System.out.println("dati luna in care terminati inchirierea: ");
                    int lunaSfarsit = scanner.nextInt();
                    System.out.println("dati anul in care terminati inchirierea: ");
                    int anSfarsit = scanner.nextInt();
                    LocalDate dataInceput = LocalDate.of(anInceput,lunaInceput,ziInceput);
                    LocalDate dataSfarsit = LocalDate.of(anSfarsit,lunaSfarsit,ziSfarsit);

                    Collection<Masina> masini = masinaService.getAll();
                    Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                    Collection<Masina> masiniSuprapuse = new ArrayList<>();


                    if(inchirieri.isEmpty())
                    {
                        System.out.println("Dati id-ul masinii pe care doriti sa o inchiriati: ");
                        for(Masina m : masini)
                            System.out.println(m);
                        int idMasina = scanner.nextInt();
                        Masina masina = masinaService.readMasina(idMasina);
                        inchiriereService.add(idInchiriere, masina, dataInceput, dataSfarsit);
                    }
                     else
                     {
                        for (Inchiriere i : inchirieri)
                            if (i.getDataInceput().isBefore(dataSfarsit) && i.getDataSfarsit().isAfter(dataInceput))
                                masiniSuprapuse.add(i.getMasina());

                            if(masiniSuprapuse.size()==masini.size())
                                System.out.println("nu exista masini disponibile");
                            else
                            {
                                System.out.println("aceasta este lista masinilor pe care le puteti inchiria: ");
                                for (Masina m : masini) {
                                    if (!masiniSuprapuse.contains(m))
                                        System.out.println(m);
                                }
                                System.out.println("Dati id-ul masinii pe care doriti sa o inchiriati: ");
                                int idMasina = scanner.nextInt();
                                Masina masina = masinaService.readMasina(idMasina);
                                if(!masiniSuprapuse.contains(masina))
                                    inchiriereService.add(idInchiriere, masina, dataInceput, dataSfarsit);
                            }
                    }
                    break;
                }
                case "m":
                {
                    Collection<Masina> masini = masinaService.getAll();
                    for(Masina m: masini)
                        System.out.println(m);
                    break;
                }
                case "i":
                {
                    Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                    for(Inchiriere i: inchirieri)
                        System.out.println(i);
                    break;
                }
                case "2":
                {
                    try
                    {
                        System.out.println("Dati id-ul masinii pe care doriti sa o modificati");
                        int id = scanner.nextInt();
                        System.out.println("Dati un model nou: ");
                        String marca = scanner.next();
                        System.out.println("Dati un model nou");
                        String model = scanner.next();
                        Masina masina = new Masina(id, marca, model);
                        masinaService.modify(id, masina);
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.toString());
                    }
                    break;
                }
                case "3":
                {
                    try
                    {
                        System.out.println("Dati id-ul masinii pe care doriti sa il stergeti");
                        int id = scanner.nextInt();
                        masinaService.remove(id);
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.toString());
                    }
                    break;
                }
                case "5":
                {
                    try
                    {
                        System.out.println("Dati id-ul inchirierii pe care doriti sa o modificati");
                        int id = scanner.nextInt();

                        Collection<Masina> masini = masinaService.getAll();
                        Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                        Collection<Masina> masiniSuprapuse = new ArrayList<>();

                        System.out.println("Selectati perioada noua de inchiriere: ");
                        System.out.println("dati ziua in care incepeti inchirierea: ");
                        int ziInceput = scanner.nextInt();
                        System.out.println("dati luna in care incepeti inchirierea: ");
                        int lunaInceput = scanner.nextInt();
                        System.out.println("dati anul in care incepeti inchirierea: ");
                        int anInceput = scanner.nextInt();
                        System.out.println("dati ziua in care terminati inchirierea: ");
                        int ziSfarsit = scanner.nextInt();
                        System.out.println("dati luna in care terminati inchirierea: ");
                        int lunaSfarsit = scanner.nextInt();
                        System.out.println("dati anul in care terminati inchirierea: ");
                        int anSfarsit = scanner.nextInt();
                        LocalDate dataInceput = LocalDate.of(anInceput,lunaInceput,ziInceput);
                        LocalDate dataSfarsit = LocalDate.of(anSfarsit,lunaSfarsit,ziSfarsit);

                        for (Inchiriere i : inchirieri)
                            if (i.getDataInceput().isBefore(dataSfarsit) && i.getDataSfarsit().isAfter(dataInceput))
                                masiniSuprapuse.add(i.getMasina());

                        if(masiniSuprapuse.size()==masini.size())
                            System.out.println("nu exista masini disponibile");
                        else {
                            System.out.println("aceasta este lista masinilor pe care le puteti inchiria: ");
                            for (Masina m : masini) {
                                if (!masiniSuprapuse.contains(m))
                                    System.out.println(m);
                            }
                            System.out.println("Dati id-ul masinii pe care doriti sa o inchiriati: ");
                            int idMasina = scanner.nextInt();
                            Masina masina = masinaService.readMasina(idMasina);
                            if (!masiniSuprapuse.contains(masina))
                            {
                                Inchiriere inchiriere = new Inchiriere(id, masina, dataInceput, dataSfarsit);
                                inchiriereService.modify(id, inchiriere);
                            }

                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.toString());
                    }
                    break;
                }
                case "6":
                {
                    try
                    {
                        System.out.println("Dati id-ul inchirierii pe care doriti sa o stergeti: ");
                        int id = scanner.nextInt();
                        inchiriereService.remove(id);
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.toString());
                    }
                    break;
                }
                case "7":
                {
                    Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                    Map<Masina, Long> masiniCuNrInchirieri = inchirieri.stream()
                            .collect(Collectors.groupingBy(Inchiriere::getMasina, Collectors.counting()));

                    masiniCuNrInchirieri.entrySet().stream()
                            .collect(Collectors.toMap(entry -> entry.getKey().getMarca() + ", " + entry.getKey().getModel(),
                                    Map.Entry::getValue, Long::sum)) // combinarea valorilor duplicate
                            .entrySet().stream()
                            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                            .forEach(entry -> {
                                String masina = entry.getKey();
                                Long numarInchirieri = entry.getValue();
                                System.out.println(masina + " - Numar total de inchirieri: " + numarInchirieri);
                            });
                    break;
                }
                case "8":
                {
                    Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                    Map<Integer, Long> numarInchirieriPeLuna = inchirieri.stream()
                            .collect(Collectors.groupingBy(inchiriere -> inchiriere.getDataInceput().getMonthValue(), Collectors.counting()));

                    numarInchirieriPeLuna.entrySet().stream()
                            .sorted(Map.Entry.<Integer, Long>comparingByKey())
                            .forEach(entry -> {
                                Integer luna = entry.getKey();
                                Long numarInchirieri = entry.getValue();
                                System.out.println("Luna " + luna + " - Numar total de inchirieri: " + numarInchirieri);
                            });
                    break;
                }
                case "9":
                {
                    Collection<Inchiriere> inchirieri = inchiriereService.getAll();
                    Map<Masina, Long> masiniCuTimpInchiriat = inchirieri.stream()
                            .collect(Collectors.groupingBy(Inchiriere::getMasina, Collectors.summingLong(inchiriere -> inchiriere.getDataSfarsit().toEpochDay() - inchiriere.getDataInceput().toEpochDay())));

                    masiniCuTimpInchiriat.entrySet().stream()
                            .collect(Collectors.toMap(entry -> entry.getKey().getMarca() + ", " + entry.getKey().getModel(),
                                    Map.Entry::getValue, Long::sum)) // combinarea valorilor duplicate
                            .entrySet().stream()
                            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                            .forEach(entry -> {
                                String masina = entry.getKey();
                                Long timpInchiriat = entry.getValue();
                                System.out.println(masina + " - Timp total de inchiriere: " + timpInchiriat + " zile");
                            });
                    break;
                }
                case "0":
                {
                    System.out.println("La revedere!!!");
                    return;
                }
                default:
                {
                    System.out.println("Optiune gresita! Reincercati!");
                }
            }
        }
    }

}
