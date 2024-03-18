package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommentServiceJDBCTest {

    private CommentServiceJDBC commentService;

    @BeforeEach
    public void setUp() {
        commentService = new CommentServiceJDBC();
    }

    @AfterEach
    public void tearDown() {
        commentService.reset();
    }

    @Test
    public void testAddCommentAndGetComments() {
        Comment comment = new Comment("TestGame", "TestPlayer", "Test comment", new Date());
        commentService.addComment(comment);
        List<Comment> comments = commentService.getComments("TestGame");
        assertEquals(1, comments.size());
        assertEquals(comment, comments.get(0));
    }

    @Test
    public void testReset() {
        Comment comment = new Comment("TestGame", "TestPlayer", "Test comment", new Date());
        commentService.addComment(comment);
        commentService.reset();
        List<Comment> comments = commentService.getComments("TestGame");
        assertTrue(comments.isEmpty());
    }
}