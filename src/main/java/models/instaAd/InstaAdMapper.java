package models.instaAd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstaAdMapper
{
    private Connection connection;

    public InstaAdMapper(Connection new_connection)
    {
        connection = new_connection;
    }

    public List<InstaAd> GetAll()
    {
        List<InstaAd> ans = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM instagram_ads"))
        {
            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    ans.add(new InstaAd(resultSet.getLong("instagram_id"), resultSet.getString("url"),
                            resultSet.getInt("count_of_days"), resultSet.getDouble("amount")));
                }
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return ans;
    }

    public InstaAd findById(Long instaId)
    {
        InstaAd answer = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM instagram_ads " +
                "WHERE instagram_id = ?"))
        {
            statement.setLong(1, instaId);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    answer = new InstaAd(instaId, resultSet.getString("url"), resultSet.getInt("count_of_days"),
                            resultSet.getDouble("amount"));
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

