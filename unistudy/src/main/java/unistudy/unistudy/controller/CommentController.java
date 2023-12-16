// CommentController.java

package unistudy.unistudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unistudy.unistudy.DTO.CommentDto;
import unistudy.unistudy.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // write comment
    @PostMapping("/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }


    // find comment with id
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer id) {
        Optional<CommentDto> comment = commentService.getCommentById(id);
        return comment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // get comments and filtering by postId, writerId, mainText, postedAt
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getComments(
            @RequestParam(required = false) Integer postId,
            @RequestParam(required = false) Integer writerId,
            @RequestParam(required = false) String mainText,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date postedAt
    ) {
        List<CommentDto> comments;

        if (postId != null) {
            comments = commentService.findByPostId(postId);
        } else if (writerId != null) {
            comments = commentService.findByWriterId(writerId);
        } else if (mainText != null) {
            comments = commentService.findByMainTextContaining(mainText);
        } else if (postedAt != null) {
            comments = commentService.findByPostedAtBetween(postedAt, new Date(postedAt.getTime() + 24 * 60 * 60 * 1000)); // Adding one day to include the specified date
        } else {
            // Handle the case when no filter is provided, fetch all comments
            comments = commentService.findAllComments();
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // edit comments
    @PutMapping("/comment/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(id, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // delete comments
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
