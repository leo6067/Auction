package com.leo.auction.mvp;


/**
 * Created by Leo on 2019/4/9.
 */

public interface LoginMvpContract {  //控制器，M层 和 V层 便于方法查看


    public interface LoginModel extends BaseModel {    //逻辑数据操作。提供方法，这边不实现
        void login(String name, String password);
    }




    public interface LoginView extends BaseView <LoginModel> {
        String getUserName();

        String getUserPwd();

        void refresh();

    }




}
