package models.vkAdRelationship;

public class VkAdRelationship
{
    Integer vkUserId;
    Integer vkGroupId;
    String type;

    public VkAdRelationship(Integer newVkUserId, Integer newVkAdId, String newType)
    {
        vkUserId = newVkUserId;
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

    public Integer getVkUserId()
    {
        return vkUserId;
    }

    public void setVkUserId(Integer vkUserId)
    {
        this.vkUserId = vkUserId;
    }

    public void setVkGroupId(Integer vkGroupId)
    {
        this.vkGroupId = vkGroupId;
    }
}
