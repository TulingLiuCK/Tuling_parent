package com.lck;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/***
 #Create by LCK on 2022/3/10
 # 用法:
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        //根据视频id获取视频播放凭证
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tPesBXMrbMM7t3fb2jS", "VO9zx73HjdNRSIiWhQqVLS6TpkxaSw");
        //创建获取视频凭证request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        //向request设置视频id
        request.setVideoId("cb12254ac7444f0a83adf442138caf3b");
        //调用初始化对象的方法得到凭证
        response= client.getAcsResponse(request);
        System.out.println("PlayAuth："+response.getPlayAuth());


    }
    //根据视频id获取视频播放地址
    public static void getPlayUrl() throws ClientException{

        //创建初始化对象
        DefaultAcsClient cl = InitObject.initVodClient("LTAI5tPesBXMrbMM7t3fb2jS", "VO9zx73HjdNRSIiWhQqVLS6TpkxaSw");
        //创建获取视频地址request对象和response对象
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        //向request对象设置视频id值
        request.setVideoId("cb12254ac7444f0a83adf442138caf3b");


        //调用初始化对象里面的方法传递request，获取数据
        response = cl.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            //PlayInfo.PlayURL = https://outin-5e688ec47b2c11ebb00500163e06123c.oss-cn-shanghai.aliyuncs.com/sv/52a57879-177f1fd5033/52a57879-177f1fd5033.mp4?Expires=1614680252&OSSAccessKeyId=LTAIrkwb21KyGjJl&Signature=FPlVfGbIDVGuvR3W8f2K4QcpATw%3D
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");//VideoBase.Title = 6 - What If I Want to Move Faster.mp4
    }
}
