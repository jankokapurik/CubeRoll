package sk.tuke.gamestudio.game.CubeRoll.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Tile.class, name = "tile"),
        @JsonSubTypes.Type(value = ButtonTile.class, name = "buttonTile"),
        @JsonSubTypes.Type(value = TeleportTile.class, name = "teleportTile"),
        @JsonSubTypes.Type(value = FinishTile.class, name = "finishTile"),
        @JsonSubTypes.Type(value = PaintTile.class, name = "paintTile"),
        @JsonSubTypes.Type(value = ColorTile.class, name = "colorTile"),
})
public class Tile {
}
