package com.example.dayte.admin.contents.dto;

import com.example.dayte.admin.contents.domain.AdminContents;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class AdminContentsImageDTO {

    private List<MultipartFile> images;

    private String imageURL;

    private AdminContents adminContents;

    public AdminContentsImageDTO(List<MultipartFile> images) {
        this.images = images;
    }

}
