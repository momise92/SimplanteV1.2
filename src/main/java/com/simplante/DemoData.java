package com.simplante;
import com.simplante.dao.CategoryRepository;
import com.simplante.dao.PostRepository;
import com.simplante.model.Category;
import com.simplante.model.Post;
import com.simplante.service.PostService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements ApplicationRunner {

    private CategoryRepository categoryRepository;
    private PostService postService;
    private PostRepository postRepository;

    public DemoData(CategoryRepository categoryRepository, PostService postService, PostRepository postRepository) {
        this.categoryRepository = categoryRepository;
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Category category1 = new Category();
        category1.setName("Drôle");
        categoryRepository.save(category1);


        Post post1 = new Post();
        post1.setTitle("Premier post");
        post1.setBody("Voilà le premier test Yes !!");
        post1.setCategoryId(1L);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("Deuxieme post");
        post2.setBody("Voilà le deuxième test Yes !!");
        post2.setCategoryId(1L);
        postRepository.save(post1);

    }
}
