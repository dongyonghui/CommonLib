package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.printer.DeliveryAddressPrinterHeaderBean;
import com.dyh.commonlib.entity.printer.DeliveryAddressPrinterItemBean;
import com.dyh.commonlib.ui.view.IPrinterView;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.ArrayList;
import java.util.List;

import cn.vip.dw.bluetoothprinterlib.BluetoothPrintManager;
import cn.vip.dw.bluetoothprinterlib.OnPrinterNotifyListener;
import cn.vip.dw.bluetoothprinterlib.velocity.PrinterBean;

import static cn.vip.dw.bluetoothprinterlib.OnPrinterNotifyListener.NotifyMessage.PRINT_FINISH;

/**
 * 项目名称：GouKu_Toolkit
 * 类描述：
 * 修改备注：打印配送单
 */
public class PrinterPresenter extends MVPPresenter<IPrinterView> {

    public PrinterPresenter( IPrinterView view) {
        super(view);
    }

    /**
     * 递归打印配送地址列表项
     *
     * @param printerItemBeanList
     */
    public void printDeliveryList(List<DeliveryAddressPrinterHeaderBean> printerItemBeanList, int printerWidth, int printerNum) {
        if (null == printerItemBeanList || printerItemBeanList.size() == 0) {
            getView().onPrintFinish();
            return;
        }
        //打印头部汇总信息
        String tempPath = "/assets/printer_template_" + printerWidth + "/bd_delivery_order_header.vm";
        PrinterBean printerBean = BluetoothPrintManager.getInstance().getPrinterBean(getActivity(),
                tempPath,
                "printBean", printerItemBeanList.get(0)
                , printerNum);
        BluetoothPrintManager.getInstance()
                .setAutoOpenSettingActivity(true)
                .setNeedShowPrintingDialog(getActivity(), true)
                .setOnPrinterNotifyListener(new OnPrinterNotifyListener() {
                    @Override
                    public void onPrinterNotify(NotifyMessage notifyMessage) {
                        switch (notifyMessage) {
                            case PRINT_FINISH://打印成功后开始打印用户小票
                                //递归打印列表项
                                List<DeliveryAddressPrinterItemBean> tempList = new ArrayList<DeliveryAddressPrinterItemBean>(printerItemBeanList.get(0).printerItemBeanList);
                                printerItemBeanList.remove(0);
                                printItemList(tempList, printerItemBeanList, printerWidth, printerNum);
                                break;
                        }
                    }
                })
                .print(getActivity(), printerBean);
    }

    /**
     * 递归打印列表项
     *
     * @param printerItemBeanList
     */
    private void printItemList(List<DeliveryAddressPrinterItemBean> printerItemBeanList, List<DeliveryAddressPrinterHeaderBean> next2printerHeaderBeanList, int printerWidth, int printerNum) {
        if (null == printerItemBeanList || printerItemBeanList.size() == 0) {
            printDeliveryList(next2printerHeaderBeanList, printerWidth, printerNum);
            return;
        }
        String tempPath = "/assets/printer_template_" + printerWidth + "/bd_delivery_order_person_item.vm";
        PrinterBean printerBean = BluetoothPrintManager.getInstance().getPrinterBean(getActivity(),
                tempPath,
                "printBean", printerItemBeanList.get(0)
                , printerNum);
        BluetoothPrintManager.getInstance()
                .setAutoOpenSettingActivity(true)
                .setNeedShowPrintingDialog(getActivity(), true)
                .setOnPrinterNotifyListener(new OnPrinterNotifyListener() {
                    @Override
                    public void onPrinterNotify(NotifyMessage notifyMessage) {
                        if (notifyMessage == PRINT_FINISH) {//打印成功后开始打印用户小票
                            if (printerItemBeanList.size() > 0) {
                                printerItemBeanList.remove(0);
                            }
                            printItemList(printerItemBeanList, next2printerHeaderBeanList, printerWidth, printerNum);
                        }
                    }
                })
                .print(getActivity(), printerBean);
    }

}
