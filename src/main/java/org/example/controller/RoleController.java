package org.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.example.advice.ExceptionEnums;
import org.example.advice.MyException;
import org.example.entity.Role;
import org.example.service.RoleService;
import org.example.untils.DTO.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "用户相关接口")
@Controller
@RequestMapping(value = "api/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * list（role的）
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Role>> list() {
        List<Role> roles = roleService.selectAll();
        if (!CollectionUtils.isEmpty(roles)) {
            return ResponseEntity.ok(roles.stream().filter(v -> v.getRoleName().contains("医生")
                    || v.getRoleName().contains("辅导员")
                    || v.getRoleName().contains("用户")).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(roles);
    }

    /**
     * 获取角色id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<Role> get(@PathVariable("id") String id) {
        Role role = roleService.getById(id);
        if (role != null) {
            return ResponseEntity.ok(role);
        }
        throw new MyException(ExceptionEnums.GET_ITEM);
    }

    /**
     * 分页功能
     *
     * @param condition
     * @return
     */
    @ApiOperation(value = "基础接口: 分页返回数据")
    @PostMapping(value = "page")
    public ResponseEntity<Page<Role>> page(@RequestBody Condition condition) {
        Page page = roleService.selectPage(condition);
        if (page != null) {
            return ResponseEntity.ok(page);
        }
        throw new MyException(ExceptionEnums.GET_LIST_ERROR);
    }

    /**
     * 修改角色数据
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "修改数据")
    @PostMapping(value = "update")
    @RequiresPermissions("role:update")
    public ResponseEntity<Role> update(@RequestBody Role role) {
        role.setCreateDatetime(new Date());
        role.setUpdateDatetime(new Date());
        boolean b = roleService.updateById(role);
        if (b) {
            return ResponseEntity.ok(role);
        }
        throw new MyException(ExceptionEnums.UPDATE_ERROR);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除指定ID的数据")
    @GetMapping(value = "delete/{id}")
    @RequiresPermissions("role:delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        boolean b = roleService.removeById(id);
        if (b) {
            return ResponseEntity.ok("删除成功");
        }
        throw new MyException(ExceptionEnums.DELETE_ERROR);
    }

    /**
     * add
     * @param role
     * @return
     */
    @ApiOperation(value = "新增操作")
    @PostMapping(value = "add")
    @RequiresPermissions("role:add")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        role.setUpdateDatetime(new Date());
        role.setCreateDatetime(new Date());

        boolean save = roleService.save(role);
        if (save) {
            return ResponseEntity.ok(role);
        }
        throw new MyException(ExceptionEnums.ADD_ERROR);
    }

    /**
     * 返回所有数据
     * @return
     */
    @ApiOperation(value = "返回所有数据")
    @GetMapping(value = "all")
    public ResponseEntity<List<Role>> all() {
        List<Role> list = roleService.list();
        if (list != null) {
            return ResponseEntity.ok(list);
        }
        throw new MyException(ExceptionEnums.GET_LIST_ERROR);
    }

}
