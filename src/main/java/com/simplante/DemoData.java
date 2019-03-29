package com.simplante;

import com.simplante.dao.*;
import com.simplante.model.*;
import com.simplante.service.PostService;
import com.simplante.service.UserAppService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements ApplicationRunner {

    private CategoryRepository categoryRepository;
    private PostService postService;
    private CommentRepository commentRepository;
    private UserAppService userAppService;
    private RoleAppRepository roleAppRepository;
    private PostRepository postRepository;

    public DemoData(CategoryRepository categoryRepository, PostService postService, CommentRepository commentRepository,
                    UserAppService userAppService, RoleAppRepository roleAppRepository, PostRepository postRepository) {
        this.categoryRepository = categoryRepository;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.userAppService = userAppService;
        this.roleAppRepository = roleAppRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Category category1 = new Category();
        category1.setName("Drôle");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Triste");
        categoryRepository.save(category2);


        Post post1 = new Post();
        post1.setTitle("Premier post");
        post1.setBody("Voilà le premier test Yes !!");
        post1.setCategory(categoryRepository.findById(1L).get());
        postRepository.save(post1);

        Comment comment = new Comment("De la bombe");
        commentRepository.save(comment);
        post1.addComment(comment);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("Deuxieme post");
        post2.setBody("Voilà le deuxième test Yes !!");
        post2.setCategory(category2);
        postRepository.save(post2);

        roleAppRepository.save(new RoleApp("ADMIN"));
        roleAppRepository.save(new RoleApp("USER"));

        userAppService.createUser(new UserApp("test@gmail.com", "password", "admin",
                "Carter", "Hash"));

        userAppService.createUser(new UserApp("test2@gmail.com", "password", "User",
                "Carter", "Guetchly"));

        userAppService.addRoleToUser("admin", "ADMIN");

    }
}
