package com.simplante.web;

import com.simplante.dto.CategoryDto;
import com.simplante.dto.PostDto;
import com.simplante.dto.mapper.CategoryMapper;
import com.simplante.dto.mapper.PostMapper;
import com.simplante.model.Category;
import com.simplante.service.CategoryService;
import com.simplante.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final PostMapper postMapper;
    private final CategoryService categoryService;


    public CategoryController(PostService postService, CategoryMapper categoryMapper,
                              PostMapper postMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.postMapper = postMapper;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> listAllCategories(){
        log.debug("get list Categories");
        return new ResponseEntity<>(categoryMapper.CategoriesToListDto(
                categoryService.ListCategories()),HttpStatus.OK);
    }

    /**
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        try {
            if (categoryService.findById(id) == null)
                return new ResponseEntity<Object>(new Exception("Category not exist"),HttpStatus.NOT_FOUND);

            log.debug("Get post by ID");
            return new ResponseEntity<>(categoryMapper.categoryToDto(categoryService.findById(id)),HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find by id : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Long id) {
        try {
            Category category = categoryService.findById(id);
            if (category == null)
               return new ResponseEntity<Object>(new Exception("CATEGORY_NOT_FOUND "),HttpStatus.NOT_FOUND);
            List<PostDto> result = postMapper.listPostsToListPostsDto(categoryService.getPostsByCategory(id));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object>createCategory(@RequestBody @Valid CategoryDto categoryDto) {

        try {
            if (categoryDto.getId() != null)
                return new ResponseEntity<>(new Exception("Please remove the Id"),HttpStatus.UNAUTHORIZED);

            if (categoryService.findByName(categoryDto.getName()) != null)
                return new ResponseEntity<>(new Exception("Category already exist"),HttpStatus.CONFLICT);

            Category result = categoryService.createCategory(categoryMapper.DtoToCategory(categoryDto));
            return new ResponseEntity<>(categoryMapper.categoryToDto(result), HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateCategory(@PathVariable Long id, @RequestBody @Valid  CategoryDto categoryDto) {

        try {

            if (categoryService.findById(id) == null)
                return new ResponseEntity<>("This category not exist", HttpStatus.FORBIDDEN);
            categoryDto.setId(id);
            Category result = categoryService.updateCategory(categoryMapper.DtoToCategory(categoryDto));
            return new ResponseEntity<>(categoryMapper.categoryToDto(result), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable Long id){
        try {
            if (categoryService.findById(id) == null)
                throw new Exception("This category not exist");
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("delete category :", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllCategories() {
        try {
            Long count = categoryService.countCategory();
            categoryService.deleteAllCategory();
            log.debug("delete " + count + " categories");
            return new ResponseEntity<>("deleted " + count + " categories", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
