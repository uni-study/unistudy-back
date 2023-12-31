package unistudy.unistudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unistudy.unistudy.domain.Post;
import unistudy.unistudy.domain.Studygroup;
import unistudy.unistudy.repository.PostRepository;
import unistudy.unistudy.repository.StudygroupRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudygroupService {
    private StudygroupRepository studygroupRepository;
    private PostRepository postRepository;

    @Autowired
    public StudygroupService(StudygroupRepository studygroupRepository, PostRepository postRepository) {
        this.studygroupRepository = studygroupRepository;
        this.postRepository = postRepository;
    }

    public Integer createStudygroup(Studygroup studygroup){
        studygroupRepository.save(studygroup);
        return studygroup.getId();
    }

    public List<Studygroup> findAllStudygroup() {
        return studygroupRepository.findAll();
    }

    public Optional<Studygroup> findById(Integer studygroupId){
        return studygroupRepository.findById(studygroupId);
    }

    public List<Studygroup> findByLeaderId(Integer leaderId){
        return studygroupRepository.findByLeaderId(leaderId);
    }

    public List<Studygroup> findByNameContaining(String name){
        return studygroupRepository.findByNameContaining(name);
    }

    public List<Studygroup> findByDescriptionContaining(String description){
        return studygroupRepository.findByDescriptionContaining(description);
    }

    public List<Studygroup> findByCurrentState(Integer state){
        return studygroupRepository.findByCurrentState(state);
    }

    public List<Studygroup> findByContactContaining(String contact){
        return studygroupRepository.findByContactContaining(contact);
    }

    public List<Studygroup> findByDepartment(Integer department){
        return studygroupRepository.findByDepartment(department);
    }

    public List<Studygroup> findByRecruitmentDeadlineBetween(Date startDate, Date endDate){
        return studygroupRepository.findByRecruitmentDeadlineBetween(startDate, endDate);

    }

    public void updateStudygroup(Integer id, Studygroup updatedStudygroup){
        Optional<Studygroup> existingStudygroupOptional = studygroupRepository.findById(id);

        if(existingStudygroupOptional.isPresent()) {
            Studygroup existingStudygroup = existingStudygroupOptional.get();

            existingStudygroup.setName(updatedStudygroup.getName());
            existingStudygroup.setCurrentState(updatedStudygroup.getCurrentState());
            existingStudygroup.setLeaderId(updatedStudygroup.getLeaderId());
            existingStudygroup.setStudyPeriod(updatedStudygroup.getStudyPeriod());
            existingStudygroup.setDescription(updatedStudygroup.getDescription());
            existingStudygroup.setStudyMethod(updatedStudygroup.getStudyMethod());
            existingStudygroup.setContact(updatedStudygroup.getContact());
            existingStudygroup.setRecruitmentDeadline(updatedStudygroup.getRecruitmentDeadline());

            studygroupRepository.save(existingStudygroup);
        }
        else{
            throw new RuntimeException("Study group not found");
        }
    }

    public void deleteStudygroup(Integer id){
        Optional<Studygroup> studygroupOptional = studygroupRepository.findById(id);

        if(studygroupOptional.isPresent()) {
            Studygroup studygroup = studygroupOptional.get();

            // Post 엔티티에서 해당 User를 참조하는 필드를 null로 설정
            List<Post> posts = postRepository.findByStudygroup_Id(studygroup.getId());
            for (Post post : posts) {
                post.setStudygroup(null);
                postRepository.save(post);
            }


            studygroupRepository.deleteById(id);
        }else{
            throw new RuntimeException("Study group not found");
        }
    }

}

