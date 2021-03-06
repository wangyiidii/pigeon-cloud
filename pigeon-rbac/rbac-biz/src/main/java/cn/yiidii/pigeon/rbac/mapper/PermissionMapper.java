package cn.yiidii.pigeon.rbac.mapper;

import cn.yiidii.pigeon.rbac.api.entity.Menu;
import cn.yiidii.pigeon.rbac.api.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源mapper
 *
 * @author: YiiDii Wang
 * @create: 2021-01-13 21:54
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
