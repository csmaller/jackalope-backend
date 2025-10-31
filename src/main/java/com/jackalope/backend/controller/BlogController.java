package com.jackalope.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jackalope.backend.model.Blog;
import com.jackalope.backend.model.User;
import com.jackalope.backend.service.BlogService;
import com.jackalope.backend.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        System.out.println("Received blog: title=" + blog.getTitle() + ", authorId=" + blog.getAuthorId());
        if (blog.getAuthorId() != null) {
            User author = userService.getUser(blog.getAuthorId());
            blog.setAuthor(author);
        } else {
            throw new RuntimeException("Author ID is required");
        }
        Blog savedBlog = blogService.saveBlog(blog);
        return ResponseEntity.ok(savedBlog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id) {
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok(blog);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        if (blog.getAuthorId() != null) {
            User author = userService.getUser(blog.getAuthorId());
            blog.setAuthor(author);
        }
        Blog updatedBlog = blogService.updateBlog(id, blog);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Blog>> getBlogsByAuthor(@PathVariable Long authorId) {
        List<Blog> blogs = blogService.getBlogsByAuthor(authorId);
        return ResponseEntity.ok(blogs);
    }
    
    @GetMapping("/latest")
    public ResponseEntity<List<Blog>> getLatestBlogs() {
        List<Blog> blogs = blogService.getLatestBlogs();
        return ResponseEntity.ok(blogs);
    }
}