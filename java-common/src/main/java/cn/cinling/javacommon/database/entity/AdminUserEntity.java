package cn.cinling.javacommon.database.entity;

public class AdminUserEntity {
    public AdminUserEntity() {

    }

    public AdminUserEntity(String account, String password, String nickname, Integer createTime) {
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    private Integer id;
    private String account;
    private String password;
    private String nickname;
    private Integer createTime;
}
