package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Set;

import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.framework.web.service.SysRegisterService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.company.domain.Company;
import com.ruoyi.web.controller.company.service.ICompanyService;
import com.ruoyi.web.controller.forest.Oauth2;
import com.ruoyi.web.controller.forest.dto.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysMenuService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private Oauth2 oauth2;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private SysRegisterService sysRegisterService;

    @Autowired
    private ISysUserService sysUserService;


    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        if (!loginUser.getPermissions().equals(permissions)) {
            loginUser.setPermissions(permissions);
            tokenService.refreshToken(loginUser);
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 获取AccessToken
     * @param code 授权码
     * @param state 客户端的状态
     * @param ret 状态码
     * @param response 响应
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @RequestMapping("/authorize/{code}/{state}")
    public void getAccessToken(@PathVariable("code") String code, @PathVariable("state") String state, @RequestParam String ret, HttpServletResponse response) {

        if (!"0".equals(ret)) {
            switch (code) {
                case "6081":
                    throw new RuntimeException("client_id非法（不是服务端已存在的资源系统）");
                case "6082":
                    throw new RuntimeException("当前登录用户没有此client_id权限");
                case "6600":
                    throw new RuntimeException("未通过认证(手机端)");
                case "6900":
                    throw new RuntimeException("请求参数格式错误，具体参见返回信息中的msg字段。");
                case "6905":
                    throw new RuntimeException("redirect_uri无法解析出主域名。");
                default:
                    throw new RuntimeException("未知错误");
            }
        } else {
             oauth2.getUserInfo("authorization_code", "kjzc", "9fc2ae24329fef5d1db07f6d294cc04c", code, "http://10.10.49.254:5388/oauth");
        }
    }

    /**
     * 单点登录
     * @param token 授权对象（含AccessToken）
     * @return token（本系统token）
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @RequestMapping("/oauth")
    public AjaxResult oauth(@RequestBody Authentication token) {
        if (!token.getCode().isEmpty()) {
            switch (token.getCode()) {
                case "7000":
                    throw new RuntimeException("APP同时在线用户数已达license上限（此错误码为APP对接时才有）");
                case "7001":
                    throw new RuntimeException("accessToken创建失败");
                case "7018":
                    throw new RuntimeException("client_secret非法（资源id和title不匹配）");
                case "7020":
                    throw new RuntimeException("code已过期");
                case "7081":
                    throw new RuntimeException("client_id非法（不是服务端已存在的资源系统）");
                case "7900":
                    throw new RuntimeException("请求参数格式错误，具体参见返回信息中的msg字段");
                case "7905":
                    throw new RuntimeException("redirect_uri无法解析出主域名");
            }
        }
            Authentication auth = oauth2.getOpenId(token.getAccessToken());
            if (!auth.getCode().isEmpty()) {
                switch (auth.getCode()) {
                    case "9094":
                        return AjaxResult.error("access_token不存在");
                    case "9900":
                        return AjaxResult.error("请求参数格式错误，具体参见返回信息中的msg字段");
                }
            }
            Company cbyId = companyService.selectCompanyByOpenid(auth.getOpenid());
            SysUser sysUser = sysUserService.selectUserByUserName(auth.getUsername());
            AjaxResult ajax = AjaxResult.success();
            if (cbyId == null) {
                Company cbyName = companyService.selectCompanyByName(auth.getUsername());
                RegisterBody registerBody = new RegisterBody();
                registerBody.setUsername(auth.getUsername());
                registerBody.setPassword("Long@123456");
                sysRegisterService.register(registerBody);
                if (cbyName == null) {
                    Company company = new Company(auth.getUsername(), auth.getOpenid(), sysUser.getUserId());
                    companyService.insertCompany(company);
                } else {
                    Company company = new Company(cbyName.getId(), auth.getOpenid(), sysUser.getUserId());
                    companyService.updateCompany(company);
                }
                String tk = loginService.login(registerBody.getUsername(), registerBody.getPassword());
                ajax.put(Constants.TOKEN, tk);
            }else {
                String tk = loginService.login(sysUser.getUserName(), sysUser.getPassword());
                ajax.put(Constants.TOKEN, tk);
            }
            return ajax;
    }

    /**
     * 将getAccessToken和oauth放在同个接口进行
     * @param code 授权码
     * @param state 客户端状态
     * @param ret 状态码
     * @param response 响应
     * @return token
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @RequestMapping("/v2/authorize/{code}/{state}")
    public AjaxResult getUserInfo(@PathVariable("code") String code, @PathVariable("state") String state, @RequestParam String ret, HttpServletResponse response) {

        if (!"0".equals(ret)) {
            switch (code) {
                case "6081":
                    return AjaxResult.error("client_id非法（不是服务端已存在的资源系统）");
                case "6082":
                    return AjaxResult.error("当前登录用户没有此client_id权限");
                case "6600":
                    return AjaxResult.error("未通过认证(手机端)");
                case "6900":
                    return AjaxResult.error("请求参数格式错误，具体参见返回信息中的msg字段。");
                case "6905":
                    return AjaxResult.error("redirect_uri无法解析出主域名。");
                default:
                    return AjaxResult.error("未知错误");
            }
        } else {
            Authentication token = oauth2.getUserInfo("authorization_code", "kjzc", "9fc2ae24329fef5d1db07f6d294cc04c", code, "http://10.10.49.254:5388/authorize");
            if (!token.getCode().isEmpty()) {
                switch (token.getCode()) {
                    case "7000":
                        return AjaxResult.error("APP同时在线用户数已达license上限（此错误码为APP对接时才有）");
                    case "7001":
                        return AjaxResult.error("accessToken创建失败");
                    case "7018":
                        return AjaxResult.error("client_secret非法（资源id和title不匹配）");
                    case "7020":
                        return AjaxResult.error("code已过期");
                    case "7081":
                        return AjaxResult.error("client_id非法（不是服务端已存在的资源系统）");
                    case "7900":
                        return AjaxResult.error("请求参数格式错误，具体参见返回信息中的msg字段");
                    case "7905":
                        return AjaxResult.error("redirect_uri无法解析出主域名");
                }
            }
            Authentication auth = oauth2.getOpenId(token.getAccessToken());
            if (!auth.getCode().isEmpty()) {
                switch (auth.getCode()) {
                    case "9094":
                        return AjaxResult.error("access_token不存在");
                    case "9900":
                        return AjaxResult.error("请求参数格式错误，具体参见返回信息中的msg字段");
                }
            }
            Company cbyId = companyService.selectCompanyByOpenid(auth.getOpenid());
            SysUser sysUser = sysUserService.selectUserByUserName(auth.getUsername());
            AjaxResult ajax = AjaxResult.success();
            if (cbyId == null) {
                Company cbyName = companyService.selectCompanyByName(auth.getUsername());
                RegisterBody registerBody = new RegisterBody();
                registerBody.setUsername(auth.getUsername());
                registerBody.setPassword("Long@123456");
                sysRegisterService.register(registerBody);
                if (cbyName == null) {
                    Company company = new Company(auth.getUsername(), auth.getOpenid(), sysUser.getUserId());
                    companyService.insertCompany(company);
                } else {
                    Company company = new Company(cbyName.getId(), auth.getOpenid(), sysUser.getUserId());
                    companyService.updateCompany(company);
                }
                String tk = loginService.login(registerBody.getUsername(), registerBody.getPassword());
                ajax.put(Constants.TOKEN, tk);
            }else {
                String tk = loginService.login(sysUser.getUserName(), sysUser.getPassword());
                ajax.put(Constants.TOKEN, tk);
            }
            return ajax;
        }
    }
}
