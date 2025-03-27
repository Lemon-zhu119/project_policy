package com.ruoyi.web.controller.forest;

import com.dtflys.forest.annotation.*;
import com.ruoyi.web.controller.forest.dto.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;


@BaseRequest(
        readTimeout = 20 * 3600,
        connectTimeout = 20 * 3600
)
public interface Oauth2 {
    @Post(
            url = "https://github.com/login/oauth/access_token?",
            headers = {
                    "Content-Type: application/x-www-form-urlencoded",
                    "Accept: */*"
            }
    )
    String getAccessToken(@Body Map<String, Object> map);

    @Get(
            url = "http://ip/ssoserver/moc2/token?grant_type={grantType}&client_id={clientId}&client_secret={clientSecret}&code={code}&redirect_uri=#{redirectUri} "
    )
    Authentication getUserInfo(@Var("grantType") String grantType,
                               @Var("clientId") String clientId,
                               @Var("clientSecret") String clientSecret,
                               @Var("code") String code,
                               @Var("redirectUri") String redirectUri);


    @Get(
            url = "http://ip/ssoserver/moc2/me?access_token={accessToken}"
    )
    Authentication getOpenId(@Var("accessToken") String accessToken);

}