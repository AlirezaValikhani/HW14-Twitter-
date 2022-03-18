package repositories;

import exceptions.UserNotFoundException;
import models.Comment;
import models.User;

import javax.persistence.NoResultException;
import java.util.List;

public class UserRepository extends BaseRepositoryImpl<User,Long> {

    public User findById(Long id){
        try(var session = sessionFactory.openSession()){
            /*return session.find(User.class,id);*/
                return session
                        .createQuery("SELECT u FROM models.User u WHERE u.id = :id ", User.class)
                        .setParameter("id", id)
                        .getSingleResult();

        }
    }

    public List<User> findAll(){
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT u FROM models.User u",User.class)
                    .list();
        }
    }

    public User findByUserName(String userName) {
        try(var session = sessionFactory.openSession()){
            try{
                var user = session.
                        createQuery("SELECT u FROM models.User u WHERE u.userName = :userName",User.class)
                        .setParameter("userName",userName)
                        .getSingleResult();
                return user;
            }catch (NoResultException n){
                n.getMessage();
            }
        }
        return null;
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
