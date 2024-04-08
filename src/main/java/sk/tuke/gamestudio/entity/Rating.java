package sk.tuke.gamestudio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import sk.tuke.gamestudio.service.RatingException;

import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery( name = "Rating.getRating",
        query = "SELECT r FROM Rating r WHERE r.game=:game AND r.player =: player")
@NamedQuery( name = "Rating.resetRatings",
        query = "DELETE FROM Rating")
@NamedQuery( name = "Rating.getAverageRating",
        query = "SELECT AVG(rating ) AS rating FROM Rating r WHERE r.game =: game")

public class Rating implements Serializable {

    @Id
    @GeneratedValue
    private int ident;
    private String game;
    private String player;
    private int rating;
    private Date ratedOn;

    public Rating() {
    }

    public Rating(String game, String player, int rating, Date ratedOn) {
        this.game = game;
        this.player = player;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getRating() {
        return rating;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new RatingException("Rating must be in the range 1-5");
        }
        this.rating = rating;
    }

    public Date getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(Date ratedOn) {
        this.ratedOn = ratedOn;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", rating=" + rating +
                ", ratedOn=" + ratedOn +
                '}';
    }
}
