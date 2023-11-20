package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.Studygroup;

@Repository
public interface StudygroupRepository extends JpaRepository<Studygroup, Integer> {
}
