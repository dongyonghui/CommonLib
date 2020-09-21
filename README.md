# gradle引入方法
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.dongyonghui:CommonLib:2.0'
	}
# 初始化

在Application继承BaseApplication，并复写重新登录方法：
```
    @Override
    public void reLogin() {
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        spEntity.loginResponseBean = null;
        spEntity.commit();
        //如果重新登录不需要退出所有页面，可删除下面这行
        AppExit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
```
初始化日志和网络
```
        //是否启用日志
        EasyLog.Companion.getDEFAULT().setEnable(true);
        
        private void initHttp() {
                //设置请求头
                HttpHeaders headers = getCommonHttpHeaders();
                //设置请求参数
                EasyHttp.getInstance()
                        .debug("GouKu", BuildConfig.DEBUG)
                        .addCommonHeaders(headers)//设置全局公共头
                        .addInterceptor(new MyHeaderTokenInterceptor(headers));//token追加拦截器
        
            }
        
        public HttpHeaders getCommonHttpHeaders() {
            HttpHeaders headers = new HttpHeaders();
            headers.put("User-Agent", SystemInfoUtils.getUserAgent(this));
            headers.put("Gouku-App-Origin", "31");
            headers.put("Content-Type", "application/json;charset=UTF-8");
            return headers;
        }
```

使用MVP：
Activity 继承 BaseMVPActivity
Presenter 继承 MVPPresenter<T : MVPView>
然后在Activity中实例化Presenter

RecyclerView适配器基类：
普通列表适配器继承 BaseQuickRecyclerViewAdapter<T>
分组列表适配器继承 BaseSectionQuickRecyclerViewAdapter<T extends SectionEntity>

ListView适配器基类：
继承 BaseListViewAdapter

添加权限

```
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.CAMERA" />
    
```


### 目录索引
- [EasyUtils](#EasyUtils): EasyAndroid开源项目工具类
- [EasyHttp](#EasyHttp): RxEasyHttp开源项目工具类
- [PictureLib](#PictureLib): PictureLib开源项目工具类
- [UpdateApp](#UpdateApp): UpdateApp开源项目工具类
- [Calender](#Calender): Calender开源项目工具类
- [Dialog](#Dialog): Dialog开源项目工具类
- [Number](#Number): Number开源项目工具类
- [Picker](#Picker): Picker开源项目工具类
- [PopFilter](#PopFilter): PopFilter开源项目工具类
- [SeekBar](#SeekBar): SeekBar开源项目工具类
- [TabLayout](#TabLayout): TabLayout开源项目工具类
- [TitleBar](#TitleBar): TitleBar开源项目工具类
- [SlantedTextView_使用说明](#SlantedTextView_使用说明): SlantedTextView_使用说明
- [TagGroup_使用说明](#TagGroup_使用说明): TagGroup_使用说明
- [WrapLayout_使用说明](#WrapLayout_使用说明): WrapLayout_使用说明

### [EasyUtils](./docs/EASY_README.md)
> [点我查看完整使用文档](./docs/EASY_README.md)
### [EasyHttp](./docs/EasyHttp_README.md)
> [点我查看完整使用文档](./docs/EasyHttp_README.md)
### [PictureLib](./docs/Picture_README.md)
> [点我查看完整使用文档](./docs/Picture_README.md)
### [UpdateApp](./docs/UpdateApp_README.md)
> [点我查看完整使用文档](./docs/UpdateApp_README.md)
### [Calender](./docs/Calender_README.md)
> [点我查看完整使用文档](./docs/Calender_README.md)
### [Dialog](./docs/Dialog_README.md)
> [点我查看完整使用文档](./docs/Dialog_README.md)
### [Number](./docs/Number_README.md)
> [点我查看完整使用文档](./docs/Number_README.md)
### [Picker](./docs/Picker_README.md)
> [点我查看完整使用文档](./docs/Picker_README.md)
### [PopFilter](./docs/PopFilter_README.md)
> [点我查看完整使用文档](./docs/PopFilter_README.md)
### [SeekBar](./docs/SeekBar_README.md)
> [点我查看完整使用文档](./docs/SeekBar_README.md)
### [TabLayout](./docs/TabLayout_README.md)
> [点我查看完整使用文档](./docs/TabLayout_README.md)
### [TitleBar](./docs/TitleBar_README.md)
> [点我查看完整使用文档](./docs/TitleBar_README.md)
### [SlantedTextView_使用说明](./docs/SlantedTextView_使用说明.md)
> [点我查看完整使用文档](./docs/SlantedTextView_使用说明.md)
### [TagGroup_使用说明](./docs/TagGroup_使用说明.md)
> [点我查看完整使用文档](./docs/TagGroup_使用说明.md)
### [WrapLayout_使用说明](./docs/WrapLayout_使用说明.md)
> [点我查看完整使用文档](./docs/WrapLayout_使用说明.md)
