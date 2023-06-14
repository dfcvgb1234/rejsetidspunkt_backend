package nu.berggame.rejsetidspunkt.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nu.berggame.rejsetidspunkt.entities.Favorite;
import nu.berggame.rejsetidspunkt.entities.User;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteResponse {

    public FavoriteResponse(Favorite favorite) {
        this.id = favorite.getId();
        this.stationId = favorite.getStationId();
        this.line = favorite.getLine();
        this.direction = favorite.getDirection();
    }

    private int id;
    private String stationId;
    private String line;
    private String direction;
}
