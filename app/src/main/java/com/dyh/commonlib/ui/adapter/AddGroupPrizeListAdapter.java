package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.widget.EditText;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.GroupPrizeItemBean;
import com.dyh.commonlib.util.CommonImagesUtil;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.dw.mylistener.MyTextChangeListener;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 15:33
 * 描述：添加团队奖励列表适配器
 */
public class AddGroupPrizeListAdapter extends BaseQuickRecyclerViewAdapter<GroupPrizeItemBean.WareListBean> {

    private Map<String, MyTextChangeListener> textChangeListenerMap = new HashMap<>();

    public AddGroupPrizeListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GroupPrizeItemBean.WareListBean item) {
        helper.setText(R.id.mNameEditText, item.getName())
                .setText(R.id.mPersonNumberEditText, item.getPersonNum())
                .setText(R.id.mDescriptionEditText, item.getDescription())
                .addOnClickListener(R.id.mImageView);

        //图片
        EasyGlide.loadImage(mContext, CommonImagesUtil.getFullImageHttpUrl(item.getPicture()), helper.getView(R.id.mImageView));

        //监听编辑名称
        EditText mNameEditText = helper.getView(R.id.mNameEditText);
        MyTextChangeListener myTextChangeListener = textChangeListenerMap.get("name" + helper.getLayoutPosition());
        if (null == myTextChangeListener) {
            myTextChangeListener = new MyTextChangeListener() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    item.setName(mNameEditText.getText().toString());
                }
            };
            textChangeListenerMap.put("name" + helper.getLayoutPosition(), myTextChangeListener);
        } else {
            mNameEditText.removeTextChangedListener(myTextChangeListener);
        }
        mNameEditText.addTextChangedListener(myTextChangeListener);
        //监听编辑人数
        EditText mPersonNumberEditText = helper.getView(R.id.mPersonNumberEditText);
        myTextChangeListener = textChangeListenerMap.get("personNum" + helper.getLayoutPosition());
        if (null == myTextChangeListener) {
            myTextChangeListener = new MyTextChangeListener() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    item.setPersonNum(mPersonNumberEditText.getText().toString());
                }
            };
            textChangeListenerMap.put("personNum" + helper.getLayoutPosition(), myTextChangeListener);
        } else {
            mPersonNumberEditText.removeTextChangedListener(myTextChangeListener);
        }
        mPersonNumberEditText.addTextChangedListener(myTextChangeListener);
        //监听编辑描述
        EditText mDescriptionEditText = helper.getView(R.id.mDescriptionEditText);
        myTextChangeListener = textChangeListenerMap.get("description" + helper.getLayoutPosition());
        if (null == myTextChangeListener) {
            myTextChangeListener = new MyTextChangeListener() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    item.setDescription(mDescriptionEditText.getText().toString());
                }
            };
            textChangeListenerMap.put("description" + helper.getLayoutPosition(), myTextChangeListener);
        } else {
            mDescriptionEditText.removeTextChangedListener(myTextChangeListener);
        }
        mDescriptionEditText.addTextChangedListener(myTextChangeListener);
    }

}
