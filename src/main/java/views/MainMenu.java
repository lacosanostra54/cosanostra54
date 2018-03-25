package views;

import helpers.qiwi.Qiwi;
import helpers.vk.VkChecker;
import models.Mappers;
import models.user.User;
import models.vkAd.VkAd;
import models.vkAd.VkAdMapper;
import models.vkAdRelationship.VkAdRelationship;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends View
{
    private Qiwi qiwi;
    MainMenu(Mappers newMappers, Views newViews, Qiwi newQiwi)
    {
        super(newMappers, newViews);
        qiwi = newQiwi;
    }

    @Override
    public ReplyKeyboardMarkup generateMarkup(User user)
    {
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Заработать");
        row.add("Мой профиль");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Вывести деньги");
        row.add("Для рекламодателей");
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
            case "Заработать":
                message.setText("Выберите соц сеть");
                user.setViewId(views.getGetJobId());
                break;
            case "Для рекламодателей":
                message.setText("Свяжитесь с нами: @annonew 89270341030");
                break;
            case "Мой профиль":
                message.setText(views.getMyProfile().generateInnerMassage(user));
                user.setViewId(views.getMyProfileId());
                break;
            case "Вывести деньги":
                if (user.getQiwiPhoneNumber() == null)
                {
                    message.setText("Вы не ввели данные Qiwi кошелька");
                }
                else
                {
                    String text = "Сумма на вашем счете состовляет: ";
                    text += user.getMoney();
                    text += "руб \n";
                    if (user.getMoney() < 2)
                        text += "Минимальная сумма для вывода 2руб";
                    else try
                    {
                        qiwi.sendMoneyToQiwi(user.getMoney(), user.getQiwiPhoneNumber(), "Спасибо");
                        text += "Успешно!!";
                        user.setMoney(0.0);
                    }
                    catch (Exception exception)
                    {
                        text += "Чтото пошло не так.";
                    }
                    message.setText(text);
                }
                break;
            default:
                message.setText("Неверное действие");
        }
        return message;
    }
}
