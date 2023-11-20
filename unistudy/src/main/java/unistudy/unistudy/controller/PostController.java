package unistudy.unistudy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unistudy.unistudy.domain.Post;
import unistudy.unistudy.service.PostService;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    /* 포스트 작성 */
    @PostMapping("/post")
    public ResponseEntity<Integer> post(@RequestBody Post post) {
        try {
            Integer postId = postService.uploadPost(post);
            return new ResponseEntity<>(postId, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping("/posts")
public ResponseEntity<List<Post>> getPosts(
        @RequestParam(required = false) Integer writerId,
        @RequestParam(required = false) Integer studyGroupId
) {
    List<Post> posts;

    if (writerId != null) {
        posts = postService.findPostByWriterId(writerId);
    } else if (studyGroupId != null) {
        posts = postService.findPostByStudygroupId(studyGroupId);
    } else {
        posts = postService.findAllPosts();
    }

    return new ResponseEntity<>(posts, HttpStatus.OK);
}


    /* 특정 id의 post 조회 */
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Optional<Post> optionalPost = postService.findPostById(id);
        if (optionalPost.isPresent()) {
            return new ResponseEntity<>(optionalPost.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /* 특정 포스트 수정 */
    @PutMapping("/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Integer id, @RequestBody Post updatedPost) {
        try {
            postService.updatePost(id, updatedPost);
            return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /* 특정 포스트 삭제 */
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
