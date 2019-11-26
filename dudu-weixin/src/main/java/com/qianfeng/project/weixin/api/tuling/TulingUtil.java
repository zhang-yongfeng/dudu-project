package com.qianfeng.project.weixin.api.tuling;

import com.qianfeng.project.weixin.api.tuling.bean.InputText;
import com.qianfeng.project.weixin.api.tuling.bean.Perception;
import com.qianfeng.project.weixin.api.tuling.bean.TulingBean;
import com.qianfeng.project.weixin.api.tuling.bean.UserInfo;
import com.qianfeng.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Classname TulingUtil
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/11/21 9:49
 * @Created by Administrator
 */
@Service
public class TulingUtil {
    private static final String TULING_API_URL="http://openapi.tuling123.com/openapi/api/v2";

    /** ######### TODO 发送消息和响应消息
     * @InputParam msg 用户发送的文本
     * @OutputParam result 响应的文本
     */
    public String sendMessage(String msg, String apiKey){
        //生成入参的JSON对象
       JSONObject jsonObject= sendJSONObject(msg,apiKey);
        //2步  对指定的API 地址 发送POST请求 （传入入参JSON对象）
        JSONObject object= WeixinUtil.httpRequest(TULING_API_URL,"POST",jsonObject.toString());

        System.out.println("响应的消息："+object.toString());
        JSONArray  array= (JSONArray) object.get("results");

        JSONObject object1= (JSONObject) array.get(0);
        JSONObject object2= (JSONObject) object1.get("values");
        String result= (String) object2.get("text");
        return result;
    }

    /** ###########TODO 生成入参JSON对象
     * 按规则生成入参的JSON对象
     * @param msg 用户发送的文本
     * @param apiKey 机器人的apiKey
     * @return
     */
    public JSONObject  sendJSONObject(String msg, String apiKey){
        //JSON格式数据（入参）
        InputText inputText=new InputText();
        inputText.setText(msg);
        Perception perception=new Perception();
        perception.setInputText(inputText);

        UserInfo userInfo=new UserInfo();
        userInfo.setApiKey(apiKey);
        userInfo.setUserId("java1903");

        TulingBean tulingBean=new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject=JSONObject.fromObject(tulingBean);
        return jsonObject;
    }


    //初始key数据值
    private static String[]  keyStr=new String[]{
            //本机
            "fb5a78bb2e79482d8075acd90b13231d",
            "cd6f7bfdecba4d56a08a6956ea32c0f1",
            "acc513be8b5e4b26929247e83132f116",
            "911ea1eef67843449750dc7f19fb3d8d",
            "a2248ded1f8842729a36589d1829a986",
            //朱佳俊
            "9c8f002686014beb965d9715f41f1f7c",
            "64d81041209e4d97a65a25c51f1a5d33",
            "f68085b8e2df489b9499e0147567d6bc",
            "70e630a2c70f4cfaadbf45ee29bc0d51",
            "5e27868189294c0cb64382ef965d6697",
            //方明星
            "1896a791efbd4c338f36aa8c4636fbbc",
            "768493348f674b59bd33532af96163f0",
            "bfe05586db914cc196017efbd927be28",
            "eb6cdf1f7a974bc7bdd6b35c39e60c7a",
            "a285d643902a4a00a546ded3495af4bf",
            //周晓波
            "5d46f432d7a744dab2d0cdb7f5e5532b",
            "160bc76215184671a94a33825d9469a5",
            "a38a07f2386f4abeaf8cb7c027d46286",
            "cbb6dc788c1a47cda4f44bf4e7035c3f",
            "dc83ad93f4a443e4860aea319018a792",
            //汪韵
            "358531b5a1d84b60be928a356440fedb",
            "48a3c5146e084a93954823e6739ed2ba",
            "8473f2fd011945e9810424cc205e1e5c",
            "9adb33aa72a540aba4da94983f3c6951",
            "0d44699bde544431acecdfc79881a028",
            //张国良
            "17d52031ee2646d3a7ce71c7ad0dd186",
            "afcdf35056264e3c9dfb2731fabbb4c8",
            "8d98aaff96ad4ea1810a41fbbc700c09",
            "75a01188e54846ddaf35d757cdb35fa0",
            "6e7a7a95d11f46f8a018bb25da5ac16d",
            //孙文强
            "e981e3306924444fbe1a019a0ab6ec78",
            "c5f0de7a280845329a7227360118ea04",
            "b487474670ab445cbd90a0f114133450",
            "2e9dfac420ca47be92953e5a43369eea",
            "dbccd76c8a2344e0a438dd159b71f91b"
    };
    private static int KEY_NUM=0;  //记录数组
    private static String KEY_DEFAULT_VAL=keyStr[0];//设定默认值
    /**
     * 递归方法，继续尝试下一个key 是否有效
     * @return
     */
    public String invoke(String inputMsg){
        String s = this.sendMessage(inputMsg,KEY_DEFAULT_VAL);
        if("请求次数超限制!".equals(s)){
            for(int i=0;i<keyStr.length;i++){
                KEY_NUM++;
                if(KEY_NUM==keyStr.length){
                    KEY_NUM=0;
                }
                KEY_DEFAULT_VAL=keyStr[KEY_NUM];
                return invoke(inputMsg);
            }
        }
        return s;
    }
























    public static void main(String[] args) {
        String api="http://openapi.tuling123.com/openapi/api/v2";

        String msg="今天天气怎么样"; //用户输入的文本
        //JSON格式数据（入参）
        InputText inputText=new InputText();
        inputText.setText(msg);
        Perception perception=new Perception();
        perception.setInputText(inputText);

        UserInfo userInfo=new UserInfo();
        userInfo.setApiKey("acc513be8b5e4b26929247e83132f116");
        userInfo.setUserId("java1903");

        TulingBean tulingBean=new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject=JSONObject.fromObject(tulingBean);
        System.out.println("请求数据："+jsonObject.toString());

        //2步  对指定的API 地址 发送POST请求 （传入入参JSON对象）
      JSONObject object= WeixinUtil.httpRequest(api,"POST",jsonObject.toString());
        System.out.println("响应数据："+object.toString());

       JSONArray  array= (JSONArray) object.get("results");

      JSONObject object1= (JSONObject) array.get(0);
      JSONObject object2= (JSONObject) object1.get("values");
      String result= (String) object2.get("text");
        System.out.println("返回的结果："+result);
    }

}
