package com.aspirecn.hutooldemo.controller;

import com.aspirecn.hutooldemo.common.Result;
import com.aspirecn.hutooldemo.common.StatusCode;
import com.aspirecn.hutooldemo.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description UmsMemberController
 * @Author Fuyufeng
 * @Date 2020/1/10 15:01
 * @since JDK 1.8
 */
@RestController
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired(required = false)
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public Result<?> getAuthCode(@RequestParam String telephone) {
        StringBuilder code = memberService.generateAuthCode(telephone);
        return new Result<String>(true, StatusCode.OK,"获取验证码成功", code);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        Result<Object> number = memberService.verifyAuthCode(telephone, authCode);
        return new Result<String>(true, StatusCode.OK,"获取验证码成功",number);
    }
}
