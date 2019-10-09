package com.simplante.web;

import java.util.List;

import javax.validation.Valid;

import com.simplante.dto.mapper.ObjectMapperUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplante.dto.CategoryDto;
import com.simplante.dto.PostDto;
import com.simplante.model.Category;
import com.simplante.service.CategoryService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {



    private final CategoryService categoryService;


    /**
     *
     * @param categoryService service for this
     *
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * @return return list of categories
     */
    @GetMapping
    public ResponseEntity<?> listAllCategories() {
        log.debug("get list Categories");
        return new ResponseEntity<>(ObjectMapperUtils.mapAll(
                categoryService.ListCategories(), CategoryDto.class), HttpStatus.OK);
    }


    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            if (categoryService.findById(id) == null)
                return new ResponseEntity<Object>(new Exception("Category not exist"), HttpStatus.NOT_FOUND);

            log.debug("Get post by ID");
            return new ResponseEntity<>(ObjectMapperUtils.map(categoryService.findById(id), CategoryDto.class), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Find by id : ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}/posts")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Long id) {
        try {
            Category category = categoryService.findById(id);
            if (category == null)
                return new ResponseEntity<>(new Exception("CATEGORY_NOT_FOUND "), HttpStatus.NOT_FOUND);
            List<PostDto> result = ObjectMapperUtils.mapAll(categoryService.getPostsByCategory(id), PostDto.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param categoryDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody @Valid CategoryDto categoryDto) {

        try {
            if (categoryDto.getId() != null)
                return new ResponseEntity<>(new Exception("Please remove the Id"), HttpStatus.UNAUTHORIZED);

            if (categoryService.findByName(categoryDto.getName()) != null)
                return new ResponseEntity<>(new Exception("Category already exist"), HttpStatus.CONFLICT);

            Category result = categoryService.createCategory(ObjectMapperUtils.map(categoryDto, Category.class));
            return new ResponseEntity<>(ObjectMapperUtils.map(result, CategoryDto.class), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id
     * @param categoryDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {

        try {

            if (categoryService.findById(id) == null)
                return new ResponseEntity<>("This category not exist", HttpStatus.FORBIDDEN);
            categoryDto.setId(id);
            Category result = categoryService.updateCategory(ObjectMapperUtils.map(categoryDto, Category.class));
            return new ResponseEntity<>(ObjectMapperUtils.map(result, CategoryDto.class), HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * @return
     */
    /*@PreAuthorize("hasAuthority('ADMIN')")*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
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

    /**
     * @return
     */
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
