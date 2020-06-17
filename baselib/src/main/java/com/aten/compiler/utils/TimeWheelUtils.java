package com.aten.compiler.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.aten.compiler.R;
import com.aten.compiler.widget.customerDialog.BottomDialog;
import com.aten.compiler.widget.customerDialog.BottomDialogUtils;
import com.aten.compiler.widget.wheel.WheelView;
import com.aten.compiler.widget.wheel.ex.DayWheelView;
import com.aten.compiler.widget.wheel.ex.MonthWheelView;
import com.aten.compiler.widget.wheel.ex.YearWheelView;
import com.aten.compiler.widget.wheel.pickView.DatePickerView;

import java.util.Calendar;
import java.util.Date;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.widget.customerDialog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/10/29
 * 描    述：时间选择wheel
 * ================================================
 */
public class TimeWheelUtils {
    private BottomDialogUtils bottomDialogUtils;
    private String dateInfo = "";
    private boolean showYear = true, YearDirectionFactor = true, showMouth = true,
            MouthDirectionFactor = false, showDay = true, DayDirectionFactor = true;//是否显示年，月，日的时间 默认显示

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static TimeWheelUtils instance = new TimeWheelUtils();
    }

    /**
     * 私有的构造函数
     */
    private TimeWheelUtils() {
    }

    public static TimeWheelUtils getInstance() {
        return TimeWheelUtils.SingletonHolder.instance;
    }


    //是否显示日的时间
    public void isShowDay(boolean showYear, boolean YearDirectionFactor, boolean showMouth,
                          boolean MouthDirectionFactor, boolean showDay, boolean DayDirectionFactor) {
        this.showYear = showYear;
        this.YearDirectionFactor = YearDirectionFactor;
        this.showMouth = showMouth;
        this.MouthDirectionFactor = MouthDirectionFactor;
        this.showDay = showDay;
        this.DayDirectionFactor = DayDirectionFactor;
    }

    public void showTimeWheel(Context context,String title, final TimeWheelClickListener timeWheelClickListener) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
        dateInfo = "";
        if (showYear) {
            dateInfo += year + "-";
        }

        if (showMouth) {
            dateInfo +=(month + 1>=10)?(month + 1+ "-"):("0"+(month + 1)+ "-");
        }

        if (showDay) {
            dateInfo +=day>=10?(day+ "-"):("0"+day+ "-");
        }

        View timeWheelView = View.inflate(context, R.layout.layout_time_wheel_choose, null);
        DatePickerView dpvTimeWheel = (DatePickerView) timeWheelView.findViewById(R.id.dpv_time_wheel);
        dpvTimeWheel.setVisibleItems(7);
        dpvTimeWheel.setLineSpacing(10f, true);
        dpvTimeWheel.setTextSize(18f, true);
        dpvTimeWheel.setSelectedRectColor(Color.parseColor("#1e1e1e"));
        dpvTimeWheel.setNormalItemTextColor(Color.parseColor("#808080"));
        dpvTimeWheel.setShowLabel(false);//隐藏年月日
        dpvTimeWheel.setTextBoundaryMargin(18, true);
        dpvTimeWheel.setShowDivider(true);
        dpvTimeWheel.setDividerType(WheelView.DIVIDER_TYPE_FILL);
        dpvTimeWheel.setDividerColor(context.getResources().getColor(R.color.line02));
        dpvTimeWheel.setDividerPaddingForWrap(10, true);
        dpvTimeWheel.setDividerHeight(0.5f, true);

        if (showYear) {
            YearWheelView yearWv3 = dpvTimeWheel.getYearWv();
            dpvTimeWheel.showYearItem(View.GONE);
            yearWv3.setIntegerNeedFormat("%d年");
            if (YearDirectionFactor) {
                yearWv3.setCurvedArcDirection(WheelView.CURVED_ARC_DIRECTION_LEFT);
                yearWv3.setCurvedArcDirectionFactor(0.65f);
            } else {
                yearWv3.setCurvedArcDirectionFactor(0f);
            }
        } else {
            dpvTimeWheel.hideYearItem();
        }

        if (showMouth) {
            MonthWheelView monthWv3 = dpvTimeWheel.getMonthWv();
            dpvTimeWheel.showMonthItem(View.GONE);
            monthWv3.setIntegerNeedFormat("%d月");
            if (MouthDirectionFactor) {
                monthWv3.setCurvedArcDirection(WheelView.CURVED_ARC_DIRECTION_LEFT);
                monthWv3.setCurvedArcDirectionFactor(0.65f);
            } else {
                monthWv3.setCurvedArcDirectionFactor(0f);
            }
        } else {
            dpvTimeWheel.hideMonthItem();
        }

        if (showDay) {
            DayWheelView dayWv3 = dpvTimeWheel.getDayWv();
            dpvTimeWheel.showDayItem(View.GONE);
            dayWv3.setIntegerNeedFormat("%02d日");
            if (DayDirectionFactor) {
                dayWv3.setCurvedArcDirection(WheelView.CURVED_ARC_DIRECTION_RIGHT);
                dayWv3.setCurvedArcDirectionFactor(0.65f);
            } else {
                dayWv3.setCurvedArcDirectionFactor(0f);
            }

        } else {
            dpvTimeWheel.hideDayItem();
        }

        dpvTimeWheel.setOnDateSelectedListener(new DatePickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(DatePickerView datePickerView, int year, int month, int day, @Nullable Date date) {
                dateInfo = "";
                if (showYear) {
                    dateInfo += year + "-";
                }

                if (showMouth) {
                    dateInfo +=month>=10?(month+ "-"):("0"+month+ "-");
                }

                if (showDay) {
                    dateInfo +=day>=10?(day+ "-"):("0"+day+ "-");
                }

            }
        });

        bottomDialogUtils = new BottomDialogUtils(context);
        bottomDialogUtils.showBottomDialogDialog(timeWheelView, title, new BottomDialogUtils.BottomClickListener() {
            @Override
            public void onSure(BottomDialog bottomDialog) {
                if (dateInfo.length() >= 1) {
                    timeWheelClickListener.onchooseDate(dateInfo.substring(0, dateInfo.length() - 1));
                }
            }

            @Override
            public void onCancle(BottomDialog bottomDialog) {
            }
        });
    }

    public void dissTimeWheel() {
        bottomDialogUtils.dismissBottomDialogDialog();
    }

    public interface TimeWheelClickListener {
        void onchooseDate(String dateInfo);
    }
}
