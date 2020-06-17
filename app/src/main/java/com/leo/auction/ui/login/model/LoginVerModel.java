package com.leo.auction.ui.login.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * =================居安思危，马到功成====================
 * 项目名称: SuperWareHouse_Android
 * 包    名： com.tjl.super_warehouse.ui.mine.model
 * 作    者： Leo---我心悠悠
 * 时    间： 2020/5/15
 * 描    述： 手势验证
 * 修    改：
 * ===================又是充满希望的一天====================
 */
public class LoginVerModel implements Parcelable {


    /**
     * sessionId : 01meJRDQhjKvuhvkU5cu47-PLrEDWAetNDIJeZZGnP-rz0b29XVLsP7w1np2ytPyFCY5p2QgcwXT8ElNhnUS_x6P0gAr0O8tIId3dMSMVprRhgj08S1T5hVVoDayX289iJgRs6Fr05S96tAEyyAL3SSJz7-Cu27ygwupux7LYzDy5kJAr_j9XJ-iPPVAq_1yghoGakhcZVIgIqfv3qntnOmQ
     * token : FFFF0N00000000009108:1589521565592:0.33958133475667496
     * scene : nc_message_h5
     * sign : 05XqrtZ0EaFgmmqIQes-s-CBAyL5Yfpsr5LuvV4FALTA6OWNfDKpYoNOYeQFfp6R26vOypZVRljSU8MrMKdx6d-ioUb28XOjyZWnWGmWgPFCpg7wEoevct_fcCivXMzeYLMzNeBzatbuQtMEsEQ-BZuy3JIXaZEKR3e6sJhVNzKZIbCrBmROhJcxD8VF5IzL_-K5yTOWPAwyetBRTqXB-rN3G8xvKoRISCQ1Tfq_W9l9-PuqhHIKg-Xq1epdkhFh7mExhygtd82qIZTMnCDt79IXSrgTq03QBvhovU6i6RPL6RNE7ANnnin9jJgZD4wnAYd3fC9SGyBxOnJSWtXjDC0cvSjBnKjUf2wZDFYWAx73OwXi1SOoaitVi3gDDaiLwMb1l3Vh38XJVsDT_Qs711dh7lj5PMEC7aMIdwlPReGam2LfNJKxerABPkfED2IVBauNBDb4YqL6wp1cs4wvYdALnRAy_UrxLKNiW7f4hhNu8
     */

    private String sessionId;
    private String token;
    private String scene;
    private String sign;

    public LoginVerModel() {
    }

    public LoginVerModel(String sessionId, String token, String scene, String sign) {
        this.sessionId = sessionId;
        this.token = token;
        this.scene = scene;
        this.sign = sign;
    }

    protected LoginVerModel(Parcel in) {
        sessionId = in.readString();
        token = in.readString();
        scene = in.readString();
        sign = in.readString();
    }

    public static final Creator<LoginVerModel> CREATOR = new Creator<LoginVerModel>() {
        @Override
        public LoginVerModel createFromParcel(Parcel in) {
            return new LoginVerModel(in);
        }

        @Override
        public LoginVerModel[] newArray(int size) {
            return new LoginVerModel[size];
        }
    };

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sessionId);
        dest.writeString(token);
        dest.writeString(scene);
        dest.writeString(sign);
    }
}
