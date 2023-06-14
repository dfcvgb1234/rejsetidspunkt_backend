package nu.berggame.rejsetidspunkt.services;

import nu.berggame.rejsetidspunkt.dto.request.FavoriteRequest;
import nu.berggame.rejsetidspunkt.entities.Favorite;
import nu.berggame.rejsetidspunkt.repositories.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    FavoritesRepository repository;

    @Autowired
    UserService userService;

    public List<Favorite> getAllUserFavorites(String username) {
        return repository.findByUser_Username(username);
    }

    public void addFavoriteToUser(String username, FavoriteRequest request) {
        var user = userService.getUserByUsername(username);

        var existingFavorite = repository.findByStationIdAndLineAndDirectionAndUser_Username(
                request.getStationId(),
                request.getLine(),
                request.getDirection(),
                username
        );

        if (existingFavorite.isPresent()) {
            return;
        }

        Favorite favorite = new Favorite(request);
        favorite.setUser(user);

        repository.save(favorite);
    }

    public void removeFavoriteFromUser(int id) {
        repository.deleteById(id);
    }
}
