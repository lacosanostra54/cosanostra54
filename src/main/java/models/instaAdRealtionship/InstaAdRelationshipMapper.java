package models.instaAdRealtionship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstaAdRelationshipMapper
{
    private Connection connection;

    public InstaAdRelationshipMapper(Connection new_connection)
    {
        connection = new_connection;
    }

    public void create(InstaAdRelationship adRelationship)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO instagram_ad_relationships(follower_id, followed_id, type) VALUES(?, ?, ?)");
            statement.setLong(1, adRelationship.followerId);
            statement.setLong(2, adRelationship.followedId);
            statement.setString(3, adRelationship.type);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public void update(InstaAdRelationship adRelationship)
    {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE instagram_ad_relationships " +
                        "SET type = ?" +
                        "WHERE follower_id = ? AND followed_id = ?"))
        {
            statement.setString(1, adRelationship.type);
            statement.setLong(2, adRelationship.followerId);
            statement.setLong(3, adRelationship.followedId);
            statement.executeUpdate();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public InstaAdRelationship findByIds(Long followerId, Long followedId)
    {
        InstaAdRelationship answer = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM instagram_ad_relationships " +
                "WHERE follower_id = ? AND followed_id = ?"))
        {
            statement.setLong(1, followerId);
            statement.setLong(2, followedId);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    answer = new InstaAdRelationship(followerId, followedId, resultSet.getString("type"));
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
