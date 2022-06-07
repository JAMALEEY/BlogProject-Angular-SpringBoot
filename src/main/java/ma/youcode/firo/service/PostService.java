package ma.youcode.firo.service;

import ma.youcode.firo.dto.PostDto;
import ma.youcode.firo.model.Post;
import ma.youcode.firo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private PostRepository postRepository;

    public void createPost(PostDto postDto) {
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException("No user logged in")
        );
        post.setUsername(loggedInUser.getUsername());
        post.setCreatedOn(Instant.now());
        return post;
    }


    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());

    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;

    }

    public Object readSinglePost(Long id) {
    }
}
