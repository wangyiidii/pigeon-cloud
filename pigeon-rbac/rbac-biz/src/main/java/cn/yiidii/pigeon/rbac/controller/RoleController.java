package cn.yiidii.pigeon.rbac.controller;

import cn.yiidii.pigeon.common.core.base.BaseSearchParam;
import cn.yiidii.pigeon.common.core.base.R;
import cn.yiidii.pigeon.common.core.base.entity.SuperEntity.Add;
import cn.yiidii.pigeon.common.security.util.SecurityUtils;
import cn.yiidii.pigeon.rbac.api.dto.RoleDTO;
import cn.yiidii.pigeon.rbac.api.dto.UserDTO;
import cn.yiidii.pigeon.rbac.api.entity.Role;
import cn.yiidii.pigeon.rbac.api.entity.User;
import cn.yiidii.pigeon.rbac.service.IRoleService;
import cn.yiidii.pigeon.rbac.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * 角色
 *
 * @author: YiiDii Wang
 * @create: 2021-01-13 15:30
 */
@RestController
@RequestMapping("role")
@Api(tags = "角色")
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final IRoleService roleService;

    @PostMapping("/create")
    @ApiOperation(value = "创建角色")
    public R create(@Validated(value = {Add.class}) @RequestBody RoleDTO roleDTO) {
        int row = roleService.create(roleDTO);
        return R.ok(null, row > 0 ? "创建角色成功" : "创建角色失败");
    }

    @GetMapping("/list")
    @PreAuthorize("@pms.hasPermission('user')")
    @ApiOperation(value = "角色列表")
    public R<IPage<Role>> list(BaseSearchParam searchParam) {
        return R.ok(roleService.list(searchParam));
    }

}