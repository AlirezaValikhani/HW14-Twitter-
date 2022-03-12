package repositories;

import models.Twit;
import models.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        User returnedUser = userRepository.save(user);
        Twit twit = new Twit(null,"a",returnedUser,new HashSet<>());

        Twit returnedTwit = twitRepository.save(twit);

        assertNotNull(returnedTwit);
        assertEquals(twit.getId(),returnedTwit.getId());
    }

    @Test
    public void update(){
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        User returnedUser = userRepository.save(user);
        Twit twit = new Twit(null,"a",returnedUser,new HashSet<>());
        Twit returnedTwit = twitRepository.save(twit);

        User user1 = new User(user.getId(), "b","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        User returnedUser1 = userRepository.save(user1);
        Twit twit1 = new Twit(twit.getId(), "b",returnedUser,new HashSet<>());
        Twit returnedTwit1 = twitRepository.save(twit1);
        Twit updatedTwit = twitRepository.update(returnedTwit1);

        assertEquals(returnedTwit.getId(),updatedTwit.getId());
        assertEquals("b",updatedTwit.getTwit());
        assertNotEquals(returnedTwit.getUser(),updatedTwit.getUser());
    }

    @Test
    public void delete(){
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        User returnedUser = userRepository.save(user);
        Twit twit = new Twit(null,"a",returnedUser,new HashSet<>());
        Twit returnedTwit = twitRepository.save(twit);

        twitRepository.delete(returnedTwit);

        assertNull(user.getId());
    }

    @Test
    void findById() {
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        User returnedUser = userRepository.save(user);
        Twit twit = new Twit(null,"a",returnedUser,new HashSet<>());
        twitRepository.save(twit);

        Twit returnedTwit = twitRepository.findById(twit.getId());

        assertNotNull(returnedTwit);
        assertEquals(twit.getId(),returnedTwit.getId());
        assertEquals(twit.getTwit(),returnedTwit.getTwit());
    }

    @Test
    void findAll() {
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        User returnedUser = userRepository.save(user);
        Twit twit = new Twit(null,"a",returnedUser,new HashSet<>());
        twitRepository.save(twit);

        List<Twit> returnedTwitList = twitRepository.findAll();

        assertNotNull(returnedTwitList);
        assertEquals(returnedTwitList.get(0).getId(),twit.getId());
        assertNotEquals(0,returnedTwitList.size());
    }

    @AfterEach
    void truncate() {
        twitRepository.truncate();
    }
}