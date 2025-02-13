/**
 * DTO (Data Transfer Object):
 * DTO là một design pattern dùng để đóng gói và truyền dữ liệu giữa các thành phần/layer trong ứng dụng
 * Là một class đơn giản chỉ chứa các thuộc tính (properties) và các phương thức getter/setter
 * Không chứa bất kỳ business logic nào
 * Mục đích chính là đóng gói dữ liệu để truyền qua network hoặc giữa các layer
 **/
package dto;

/**
 *
 * @author phucl
 */
public class UserDTO {
    private String userId;
    private String fullName;
    private String roleId;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userId, String fullName, String roleId, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.roleId = roleId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userId=" + userId + ", fullName=" + fullName + ", roleId=" + roleId + ", password=" + password + '}';
    }
    
}
