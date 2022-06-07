package ma.youcode.firo.service;

import ma.youcode.firo.dto.PostDto;
import ma.youcode.firo.model.Post;
import ma.youcode.firo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User username = authService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException("No user logged in")
        );
        post.setUsername(username.getUsername());
        post.setCreatedOn(Instant.now());
        postRepository.save(post);
    }


    public Object showAllPosts() {
    }

    public Object readSinglePost(Long id) {
    }
}
