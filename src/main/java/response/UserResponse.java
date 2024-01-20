package response;

import dto.UserDto;
import lombok.Data;

@Data
public class UserResponse {
    private int status;
    private String message;
    private UserDto data;

    public UserResponse(int status, String message, UserDto data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}