package com.grepp.nbe562team04.app.controller.web.member.payload;

import com.grepp.nbe562team04.model.user.dto.UserDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    private String nickname;
    @NotBlank
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Zㄱ-힣])(?!.*[ㄱ-힣]).{8,}$"
        ,message = "비밀번호는 8자리 이상의 영문자, 숫자, 특수문자 조합으로 이루어져야 합니다.")
    private String password;

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setNickname(nickname);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setCreatedAt(LocalDateTime.now());
        return userDto;
    }
}