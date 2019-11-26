package com.qianfeng.project.weixin.api.hitokoto;

import com.qianfeng.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Classname Hitokoto
 * @Author guoweixin
 * @Description TODO 一言  一句话 API https://hitokoto.cn/
 * @Date 2019/11/21 16:09
 * @Created by Administrator
 */
@Service
public class HitokotoUtil {

    private static final String HITOKOTO_API_URL="https://v1.hitokoto.cn/";

    public String sendMessage(){
        JSONObject jsonObject=WeixinUtil.httpRequest(HITOKOTO_API_URL,"GET",null);
       String result= (String) jsonObject.get("hitokoto");
        return result;
    }


}
