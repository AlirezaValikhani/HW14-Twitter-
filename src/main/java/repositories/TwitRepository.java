package repositories;

import models.Twit;

import java.util.List;

public class TwitRepository extends BaseRepositoryImpl<Twit,Long> {

    public Twit findById(Long id){
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT t FROM models.Twit t WHERE t.id = :id",Twit.class)
                    .setParameter("id",id)
                    .getSingleResult();
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

    public List<Twit> findUserTwits(Long userId){
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("select t from models.Twit t inner join models.User u on t.user.id = u.id",Twit.class)
                    .list();
        }
    }
}
