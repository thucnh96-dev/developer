package com.project.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private MultipartFile image;
}
