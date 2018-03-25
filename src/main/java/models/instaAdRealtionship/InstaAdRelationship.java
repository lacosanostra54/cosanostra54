package models.instaAdRealtionship;

public class InstaAdRelationship
{
    Long followerId;
    Long followedId;
    String type;

    public InstaAdRelationship(Long newFollowerId, Long newFollowedId, String newType)
    {
        followerId = newFollowerId;
        followedId = newFollowedId;
        type = newType;
    }

    public String getType()
    {
        return type;
    }

    public Long getFollowedId()
    {
        return followedId;
    }

    public Long getFollowerId()
    {
        return followerId;
    }

    public void setType(String type)
    {
        this.type = type;
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
