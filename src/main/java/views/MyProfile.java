package views;

import models.Mappers;
import models.user.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MyProfile extends View
{

    MyProfile(Mappers newMappers, Views newViews)
    {
        super(newMappers, newViews);
    }

    @Override
    public String generateInnerMassage(User user)
    {
        String text = "";
        if (user.getVkShortName() != null)
        {
            text += "Ваша профиль VK: " + user.getVkShortName();
        }
        else
        {
            text += "Вы еще не ввели профиль Vk";
        }
        text += "\n";
        if (user.getInstagramId() != null)
        {
            text += "Ваша профиль Instagram: " + user.getInstagramId();
        }
        else
        {
            text += "Вы еще не ввели профиль Instagram";
        }
        text += "\n";
        if (user.getQiwiPhoneNumber() != null)
        {
            text += "Ваш Qiwi кошелек: " + user.getQiwiPhoneNumber();
        }
        else
        {
            text += "Вы еще не ввели Qiwi кошелек";
        }
        return text;
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
        row.add("Qiwi");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboard.add(row);
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    @Override
    public SendMessage handleUpdate(Update update, User user)
    {
        SendMessage message = new SendMessage();
        message.setChatId(user.getTelegramId());
        switch (update.getMessage().getText())
        {
            case "Назад":
                message.setText("Главное Меню");
                user.setViewId(views.getMainMenuId());
                break;
            case "Instagram":
                message.setText("Введите имя пользователя Instagram");
                user.setViewId(views.getAddInstagramIdId());
                break;
            case "Vk":
                message.setText("Введите сылку на аккаунт vk");
                user.setViewId(views.getAddVkAccountId());
            break;
            case "Qiwi":
                message.setText("Введите номер телефона Qiwi");
                user.setViewId(views.getAddQiwiProfileId());
                break;
            default:
                message.setText("Неверное действие");
        }
        return message;
    }
}
