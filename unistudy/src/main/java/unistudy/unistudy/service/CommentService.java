// CommentService.java

package unistudy.unistudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unistudy.unistudy.DTO.CommentDto;
import unistudy.unistudy.domain.Comment;
import unistudy.unistudy.domain.Post;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.repository.CommentRepository;
import unistudy.unistudy.repository.PostRepository;
import unistudy.unistudy.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public CommentDto createComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User writer = userRepository.findById(commentDto.getWriterId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setWriter(writer);
        comment.setMainText(commentDto.getMainText());
        comment.setPostedAt(commentDto.getPostedAt());

        Comment savedComment = commentRepository.save(comment);

        return convertToDto(savedComment);
    }

    public List<CommentDto> findByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findByPost_id(postId);
        return comments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CommentDto> findByWriterId(Integer writerId) {
        List<Comment> comments = commentRepository.findByWriter_id(writerId);
        return comments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CommentDto> findByMainTextContaining(String mainText) {
        List<Comment> comments = commentRepository.findByMainTextContaining(mainText);
        return comments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CommentDto> findByPostedAtBetween(Date startDate, Date endDate) {
        List<Comment> comments = commentRepository.findByPostedAtBetween(startDate, endDate);
        return comments.stream().map(this::convertToDto).collect(Collectors.toList());
    }



    public Optional<CommentDto> getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);

        return comment.map(this::convertToDto);
    }

    public CommentDto updateComment(Integer id, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        existingComment.setMainText(commentDto.getMainText());

        Comment updatedComment = commentRepository.save(existingComment);

        return convertToDto(updatedComment);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    private CommentDto convertToDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getWriter().getId(),
                comment.getMainText(),
                comment.getPostedAt()
        );
    }
}
