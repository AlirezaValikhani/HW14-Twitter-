package repositories;

import models.Comment;
import models.User;

import java.util.List;

public class UserRepository extends BaseRepositoryImpl<User,Long> {

    public User findById(Long id){
        try(var session = sessionFactory.openSession()){
            return session.find(User.class, id);
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
            return session.find(User.class, userName);
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
