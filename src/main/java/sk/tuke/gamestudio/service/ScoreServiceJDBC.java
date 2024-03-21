package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ScoreServiceJDBC implements ScoreService {
    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "Janko.kap12";
    public static final String SELECT = "SELECT game, player, points, playedOn FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
    public static final String DELETE = "DELETE FROM score";
    public static final String INSERT = "INSERT INTO score (game, player, points, playedOn) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE score SET points = ?, playedon = ? WHERE game = ? AND player = ?";
    public static final String GET = "SELECT points FROM score WHERE game = ? AND player = ?";

    @Override
    public void addScore(Score score) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setString(1, score.getGame());
            statement.setString(2, score.getPlayer());
            statement.setInt(3, score.getPoints());
            statement.setTimestamp(4, new Timestamp(score.getPlayedOn().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ScoreException("Problem inserting score", e);
        }
    }

    @Override
    public List<Score> getTopScores(String game) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1, game);
            try (ResultSet rs = statement.executeQuery()) {
                List<Score> scores = new ArrayList<>();
                while (rs.next()) {
                    scores.add(new Score(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4)));
                }
                return scores;
            }
        } catch (SQLException e) {
            throw new ScoreException("Problem selecting score", e);
        }
    }

    @Override
    public void reset() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting score", e);
        }
    }

    public void updatePlayer(String player, String game, int points)
    {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(UPDATE)
        ) {
            statement.setInt(1, points);
            statement.setTimestamp(2, new Timestamp(Date.valueOf(LocalDate.now()).getTime() ));
            statement.setString(3, game);
            statement.setString(4,player);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ScoreException("Problem updating score", e);
        }
    }
    public int getScore(String game, String player) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(GET);
        ) {
            statement.setString(1, game);
            statement.setString(2, player);
            try (ResultSet rs = statement.executeQuery()) {
                int score = 0;
                if (rs.next()) {
                    score = rs.getInt(1);
                }
                System.out.println(score);
                return score;
            }
        } catch (SQLException e) {
            throw new RatingException("Problem selecting score", e);
        }
    }

    @Override
    public void updatePlayer(Score score) {

    }
}
