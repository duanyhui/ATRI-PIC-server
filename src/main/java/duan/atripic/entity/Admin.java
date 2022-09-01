package duan.atripic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author duanyhui
 * @since 2022-08-29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
  @ApiModel(value = "Admin对象", description = "")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "uid", type = IdType.AUTO)
      private Integer uid;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String pwd;

      @ApiModelProperty("区分角色权限ROOT，USER，VISITOR")
      private String role;

      @ApiModelProperty("账号封禁状态ENABLE或者UNABLE")
      private String status;

      @ApiModelProperty("类型QQ或者DEFAULT")
      private String type;


}
