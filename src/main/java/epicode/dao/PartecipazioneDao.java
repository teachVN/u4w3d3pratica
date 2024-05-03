package epicode.dao;

import epicode.entity.Evento;
import epicode.entity.Partecipazione;
import epicode.entity.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PartecipazioneDao {


    private EntityManager em;

    public PartecipazioneDao(EntityManager em) {
        this.em = em;
    }

    public void save(Partecipazione partecipazione){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(partecipazione);
        et.commit();
    }

    public Partecipazione getById(int id){
        return em.find(Partecipazione.class,id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Partecipazione partecipazione = getById(id);

        if(partecipazione!=null){
            em.remove(partecipazione);
        }
        else{
            System.out.println("Partecipazione non presente");
        }

        et.commit();

    }

    public List<Persona> getPersonaByEvento(Evento evento){
        Query query = em.createNamedQuery("getPersonaByEvento");
        query.setParameter("evento", evento);
        return query.getResultList();
    }

    public Number countPersonaByEvento(Evento evento){
        Query query = em.createQuery("select count(part) from Partecipazione part where part.evento=:evento");
        query.setParameter("evento", evento);
        return (Number) query.getSingleResult();
    }
}
