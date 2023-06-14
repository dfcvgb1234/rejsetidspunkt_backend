package nu.berggame.rejsetidspunkt.dto.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nu.berggame.rejsetidspunkt.entities.User;

@Getter
@Setter
@NoArgsConstructor
public class FavoriteRequest {

    private String stationId;
    private String line;
    private String direction;
}
