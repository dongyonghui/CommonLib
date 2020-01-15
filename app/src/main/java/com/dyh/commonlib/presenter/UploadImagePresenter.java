package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.response.UploadFileResultItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IUploadImageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.io.File;
import java.util.List;

/**
 * 上传图片
 */
public class UploadImagePresenter extends MVPPresenter<IUploadImageView> {


    public UploadImagePresenter( IUploadImageView view) {
        super(view);
    }

    /**
     * 上传图片
     *
     * @param file
     */
    public void uploadImage(File file) {
        EasyHttp.post(ApiPathConstants.UPLOAD_FILE_PATH)
                .params("file", file, null).execute(new ProgressDialogCallBack<List<UploadFileResultItemBean>>(new ProgressDialogDefault(getActivity())) {
            @Override
            public void onSuccess(Optional<List<UploadFileResultItemBean>> listOptional) {
                List<UploadFileResultItemBean> list = listOptional.getIncludeNull();
                if (list != null && list.size() > 0) {
                    getView().onUploadImageSuccess(list.get(0).getUrl());
                }
            }
        });
    }
}
