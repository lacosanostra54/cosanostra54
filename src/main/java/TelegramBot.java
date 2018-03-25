import helpers.instagram.Instagram;
import helpers.qiwi.Qiwi;
import helpers.vk.VkChecker;
import models.Mappers;
import models.instaAd.InstaAd;
import models.instaAdRealtionship.InstaAdRelationship;
import models.user.User;
import models.vkAd.VkAd;
import models.vkAdRelationship.VkAdRelationship;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import views.Views;

import java.sql.Connection;
import java.util.Optional;


public class TelegramBot extends TelegramLongPollingBot
{
    private Mappers mappers;
    private Views views;
    private VkChecker vkChecker;
    private String botUsername;
    private String botToken;
    private Instagram instagram;

    TelegramBot(Connection connection, VkChecker newVkChecker, Qiwi newQiwi, String newBotUsername,  String newBotToken,
                Instagram newInstagram)
    {
        mappers = new Mappers(connection);
        vkChecker = newVkChecker;
        views = new Views(mappers, vkChecker, newQiwi, newInstagram);
        botToken = newBotToken;
        botUsername = newBotUsername;
        instagram = newInstagram;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        User user;
        SendMessage message;
        if (update.getCallbackQuery() == null)
        {
            long telegramId = update.getMessage().getChat().getId();
            Optional<User> userOptional = mappers.getUsersMapper().findByTelegramId(telegramId);
            if (userOptional.isPresent())
            {
                user = userOptional.get();
                message = views.getById(user.getViewId()).handleUpdate(update, user);
            }
            else
            {
                Chat chat = update.getMessage().getChat();
                user = new User(chat.getId(), chat.getUserName(), views.getMainMenuId());
                mappers.getUsersMapper().create(user);
                message = new SendMessage();
                message.setChatId(user.getTelegramId());
                message.setText("Дорова");
            }
        }
        else
        {
            long telegramId = update.getCallbackQuery().getFrom().getId();
            Optional<User> userOptional = mappers.getUsersMapper().findByTelegramId(telegramId);
            if (userOptional.isPresent())
                user = userOptional.get();
            else
                return;
            message = new SendMessage();
            message.setChatId(user.getTelegramId());
            if (update.getCallbackQuery().getData().toCharArray()[0] == 'V')
            {
                Integer vkGroupId = Integer.valueOf(update.getCallbackQuery().getData().substring(1));
                VkAd vkAd = mappers.getVkAdMapper().findById(vkGroupId);
                VkAdRelationship relationship = mappers.getVkAdRelationshipMapper().findByIds(vkAd.getVkGroupId(), user.getTelegramId());
                if (relationship == null || relationship.getType().equals("Complete"))
                    return;
                if (vkChecker.isMember(user.getVkId(), vkAd.getVkGroupId().toString()))
                {
                    relationship.setType("Complete");
                    mappers.getVkAdRelationshipMapper().update(relationship);
                    user.setMoney(user.getMoney() + vkAd.getAmount());
                    message.setText("Поздравляю вы выпонили задание");
                }
                else
                {
                    message.setText("Вы не подписались");
                }
            }
            else
            {
                Long FollowedId = Long.valueOf(update.getCallbackQuery().getData().substring(1));
                InstaAd instaAd = mappers.getInstaAdMapper().findById(FollowedId);
                InstaAdRelationship relationship = mappers.getInstaAdRelationshipMapper()
                        .findByIds(user.getInstagramId(), instaAd.getInstaId());
                if (relationship == null || relationship.getType().equals("Complete"))
                    return;
                if (instagram.isFollower(user.getInstagramId(), instaAd.getInstaId()))
                {
                    relationship.setType("Complete");
                    mappers.getInstaAdRelationshipMapper().update(relationship);
                    user.setMoney(user.getMoney() + instaAd.getAmount());
                    message.setText("Поздравляю вы выпонили задание");
                }
                else
                {
                    message.setText("Вы не подписались");
                }
            }
        }
        try
        {
            if (message.getReplyMarkup() == null)
                message.setReplyMarkup(views.getById(user.getViewId()).generateMarkup(user));
            execute(message);
        }
        catch (TelegramApiException exception)
        {
            exception.printStackTrace();
        }
        mappers.getUsersMapper().update(user);
    }

    @Override
    public String getBotUsername()
    {
        //
        return botUsername;
    }

    @Override
    public String getBotToken()
    {
       //
        return botToken;
    }
}