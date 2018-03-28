
import com.alibaba.fastjson.JSON;
import helpers.Params.Params;
import helpers.instagram.Instagram;
import helpers.qiwi.Qiwi;
import helpers.vk.VkChecker;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;


public class Main
{

    private static String readLineByLine(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }


    public static void main(String[] args)
    {
        Params params = JSON.parseObject(readLineByLine("params.json"), Params.class);
        ApiContextInitializer.init();
        TelegramBotsApi botApi = new TelegramBotsApi();
        try
        {
            Connection connection = DriverManager.getConnection(params.getPostgreSqlParams().getUrl(),
                    params.getPostgreSqlParams().getUsername(),
                    params.getPostgreSqlParams().getPassword());
            try
            {
                botApi.registerBot(new TelegramBot(connection, new VkChecker(params.getVkAppid(),
                        params.getVkAccessToken()),
                        new Qiwi(params.getQiwiAcessToken()),
                        params.getTelegramBotUsername(),
                        params.getTelegramBotToken(), new Instagram()));
            }
            catch (TelegramApiException e)
            {
                e.printStackTrace();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
