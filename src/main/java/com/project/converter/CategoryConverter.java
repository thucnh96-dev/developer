package com.project.converter;

import com.project.entity.Category;
import com.project.form.CategoryForm;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("categoryConverter")
public class CategoryConverter extends AbstractThrowableConverter<CategoryForm, Category,Exception> {

    @Override
    public List convert(List from) throws Exception {
        return null;
    }

    @Override
    public Category convert(CategoryForm categoryForm) throws Exception {
        Category category = new Category();
        category.setId(categoryForm.getId());
        category.setName(categoryForm.getName());
         return category;
    }
}
