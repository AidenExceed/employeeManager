package com.employee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "职工信息")
@CrossOrigin
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据条件查询员工信息
     * @param employee 员工信息
     * @return
     */
    @ApiOperation("根据条件查询员工信息")
    @GetMapping("/list")
    public List<Employee> list(Employee employee) {

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        // 判断姓名是否为空
        if (!StringUtils.isEmpty(employee.getEmpName())) {

            queryWrapper.eq("emp_name", employee.getEmpName());
        }

        // 判断部门名称是否为空
        if (!StringUtils.isEmpty(employee.getDeptName())) {

            queryWrapper.eq("dept_name", employee.getDeptName());
        }

        // 判断学历名称是否为空
        if (!StringUtils.isEmpty(employee.getEmpDegreeName())) {

            queryWrapper.eq("emp_degree_name", employee.getEmpDegreeName());
        }

        // 根据条件查询数据并返回
        List<Employee> list = employeeService.list(queryWrapper);
        return list;
    }

    /**
     * 新增员工信息
     * @param employee 员工信息
     * @return
     */
    @ApiOperation("新增员工信息")
    @PostMapping("/add")
    public boolean add(@RequestBody Employee employee) {

        // 新增员工信息并返回成功或失败
        return employeeService.save(employee);
    }

    /**
     * 修改员工信息
     * @param employee 员工信息
     * @return
     */
    @ApiOperation("修改员工信息")
    @PostMapping("/edit")
    public boolean edit(@RequestBody Employee employee) {

        // 修改员工信息并返回成功或失败
        return employeeService.updateById(employee);
    }

    /**
     * 删除员工信息
     * @param id 员工id
     * @return
     */
    @ApiOperation("删除员工信息")
    @GetMapping("/remove")
    public boolean remove(Long id) {

        // 删除员工信息并返回成功或失败
        return employeeService.removeById(id);
    }

}
