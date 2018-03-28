package models.instaAdRealtionship;

import java.lang.invoke.LambdaConversionException;

public class InstaAdRelationship
{
    Long followerId;
    Long followedId;
    Integer currentDay;
    Long lastCheckTime;

    public InstaAdRelationship(Long newFollowerId, Long newFollowedId, Integer newCurrentDay, Long newCheckTime)
    {
        followerId = newFollowerId;
        followedId = newFollowedId;
        currentDay = newCurrentDay;
        lastCheckTime = newCheckTime;
    }

    public Long getLastCheckTime()
    {
        return lastCheckTime;
    }

    public Integer getCurrentDay()
    {
        return currentDay;
    }

    public void setLastCheckTime(Long lastCheckTime)
    {
        this.lastCheckTime = lastCheckTime;
    }

    public void setCurrentDay(Integer currentDay)
    {
        this.currentDay = currentDay;
    }

    public Long getFollowedId()
    {
        return followedId;
    }

    public Long getFollowerId()
    {
        return followerId;
    }

    public void setFollowedId(Long followedId)
    {
        this.followedId = followedId;
    }

    public void setFollowerId(Long followerId)
    {
        this.followerId = followerId;
    }

}
