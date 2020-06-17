package com.leo.auction.ui.login.model;


import org.litepal.annotation.Encrypt;
import org.litepal.crud.LitePalSupport;

/**
 * ================================================

 * ================================================
 */
public class UserInfoModel extends LitePalSupport {
    private int id;
    @Encrypt(algorithm = AES)
    private String gfsgshdkdkko;//用户昵称
    @Encrypt(algorithm = AES)
    private String mmnfjedll;//用户昵称
    @Encrypt(algorithm = AES)
    private String sklelldsssssof;//token
    @Encrypt(algorithm = AES)
    private String dkks;//支付密码  //是否有支付密码, 1-有 0-没有
    @Encrypt(algorithm = AES)
    private String llood;//有值就是免密
    @Encrypt(algorithm = AES)
    private String sswwwwweeee;//"身份 0 用户 1 供货商 2 代理"，
    @Encrypt(algorithm = AES)
    private String sssdfght;//可用的余额
    @Encrypt(algorithm = AES)
    private String fgghhjjkkw;//商户标识
    @Encrypt(algorithm = AES)
    private String dfgqqqhttw;//商户标识
    @Encrypt(algorithm = AES)
    private String dadadgfgttjh;//商户标识
    @Encrypt(algorithm = AES)
    private String qwqertytggg;//固定我的为商家,有值就是开通了
    @Encrypt(algorithm = AES)
    private int sdfgt;//待付款数量(买家)
    @Encrypt(algorithm = AES)
    private int aaaadd;//待收货数量(买家)
    @Encrypt(algorithm = AES)
    private int hjkloiu;//待发货数量(买家)
    @Encrypt(algorithm = AES)
    private int aswertyt;//售后数量(买家)
    @Encrypt(algorithm = AES)
    private int asasxccvfw;//待付款数量(卖家)
    @Encrypt(algorithm = AES)
    private int qqwrtyy;//待收货数量(卖家)
    @Encrypt(algorithm = AES)
    private int qweyuiioo;//待发货数量(卖家)
    @Encrypt(algorithm = AES)
    private int lopqwsxzs;//售后数量(卖家)
    @Encrypt(algorithm = AES)
    private String sjidkpllkuju;//消保金
    @Encrypt(algorithm = AES)
    private String sfgtthjjj;//头像
    @Encrypt(algorithm = AES)
    private String fgrgtgt6hj;//店铺二维码
    @Encrypt(algorithm = AES)
    private String sdsdffggyhy;//手机号码
    @Encrypt(algorithm = AES)
    private boolean sasaawq;//企业认证
    @Encrypt(algorithm = AES)
    private boolean fdrrrgll;//是否是合伙人
    @Encrypt(algorithm = AES)
    private String ertghmn;//可用的超级币余额
    @Encrypt(algorithm = AES)
    private boolean ffgghhjkjww;//是否环信注册成功

    public UserInfoModel(){}

    public UserInfoModel(String userId01, String nickname, String token, String payPwd,
                         String balanceExempt, String type, String usableBalance,
                         String supplierId, String shopUri, String userId, String fixedSupplier,
                         int noPayNum, int receiveNum, int sendNum, int serviceNum,
                         int seller_noPayNum, int seller_receiveNum, int seller_sendNum, int seller_serviceNum, String warrantBalance,
                         String headimg, String supplierQrcode, String phone, boolean companyAuth, boolean partner, String coinNum, boolean dialogConnect) {
        this.gfsgshdkdkko=userId01;
        this.mmnfjedll = nickname;
        this.sklelldsssssof = token;
        this.dkks = payPwd;
        this.llood = balanceExempt;
        this.sswwwwweeee = type;
        this.sssdfght = usableBalance;
        this.fgghhjjkkw = supplierId;
        this.dfgqqqhttw=shopUri;
        this.dadadgfgttjh=userId;
        this.qwqertytggg=fixedSupplier;
        this.sdfgt=noPayNum;
        this.aaaadd=receiveNum;
        this.hjkloiu=sendNum;
        this.aswertyt=serviceNum;
        this.asasxccvfw=seller_noPayNum;
        this.qqwrtyy=seller_receiveNum;
        this.qweyuiioo=seller_sendNum;
        this.lopqwsxzs=seller_serviceNum;
        this.sjidkpllkuju=warrantBalance;
        this.sfgtthjjj=headimg;
        this.fgrgtgt6hj=supplierQrcode;
        this.sdsdffggyhy=phone;
        this.sasaawq=companyAuth;
        this.fdrrrgll=partner;
        this.ertghmn=coinNum;
        this.ffgghhjkjww=dialogConnect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId01() {
        return gfsgshdkdkko;
    }

    public void setUserId01(String userId01) {
        this.gfsgshdkdkko = userId01;
    }

    public String getNickname() {
        return mmnfjedll;
    }

    public void setNickname(String nickname) {
        this.mmnfjedll = nickname;
    }

    public String getToken() {
        return sklelldsssssof;
    }

    public void setToken(String token) {
        this.sklelldsssssof = token;
    }

    public String getPayPwd() {
        return dkks;
    }

    public void setPayPwd(String payPwd) {
        this.dkks = payPwd;
    }

    public String getBalanceExempt() {
        return llood;
    }

    public void setBalanceExempt(String balanceExempt) {
        this.llood = balanceExempt;
    }

    public String getType() {
        return sswwwwweeee;
    }

    public void setType(String type) {
        this.sswwwwweeee = type;
    }

    public String getUsableBalance() {
        return sssdfght;
    }

    public void setUsableBalance(String usableBalance) {
        this.sssdfght = usableBalance;
    }

    public String getSupplierId() {
        return fgghhjjkkw;
    }

    public void setSupplierId(String supplierId) {
        this.fgghhjjkkw = supplierId;
    }

    public String getShopUri() {
        return dfgqqqhttw;
    }

    public void setShopUri(String shopUri) {
        this.dfgqqqhttw = shopUri;
    }

    public String getUserId() {
        return dadadgfgttjh;
    }

    public void setUserId(String userId) {
        this.dadadgfgttjh = userId;
    }

    public String getFixedSupplier() {
        return qwqertytggg;
    }

    public void setFixedSupplier(String fixedSupplier) {
        this.qwqertytggg = fixedSupplier;
    }

    public int getNoPayNum() {
        return sdfgt;
    }

    public void setNoPayNum(int noPayNum) {
        this.sdfgt = noPayNum;
    }

    public int getReceiveNum() {
        return aaaadd;
    }

    public void setReceiveNum(int receiveNum) {
        this.aaaadd = receiveNum;
    }

    public int getSendNum() {
        return hjkloiu;
    }

    public void setSendNum(int sendNum) {
        this.hjkloiu = sendNum;
    }

    public int getServiceNum() {
        return aswertyt;
    }

    public void setServiceNum(int serviceNum) {
        this.aswertyt = serviceNum;
    }

    public int getSeller_noPayNum() {
        return asasxccvfw;
    }

    public void setSeller_noPayNum(int seller_noPayNum) {
        this.asasxccvfw = seller_noPayNum;
    }

    public int getSeller_receiveNum() {
        return qqwrtyy;
    }

    public void setSeller_receiveNum(int seller_receiveNum) {
        this.qqwrtyy = seller_receiveNum;
    }

    public int getSeller_sendNum() {
        return qweyuiioo;
    }

    public void setSeller_sendNum(int seller_sendNum) {
        this.qweyuiioo = seller_sendNum;
    }

    public int getSeller_serviceNum() {
        return lopqwsxzs;
    }

    public void setSeller_serviceNum(int seller_serviceNum) {
        this.lopqwsxzs = seller_serviceNum;
    }

    public String getWarrantBalance() {
        return sjidkpllkuju;
    }

    public void setWarrantBalance(String warrantBalance) {
        this.sjidkpllkuju = warrantBalance;
    }

    public String getHeadimg() {
        return sfgtthjjj;
    }

    public void setHeadimg(String headimg) {
        this.sfgtthjjj = headimg;
    }

    public String getSupplierQrcode() {
        return fgrgtgt6hj;
    }

    public void setSupplierQrcode(String supplierQrcode) {
        this.fgrgtgt6hj = supplierQrcode;
    }

    public String getPhone() {
        return sdsdffggyhy;
    }

    public void setPhone(String phone) {
        this.sdsdffggyhy = phone;
    }

    public boolean isCompanyAuth() {
        return sasaawq;
    }

    public void setCompanyAuth(boolean companyAuth) {
        this.sasaawq = companyAuth;
    }

    public boolean getPartner() {
        return fdrrrgll;
    }

    public void setPartner(boolean partner) {
        this.fdrrrgll = partner;
    }

    public String getCoinNum() {
        return ertghmn;
    }

    public void setCoinNum(String coinNum) {
        this.ertghmn = coinNum;
    }

    public boolean isDialogConnect() {
        return ffgghhjkjww;
    }

    public void setDialogConnect(boolean dialogConnect) {
        this.ffgghhjkjww = dialogConnect;
    }
}
