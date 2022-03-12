package repositories;

import models.Twit;

import java.util.List;

public class TwitRepository extends BaseRepositoryImpl<Twit,Long> {

    public Twit findById(Long id){
        try(var session = sessionFactory.openSession()){
            return session.find(Twit.class, id);
        }
    }

    public List<Twit> findAll(){
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT t FROM models.Twit t",Twit.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Twit CASCADE ", Twit.class)
                    .executeUpdate();
            transaction.commit();
        }
    }
}
