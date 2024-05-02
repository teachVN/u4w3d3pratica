package epicode.dao;

import epicode.entity.Evento;
import epicode.entity.Partecipazione;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
