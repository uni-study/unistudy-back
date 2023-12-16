package unistudy.unistudy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unistudy.unistudy.DTO.PostDto;
import unistudy.unistudy.domain.Post;
import unistudy.unistudy.domain.Studygroup;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.service.PostService;
import unistudy.unistudy.service.StudygroupService;
import unistudy.unistudy.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PostController {
    private final PostService postService;
    private final StudygroupService studygroupService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, StudygroupService studygroupService, UserService userService){
        this.postService = postService;
        this.studygroupService = studygroupService;
        this.userService = userService;
    }


    // write post
    @PostMapping("/post")
    public ResponseEntity<PostDto> post(@RequestBody PostDto postDto) {
        try {
            // 클라이언트가 전달한 studygroupId와 writerId를 사용하여 Studygroup 및 Writer 객체를 가져옴
            Studygroup studygroup = studygroupService.findById(postDto.getStudygroupId())
                    .orElse(null);  // orElse 메서드를 사용하여 Optional이 비어있을 때 null을 반환하도록 함

            User writer = userService.findOneUser(postDto.getWriterId())
                    .orElse(null);  // orElse 메서드를 사용하여 Optional이 비어있을 때 null을 반환하도록 함


            // Post 엔티티 생성 및 필드 설정
            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setMainText(postDto.getMainText());
            post.setStudygroup(studygroup);
            post.setWriter(writer);
            post.setPostedAt(postDto.getPostedAt());
            post.setUpdatedAt(postDto.getUpdatedAt());
            post.setExpiredAt(postDto.getExpiredAt());

            Integer postId = postService.uploadPost(post);
            Optional<Post> createdPost = postService.findPostById(postId);

            if (createdPost.isPresent()) {
                PostDto createdPostDto = convertToDTO(createdPost.get());
                return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // get post list and filter by writerId, studygroupId, title, writtenPeriod
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
        // change post entity to post Dto
        List<PostDto> postDTOs = posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDTOs, HttpStatus.OK);
    }


    // find post by id
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

    // edit post with id
    @PutMapping("/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Integer id, @RequestBody Post updatedPost) {
        try {
            postService.updatePost(id, updatedPost);
            return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // delete post with id
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Post entity to post dto
    private PostDto convertToDTO(Post post) {
        Integer writerId = (post.getWriter() != null) ? post.getWriter().getId() : null;
        Integer studygroupId = (post.getStudygroup() != null) ? post.getStudygroup().getId() : null;

        PostDto postDTO = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getMainText(),
                post.getPostedAt(),
                post.getUpdatedAt(),
                post.getExpiredAt(),
                studygroupId,
                writerId
        );

        return postDTO;
    }


}
