package nu.berggame.rejsetidspunkt.repositories;

import nu.berggame.rejsetidspunkt.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser_Username(String username);

    Optional<Favorite> findByStationIdAndLineAndDirectionAndUser_Username(String stationId, String line, String direction, String username);
}
