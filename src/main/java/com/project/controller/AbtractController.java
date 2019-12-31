package com.project.controller;

import com.project.entity.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.security.Principal;
import java.util.LinkedHashMap;

public abstract class AbtractController {

    @Autowired
    private UserService userService;

    protected User realUser(Principal principal){
        User user = userService.findByUserName(principal.getName());
        return user ;
    }

    protected PageRequest getListUserPostNotActive(int page, int size) {
        return PageRequest.of(page, size, Sort.Direction.DESC, "id");
    }

    protected PageRequest getListUserPostInActive(int page,int size) {
        return PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    }
    protected String StringcheckHepper(LinkedHashMap<String,Object> res, String string){
        if (res.get(string)!=null && !"".equals(res.get(string))){
            return res.get(string).toString();
        }else{
            return "";
        }

    }
    protected int IntcheckHepper(LinkedHashMap<String,Object> res,String string){
        if (res.get(string)!=null && !"".equals(res.get(string))){
            return Integer.valueOf(res.get(string).toString());
        }else{
            return 0;
        }

    }
}
