package epicode.dao;

import epicode.entity.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EventoDao {

    private EntityManager em;

    public EventoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Evento evento){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(evento);
        et.commit();
    }

    public Evento getById(int id){
        return em.find(Evento.class,id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Evento evento = getById(id);

        if(evento!=null){
            em.remove(evento);
        }
        else{
            System.out.println("Evento non presente");
        }

        et.commit();

    }

}
