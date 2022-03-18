package repositories;

import models.Comment;
import models.Twit;
import models.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentRepositoryTest {
    private static SessionFactory sessionFactory;
    private static CommentRepository commentRepository;
    private static TwitRepository twitRepository;
    private static UserRepository userRepository;

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        commentRepository = new CommentRepository();
        twitRepository = new TwitRepository();
        userRepository = new UserRepository();
    }

    @Test
    public void save(){
        Comment comment = new Comment(null,"a",null,null);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        comment.setTwit(twit);
        comment.setUser(user);

        Comment returnedComment = commentRepository.save(comment);

        assertNotNull(returnedComment);
        assertEquals(user,comment.getUser());
        assertEquals(twit,comment.getTwit());
    }

    @Test
    public void update(){
        Comment comment = new Comment(null,"a",null,null);
        commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        comment.setTwit(twit);
        comment.setUser(user);
        Comment returnedComment = commentRepository.save(comment);

        Comment updatedComment = new Comment(comment.getId(), "b",null,null);
        commentRepository.update(updatedComment);

        assertEquals(comment.getId(),updatedComment.getId());
        assertEquals("b",updatedComment.getComment());
    }

    @Test
    public void delete(){
        Comment comment = new Comment(null,"a",null,null);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        twitRepository.save(twit);
        comment.setTwit(twit);
        comment.setUser(user);
        Comment returnedComment = commentRepository.save(comment);

        commentRepository.delete(returnedComment);

        assertEquals(0,twitRepository.findAll().size());
    }

    @Test
    void findById() {
        Comment comment = new Comment(null,"a",null,null);
        commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        comment.setTwit(twit);
        comment.setUser(user);

        Comment comment1 = commentRepository.findById(comment.getId());

        assertNotNull(comment1);
        assertEquals(comment.getId(),comment1.getId());
        assertEquals(comment.getComment(),comment1.getComment());
    }

    @Test
    void findAll() {
        Comment comment = new Comment(null,"a",null,null);
        commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        comment.setTwit(twit);
        comment.setUser(user);

        Comment comment1 = new Comment(null,"a",null,null);
        commentRepository.save(comment);
        Twit twit1 = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit1);
        User user1 = new User(null,"b","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user1);
        twit.setUser(user1);
        comment1.setTwit(twit1);
        comment1.setUser(user1);

        List<Comment> returnedCommentList = commentRepository.findAll();

        assertNotNull(returnedCommentList);
        assertNotEquals(0,returnedCommentList.size());
        assertEquals(2,returnedCommentList.size());
    }

    @AfterEach
    void truncate() {
        commentRepository.truncate();
        twitRepository.truncate();
        userRepository.truncate();
    }
}