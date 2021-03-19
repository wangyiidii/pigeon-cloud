package cn.yiidii.pigeon.rbac.service;


import cn.yiidii.pigeon.common.core.base.BaseSearchParam;
import cn.yiidii.pigeon.rbac.api.dto.RoleDTO;
import cn.yiidii.pigeon.rbac.api.entity.Resource;
import cn.yiidii.pigeon.rbac.api.entity.Role;
import cn.yiidii.pigeon.rbac.api.form.RoleForm;
import cn.yiidii.pigeon.rbac.api.form.RoleMenuForm;
import cn.yiidii.pigeon.rbac.api.form.RoleUserForm;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色业务接口
 *
 * @author: YiiDii Wang
 * @create: 2021-01-13 21:46
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据uid获取角色集合
     *
     * @param uid
     * @return
     */
    List<Role> getRoleListByUid(Long uid);

    /**
     * 根据角色code获取角色
     *
     * @param code
     * @return
     */
    Role getRoleByCode(String code);

    /**
     * 创建角色
     *
     * @param roleDTO
     * @return
     */
    int create(RoleDTO roleDTO);

    /**
     * 创建角色
     *
     * @param roleForm
     * @return
     */
    int create(RoleForm roleForm);

    /**
     * 角色列表
     *
     * @param searchParam
     * @return
     */
    IPage<Role> list(BaseSearchParam searchParam);

    /**
     * 角色菜单
     * @param roleId    角色ID
     * @return
     */
    List<Resource> getRoleMenu(Long roleId);

    /**
     * 绑定用户
     * @param roleUserForm
     */
    void bindUser(RoleUserForm roleUserForm);

    /**
     * 绑定菜单
     * @param roleMenuForm
     */
    void bindMenu(RoleMenuForm roleMenuForm);

    /**
     * 删除角色
     * @param roleIdList    角色ID集合
     * @return
     */
    boolean delRole(List<Long> roleIdList);
}
