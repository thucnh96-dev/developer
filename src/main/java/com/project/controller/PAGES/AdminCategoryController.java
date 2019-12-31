package com.project.controller.PAGES;

import com.project.controller.AbtractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCategoryController extends AbtractController {

    @GetMapping(value = "/admin/cate")
    public String category(){
        return "/page/admin/category/page";
    }


}
