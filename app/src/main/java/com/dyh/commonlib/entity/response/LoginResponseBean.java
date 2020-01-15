package com.dyh.commonlib.entity.response;

import android.content.Context;
import android.text.TextUtils;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.BaseHttpResponseBean;
import com.dyh.commonlib.entity.local.HomeActionBean;
import com.dyh.commonlib.ui.city.BannerManageActivity;
import com.dyh.commonlib.ui.city.BetterGoodsTimeManageActivity;
import com.dyh.commonlib.ui.city.CityDeliveryPriceManageActivity;
import com.dyh.commonlib.ui.city.CityManageActivity;
import com.dyh.commonlib.ui.city.CityTaskManageActivity;
import com.dyh.commonlib.ui.city.CityTimeManageActivity;
import com.dyh.commonlib.ui.city.GroupPrizeManageActivity;
import com.dyh.commonlib.ui.city.NoticeManageActivity;
import com.dyh.commonlib.ui.city.SeckillTimeManageActivity;
import com.dyh.commonlib.ui.delivery_address.DeliveryAddressManageActivity;
import com.dyh.commonlib.ui.delivery_man.DeliveryManManageActivity;
import com.dyh.commonlib.ui.order.OrderManageActivity;
import com.dyh.commonlib.ui.print.PrintDeliveryOrderActivity;
import com.dyh.commonlib.ui.supply_pool.SupplyPoolManageActivity;
import com.dyh.commonlib.ui.tpc.TpcGoodsManageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录响应结果
 */
public class LoginResponseBean extends BaseHttpResponseBean {
    private OperBean oper;
    private String token;

    /**
     * 获取首页菜单项
     *
     * @param context
     * @return
     */
    public List<HomeActionBean> getHomeMenuActionBean(Context context) {
        List<HomeActionBean> list = new ArrayList<>();

        if (null == oper || null == oper.menuList) {
            return list;
        }
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.TPC_MANAGE)) {
            list.add(new HomeActionBean(context.getString(R.string.tpcManage), R.mipmap.food_blue, TpcGoodsManageActivity.class));
        }
        list.add(new HomeActionBean(context.getString(R.string.printDeliveryOrder), R.mipmap.printer_blue, PrintDeliveryOrderActivity.class));
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.ADDRESS_MANAGE)) {
            list.add(new HomeActionBean(context.getString(R.string.deliveryAddressManage), R.mipmap.address_blue, DeliveryAddressManageActivity.class));
        }
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.SUPPLY_POOL_MANAGE)) {
            list.add(new HomeActionBean(context.getString(R.string.supplyPoolManage), R.mipmap.supply_pool_blue, SupplyPoolManageActivity.class));
        }
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_MANAGE)) {
            list.add(new HomeActionBean(context.getString(R.string.cityManage), R.mipmap.city_blue, CityManageActivity.class));
        }
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.DELIVERY_MAN_MANAGE)) {
            list.add(new HomeActionBean(context.getString(R.string.deliveryManManage), R.mipmap.delivery_man_blue, DeliveryManManageActivity.class));
        }
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.ORDER_MANAGE)) {
            list.add(new HomeActionBean(context.getString(R.string.orderManage), R.mipmap.order_blue, OrderManageActivity.class));
        }
//        list.add(new HomeActionBean("店铺管理", R.mipmap.shop_blue, HomeActivity.class));
//        list.add(new HomeActionBean("拼团商品管理", R.mipmap.food_group_blue, HomeActivity.class));
//        list.add(new HomeActionBean("分类管理", R.mipmap.category_blue, HomeActivity.class));
//        list.add(new HomeActionBean("优惠券管理", R.mipmap.coupon_blue, HomeActivity.class));
//        list.add(new HomeActionBean("团体管理", R.mipmap.group_blue, HomeActivity.class));
//        list.add(new HomeActionBean("职位管理", R.mipmap.position_blue, HomeActivity.class));
//        list.add(new HomeActionBean("成员管理", R.mipmap.member_blue, HomeActivity.class));
//        list.add(new HomeActionBean("站点管理", R.mipmap.station_blue, HomeActivity.class));
        return list;
    }

    /**
     * 获取城市管理二级菜单项
     *
     * @param context
     * @return
     */
    public List<HomeActionBean> getCityManageMenuActionBean(Context context) {
        List<HomeActionBean> list = new ArrayList<>();

        if (null == oper || null == oper.menuList) {
            return list;
        }
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_TASK)) {
            list.add(new HomeActionBean(context.getString(R.string.taskManage), R.mipmap.icon_task, CityTaskManageActivity.class));
        }
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_BANNER))
            list.add(new HomeActionBean(context.getString(R.string.bannerManage), R.mipmap.icon_banner, BannerManageActivity.class));
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_NOTICE))
            list.add(new HomeActionBean(context.getString(R.string.noticeManage), R.mipmap.icon_notice, NoticeManageActivity.class));
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_PRIZE))
            list.add(new HomeActionBean(context.getString(R.string.groupPrizeManage), R.mipmap.icon_prize, GroupPrizeManageActivity.class));
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_DELIVERY))
            list.add(new HomeActionBean(context.getString(R.string.cityDeliverPriceManage), R.mipmap.icon_deliver_price, CityDeliveryPriceManageActivity.class));
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_GOOD_TIME))
            list.add(new HomeActionBean(context.getString(R.string.betterGoodsTimeManage), R.mipmap.icon_time, BetterGoodsTimeManageActivity.class));
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.CITY_TIME))
            list.add(new HomeActionBean(context.getString(R.string.cityTimeManage), R.mipmap.icon_time, CityTimeManageActivity.class));
        if (isHaveMenu(oper.menuList, ServerConstants.MenuWebPath.LIMITED_TIME_DISCOUNT))
            list.add(new HomeActionBean(context.getString(R.string.seckillTimeManage), R.mipmap.icon_time, SeckillTimeManageActivity.class));
        return list;
    }

    public boolean isHaveMenu(List<OperBean.MenuListBean> menuListBeanList, String webPath) {
        if (TextUtils.isEmpty(webPath)) {
            return false;
        }
        if (null != menuListBeanList) {
            for (OperBean.MenuListBean menuListBean : menuListBeanList) {
                //如果匹配成功，返回true
                if (webPath.equals(menuListBean.getRemark())) {
                    return true;
                }

                //如果有下一级菜单项，则递归遍历下一级菜单项，如果匹配成功则返回，如果没有匹配成功则继续
                if (null != menuListBean.children && menuListBean.children.size() > 0) {
                    boolean isHave = isHaveMenu(menuListBean.children, webPath);
                    if (isHave) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return true表示是管理员
     */
    public boolean isAdmin() {
        if (null != oper && "1".equals(oper.getGrade())) {
            return true;
        }
        return false;
    }

    /**
     * @return true表示是城市经理
     */
    public boolean isCityAdmin() {
        if (null != oper && "3".equals(oper.getGrade())) {
            return true;
        }
        return false;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OperBean getOper() {
        return oper;
    }

    public void setOper(OperBean oper) {
        this.oper = oper;
    }

    public static class OperBean {
        private String operatorId;
        private String rolesId;
        private String rolesName;
        private String username;
        private String name;
        private String password;
        private String provinceId;
        private String province;
        private String cityId;
        private String city;
        private String districtId;
        private String district;
        private String grade;
        private String creatorId;
        private List<MenuListBean> menuList;

        public String getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(String operatorId) {
            this.operatorId = operatorId;
        }

        public String getRolesId() {
            return rolesId;
        }

        public void setRolesId(String rolesId) {
            this.rolesId = rolesId;
        }

        public String getRolesName() {
            return rolesName;
        }

        public void setRolesName(String rolesName) {
            this.rolesName = rolesName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
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

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public List<MenuListBean> getMenuList() {
            return menuList;
        }

        public void setMenuList(List<MenuListBean> menuList) {
            this.menuList = menuList;
        }

        public static class MenuListBean {
            /**
             * id : 1
             * createAt : null
             * updateAt : null
             * deleteAt : null
             * isDeleted : null
             * name : 配送地址管理
             * remark : page/address/addressManage.html
             * pid : -1
             * children : null
             */

            private String id;
            private String createAt;
            private String updateAt;
            private String deleteAt;
            private String isDeleted;
            private String name;
            private String remark;
            private String pid;
            private List<MenuListBean> children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateAt() {
                return createAt;
            }

            public void setCreateAt(String createAt) {
                this.createAt = createAt;
            }

            public String getUpdateAt() {
                return updateAt;
            }

            public void setUpdateAt(String updateAt) {
                this.updateAt = updateAt;
            }

            public String getDeleteAt() {
                return deleteAt;
            }

            public void setDeleteAt(String deleteAt) {
                this.deleteAt = deleteAt;
            }

            public String getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(String isDeleted) {
                this.isDeleted = isDeleted;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public List<MenuListBean> getChildren() {
                return children;
            }

            public void setChildren(List<MenuListBean> children) {
                this.children = children;
            }
        }
    }
}
