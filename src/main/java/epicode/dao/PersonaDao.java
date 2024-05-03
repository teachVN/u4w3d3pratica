package epicode.dao;

import epicode.entity.Evento;
import epicode.entity.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class PersonaDao {

    private EntityManager em;

    public PersonaDao(EntityManager em) {
        this.em = em;
    }

    public void save(Persona persona){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(persona);
        et.commit();
    }

    public Persona getById(int id){
        return em.find(Persona.class,id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Persona persona = getById(id);

        if(persona!=null){
            em.remove(persona);
        }
        else{
            System.out.println("Persona non presente");
        }

        et.commit();

    }

    public List<Persona> getPersonaByName(String nome){
        //richiamo una namedQuery della classe Persona attraverso il nome della NamedQuery
        Query query = em.createNamedQuery("getPersonaByName");
        //setto il parametro della query sostituendo il paramentro con il valore
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    public List<Persona> getPersonaByCognome(String cognome){
        Query query = em.createQuery("select p from Persona p where p.cognome= :cognome");
        query.setParameter("cognome", cognome);
        return query.getResultList();
    }

    public List<Persona> getPersonaByPartName(String nome){
        Query query = em.createQuery("select p from Persona p where p.nome like :nome");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}
