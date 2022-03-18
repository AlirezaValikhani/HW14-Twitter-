package repositories;

import models.Twit;
import models.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwitRepositoryTest {
    private static SessionFactory sessionFactory;
    private static TwitRepository twitRepository;
    private static UserRepository userRepository;

    @BeforeAll
    static void connection(){
        sessionFactory = SessionFactorySingleton.getInstance();
        twitRepository = new TwitRepository();
        userRepository = new UserRepository();
    }

    @Test
    public void save(){
        Twit twit = new Twit(null,"a",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"f","a","a","fd","f","3",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);

        Twit returnedTwit = twitRepository.save(twit);

        assertNotNull(returnedTwit);
        assertEquals(twit.getId(),returnedTwit.getId());
    }

    @Test
    public void update(){
        Twit twit = new Twit(null,"a",null,new HashSet<>());
        Twit returnedTwit = twitRepository.save(twit);
        User user = new User(null,"f","a","a","fd","f","3",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);

        Twit twit1 = new Twit(twit.getId(), "b",null,new HashSet<>());
        Twit updatedTwit = twitRepository.update(twit1);

        assertEquals(returnedTwit.getId(),updatedTwit.getId());
        assertEquals("b",updatedTwit.getTwit());
    }

    @Test
    public void delete(){
        Twit twit = new Twit(null,"a",null,new HashSet<>());
        Twit returnedTwit = twitRepository.save(twit);
        User user = new User(null,"f","a","a","fd","f","3",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);

        twitRepository.delete(returnedTwit);

        assertEquals(0,twitRepository.findAll().size());
    }

    @Test
    void findById() {
        Twit twit = new Twit(null,"a",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"f","a","a","fd","f","3",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);

        Twit returnedTwit = twitRepository.findById(twit.getId());

        assertNotNull(returnedTwit);
        assertEquals(twit.getId(),returnedTwit.getId());
        assertEquals(twit.getTwit(),returnedTwit.getTwit());
    }

    @Test
    void findAll() {
        Twit twit = new Twit(null,"a",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"f","a","a","fd","f","3",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);

        List<Twit> returnedTwitList = twitRepository.findAll();

        assertNotNull(returnedTwitList);
        assertEquals(returnedTwitList.get(0).getId(),twit.getId());
        assertNotEquals(0,returnedTwitList.size());
    }

    @AfterEach
    void truncate() {
        twitRepository.truncate();
        userRepository.truncate();
    }
}