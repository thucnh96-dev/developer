package com.project.controller.API;

import com.project.controller.AbtractController;
import com.project.entity.User;
import com.project.repository.specification.UserSpecification;
import com.project.response.APIResponse;
import com.project.response.PagingResponseModel;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController extends AbtractController {

    @Autowired
     private UserService userService;

    @GetMapping(value = "/api/user")
    public ResponseEntity<APIResponse> getAll(@RequestBody User user){

        UserSpecification  userSpecification = new UserSpecification(0l,user.getName(),user.getPhone(),true,"thuc",0,true);

        Page<User> users = userService.findAll(userSpecification,  PageRequest.of(0,10));
        PagingResponseModel pagingResponseModel = new PagingResponseModel();
        pagingResponseModel.setData(users.getContent());
        pagingResponseModel.setTotalResult(users.getTotalElements());
        pagingResponseModel.setTotalPage(users.getTotalPages());
        pagingResponseModel.setCurrentPage(users.getNumber());
        return  responseUtil.successResponse(pagingResponseModel);

    }
}
