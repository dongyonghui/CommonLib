
## Android 版本更新

## 目录

* [功能介绍](#功能介绍)
* [效果图与示例 apk](#效果图与示例-apk)
* [Gradle 依赖](#Gradle依赖)
* [简单使用](#简单使用)
* [详细说明](#详细说明)
* [更新日志](#更新日志)
* [License](#license)

## 功能介绍

- [x] 实现android版本更新
- [x] 对kotlin适配，调用更简单
- [x] 自定义接口协议，可以不改变现有项目的协议就能使用
- [x] 支持get,post请求
- [x] 支持进度显示，对话框进度条，和通知栏进度条展示
- [x] 支持后台下载
- [x] 支持强制更新
- [x] 支持简单主题色配置(可以自动从顶部图片提取主色)
- [x] 支持自定义对话框（可以监听下载进度）
- [x] 支持静默下载（可以设置wifi状态下）
- [x] 支持android7.0

## 效果图与示例 apk

<img src="https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/image/example_01.png?raw=true" width="1000">

<img src="https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/image/example_02.png?raw=true" width="1000">

<img src="https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/image/example_03.png?raw=true" width="1000">

<img src="https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/image/example_05.png" width="1000">

<img src="https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/image/example_06.png" width="1000">

	
[点击下载 Demo.apk](https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk) 或扫描下面的二维码安装

![Demo apk文件二维](https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/image/1498810770.png)

## 简单使用
1,java方式

```java
	new UpdateAppManager
                    .Builder()
                    .setIgnoreDefParams(true)
                    //当前Activity
                    .setActivity(getActivity())
                    .setHeaders(MyApplication.getInstance().getCommonHttpHeaders().headersMap)
                    //更新地址
                    .setUpdateUrl(ApiPathConstants.APP_VERSION_PATH)
                    //实现httpManager接口的对象
                    .setHttpManager(new UpdateAppHttpUtil())
                    .build()
                    .update();
```
2,kotlin方式

```kotlin
	updateApp(mUpdateUrl, UpdateAppHttpUtil()).update()
```


#### 进度条使用的是代码家的「[NumberProgressBar](https://github.com/daimajia/NumberProgressBar)」
