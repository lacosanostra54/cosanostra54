package helpers.Params;

public class Params
{
    private Integer vkAppid;
    private String vkAccessToken;
    private String qiwiAcessToken;
    private String telegramBotUsername;
    private String telegramBotToken;

    public Integer getVkAppid()
    {
        return vkAppid;
    }

    public String getQiwiAcessToken()
    {
        return qiwiAcessToken;
    }

    public String getTelegramBotToken()
    {
        return telegramBotToken;
    }

    public String getTelegramBotUsername()
    {
        return telegramBotUsername;
    }

    public String getVkAccessToken()
    {
        return vkAccessToken;
    }

    public void setQiwiAcessToken(String qiwiAcessToken)
    {
        this.qiwiAcessToken = qiwiAcessToken;
    }

    public void setTelegramBotToken(String telegramBotToken)
    {
        this.telegramBotToken = telegramBotToken;
    }

    public void setTelegramBotUsername(String telegramBotUsername)
    {
        this.telegramBotUsername = telegramBotUsername;
    }

    public void setVkAccessToken(String vkAccessToken)
    {
        this.vkAccessToken = vkAccessToken;
    }

    public void setVkAppid(Integer vkAppid)
    {
        this.vkAppid = vkAppid;
    }

}
