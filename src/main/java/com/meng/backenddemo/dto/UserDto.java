package com.meng.backenddemo.dto;

import lombok.Data;
import java.util.Date;

@Data
public class UserDto {
    private String userUuid;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String gender;
    private String phone;
    private int userRank;
}
