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
                    "INSERT INTO instagram_ad_relationships(follower_id, followed_id, current_day, last_check_time) " +
                            "VALUES(?, ?, ?, ?)");
            statement.setLong(1, adRelationship.followerId);
            statement.setLong(2, adRelationship.followedId);
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

    public void update(InstaAdRelationship adRelationship)
    {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE instagram_ad_relationships " +
                        "SET current_day = ?, last_check_time = ?" +
                        "WHERE follower_id = ? AND followed_id = ?"))
        {
            statement.setInt(1, adRelationship.currentDay);
            statement.setLong(2, adRelationship.lastCheckTime);
            statement.setLong(3, adRelationship.followerId);
            statement.setLong(4, adRelationship.followedId);
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
                    answer = new InstaAdRelationship(followerId, followedId,
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
