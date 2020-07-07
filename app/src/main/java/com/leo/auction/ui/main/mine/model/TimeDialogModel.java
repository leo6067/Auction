package com.leo.auction.ui.main.mine.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.mine.model
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/4
 * 描    述： 截拍时间适配器专用
 * 修    改：
 * ===============================================
 */
public class TimeDialogModel  implements MultiItemEntity {

    private String delayText;
    private int delayTime;
    private int interceptId;
    private int type;
    private String typeName;

    /**
     * showText : 12:00
     * timeNode : 43200
     * timeNodeId : 6
     */

    private String showText;
    private int timeNode;
    private int timeNodeId;


    private int mItemType;

    private boolean select;

    private String timeType;  //快速截拍-quick  今天-today 明天-tomorrow 后天-after_tomorrow

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getDelayText() {
        return delayText;
    }

    public void setDelayText(String delayText) {
        this.delayText = delayText;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public int getInterceptId() {
        return interceptId;
    }

    public void setInterceptId(int interceptId) {
        this.interceptId = interceptId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getShowText() {
        return showText;
    }

    public void setShowText(String showText) {
        this.showText = showText;
    }

    public int getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(int timeNode) {
        this.timeNode = timeNode;
    }

    public int getTimeNodeId() {
        return timeNodeId;
    }

    public void setTimeNodeId(int timeNodeId) {
        this.timeNodeId = timeNodeId;
    }
    public void setItemType(int itemType) {
        mItemType = itemType;
    }



    @Override
    public int getItemType() {
        return mItemType;
    }
}
