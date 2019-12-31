package com.project.controller.API;

import com.project.constants.CommonConstants;
import com.project.controller.AbtractController;
import com.project.converter.CategoryConverter;
import com.project.entity.Category;
import com.project.entity.Product;
import com.project.form.CategoryForm;
import com.project.service.CategoryService;
import com.project.specification.CategorySpec;
import com.project.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class CategoryController extends AbtractController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    @Qualifier("categoryConverter")
    CategoryConverter categoryConverter;

    int PAGE_DEFAULT = 0;
    int SIZE_DEFAULT = 10;

     @PostMapping(value = "/category")
     public String create(@Valid CategoryForm categoryForm, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {

         if (bindingResult.hasErrors()){
             return "";
         }
         String fileName = categoryForm.getImage().getOriginalFilename();
         String folderName = Paths.get(CommonConstants.RESOURCE_CATEGORY, categoryForm.getName()).toString();

         String filePath = FileUploadUtil.uploadFileNotifToServer(categoryForm.getImage().getBytes(), folderName , fileName, request);

         Category category = categoryConverter.convert(categoryForm);
         category.setImage(filePath);
         category = categoryService.save(category);
         redirectAttributes.addFlashAttribute("mes",category);

     return "";

    }

    @GetMapping(value = "/categories")
    public String create(Model model,@RequestParam LinkedHashMap<String,Object> res, HttpServletRequest request) throws Exception {
        int page = res.get("page") == null ? PAGE_DEFAULT : Integer.parseInt(res.get("page").toString());
        int size = res.get("size") == null ? SIZE_DEFAULT : Integer.parseInt(res.get("size").toString());

        Specification<Category> spec= Specification.where(CategorySpec.defaultWhere());

        String name = StringcheckHepper(res,"name");
        if (!StringUtils.isEmpty(name)) {
            spec = spec.and(CategorySpec.findByNameLike(name));
        }

        Page<Category> categories = categoryService.findAll(spec,this.getListUserPostNotActive(page,size));
       /* DTOUtils.mapPage(exams,ExamQuestioDTO.class) */;
        model.addAttribute("data",categories);
        return "";
    }

    @GetMapping(value = "/category/{id}")
    public String get(@PathVariable("id") long id, Model model) throws Exception {

        Optional<Category> categories =categoryService.findById(id);
        for (Product product : categories.get().getProducts()){
            System.out.println("product.getName() = " + product.getName());
        }

        model.addAttribute("data",categories.get());
        return "";
    }



}
