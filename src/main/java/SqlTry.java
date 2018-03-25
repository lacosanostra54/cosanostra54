import java.lang.management.OperatingSystemMXBean;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Optional;
import java.util.stream.Stream;

import helpers.instagram.Instagram;
import models.user.User;
import models.user.UsersMapper;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SqlTry
{

    public static void main(String[] args)
    {
        Instagram instagram = new Instagram();
        System.out.println(instagram.isFollower(328072532L, 1066779730L));
    }
}