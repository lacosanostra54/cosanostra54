package models.user;
import java.sql.*;
import java.util.Optional;

public class UsersMapper
{
    private Connection connection;

    public UsersMapper(Connection new_connection)
    {
        connection = new_connection;
    }

    public void create(User user)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(user_name, telegram_id, view_id, money) VALUES(?, ?, ?, ?)");
            statement.setString(1, user.userName);
            statement.setLong(2, user.telegramId);
            statement.setInt(3, user.viewId);
            statement.setDouble(4, user.money);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public void update(User user)
    {
        try(PreparedStatement statement = connection.prepareStatement(
                        "UPDATE users SET user_name = ?, view_id = ?, vk_id = ?, " +
                                "vk_short_name = ?, qiwi_phone_number = ?, money = ?, instagram_id = ?" +
                                "WHERE telegram_id = ?"))
        {
            statement.setString(1, user.userName);
            statement.setInt(2, user.viewId);
            if (user.vkId != null)
                statement.setInt(3, user.vkId);
            else
                statement.setNull(3, 3);
            if (user.vkShortName != null)
                statement.setString(4, user.vkShortName);
            else
                statement.setNull(4, 4);
            if (user.qiwiPhoneNumber != null)
                statement.setString(5, user.qiwiPhoneNumber);
            else
                statement.setNull(5, 5);
            statement.setDouble(6, user.money);
            if (user.instagramId != null)
                statement.setLong(7, user.instagramId);
            else
                statement.setNull(7, 7);
            statement.setLong(8, user.telegramId);
            statement.executeUpdate();
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
    }

    public Optional<User> findByTelegramId(long telegramId)
    {
        Optional<User> answer = Optional.empty();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE telegram_id = ?"))
        {
            statement.setLong(1, telegramId);
            try(ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    answer = Optional.of(new User(resultSet.getLong("telegram_id"),
                            resultSet.getString("user_name"),
                            resultSet.getInt("view_id")));
                    String qiwiPhoneNumber = resultSet.getString("qiwi_phone_number");
                    answer.get().setQiwiPhoneNumber(qiwiPhoneNumber);
                    Integer vkId = resultSet.getInt("vk_id");
                    if (vkId != 0)
                        answer.get().setVkId(vkId);
                    String vkShortName = resultSet.getString("vk_short_name");
                    answer.get().setVkShortName(vkShortName);
                    Double money = resultSet.getDouble("money");
                    Long instagramId = resultSet.getLong("instagram_id");
                    if (instagramId != 0)
                        answer.get().setInstagramId(instagramId);
                    answer.get().setMoney(money);
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
