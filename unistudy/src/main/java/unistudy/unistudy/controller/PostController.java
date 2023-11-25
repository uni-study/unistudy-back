package unistudy.unistudy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unistudy.unistudy.DTO.PostDto;
import unistudy.unistudy.domain.Post;
import unistudy.unistudy.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    /* 포스트 작성 */
    @PostMapping("/post")
    public ResponseEntity<PostDto> post(@RequestBody Post post) {
        try {
            Integer postId = postService.uploadPost(post);
            Optional<Post> createdPost = postService.findPostById(postId);

            if (createdPost.isPresent()) {
                PostDto postDto = convertToDTO(createdPost.get());
                return new ResponseEntity<>(postDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts(
            @RequestParam(required = false) Integer writerId,
            @RequestParam(required = false) Integer studyGroupId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        List<Post> posts;

        if (title != null) {
            posts = postService.findByTitleContaining(title);
        } else if (startDate != null && endDate != null) {
            posts = postService.findByPostedAtBetween(startDate, endDate);
        } else if (writerId != null) {
            posts = postService.findPostByWriterId(writerId);
        } else if (studyGroupId != null) {
            posts = postService.findPostByStudygroupId(studyGroupId);
        } else {
            posts = postService.findAllPosts();
        }
// Post 엔티티를 PostDTO로 변환
        List<PostDto> postDTOs = posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDTOs, HttpStatus.OK);
//        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    /* 특정 id의 post 조회 */
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
        Optional<Post> optionalPost = postService.findPostById(id);
        if (optionalPost.isPresent()) {
            PostDto postDto = convertToDTO(optionalPost.get());
            return new ResponseEntity<>(postDto, HttpStatus.OK);
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

    // Post 엔티티를 PostDTO로 변환하는 메서드
    private PostDto convertToDTO(Post post) {
        PostDto postDTO = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getMainText(),
                post.getPostedAt(),
                post.getUpdatedAt(),
                post.getExpiredAt(),
                (post.getStudygroup() != null) ? post.getStudygroup().getId() : null
        );

        return postDTO;
    }


}
