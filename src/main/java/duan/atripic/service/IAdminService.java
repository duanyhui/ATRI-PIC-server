package duan.atripic.service;

import duan.atripic.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import duan.atripic.entity.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanyhui
 * @since 2022-08-29
 */
public interface IAdminService extends IService<Admin> {


    public Result login(Admin admin);

    public Result logout();
}
