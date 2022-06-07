package ma.youcode.firo.service;

import ma.youcode.firo.dto.PostDto;
import ma.youcode.firo.model.Post;
import ma.youcode.firo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    private void createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User username = authService.getCurrentUser().orElseThrow(
                () -> new illegalArgumentException("No user logged in")
        );
        post.setUsername(username.getUserName());
        post.setCreatedOn();
    }
}
