package com.dyh.android.winehall.entity.local;

/**
 * 作者：lblbh on 2020/5/6 16:30
 * 邮箱：lanbuhan@163.com
 * 功能：用户信息
 * {
 * "headPhoto": "",  //头像
 * "learnTime": "0分钟",  //本年学习时长
 * "realName": "森林之王005",   //真名
 * "mobile":"",  //手机
 * "sex":"男",
 * "deptName":"部门",
 * "card":"232112",  //身份证号
 * "email":"tianh@163.com",  //邮箱
 * "grandTotalTime": "0分钟",    //累计学习时长
 * "nickName": "森林之王005",  //昵称
 * "signStatus": false,   //当天是否签到  true已签到，false未签到
 * "qqOpenid": "",
 * "integral": 0,  //学习总积分
 * "count": 0,   //累计签到
 * "helpCenterUrl": "http://web.syrkmooc.com/app/helpcenter.html?token=7bac2bd5-d3d8-4b74-b57d-7c5d644c0194", //帮助中心链接
 * "wechatUnionid": "",
 * "downloadUrl":"http://www.app.download",  //app下载地址
 * "memberId": 221     //学员id
 * }
 */
public class MineInfo {

    private String headPhoto;
    private String learnTime;
    private String realName;
    private String mobile;
    private String sex;
    private String deptName;
    private String card;
    private String email;
    private String grandTotalTime;
    private String nickName;
    private boolean signStatus;
    private String qqOpenid;
    private String integral;
    private String count;
    private String helpCenterUrl;
    private String wechatUnionid;
    private String downloadUrl;
    private String memberId;

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getLearnTime() {
        return learnTime;
    }

    public void setLearnTime(String learnTime) {
        this.learnTime = learnTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrandTotalTime() {
        return grandTotalTime;
    }

    public void setGrandTotalTime(String grandTotalTime) {
        this.grandTotalTime = grandTotalTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isSignStatus() {
        return signStatus;
    }

    public void setSignStatus(boolean signStatus) {
        this.signStatus = signStatus;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getHelpCenterUrl() {
        return helpCenterUrl;
    }

    public void setHelpCenterUrl(String helpCenterUrl) {
        this.helpCenterUrl = helpCenterUrl;
    }

    public String getWechatUnionid() {
        return wechatUnionid;
    }

    public void setWechatUnionid(String wechatUnionid) {
        this.wechatUnionid = wechatUnionid;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
