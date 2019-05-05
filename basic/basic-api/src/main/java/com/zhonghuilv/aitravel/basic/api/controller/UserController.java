package com.zhonghuilv.aitravel.basic.api.controller;

import com.zhonghuilv.aitravel.authorization.common.util.UserUtil;
import com.zhonghuilv.aitravel.basic.intf.clients.UserClient;
import com.zhonghuilv.aitravel.basic.intf.enums.EnumUserType;
import com.zhonghuilv.aitravel.basic.intf.pojo.User;
import com.zhonghuilv.aitravel.basic.intf.pojo.dto.LoginDTO;
import com.zhonghuilv.aitravel.common.ApiResult;
import com.zhonghuilv.aitravel.common.excption.ParameterNotValidException;
import com.zhonghuilv.aitravel.constant.intf.Enum.EnumVcode;
import com.zhonghuilv.aitravel.constant.intf.clients.SmsClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Create By zhengjing on 2017/12/18 10:58
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    SmsClient smsClient;

    @Value("${login-url}")
    String url;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @GetMapping("")
    @ApiOperation("获取用户信息")
    public ApiResult<User> info() {
        User user = userClient.loadById(UserUtil.getUserId());
        user.setPassword(null);
        return ApiResult.success(user);
    }

    @PostMapping("/noauth")
    @ApiOperation("注册")
    public ApiResult<User> register(@RequestParam("phone") String phone,
                                    @RequestParam("code") String code) {

        User user = new User();
        user.setUsername(phone);
        user.setPhone(phone);
        user.setEmail("");
        user.setUserType(EnumUserType.APP.getKey());

        if (!Pattern.matches("^1[0-9]{10}$", phone)) {
            throw new ParameterNotValidException("手机号码格式不正确");
        }

        User query = new User();
        query.setPhone(phone);
        if (userClient.loadOne(query) != null) {
            throw new ParameterNotValidException("该手机号已注册");
        }
        User result = userClient.save(user);
        return ApiResult.success(result);
    }

    @PostMapping("")
    @ApiOperation("新增")
    public ApiResult<User> add(@RequestBody User user) {

        User result = userClient.save(user);
        return ApiResult.success(result);

    }

    @PatchMapping("")
    @ApiOperation("传入需要修改的参数")
    public ApiResult<Boolean> register(@RequestBody User user) {
        if (Objects.nonNull(user.getUsername())) {
            throw new ParameterNotValidException("username不允许主动修改！");
        }
        Long userId = UserUtil.getUserId();
        user.setId(userId);
        userClient.updateSelective(user);

        return ApiResult.success(Boolean.TRUE);

    }

    @ApiOperation(value = "后台登录")
    @PostMapping("/noauth/login")
    public DefaultOAuth2AccessToken login(@RequestBody LoginDTO loginDTO) {

        return getToken(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @ApiOperation(value = "绑定手机号")
    @PatchMapping("/_bind_phone")
    public ApiResult<Boolean> bindPhone(@RequestParam("phone") String phone
            , @RequestParam("code") String code) {
        if (smsClient.verifyCode(EnumVcode.BIND_PHONE.getType(), phone, code)) {

            User query = new User();
            query.setPhone(phone);
            if (userClient.loadOne(query) != null) {
                throw new ParameterNotValidException("该手机号已被绑定");
            }
            User user = new User();
            user.setId(UserUtil.getUserId());
            user.setPhone(phone);
            userClient.updateSelective(user);
            return ApiResult.success(Boolean.TRUE);
        }

        throw new ParameterNotValidException("验证码不正确");
    }

    private DefaultOAuth2AccessToken getToken(String userName, String password) {

        try {
            String basic = "Basic " + Base64.getEncoder().encodeToString((oAuth2ClientProperties.getClientId() + ":" +
                    oAuth2ClientProperties.getClientSecret()).getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", basic);
            String params = "?grant_type=password&username=" + userName + "&password=" + password;

            ResponseEntity<DefaultOAuth2AccessToken> exchange = restTemplate.exchange
                    (url + "/oauth/token" +
                            params, HttpMethod.POST, new HttpEntity(headers), DefaultOAuth2AccessToken.class);

            return exchange.getBody();
        } catch (HttpClientErrorException e) {
            log.error("登录异常", e);
            throw new ParameterNotValidException("用户名或密码不正确！");
        }

    }

    @ApiOperation(value = "发送手机验证码")
    @RequestMapping(value = "/noauth/send_code", method = RequestMethod.POST)
    public ApiResult<Boolean> sendVerifyCode(@RequestParam("phone") String phone,
                                             @ApiParam("0:登录 1:注册：2：找回密码") @RequestParam("type") Integer type) {
        User query = new User();
        query.setPhone(phone);
//        if ((Integer.valueOf(2).equals(type) || Integer.valueOf(0).equals(type)) && userClient.loadOne(query) ==
// null) {
//            throw new ParameterNotValidException("该手机号未注册");
//        }
//        if (Integer.valueOf(1).equals(type) && userClient.loadOne(query) != null) {
//            throw new ParameterNotValidException("该手机号已经注册");
//        }
        if (!Pattern.matches("^1[0-9]{10}$", phone)) {
            throw new ParameterNotValidException("手机号码格式不正确");
        }
        return ApiResult.success(smsClient.sendVerifyCode(phone, type));
    }
}

