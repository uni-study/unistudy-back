package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.Studygroup;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudygroupRepository extends JpaRepository<Studygroup, Integer> {

    @Override
    Optional<Studygroup> findById(Integer integer);
    List<Studygroup> findByLeaderId(Integer leaderId);
    List<Studygroup> findByNameContaining(String name);
    List<Studygroup> findByDescriptionContaining(String description);
    List<Studygroup> findByCurrentState(Integer state);
    List<Studygroup> findByContactContaining(String contact);
    List<Studygroup> findByDepartment(Integer department);

    List<Studygroup> findByRecruitmentDeadlineBetween(Date startDate, Date endDate);
}
