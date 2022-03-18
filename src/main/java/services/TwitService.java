package services;

import models.Twit;
import repositories.TwitRepository;

import java.util.List;

public class TwitService implements BaseService<Twit, Long> {
    private TwitRepository twitRepository;

    public TwitService(){
        this.twitRepository = new TwitRepository();
    }

    @Override
    public Twit save(Twit twit) {
        return twitRepository.save(twit);
    }

    @Override
    public void update(Twit twit) {
        twitRepository.update(twit);
    }

    @Override
    public void delete(Twit twit) {
        twitRepository.delete(twit);
    }

    @Override
    public Twit findById(Long id) {
        return twitRepository.findById(id);
    }

    @Override
    public List<Twit> findAll() {
        return twitRepository.findAll();
    }


    public List<Twit> findUserTwits(Long userId) {
        return twitRepository.findUserTwits(userId);
    }
}
