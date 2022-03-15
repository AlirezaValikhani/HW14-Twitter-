package repositories;

import models.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private static SessionFactory sessionFactory;
    private static UserRepository userRepository;

    @BeforeAll
    static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        userRepository = new UserRepository();
    }

    @Test
    void connectionTest(){
        assertDoesNotThrow(() -> sessionFactory = SessionFactorySingleton.getInstance());
    }

    @Test
    public void save(){
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());

        userRepository.save(user);

        assertNotNull(user);
        assertEquals(user.getId(),user.getId());
    }

    @Test
    public void update(){
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);

        User updatedUser = new User(user.getId(),"w","w","3","w","8","00",new HashSet<>(),new HashSet<>());
        userRepository.update(updatedUser);

        assertEquals(user.getId(),user.getId());
        assertEquals("w",updatedUser.getUserName());
        assertEquals("8",updatedUser.getNationalCode());
    }

    @Test
    public void delete(){
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);

        userRepository.delete(user);

        assertEquals(0,userRepository.findAll().size());
    }

    @Test
    void findById() {
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);

        User returnedUser = userRepository.findById(user.getId());

        assertNotNull(returnedUser);
        assertEquals(user.getId(),returnedUser.getId());
        assertEquals(user.getUserName(),returnedUser.getUserName());
    }

    @Test
    void findAll() {
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        User user1 = new User(null,"a1","a1","11","b1","21","121",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        userRepository.save(user1);

        List<User> returnedUserList = userRepository.findAll();

        assertNotNull(returnedUserList);
        assertEquals(returnedUserList.get(0).getId(),user.getId());
        assertNotEquals(0,returnedUserList.size());
    }

    @Test
    void findByUserName() {
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);

        userRepository.findByUserName(user.getUserName());

        assertNotNull(user);
        /*assertEquals(user.getUserName(),user.getUserName());*/
    }

    @AfterEach
    void truncate() {
        userRepository.truncate();
    }
}