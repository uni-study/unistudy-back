package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.Comment;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Override
    Optional<Comment> findById(Integer id);
    List<Comment> findByPost_id(Integer postId);
    List<Comment> findByWriter_id(Integer writerId);
    List<Comment> findByMainTextContaining(String content);
    List<Comment> findByPostedAtBetween(Date startDate, Date endDate);
}
