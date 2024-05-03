package epicode;

import epicode.dao.EventoDao;
import epicode.dao.LocationDao;
import epicode.dao.PartecipazioneDao;
import epicode.dao.PersonaDao;
import epicode.entity.*;
import epicode.enums.Genere;
import epicode.enums.Sesso;
import epicode.enums.Stato;
import epicode.enums.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;

public class ProvaEvento {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");
        EntityManager em = emf.createEntityManager();

        EventoDao eventoDao = new EventoDao(em);
        LocationDao locationDao = new LocationDao(em);
        PersonaDao personaDao = new PersonaDao(em);
        PartecipazioneDao partecipazioneDao = new PartecipazioneDao(em);

        //creo l'evento
//        Evento e1 = new Evento();
//        e1.setTipoEvento(TipoEvento.PUBBLICO);
//        e1.setTitolo("Concerto 1 maggio");
//        e1.setDescrizione("Concerto al Circo Massimo");
//        e1.setDataEvento(LocalDate.of(2024,5,1));
//        e1.setNumeroMassimoPartecipanti(50000);
//
//        //salvo l'evento sul db
//        eventoDao.save(e1);
//
//        //creo la location
//        Location location = new Location();
//        location.setNome("Circo Massimo");
//        location.setCitta("Roma");
//        //salvo la location sul db
//        locationDao.save(location);
//
//        //aggancio la location all'evento
//        e1.setLocation(location);
//        //risalvo l'evento
//        eventoDao.save(e1);
//
//        //creo una persona
//        Persona p1 = new Persona();
//        p1.setNome("Dario");
//        p1.setCognome("Sellitti");
//        p1.setEmail("dfasljk");
//        p1.setSesso(Sesso.M);
//        //salvo la persona
//        personaDao.save(p1);
//
//        //creo un'altra persona
//        Persona p2 = new Persona();
//        p2.setNome("Mario");
//        p2.setCognome("Stanco");
//        p2.setEmail("dfasljk");
//        p2.setSesso(Sesso.M);
//        //salvo la persona sul db
//        personaDao.save(p2);
//
//        //creo una partecipazione passandogli la persona e l'evento
//        Partecipazione partecipazione = new Partecipazione();
//        partecipazione.setEvento(e1);
//        partecipazione.setPersona(p1);
//        partecipazione.setStato(Stato.DA_CONFERMARE);
//
//        //salvo la partecipazione
//        partecipazioneDao.save(partecipazione);
//
//        //creo un'altra partecipazione passandogli la persona e l'evento
//        Partecipazione partecipazione2 = new Partecipazione();
//        partecipazione2.setEvento(e1);
//        partecipazione2.setPersona(p2);
//        partecipazione2.setStato(Stato.DA_CONFERMARE);
//
//        //salvo la seconda partecipazione
//        partecipazioneDao.save(partecipazione2);

        //estrarre le persone che si chiamano Mario
        personaDao.getPersonaByName("Mario").forEach(System.out::println);

        //estrarre le persone che hanno cognome Sellitti
        personaDao.getPersonaByCognome("Sellitti").forEach(System.out::println);

        System.out.println();

        //estraggo un evento con id=20 dal db
        Evento evento = eventoDao.getById(20);
        //estraggo tutte le persone che partecipano a quell'evento
        partecipazioneDao.getPersonaByEvento(evento).forEach(System.out::println);

        System.out.println();

        //conto tutte le persone che partecipano a quell'evento
        System.out.println(partecipazioneDao.countPersonaByEvento(evento));
        //estraggo tutte le persone che hanno la lettera o nel nome
        personaDao.getPersonaByPartName("o").forEach(System.out::println);

        //creo una partita di calcio
        PartitaDiCalcio partitaDiCalcio = new PartitaDiCalcio();
        partitaDiCalcio.setSquadraCasa("Inter");
        partitaDiCalcio.setSquadraOspite("Salernitana");
        partitaDiCalcio.setSquadraVincente("Salernitana");
        partitaDiCalcio.setGoalCasa(1);
        partitaDiCalcio.setGoalOspite(3);
        partitaDiCalcio.setDescrizione("Inter-Salernitana");
        partitaDiCalcio.setTitolo("Partita Inter Salernitana");
        partitaDiCalcio.setNumeroMassimoPartecipanti(80000);
        partitaDiCalcio.setDataEvento(LocalDate.of(2026,5,6));
        //salvo sul db la partita di calcio
        eventoDao.save(partitaDiCalcio);

        //creo un concerto
        Concerto concerto = new Concerto();
        concerto.setGenere(Genere.POP);
        concerto.setInStreaming(false);
        concerto.setDescrizione("Concerto Harry Styles");
        concerto.setDataEvento(LocalDate.of(2025,6,6));
        concerto.setNumeroMassimoPartecipanti(80000);
        concerto.setTipoEvento(TipoEvento.PUBBLICO);
        //salvo sul db il concerto
        eventoDao.save(concerto);

        //query per estrarre tutti i concerti che hanno streaming true o false
        Query query = em.createQuery("select c from Concerto c where c.inStreaming=:valore");

        //query per estrarre tutti i concerti di un certo genere parametrizzato
        Query query2 = em.createQuery("select c from Concerto c where c.genere=:valore");

        //query per estrarre tutte le partite di calcio vinte dalla squadra di casa
        Query query3 = em.createQuery("select p from PartitaDiCalcio p where p.goalCasa>p.goalOspite");

        //query per estrarre tutte le gare di atletica vinte da una persona parametrizzata
        Query query4 = em.createQuery("select g from GaraDiAtletica g where g.vincitore=:persona");
    }
}
