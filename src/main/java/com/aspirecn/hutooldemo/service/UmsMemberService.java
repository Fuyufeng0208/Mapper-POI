package com.aspirecn.hutooldemo.service;

import com.aspirecn.hutooldemo.common.Result;

/**
 * @Description UmsMemberService
 * @Author Fuyufeng
 * @Date 2020/1/10 15:03
 * @since JDK 1.8
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     * @return
     */
    StringBuilder generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     * @return
     */
    Result<Object> verifyAuthCode(String telephone, String authCode);
}
