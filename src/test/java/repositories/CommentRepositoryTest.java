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
        Comment resultComment = commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        resultComment.setTwit(twit);
        resultComment.setUser(user);

        Comment returnedComment = commentRepository.save(resultComment);

        assertNotNull(returnedComment);
        assertEquals(user,resultComment.getUser());
        assertEquals(twit,resultComment.getTwit());
    }

    @Test
    public void update(){
        Comment comment = new Comment(null,"a",null,null);
        Comment resultComment = commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        resultComment.setTwit(twit);
        resultComment.setUser(user);
        Comment returnedComment = commentRepository.save(resultComment);

        Comment updatedComment = new Comment(comment.getId(), "b",twit,user);
        commentRepository.update(updatedComment);

        assertEquals(comment.getId(),updatedComment.getId());
        assertEquals("b",updatedComment.getComment());
    }

    @Test
    public void delete(){
        Comment comment = new Comment(null,"a",null,null);
        Comment resultComment = commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        resultComment.setTwit(twit);
        resultComment.setUser(user);
        Comment returnedComment = commentRepository.save(resultComment);

        commentRepository.delete(returnedComment);

        assertNull(resultComment.getId());
    }

    @Test
    void findById() {
        Comment comment = new Comment(null,"a",null,null);
        Comment resultComment = commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        resultComment.setTwit(twit);
        resultComment.setUser(user);
        Comment returnedComment = commentRepository.save(resultComment);

        Comment comment1 = commentRepository.findById(returnedComment.getId());

        assertNotNull(comment1);
        assertEquals(returnedComment.getId(),comment1.getId());
        assertEquals(returnedComment.getComment(),comment1.getComment());
    }

    @Test
    void findAll() {
        Comment comment = new Comment(null,"a",null,null);
        Comment resultComment = commentRepository.save(comment);
        Twit twit = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit);
        User user = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user);
        twit.setUser(user);
        resultComment.setTwit(twit);
        resultComment.setUser(user);
        Comment returnedComment = commentRepository.save(resultComment);
        Comment comment1 = new Comment(null,"a",null,null);
        Comment resultComment1 = commentRepository.save(comment1);
        Twit twit1 = new Twit(null,"g",null,new HashSet<>());
        twitRepository.save(twit1);
        User user1 = new User(null,"a","a","1","b","2","12",new HashSet<>(),new HashSet<>());
        userRepository.save(user1);
        twit.setUser(user);
        resultComment.setTwit(twit);
        resultComment.setUser(user);
        Comment returnedComment1 = commentRepository.save(resultComment1);

        List<Comment> returnedCommentList = commentRepository.findAll();

        assertNotNull(returnedCommentList);
        assertEquals(returnedCommentList.get(0).getId(),returnedComment.getId());
        assertNotEquals(0,returnedCommentList.size());
        assertEquals(2,returnedCommentList.size());
    }

    @AfterEach
    void truncate() {
        commentRepository.truncate();
    }
}