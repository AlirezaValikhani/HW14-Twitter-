package services;

import models.User;
import repositories.UserRepository;

import java.util.List;

public class UserService implements BaseService<User,Long> {
    private UserRepository userRepository;

    public UserService(){
        this.userRepository = new UserRepository();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
