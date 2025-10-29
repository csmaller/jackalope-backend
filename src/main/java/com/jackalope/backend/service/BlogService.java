package com.jackalope.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jackalope.backend.model.Blog;
import com.jackalope.backend.model.User;
import com.jackalope.backend.repository.BlogRepository;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    
    @Autowired
    private UserService userService;

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog updateBlog(Long id, Blog blog) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        existingBlog.setTitle(blog.getTitle());
        existingBlog.setContent(blog.getContent());
        existingBlog.setAuthor(blog.getAuthor());
        return blogRepository.save(existingBlog);
    }

    public void deleteBlog(Long id) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        blogRepository.delete(existingBlog);
    }

    public List<Blog> getBlogsByAuthor(Long authorId) {
        User author = userService.getUser(authorId);
        return blogRepository.findByAuthor(author);
    }
}