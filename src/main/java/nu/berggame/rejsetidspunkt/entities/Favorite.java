package nu.berggame.rejsetidspunkt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nu.berggame.rejsetidspunkt.dto.request.FavoriteRequest;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "favorites")
public class Favorite {

    public Favorite(FavoriteRequest request) {
        this.stationId = request.getStationId();
        this.line = request.getLine();
        this.direction = request.getDirection();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stationId;
    private String line;
    private String direction;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
