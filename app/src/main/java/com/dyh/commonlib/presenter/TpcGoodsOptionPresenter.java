package com.dyh.commonlib.presenter;

import android.content.res.Resources;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.request.SubmitAuditingRequestBean;
import com.dyh.commonlib.entity.response.CategoryItemBean;
import com.dyh.commonlib.entity.response.FinerTimeItemBean;
import com.dyh.commonlib.entity.response.LabelItemBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.weigit.dialog_default.DialogType;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnTextInputConfirmListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 头牌菜商品操作
 */
public class TpcGoodsOptionPresenter extends MVPPresenter<IDefaultOptionView> {


    public TpcGoodsOptionPresenter( IDefaultOptionView view) {
        super(view);
    }


    /**
     * 同意
     *
     * @param goodsItemBean
     */
    public void agree(TpcGoodsItemBean goodsItemBean) {
        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
        requestBean.spu = goodsItemBean.getSpu();
        requestBean.shopId = goodsItemBean.getShopId();
        requestBean.name = goodsItemBean.getName();
        requestBean.addPrice = "0";
        requestBean.isAgree = "1";
        requestBean.reason = "";
        sendOption2Server(ApiPathConstants.SUBMIT_AUDITING, requestBean);
    }

    /**
     * 设置优选时间
     *
     * @param goodsItemBean
     */
    public void setFinerTime(TpcGoodsItemBean goodsItemBean, List<FinerTimeItemBean> selectedTimeList) {
        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
        requestBean.spu = goodsItemBean.getSpu();
        requestBean.shopId = goodsItemBean.getShopId();
        List<SubmitAuditingRequestBean.CateListBean> cateListBeans = new ArrayList<>();
        if (null != selectedTimeList) {
            for (FinerTimeItemBean finerTimeItemBean : selectedTimeList) {
                SubmitAuditingRequestBean.CateListBean cateListBean = new SubmitAuditingRequestBean.CateListBean();
                cateListBean.setId(finerTimeItemBean.getId());
                cateListBean.setName(finerTimeItemBean.getName());
                cateListBean.setTime(finerTimeItemBean.getEndTimeStr() + "-" + finerTimeItemBean.getSendTimeStr());
                cateListBeans.add(cateListBean);
            }
        }
        requestBean.setCateList(cateListBeans);
        sendOption2Server(ApiPathConstants.SET_FINER_TPC_GOODS, requestBean);
    }

    /**
     * 设置头牌菜分类
     *
     * @param goodsItemBean
     */
    public void setTpcGoodsCategory(TpcGoodsItemBean goodsItemBean, List<CategoryItemBean> selectedList) {
        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
        requestBean.spu = goodsItemBean.getSpu();
        requestBean.shopId = goodsItemBean.getShopId();
        requestBean.setCategoryList(selectedList);
        sendOption2Server(ApiPathConstants.SET_CATEGORY_TPC_GOODS, requestBean);
    }

    /**
     * 设置标签
     *
     * @param goodsItemBean
     */
    public void setLabel(TpcGoodsItemBean goodsItemBean, List<LabelItemBean> selectedList) {
        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
        requestBean.spu = goodsItemBean.getSpu();
        requestBean.shopId = goodsItemBean.getShopId();
        List<String> cateListBeans = new ArrayList<>();
        if (null != selectedList) {
            for (LabelItemBean itemBean : selectedList) {
                cateListBeans.add(itemBean.getShopWareTagsId());
            }
        }
        requestBean.shopWareTagsIds = cateListBeans;
        sendOption2Server(ApiPathConstants.SET_TAGS, requestBean);
    }

    /**
     * 同意并加价
     *
     * @param goodsItemBean
     */
    public void agreeAndAddPrice(TpcGoodsItemBean goodsItemBean) {
        Resources resources = getActivity().getResources();
        String inputInfo = resources.getString(R.string.inputAddPriceNumber);
        new MyAppDialog(getActivity())
                .setHeaderBackground(R.drawable.dialog_title_bg_shape)
                .setTitle(inputInfo)
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.common_confirm))
                .input(inputInfo, inputInfo, new OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String input) {
                        try {
                            Double.parseDouble(input);
                        } catch (Exception e) {
                            EasyToast.getDEFAULT().show(R.string.inputRightPrice);
                            agreeAndAddPrice(goodsItemBean);
                            return;
                        }

                        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
                        requestBean.spu = goodsItemBean.getSpu();
                        requestBean.shopId = goodsItemBean.getShopId();
                        requestBean.name = goodsItemBean.getName();
                        requestBean.addPrice = input;
                        requestBean.isAgree = "1";
                        requestBean.reason = "";
                        sendOption2Server(ApiPathConstants.SUBMIT_AUDITING, requestBean);
                    }
                })
                .isCancelable(true)
                .setDialogType(DialogType.INPUT)
                .show();

    }

    /**
     * 驳回
     *
     * @param goodsItemBean
     */
    public void reject(TpcGoodsItemBean goodsItemBean) {
        String inputInfo = getActivity().getString(R.string.inputRejectReason);
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(inputInfo)
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.common_confirm))
                .input(inputInfo, inputInfo, new OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String input) {
                        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
                        requestBean.spu = goodsItemBean.getSpu();
                        requestBean.shopId = goodsItemBean.getShopId();
                        requestBean.name = goodsItemBean.getName();
                        requestBean.isAgree = "0";
                        requestBean.reason = input;
                        sendOption2Server(ApiPathConstants.SUBMIT_AUDITING, requestBean);
                    }
                })
                .isCancelable(true)
                .setDialogType(DialogType.INPUT)
                .show();

    }

    /**
     * 修改加价
     *
     * @param goodsItemBean
     */
    public void modifyAddPrice(TpcGoodsItemBean goodsItemBean) {
        Resources resources = getActivity().getResources();
        String inputInfo = resources.getString(R.string.inputAddPriceNumber);
        new MyAppDialog(getActivity())
                .setHeaderBackground(R.drawable.dialog_title_bg_shape)
                .setTitle(inputInfo)
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.common_confirm))
                .input(inputInfo, inputInfo, new OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String input) {
                        try {
                            Double.parseDouble(input);
                        } catch (Exception e) {
                            EasyToast.getDEFAULT().show(R.string.inputRightPrice);
                            agreeAndAddPrice(goodsItemBean);
                            return;
                        }
                        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
                        requestBean.spu = goodsItemBean.getSpu();
                        requestBean.shopId = goodsItemBean.getShopId();
                        requestBean.addPrice = input;
                        sendOption2Server(ApiPathConstants.ADD_PRICE, requestBean);
                    }
                })
                .isCancelable(true)
                .setDialogType(DialogType.INPUT)
                .show();
    }

    /**
     * 修改权重
     *
     * @param goodsItemBean
     */
    public void modifySort(TpcGoodsItemBean goodsItemBean) {
        Resources resources = getActivity().getResources();
        String inputInfo = resources.getString(R.string.modifySortInfo);
        new MyAppDialog(getActivity())
                .setTitle(resources.getString(R.string.modifySortInfo))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.common_confirm))
                .input(inputInfo, inputInfo, new OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String input) {
                        try {
                            Integer.parseInt(input);
                        } catch (Exception e) {
                            EasyToast.getDEFAULT().show(R.string.inputRightNumber);
                            modifySort(goodsItemBean);
                            return;
                        }
                        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
                        requestBean.spu = goodsItemBean.getSpu();
                        requestBean.shopId = goodsItemBean.getShopId();
                        requestBean.sort = input;
                        sendOption2Server(ApiPathConstants.UPDATE_SORT, requestBean);
                    }
                })
                .isCancelable(true)
                .setDialogType(DialogType.INPUT)
                .show();
    }

    /**
     * 删除商品
     *
     * @param goodsItemBean
     */
    public void delete(TpcGoodsItemBean goodsItemBean) {
        if (goodsItemBean == null) {
            return;
        }
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .isCancelable(true)
                .setTitle(resources.getString(R.string.deleteQuery))
                .setMessage(resources.getString(R.string.formatString_deleteInfo, goodsItemBean.getName()))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(getActivity().getString(R.string.delete), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        SubmitAuditingRequestBean requestBean = new SubmitAuditingRequestBean();
                        requestBean.spu = goodsItemBean.getSpu();
                        requestBean.shopId = goodsItemBean.getShopId();
                        sendOption2Server(ApiPathConstants.DELETE_TPC_GOODS, requestBean);
                    }
                })
                .show();
    }

    /**
     * 提交请求
     *
     * @param requestBean
     */
    private void sendOption2Server(String url, SubmitAuditingRequestBean requestBean) {
        EasyHttp.post(url)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(Optional<Object> listOptional) {
                        getView().onOptionOk();
                    }
                });
    }
}
