package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.Post;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Override
    Optional<Post> findById(Integer id);
    List<Post> findByWriter_Id(Integer userId);
    List<Post> findByStudygroup_Id(Integer studygroupId);

    List<Post> findByTitleContaining(String title);
    List<Post> findByPostedAtBetween(Date startDate, Date endDate);
    List<Post> findByUpdatedAtBetween(Date startDate, Date endDate);


}
