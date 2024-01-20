package dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String userUuid;
    private String birthday;
    private String gender;
    private String phone;
    private List<String> collections;
}
