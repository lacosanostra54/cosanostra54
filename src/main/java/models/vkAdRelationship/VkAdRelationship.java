package models.vkAdRelationship;

public class VkAdRelationship
{
    Integer vkUserId;
    Integer vkGroupId;
    Integer currentDay;
    Long lastCheckTime;

    public VkAdRelationship(Integer newVkUserId, Integer newVkAdId, Integer newCurrentDay, long newCheckTime)
    {
        vkUserId = newVkUserId;
        vkGroupId = newVkAdId;
        currentDay = newCurrentDay;
        lastCheckTime = newCheckTime;
    }

    public Long getLastCheckTime()
    {
        return lastCheckTime;
    }

    public void setLastCheckTime(Long lastCheckTime)
    {
        this.lastCheckTime = lastCheckTime;
    }

    public Integer getCurrentDay()
    {
        return currentDay;
    }

    public void setCurrentDay(Integer currentDay)
    {
        this.currentDay = currentDay;
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
