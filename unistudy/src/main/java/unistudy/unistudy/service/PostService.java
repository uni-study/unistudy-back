package unistudy.unistudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unistudy.unistudy.domain.Post;
import unistudy.unistudy.repository.PostRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /* 포스팅 */
    public Integer uploadPost(Post post){
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findPostById(Integer postId) {
        return postRepository.findById(postId);
    }


    public List<Post> findPostByWriterId(Integer writerId) {
        return postRepository.findByWriterId(writerId);
    }

    public List<Post> findPostByStudygroupId(Integer studygroupId) {
        return postRepository.findByStudyGroupId(studygroupId);
    }

    public List<Post> findByTitleContaining(String title) {
        return postRepository.findByTitleContaining(title);
    }

    public List<Post> findByPostedAtBetween(Date startDate, Date endDate){
        return postRepository.findByPostedAtBetween(startDate, endDate);
    }
    public List<Post> findByUpdatedAtBetween(Date startDate, Date endDate){
        return postRepository.findByUpdatedAtBetween(startDate, endDate);
    }
    public void updatePost(Integer id, Post updatedPost) {
        Optional<Post> existingPostOptional = postRepository.findById(id);

        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();

            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setMainText(updatedPost.getMainText());
            existingPost.setUpdatedAt(updatedPost.getUpdatedAt());

            // save 메서드를 사용하여 엔티티를 업데이트
            postRepository.save(existingPost);
        } else {
            throw new RuntimeException("Post not found");
        }
    }
    public void deletePost(Integer id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new RuntimeException("Post not found");
        }
    }

}
