# TeaScreenPopupWindow
多类型筛选弹框

![这是一张图片](https://github.com/YangsBryant/TeaScreenPopupWindow/blob/master/image/kfgmg-a8c9e.gif)

## 引入module
```java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://www.jitpack.io' }
    }
}
```
```java
implementation 'com.github.YangsBryant:TeaScreenPopupWindow:1.0.2'
```

## 主要代码
```java
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    private ScreenPopWindow screenPopWindow;

    private List<FiltrateBean> dictList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind( this );
        initParam();
        initView();
    }

    private void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenPopWindow = new ScreenPopWindow(this, mFiltrateBeanList);
                        //默认单选，因为共用的一个bean，这里调用reset重置下数据
                        screenPopWindow.build();
                        screenPopWindow.showAsDropDown(mFilterTextView);
                        screenPopWindow.setOnConfirmClickListener(new ScreenPopWindow.OnConfirmClickListener() {
                            @Override
                            public void onConfirmClick(Map<String, FiltrateBean> selectedValues) {
                                mGetGoodsListRequestBean.auditingStatus = selectedValues.get("auditingStatus") == null ? "0" : selectedValues.get("auditingStatus").getSingleValue();
                                mGetGoodsListRequestBean.auditingType = selectedValues.get("auditingType") == null ? "0" : selectedValues.get("auditingType").getSingleValue();
                                mGetGoodsListRequestBean.cityId = selectedValues.get("cityId") == null ? "0" : selectedValues.get("cityId").getSingleValue();
                                mGetGoodsListRequestBean.name = selectedValues.get("name") == null ? "" : selectedValues.get("name").getSingleValue();
                                mPresenter.refreshList(mGetGoodsListRequestBean);
                            }
                        });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    private void initParam() {
        List<FiltrateBean> dictList = new ArrayList<>();
                FiltrateBean filtrateBean;
                List<FiltrateBean.Children> childrenList;
        
                filtrateBean = new FiltrateBean();
                dictList.add(filtrateBean);
                filtrateBean.setKey("name");
                filtrateBean.setTitleName("请输入商品名称或店铺名称");
                filtrateBean.setType(FiltrateBean.TYPE_INPUT);
        
                filtrateBean = new FiltrateBean();
                dictList.add(filtrateBean);
                childrenList = new ArrayList<>();
                filtrateBean.setChildren(childrenList);
                filtrateBean.setTitleName("菜品类型");
                filtrateBean.setKey("auditingType");
                childrenList.add(new FiltrateBean.Children("头牌菜", "2"));
                childrenList.add(new FiltrateBean.Children("优选菜", "4"));
                childrenList.add(new FiltrateBean.Children("首单特惠", "5"));
                childrenList.add(new FiltrateBean.Children("限时秒杀", "7"));
        
        
                filtrateBean = new FiltrateBean();
                dictList.add(filtrateBean);
                childrenList = new ArrayList<>();
                filtrateBean.setChildren(childrenList);
                filtrateBean.setTitleName("审核状态");
                filtrateBean.setKey("auditingStatus");
                childrenList.add(new FiltrateBean.Children("待审核", "1"));
                childrenList.add(new FiltrateBean.Children("审核通过", "2"));
                childrenList.add(new FiltrateBean.Children("被驳回", "3"));
        
                filtrateBean = new FiltrateBean();
                dictList.add(filtrateBean);
                childrenList = new ArrayList<>();
                filtrateBean.setChildren(childrenList);
                filtrateBean.setTitleName("城市");
                filtrateBean.setKey("cityId");
                childrenList.add(new FiltrateBean.Children("青岛", "370200"));
                childrenList.add(new FiltrateBean.Children("西安", "610100"));
                childrenList.add(new FiltrateBean.Children("天津", "120100"));
                childrenList.add(new FiltrateBean.Children("拉萨", "540100"));
    }
}
```
## TeaScreenPopupWindow属性大全
方法名 | 属性
--------- | -------------
setTopView(Boolean bl, int color) | 设置顶部分割线是否显示，以及颜色。默认true,#f3f3f3
setBottomView(Boolean bl, int color) | 设置底部分割线是否显示，以及颜色。默认true,#f3f3f3
setConfirm(String text, int size, int textColor, int color) | 设置确定按钮的文字，字体大小，字体颜色，背景颜色。默认“确定”，14，#ffffff，#0aa666
setReset(String text, int size, int textColor, int color) | 设置重置按钮的文字，字体大小，字体颜色，背景颜色。默认“重置”，#000000，#ffffff
setAlpha(int mAlpha) | 设置阴影层的透明度 默认是0.5f
setTitleColor(int color) | 设置title的字体颜色,默认#000000
setTitleSize(int size) | 设置title的字体大小,默认14
setRadius(int radius) | 设置item圆角大小，默认12
setStrokeWidth(int width) | 设置item边框粗细，默认2
setStrokeColor(int color) | 设置item边框颜色，默认#0aa666
setBoxWidth(int width) | 设置item宽度，默认是200dp
setBoxHeight(int height) | 设置item高度，默认是WRAP_CONTENT
setChecked(String color) | 设置item选中时的颜色，默认#0aa666
setEnabled(String color) | 设置item未选中时的颜色，默认#000000
setBoxSize(int size) | 设置item字体大小，默认13
setSingle(boolean bl) | 设置是否开启单选，默认单选
reset() | 显示控件时数据重置
build() | 参数设置完毕，一定要build一下

## 联系QQ：961606042
