package com.leo.auction.model;

import java.io.Serializable;

/**
 * Created by Leo on 2017/4/16.
 *  首页的轮播图 公告
 */

public class NoticeList implements Serializable{




    private String content;
    private String contentTxt;

    private String createdDate;
    private String createdUser;

    private int id;

    private String image;

    private String lastModifiedDate;

    private String lastModifiedUser;

    private String link;

    private int status;

    private String theme;

    private int type;

    public boolean isShowAll = true;


    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setCreatedDate(String createdDate){
        this.createdDate = createdDate;
    }
    public String getCreatedDate(){
        return this.createdDate;
    }
    public void setCreatedUser(String createdUser){
        this.createdUser = createdUser;
    }
    public String getCreatedUser(){
        return this.createdUser;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setLastModifiedDate(String lastModifiedDate){
        this.lastModifiedDate = lastModifiedDate;
    }
    public String getLastModifiedDate(){
        return this.lastModifiedDate;
    }
    public void setLastModifiedUser(String lastModifiedUser){
        this.lastModifiedUser = lastModifiedUser;
    }
    public String getLastModifiedUser(){
        return this.lastModifiedUser;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setTheme(String theme){
        this.theme = theme;
    }
    public String getTheme(){
        return this.theme;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }

    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }



    @Override
    public String toString() {
        return "NoticeList{" +
                "content='" + content + '\'' +
                ", contentTxt='" + contentTxt + '\'' +
                ", createdDate=" + createdDate +
                ", createdUser='" + createdUser + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                ", link='" + link + '\'' +
                ", status=" + status +
                ", theme='" + theme + '\'' +
                ", type=" + type +
                '}';
    }
}
