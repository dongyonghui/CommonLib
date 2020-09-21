package com.dyh.common.lib.picture.observable;

import com.dyh.common.lib.picture.entity.LocalMedia;
import com.dyh.common.lib.picture.entity.LocalMediaFolder;

import java.util.List;

/**
 * author：luck
 * project：PictureSelector
 * package：com.inscloudtech.easyandroid.picture.observable
 * email：893855882@qq.com
 * data：17/1/16
 */
public interface ObserverListener {
    void observerUpFoldersData(List<LocalMediaFolder> folders);

    void observerUpSelectsData(List<LocalMedia> selectMedias);
}
