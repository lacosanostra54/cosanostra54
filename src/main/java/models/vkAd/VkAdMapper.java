package models.vkAd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VkAdMapper
{
    private Connection connection;

    public VkAdMapper(Connection new_connection)
                                            {
                                               connection = new_connection;
                                                                           }

    public List<VkAd> GetAll()
    {
        List<VkAd> ans = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM vk_ads"))
        {
            try(ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    ans.add(new VkAd(resultSet.getInt("vk_group_id"), resultSet.getInt("count_of_days"),
                            resultSet.getDouble("amount")));
                }
            }
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return ans;
    }

    public VkAd findById(Integer vkGroupId)
    {
        VkAd answer = null;
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM vk_ads " +
                "WHERE vk_group_id = ?"))
        {
            statement.setInt(1, vkGroupId);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    answer = new VkAd(vkGroupId, resultSet.getInt("count_of_days"),
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
