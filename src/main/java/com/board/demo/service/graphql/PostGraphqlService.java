package com.board.demo.service.graphql;

import com.board.demo.domain.post.Post;
import com.board.demo.domain.post.PostRepository;
import com.board.demo.dto.post.PostListResponseDto;
import com.board.demo.dto.post.PostResponseDto;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostGraphqlService implements GraphQLQueryResolver {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostResponseDto post(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> allPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostListResponseDto::new).collect(Collectors.toList());
    }
}
