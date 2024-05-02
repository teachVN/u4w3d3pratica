package epicode;

import epicode.dao.EventoDao;
import epicode.dao.LocationDao;
import epicode.dao.PartecipazioneDao;
import epicode.dao.PersonaDao;
import epicode.entity.Evento;
import epicode.entity.Location;
import epicode.entity.Partecipazione;
import epicode.entity.Persona;
import epicode.enums.Sesso;
import epicode.enums.Stato;
import epicode.enums.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class ProvaEvento {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");
        EntityManager em = emf.createEntityManager();

        EventoDao eventoDao = new EventoDao(em);
        LocationDao locationDao = new LocationDao(em);
        PersonaDao personaDao = new PersonaDao(em);
        PartecipazioneDao partecipazioneDao = new PartecipazioneDao(em);

        Evento e1 = new Evento();
        e1.setTipoEvento(TipoEvento.PUBBLICO);
        e1.setTitolo("Concerto 1 maggio");
        e1.setDescrizione("Concerto al Circo Massimo");
        e1.setDataEvento(LocalDate.of(2024,5,1));
        e1.setNumeroMassimoPartecipanti(50000);

        eventoDao.save(e1);

        Location location = new Location();
        location.setNome("Circo Massimo");
        location.setCitta("Roma");

        locationDao.save(location);

        e1.setLocation(location);
        eventoDao.save(e1);

        Persona p1 = new Persona();
        p1.setNome("Dario");
        p1.setCognome("Sellitti");
        p1.setEmail("dfasljk");
        p1.setSesso(Sesso.M);

        personaDao.save(p1);

        Persona p2 = new Persona();
        p2.setNome("Mario");
        p2.setCognome("Stanco");
        p2.setEmail("dfasljk");
        p2.setSesso(Sesso.M);

        personaDao.save(p2);

        Partecipazione partecipazione = new Partecipazione();
        partecipazione.setEvento(e1);
        partecipazione.setPersona(p1);
        partecipazione.setStato(Stato.DA_CONFERMARE);

        partecipazioneDao.save(partecipazione);

        Partecipazione partecipazione2 = new Partecipazione();
        partecipazione2.setEvento(e1);
        partecipazione2.setPersona(p2);
        partecipazione2.setStato(Stato.DA_CONFERMARE);

        partecipazioneDao.save(partecipazione2);


    }
}
