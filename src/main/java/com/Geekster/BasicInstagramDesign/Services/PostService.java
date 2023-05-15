package com.Geekster.BasicInstagramDesign.Services;

import com.Geekster.BasicInstagramDesign.Models.Post;
import com.Geekster.BasicInstagramDesign.Repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private IPostRepository postRepository;

    public Post savePost(Post post) {
        try{
            return postRepository.save(post);
        }catch (Exception e){
            return  null;
        }
    }

    public Post getPostById(Long postId) {
        try {
            Optional<Post> postOptional = postRepository.findById(postId);
            if(postOptional.isPresent()){
                return postOptional.get();
            }else{
                throw new IllegalStateException("No post found with this postId");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
