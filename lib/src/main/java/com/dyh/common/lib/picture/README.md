# PictureSelector 2.0
   一款针对Android平台下的图片选择器，支持从相册获取图片、视频、音频&拍照，支持裁剪(单图or多图裁剪)、压缩、主题自定义配置等功能，支持动态获取权限&适配Android 5.0+系统的开源图片选择框架。<br>
   
   [英文版🇺🇸](README.md)
 
   [我的博客地址](http://blog.csdn.net/luck_mw)   
  
   [体验Demo](https://github.com/LuckSiege/PictureSelector/raw/master/app/demo/app_2020_06_01_2030_2.5.8.apk)<br>
  
[![](https://jitpack.io/v/LuckSiege/PictureSelector.svg)](https://jitpack.io/#LuckSiege/PictureSelector)
[![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg)](https://github.com/LuckSiege)
[![CSDN](https://img.shields.io/twitter/url/http/blog.csdn.net/luck_mw.svg?style=social)](http://blog.csdn.net/luck_mw)
[![I](https://img.shields.io/github/issues/LuckSiege/PictureSelector.svg)](https://github.com/LuckSiege/PictureSelector/issues)
[![Star](https://img.shields.io/github/stars/LuckSiege/PictureSelector.svg)](https://github.com/LuckSiege/PictureSelector)

## 目录
-[用前需知](https://github.com/LuckSiege/PictureSelector/wiki/%E7%94%A8%E5%89%8D%E9%9C%80%E7%9F%A5)<br>
-[如何提Issues?](https://github.com/LuckSiege/PictureSelector/wiki/%E5%A6%82%E4%BD%95%E6%8F%90Issues%3F)<br>
-[功能特点](https://github.com/LuckSiege/PictureSelector/wiki/%E5%8A%9F%E8%83%BD%E7%89%B9%E7%82%B9)<br>
-[最新版本 v2.5.8](#最新版本)<br>
-[更新日志](https://github.com/LuckSiege/PictureSelector/releases/tag/v2.5.8)<br>
-[主题配置-Xml方式](https://github.com/LuckSiege/PictureSelector/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E4%B8%BB%E9%A2%98-Xml%E6%96%B9%E5%BC%8F)<br>
-[主题配置-Code方式](https://github.com/LuckSiege/PictureSelector/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E5%8A%A8%E6%80%81%E4%B8%BB%E9%A2%98(%E5%8C%85%E5%90%AB%E8%A3%81%E5%89%AA%E3%80%81%E7%9B%B8%E5%86%8C%E5%90%AF%E5%8A%A8%E5%8A%A8%E7%94%BB)-Code%E6%96%B9%E5%BC%8F)<br>
-[演示效果](#演示效果)<br>
-[集成方式](https://github.com/LuckSiege/PictureSelector/wiki/%E9%9B%86%E6%88%90%E6%96%B9%E5%BC%8F)<br>
-[Api说明](https://github.com/LuckSiege/PictureSelector/wiki/PictureSelector-Api%E8%AF%B4%E6%98%8E)<br>
-[启动相册](#启动相册)<br>
-[单独拍照](#单独拍照)<br>
-[自定义相机](#自定义相机)<br>
-[结果回调](https://github.com/LuckSiege/PictureSelector/wiki/%E7%BB%93%E6%9E%9C%E5%9B%9E%E8%B0%83)<br>
-[常见错误](https://github.com/LuckSiege/PictureSelector/wiki/%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF)<br>
-[缓存清除](#缓存清除)<br>
-[混淆配置](#混淆配置)<br>
-[License](#License)<br>
-[兼容性测试](#兼容性测试)<br>
-[联系方式](#联系方式)<br>


## 最新版本
```sh
implementation 'com.github.LuckSiege.PictureSelector:picture_library:v2.5.8'
```

## 启动相册
快捷调用，更多功能 [请查看](https://github.com/LuckSiege/PictureSelector/wiki/PictureSelector-Api%E8%AF%B4%E6%98%8E)

1、onActivityResult
```sh 
 PictureSelector.create(this)
   .openGallery(PictureMimeType.ofImage())
   .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
   .forResult(PictureConfig.CHOOSE_REQUEST);
   
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    break;
                default:
                    break;
            }            
        }
```

2、Callback
```sh
 PictureSelector.create(this)
   .openGallery(PictureMimeType.ofAll())
   .loadImageEngine(GlideEngine.createGlideEngine())
   .forResult(new OnResultCallbackListener<LocalMedia>() {
       @Override
       public void onResult(List<LocalMedia> result) {
            // 结果回调
       }

       @Override
       public void onCancel() {
            // 取消
       }
     });  
```

## 单独拍照
快捷调用，单独启动拍照或视频 根据PictureMimeType自动识别 更多功能 [请查看](https://github.com/LuckSiege/PictureSelector/wiki/PictureSelector-Api%E8%AF%B4%E6%98%8E)

onActivityResult
```sh
 PictureSelector.create(this)
   .openCamera(PictureMimeType.ofImage())
   .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
   .forResult(PictureConfig.REQUEST_CAMERA);  
   
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.REQUEST_CAMERA:
                    // 结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    break;
                default:
                    break;
            }            
        }
```

Callback
```sh
PictureSelector.create(this)
   .openCamera(PictureMimeType.ofImage())
   .loadImageEngine(GlideEngine.createGlideEngine())
   .forResult(new OnResultCallbackListener<LocalMedia>() {
       @Override
       public void onResult(List<LocalMedia> result) {
            // 结果回调
       }

       @Override
       public void onCancel() {
            // 取消
       }
     });
```

## 自定义相机
如果需要使用自定义相机需要设置
```
.isUseCustomCamera(true);
```
Application下实现如下接口
```sh
 public class App extends Application implements CameraXConfig.Provider {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @NonNull
    @Override
    public CameraXConfig getCameraXConfig() {
        return Camera2Config.defaultConfig();
    }
 }
```

## 缓存清除
```sh
 //包括裁剪和压缩后的缓存，要在上传成功后调用，type 指的是图片or视频缓存取决于你设置的ofImage或ofVideo 注意：需要系统sd卡权限  
 PictureFileUtils.deleteCacheDirFile(this,type);
 // 清除所有缓存 例如：压缩、裁剪、视频、音频所生成的临时文件
 PictureFileUtils.deleteAllCacheDirFile(this);
```
 
## 预览图片 
```
// 预览图片 可自定长按保存路径
*注意 .themeStyle(R.style.theme)；里面的参数不可删，否则闪退...

PictureSelector.create(this)
 .themeStyle(R.style.picture_default_style)
 .isNotPreviewDownload(true)
 .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
 .openExternalPreview(position, selectList);

```
## 预览视频
```sh
PictureSelector.create(this).externalPictureVideo(video_path);
```

## 项目使用第三方库

* PhotoView
* luban
* ucrop

## 混淆配置 
```sh
#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

#Ucrop
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

#Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*
```
## License
```sh
   Copyright 2017 Luck

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

## 联系方式
Android开发交流 群一 [619458861]() (已满) <br>
Android开发交流 群二 [679824206]() (已满) <br>
Android开发交流 群三 [854136996]() (已满) <br>
QQ [893855882]() <br>


## 兼容性测试
******腾讯优测-深度测试-通过率达到100%******

![image](https://github.com/LuckSiege/PictureSelector/blob/master/image/test.png)


## 演示效果

| 单一模式 | 混选模式 |
|:-----------:|:-----------:|
|![](image/home.jpg)|![](image/home_mixed.jpg)| 

| 默认风格 | 预览 | 多图裁剪 |
|:-----------:|:--------:|:---------:|
|![](image/picture_default_style_1.jpg) | <img src="image/picture_default_style_2.jpg"/> | ![](image/picture_default_style_new_3.jpg)|  

| 数字风格 | 预览 | 多图裁剪 |
|:-----------:|:--------:|:---------:|
|![](image/picture_num_style_new_1.jpg) | ![](image/picture_num_style_new_2.jpg) | ![](image/picture_num_style_new_3.jpg)| 

| 白色风格 | 预览 | 单图裁剪 |
|:-----------:|:--------:|:---------:|
|![](image/picture_sina_style_1.jpg) | ![](image/picture_sina_style_new_2.jpg) | ![](image/picture_sina_style_new_3.jpg)| 

| 全新风格 | 预览 | 多图裁剪 |
|:-----------:|:--------:|:---------:|
|![](image/picture_wechat_style_1.jpg) | ![](image/picture_wechat_style_2.jpg) | ![](image/picture_wechat_style_new_3.jpg)| 

| 相册目录 | 单选模式 | 头像裁剪|
|:-----------:|:--------:|:--------:|
|![](image/picture_wechat_album_style.jpg) |![](image/picture_wechat_single_style_3.jpg) | ![](image/picture_circular_crop_new_style.jpg)| 

| 白色风格 | 视频 | 音频 |
|:-----------:|:-----------:|:--------:|
|![](image/picture_white_style.jpeg) |![](image/picture_video.jpg) | ![](image/picture_audio.jpg)| 

boolean mode = cb_mode.isChecked();
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(MainActivity.this)
                        .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                        .isWeChatStyle(isWeChatStyle)// 是否开启微信图片选择风格
                        .isUseCustomCamera(cb_custom_camera.isChecked())// 是否使用自定义相机
                        .setLanguage(language)// 设置语言，默认中文
                        .isPageStrategy(cbPage.isChecked())// 是否开启分页策略 & 每页多少条；默认开启
                        .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                        .setPictureCropStyle(mCropParameterStyle)// 动态自定义裁剪主题
                        .setPictureWindowAnimationStyle(mWindowAnimationStyle)// 自定义相册启动退出动画
                        .setRecyclerAnimationMode(animationMode)// 列表动画效果
                        .isWithVideoImage(true)// 图片和视频是否可以同选,只在ofAll模式下有效
                        .isMaxSelectEnabledMask(cbEnabledMask.isChecked())// 选择数到了最大阀值列表是否启用蒙层效果
                        //.isAutomaticTitleRecyclerTop(false)// 连续点击标题栏RecyclerView是否自动回到顶部,默认true
                        //.loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
                        //.setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                        //.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .maxVideoSelectNum(1) // 视频最大选择数量
                        //.minVideoSelectNum(1)// 视频最小选择数量
                        //.closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 关闭在AndroidQ下获取图片或视频宽高相反自动转换
                        .imageSpanCount(4)// 每行显示个数
                        .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                        .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高,默认为true
                        .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高,默认为false
                        //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                        .isOriginalImageControl(cb_original.isChecked())// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                        //.bindCustomPlayVideoCallback(new MyVideoSelectedPlayCallback(getContext()))// 自定义视频播放回调控制，用户可以使用自己的视频播放界面
                        //.bindCustomPreviewCallback(new MyCustomPreviewInterfaceListener())// 自定义图片预览回调接口
                        //.bindCustomCameraInterfaceListener(new MyCustomCameraInterfaceListener())// 提供给用户的一些额外的自定义操作回调
                        //.cameraFileName(System.currentTimeMillis() +".jpg")    // 重命名拍照文件名、如果是相册拍照则内部会自动拼上当前时间戳防止重复，注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
                        //.renameCompressFile(System.currentTimeMillis() +".jpg")// 重命名压缩文件名、 如果是多张压缩则内部会自动拼上当前时间戳防止重复
                        //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 如果是多张裁剪则内部会自动拼上当前时间戳防止重复
                        .selectionMode(cb_choose_mode.isChecked() ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .isSingleDirectReturn(cb_single_back.isChecked())// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                        .isPreviewImage(cb_preview_img.isChecked())// 是否可预览图片
                        .isPreviewVideo(cb_preview_video.isChecked())// 是否可预览视频
                        //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                        .isEnablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                        .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
                        //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                        //.isMultipleRecyclerAnimation(false)// 多图裁剪底部列表显示动画效果
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg,Android Q使用PictureMimeType.PNG_Q
                        .isEnableCrop(cb_crop.isChecked())// 是否裁剪
                        //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                        .isCompress(cb_compress.isChecked())// 是否压缩
                        //.compressQuality(80)// 图片压缩后输出质量 0~ 100
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                        //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(!cb_hide.isChecked())// 是否显示uCrop工具栏，默认不显示
                        .isGif(cb_isGif.isChecked())// 是否显示gif图片
                        .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                        //.setCropDimmedColor(ContextCompat.getColor(getContext(), R.color.app_color_white))// 设置裁剪背景色值
                        //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                        //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                        .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .isOpenClickSound(cb_voice.isChecked())// 是否开启点击声音
                        .selectionData(mAdapter.getData())// 是否传入已选图片
                        //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                        //.videoMinSecond(10)// 查询多少秒以内的视频
                        //.videoMaxSecond(15)// 查询多少秒以内的视频
                        //.recordVideoSecond(10)//录制视频秒数 默认60s
                        //.isPreviewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于多少kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled(false) // 裁剪是否可旋转图片
                        //.scaleEnabled(false)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                        .forResult(new MyResultCallback(mAdapter));
            } else {
                // 单独拍照
                PictureSelector.create(MainActivity.this)
                        .openCamera(chooseMode)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .theme(themeId)// 主题样式设置 具体参考 values/styles
                        .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                        .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                        .setPictureCropStyle(mCropParameterStyle)// 动态自定义裁剪主题
                        .setPictureWindowAnimationStyle(mWindowAnimationStyle)// 自定义相册启动退出动画
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .isUseCustomCamera(cb_custom_camera.isChecked())// 是否使用自定义相机
                        //.setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                        .minSelectNum(1)// 最小选择数量
                        //.querySpecifiedFormatSuffix(PictureMimeType.ofPNG())// 查询指定后缀格式资源
                        .closeAndroidQChangeWH(true)//如果图片有旋转角度则对换宽高，默认为true
                        .closeAndroidQChangeVideoWH(!SdkVersionUtils.checkedAndroid_Q())// 如果视频有旋转角度则对换宽高，默认false
                        .selectionMode(cb_choose_mode.isChecked() ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        //.cameraFileName(System.currentTimeMillis() + ".jpg")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用
                        //.renameCompressFile(System.currentTimeMillis() + ".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                        //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                        .loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
                        .isPreviewImage(cb_preview_img.isChecked())// 是否可预览图片
                        .isPreviewVideo(cb_preview_video.isChecked())// 是否可预览视频
                        .isEnablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                        .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
                        .isEnableCrop(cb_crop.isChecked())// 是否裁剪
                        //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                        .isCompress(cb_compress.isChecked())// 是否压缩
                        .compressQuality(60)// 图片压缩后输出质量
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(!cb_hide.isChecked())// 是否显示uCrop工具栏，默认不显示
                        .isGif(cb_isGif.isChecked())// 是否显示gif图片
                        .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                        //.setCircleDimmedColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪背景色值
                        //.setCircleDimmedBorderColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪边框色值
                        //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                        .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .isOpenClickSound(cb_voice.isChecked())// 是否开启点击声音
                        .selectionData(mAdapter.getData())// 是否传入已选图片
                        //.isPreviewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 废弃 改用cutOutQuality()
                        .cutOutQuality(90)// 裁剪输出质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                        .forResult(new MyResultCallback(mAdapter));
            }