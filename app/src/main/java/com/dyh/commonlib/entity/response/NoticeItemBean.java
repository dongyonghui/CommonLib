package com.dyh.commonlib.entity.response;

import android.content.Context;
import android.text.TextUtils;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/10/010 14:43
 * 描述：消息公告列表项
 */
public class NoticeItemBean {

    /**
     * id : 16
     * noticeId : 16
     * noticeName : ["555555"]
     * startTime : 1574640000000
     * endTime : 1574726400000
     * startTimeStr : 2019-11-25
     * endTimeStr : 2019-11-26
     * foreverFlag : 0
     * second : 0
     * cityId : 110100
     * city : 北京市
     * status : 1
     * defaultNotice : 0
     * noticeNameList : ["555555"]
     * noticeStatus : 3
     * addressType : 0
     * addressList : [{"deliveryAddressId":104,"deliveryAddressName":"测试地址B","noticeId":null,"type":null}]
     */

    private String id;
    private String noticeId;
    private String noticeName;
    private String startTime;
    private String endTime;
    private String startTimeStr;
    private String endTimeStr;
    private String foreverFlag;
    private String second;
    private String cityId;
    private String city;
    private String status;
    private String defaultNotice;
    private String noticeStatus;
    private String addressType;
    private List<String> noticeNameList;
    private List<AddressListBean> addressList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getForeverFlag() {
        return foreverFlag;
    }

    public void setForeverFlag(String foreverFlag) {
        this.foreverFlag = foreverFlag;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefaultNotice() {
        return defaultNotice;
    }

    public void setDefaultNotice(String defaultNotice) {
        this.defaultNotice = defaultNotice;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    /**
     * 获取消息公告状态文本描述
     *
     * @param context
     * @return
     */
    public String getNoticeStatusTextInfo(Context context) {
        if (ServerConstants.NoticeStatus.UNKNOWN.equals(noticeStatus)) {
            return context.getString(R.string.unknown);
        }
        if (ServerConstants.NoticeStatus.NOT_START.equals(noticeStatus)) {
            return context.getString(R.string.notStart);
        }
        if (ServerConstants.NoticeStatus.DOING.equals(noticeStatus)) {
            return context.getString(R.string.doing);
        }
        if (ServerConstants.NoticeStatus.PASSED.equals(noticeStatus)) {
            return context.getString(R.string.timeout);
        }
        return context.getString(R.string.unknown);
    }

    /**
     * @return 将名字列表转换为文本信息
     */
    public String getAllNameInfo() {
        StringBuilder nameStringBuilder = new StringBuilder();
        if (noticeNameList != null) {
            for (String s : noticeNameList) {
                if (!TextUtils.isEmpty(nameStringBuilder)) {
                    nameStringBuilder.append(";");
                }
                nameStringBuilder.append(s);
            }
        }
        return nameStringBuilder.toString();
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public List<String> getNoticeNameList() {
        return noticeNameList;
    }

    public void setNoticeNameList(List<String> noticeNameList) {
        this.noticeNameList = noticeNameList;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class AddressListBean {
        /**
         * deliveryAddressId : 104
         * deliveryAddressName : 测试地址B
         */

        private String deliveryAddressId;
        private String deliveryAddressName;

        public String getDeliveryAddressId() {
            return deliveryAddressId;
        }

        public void setDeliveryAddressId(String deliveryAddressId) {
            this.deliveryAddressId = deliveryAddressId;
        }

        public String getDeliveryAddressName() {
            return deliveryAddressName;
        }

        public void setDeliveryAddressName(String deliveryAddressName) {
            this.deliveryAddressName = deliveryAddressName;
        }

    }
}
