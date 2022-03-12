package repositories;

import models.Comment;

import java.util.List;

public class CommentRepository extends BaseRepositoryImpl<Comment,Long>{

    public Comment findById(Long id){
        try(var session = sessionFactory.openSession()){
            return session.find(Comment.class, id);
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
}
