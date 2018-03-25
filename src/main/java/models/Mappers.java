package models;

import models.instaAd.InstaAd;
import models.instaAdRealtionship.InstaAdRelationship;
import models.instaAdRealtionship.InstaAdRelationshipMapper;
import models.user.UsersMapper;
import models.instaAd.InstaAdMapper;
import models.vkAd.VkAdMapper;
import models.vkAdRelationship.VkAdRelationship;
import models.vkAdRelationship.VkAdRelationshipMapper;

import java.sql.Connection;

public class Mappers
{
    private UsersMapper usersMapper;
    private VkAdMapper vkAdMapper;
    private VkAdRelationshipMapper vkAdRelationshipMapper;
    private InstaAdMapper instaAdMapper;
    private InstaAdRelationshipMapper instaAdRelationshipMapper;

    public Mappers(Connection connection)
    {
        usersMapper = new UsersMapper(connection);
        vkAdMapper = new VkAdMapper(connection);
        vkAdRelationshipMapper = new VkAdRelationshipMapper(connection);
        instaAdMapper = new InstaAdMapper(connection);
        instaAdRelationshipMapper = new InstaAdRelationshipMapper(connection);
    }

    public InstaAdMapper getInstaAdMapper()
    {
        return instaAdMapper;
    }

    public InstaAdRelationshipMapper getInstaAdRelationshipMapper()
    {
        return instaAdRelationshipMapper;
    }

    public UsersMapper getUsersMapper()
    {
        return usersMapper;
    }

    public VkAdMapper getVkAdMapper()
    {
        return vkAdMapper;
    }

    public VkAdRelationshipMapper getVkAdRelationshipMapper()
    {
        return vkAdRelationshipMapper;
    }
}
