package nu.berggame.rejsetidspunkt.controllers;

import nu.berggame.rejsetidspunkt.dto.request.FavoriteRequest;
import nu.berggame.rejsetidspunkt.dto.response.FavoriteResponse;
import nu.berggame.rejsetidspunkt.entities.Favorite;
import nu.berggame.rejsetidspunkt.services.FavoritesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoritesController {

    @Autowired
    FavoritesService favoritesService;

    @GetMapping("/{username}")
    public ResponseEntity<List<FavoriteResponse>> GetAllFavoritesFromUser(@PathVariable String username) {

        List<FavoriteResponse> favoriteResponses = new ArrayList<>();

        var favorites = favoritesService.getAllUserFavorites(username);
        for (Favorite fav : favorites) {
            favoriteResponses.add(new FavoriteResponse(fav));
        }

        return ResponseEntity.ok(favoriteResponses);
    }

    @PostMapping("/{username}")
    public ResponseEntity addNewFavoriteToUser(@PathVariable String username, @RequestBody FavoriteRequest request) {

        favoritesService.addFavoriteToUser(username, request);

        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFavoriteFromUser(@PathVariable int id) {
        favoritesService.removeFavoriteFromUser(id);

        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }
}
