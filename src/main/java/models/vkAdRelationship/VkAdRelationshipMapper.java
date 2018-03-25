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
                    "INSERT INTO vk_ad_relationships(vk_group_id, telegram_id, type) VALUES(?, ?, ?)");
            statement.setInt(1, adRelationship.vkGroupId);
            statement.setLong(2, adRelationship.telegramId);
            statement.setString(3, adRelationship.type);
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
                        "SET type = ?" +
                        "WHERE telegram_id = ? AND vk_group_id = ?"))
        {
            statement.setString(1, adRelationship.type);
            statement.setInt(3, adRelationship.vkGroupId);
            statement.setLong(2, adRelationship.telegramId);
            statement.executeUpdate();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public VkAdRelationship findByIds(Integer vkGroupId, Long telegramId)
    {
        VkAdRelationship answer = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM vk_ad_relationships " +
                "WHERE telegram_id = ? AND vk_group_id = ?"))
        {
            statement.setLong(1, telegramId);
            statement.setInt(2, vkGroupId);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    answer = new VkAdRelationship(resultSet.getLong("telegram_id"),
                            resultSet.getInt("vk_group_id"),
                            resultSet.getString("type"));
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
