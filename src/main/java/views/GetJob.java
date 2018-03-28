package views;

import helpers.vk.VkChecker;
import models.Mappers;
import models.instaAd.InstaAd;
import models.instaAdRealtionship.InstaAdRelationship;
import models.user.User;
import models.vkAd.VkAd;
import models.vkAdRelationship.VkAdRelationship;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class GetJob extends View
{

    private VkChecker vkChecker;

    GetJob(Mappers newMappers, Views newViews, VkChecker newVkChecker)
    {
        super(newMappers, newViews);
        vkChecker = newVkChecker;
    }

    @Override
    public ReplyKeyboardMarkup generateMarkup(User user)
    {
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Vk");
        row.add("Instagram");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboard.add(row);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private void generateInstagramJob(SendMessage message, User user)
    {
        if (user.getInstagramId() == null)
        {
            message.setText("Вы не ввели данные Instagram");
        }
        else
        {
            List<InstaAd> InstaAdList = mappers.getInstaAdMapper().GetAll();
            InstaAd cur = null;
            for (InstaAd instaAd : InstaAdList)
            {
                if (mappers.getInstaAdRelationshipMapper()
                        .findByIds(user.getInstagramId(), instaAd.getInstaId()) == null)
                {
                    cur = instaAd;
                    break;
                }
            }
            if (cur == null)
            {
                message.setText("Поздравляю вы выполнили все задания");
            }
            else
            {
                message.setText("Задание");
                try
                {
                    List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
                    buttons.add(new ArrayList<>());
                    buttons.add(new ArrayList<>());
                    InlineKeyboardButton LinkButton = new InlineKeyboardButton();
                    message.setText("Подпишитесь на груупу по ссылке\n Затем нажмие проверить\n");
                    LinkButton.setUrl(cur.getUrl());
                    LinkButton.setText("Сылка на группу");
                    buttons.get(0).add(LinkButton);
                    InlineKeyboardButton CheckButton = new InlineKeyboardButton();
                    CheckButton.setText("Проверить");
                    CheckButton.setCallbackData("I" + cur.getInstaId().toString());
                    InstaAdRelationship relationship = new InstaAdRelationship(user.getInstagramId(), cur.getInstaId(), "Processing");
                    mappers.getInstaAdRelationshipMapper().create(relationship);
                    buttons.get(1).add(CheckButton);
                    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                    keyboardMarkup.setKeyboard(buttons);
                    message.setReplyMarkup(keyboardMarkup);
                }
                catch (Exception e)
                {
                    message.setText("ЫЫЫЫЫЫЫЫЫЫЫЫЫЫ все сломалось");
                    e.printStackTrace();
                }
            }
        }
    }

    private void generateVkJob(SendMessage message, User user)
    {
        if (user.getVkId() == null)
        {
            message.setText("Вы не ввели данные VK");
        }
        else
        {
            List<VkAd> vkAdList = mappers.getVkAdMapper().GetAll();
            VkAd cur = null;
            for (VkAd vkAd : vkAdList)
            {
                if (mappers.getVkAdRelationshipMapper().findByIds(user.getVkId(), vkAd.getVkGroupId()) == null)
                {
                    cur = vkAd;
                    break;
                }
            }
            if (cur == null)
            {
                message.setText("Поздравляю вы выполнили все задания");
            }
            else
            {
                try
                {
                    String groupScreenName = vkChecker.getGroupScreenName(cur.getVkGroupId());
                    List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
                    buttons.add(new ArrayList<>());
                    buttons.add(new ArrayList<>());
                    InlineKeyboardButton LinkButton = new InlineKeyboardButton();
                    message.setText("Подпишитесь на груупу по ссылке\n Затем нажмие проверить\n");
                    LinkButton.setUrl("https://vk.com/" + groupScreenName);
                    LinkButton.setText("Сылка на группу");
                    buttons.get(0).add(LinkButton);
                    InlineKeyboardButton CheckButton = new InlineKeyboardButton();
                    CheckButton.setText("Проверить");
                    CheckButton.setCallbackData("V" + cur.getVkGroupId().toString());
                    VkAdRelationship relationship = new VkAdRelationship(user.getVkId(), cur.getVkGroupId(),
                            0, 0);
                    mappers.getVkAdRelationshipMapper().create(relationship);
                    buttons.get(1).add(CheckButton);
                    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                    keyboardMarkup.setKeyboard(buttons);
                    message.setReplyMarkup(keyboardMarkup);
                }
                catch (Exception e)
                {
                    message.setText("ЫЫЫЫЫЫЫЫЫЫЫЫЫЫ все сломалось");
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public SendMessage handleUpdate(Update update, User user)
    {
        SendMessage message = new SendMessage();
        message.setChatId(user.getTelegramId());
        switch (update.getMessage().getText())
        {
            case "Назад":
                message.setText("Главное меню");
                user.setViewId(views.getMainMenuId());
                break;
            case "Instagram":
                generateInstagramJob(message, user);
                break;
            case "Vk":
                generateVkJob(message, user);
                break;
            default:
                message.setText("Неверное действие");
        }
        return message;
    }
}
