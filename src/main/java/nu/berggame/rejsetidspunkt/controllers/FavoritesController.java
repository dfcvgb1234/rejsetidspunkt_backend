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

    @GetMapping("/{accessKey}/{id}")
    public ResponseEntity<FavoriteResponse> GetFavoriteById(@PathVariable String accessKey, @PathVariable int id) {
        var favorite = favoritesService.getFavoriteById(accessKey, id);

        if (favorite != null) {
            return ResponseEntity.ok(new FavoriteResponse(favorite));
        }
        else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }

    @GetMapping("/{accessKey}")
    public ResponseEntity<List<FavoriteResponse>> GetAllFavoritesFromUser(@PathVariable String accessKey) {

        List<FavoriteResponse> favoriteResponses = new ArrayList<>();

        var favorites = favoritesService.getAllUserFavorites(accessKey);
        for (Favorite fav : favorites) {
            favoriteResponses.add(new FavoriteResponse(fav));
        }

        return ResponseEntity.ok(favoriteResponses);
    }

    @PostMapping("/{accessKey}")
    public ResponseEntity addNewFavoriteToUser(@PathVariable String accessKey, @RequestBody FavoriteRequest request) {

        favoritesService.addFavoriteToUser(accessKey, request);

        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{accessKey}/{id}")
    public ResponseEntity deleteFavoriteFromUser(@PathVariable String accessKey, @PathVariable int id) {
        favoritesService.removeFavoriteFromUser(accessKey, id);

        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }
}
