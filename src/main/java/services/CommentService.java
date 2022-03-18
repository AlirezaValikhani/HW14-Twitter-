package services;

import models.Comment;
import repositories.CommentRepository;

import java.util.List;

public class CommentService implements BaseService<Comment,Long> {
    private CommentRepository commentRepository;

    public CommentService(){
        this.commentRepository = new CommentRepository();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentRepository.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findAllByTwitId(Long twitId) {
        return commentRepository.findAllByTwitId(twitId);
    }

    public List<Comment> findByTwitId(Long twitId) {
        return commentRepository.findByTwitId(twitId);
    }
}
