package epicode.dao;

import epicode.entity.Evento;
import epicode.entity.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LocationDao {

    private EntityManager em;

    public LocationDao(EntityManager em) {
        this.em = em;
    }

    public void save(Location location){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(location);
        et.commit();
    }

    public Location getById(int id){
        return em.find(Location.class,id);
    }

    public void delete(int id){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Location location = getById(id);

        if(location!=null){
            em.remove(location);
        }
        else{
            System.out.println("Location non presente");
        }

        et.commit();

    }
}
