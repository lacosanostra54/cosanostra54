package models.user;

public class User
{
    long telegramId;
    String userName;
    int viewId;
    String qiwiPhoneNumber;
    Integer vkId;
    String vkShortName;
    Double money;
    Long instagramId;

    public User(long newTelegramId, String newUserName, int newViewId)
    {
        telegramId = newTelegramId;
        userName = newUserName;
        viewId = newViewId;
        money = 0.0;
    }

    public Long getInstagramId()
    {
        return instagramId;
    }

    public void setInstagramId(Long instagramId)
    {
        this.instagramId = instagramId;
    }

    public Double getMoney()
    {
        return money;
    }

    public void setMoney(Double money)
    {
        this.money = money;
    }

    public String getQiwiPhoneNumber()
    {
        return qiwiPhoneNumber;
    }

    public void setQiwiPhoneNumber(String qiwiPhoneNumber)
    {
        this.qiwiPhoneNumber = qiwiPhoneNumber;
    }

    public Integer getVkId()
    {
        return vkId;
    }

    public void setVkId(Integer vkId)
    {
        this.vkId = vkId;
    }

    public String getVkShortName()
    {
        return vkShortName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setVkShortName(String vkShortName)
    {
        this.vkShortName = vkShortName;
    }

    public String getUserName()
    {
        return userName;
    }

    public long getTelegramId()
    {
        return telegramId;
    }

    public int getViewId()
    {
        return viewId;
    }

    public void setViewId(int viewId)
    {
        this.viewId = viewId;
    }
}
