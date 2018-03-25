package models.vkAdRelationship;

public class VkAdRelationship
{
    Long telegramId;
    Integer vkGroupId;
    String type;

    public VkAdRelationship(Long newTelegramId, Integer newVkAdId, String newType)
    {
        telegramId = newTelegramId;
        vkGroupId = newVkAdId;
        type = newType;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Integer getVkGroupId()
    {
        return vkGroupId;
    }

    public Long getTelegramId()
    {
        return telegramId;
    }

    public void setTelegramId(Long telegramId)
    {
        this.telegramId = telegramId;
    }

    public void setVkGroupId(Integer vkGroupId)
    {
        this.vkGroupId = vkGroupId;
    }
}
