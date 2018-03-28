package models.vkAdRelationship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VkAdRelationshipMapper
{

    private Connection connection;

    public VkAdRelationshipMapper(Connection new_connection)
    {
        connection = new_connection;
    }

    public void create(VkAdRelationship adRelationship)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO vk_ad_relationships(vk_group_id, vk_user_id, current_day, last_check_time) " +
                            "VALUES(?, ?, ?, ?)");
            statement.setInt(1, adRelationship.vkGroupId);
            statement.setInt(2, adRelationship.vkUserId);
            statement.setInt(3, adRelationship.currentDay);
            statement.setLong(4, adRelationship.lastCheckTime);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public void update(VkAdRelationship adRelationship)
    {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE vk_ad_relationships " +
                        "SET current_day = ?, last_check_time = ?" +
                        "WHERE vk_user_id = ? AND vk_group_id = ?"))
        {
            statement.setInt(1, adRelationship.currentDay);
            statement.setLong(2, adRelationship.lastCheckTime);
            statement.setInt(3, adRelationship.vkUserId);
            statement.setInt(4, adRelationship.vkGroupId);
            statement.executeUpdate();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public VkAdRelationship findByIds(Integer vkUserId, Integer vkGroupId)
    {
        VkAdRelationship answer = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM vk_ad_relationships " +
                "WHERE vk_user_id = ? AND vk_group_id = ?"))
        {
            statement.setLong(1, vkUserId);
            statement.setInt(2, vkGroupId);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    answer = new VkAdRelationship(vkUserId, vkGroupId,
                            resultSet.getInt("current_day"), resultSet.getLong("last_check_time"));
                }
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return answer;
    }
}
