package com.grepp.nbe562team04.model.user.dto;

import com.grepp.nbe562team04.model.auth.code.Role;
import com.grepp.nbe562team04.model.level.entity.Level;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class UserDto {
    private Long userId;
    private String email;
    private String password;
    private Role role;
    private String nickname;
    private String userImage;
    private Level level;
    private Integer exp;
    private String comment;
    private LocalDate createdAt;

    private MultipartFile userImageFile;
}