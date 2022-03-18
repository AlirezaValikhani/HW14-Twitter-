package repositories;

import models.Comment;
import models.Twit;

import java.util.List;

public class CommentRepository extends BaseRepositoryImpl<Comment,Long>{

    public Comment findById(Long id){
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT c FROM models.Comment c WHERE c.id = :id",Comment.class)
                    .setParameter("id",id)
                    .getSingleResult();
        }
    }

    public List<Comment> findAll(){
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT c FROM models.Comment c",Comment.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Comment CASCADE ", Comment.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public List<Comment> findAllByTwitId(Long twitId){
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT c FROM models.Comment c WHERE c.id = :id",Comment.class)
                    .setParameter("id",twitId)
                    .list();
        }
    }

    public List<Comment> findByTwitId(Long twitId){
        try(var session = sessionFactory.openSession()){
            return session
            .createQuery("select c from models.Comment c inner join models.Twit t on c.twit.id = t.id", Comment.class)
            .list();
        }
    }
}
