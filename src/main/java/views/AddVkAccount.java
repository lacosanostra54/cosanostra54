package views;

import helpers.vk.VkChecker;
import kotlin.Pair;
import models.Mappers;
import models.user.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class AddVkAccount extends FieldReader
{
    private VkChecker vkChecker;
    AddVkAccount(Mappers newMappers, Views newViews, VkChecker newVkchecker)
    {
        super(newMappers, newViews);
        vkChecker = newVkchecker;
    }

    @Override
    public SendMessage handleUpdate(Update update, User user)
    {
        SendMessage message = new SendMessage();
        message.setChatId(user.getTelegramId());
        if (update.getMessage().getText().equals("Назад"))
        {
            message.setText(views.getMyProfile().generateInnerMassage(user));
            user.setViewId(views.getMyProfileId());
        }
        else
        {
            String messageText = update.getMessage().getText();
            StringBuilder ans = new StringBuilder("");
            char[] mas= messageText.toCharArray();
            int i = mas.length - 1;
            while (i >= 0)
            {
                char last = mas[i];
                if (last == '/')
                    break;
                ans.append(last);
                i--;
            }
            ans = ans.reverse();
            messageText = ans.toString();
            try
            {
                Pair<Integer, String> pair = vkChecker.getUserIdAndScreenName(messageText);
                user.setVkId(pair.getFirst());
                user.setVkShortName(pair.getSecond());
                message.setText("аккаун Vk добавлен");
                user.setViewId(views.getMainMenuId());
            }
            catch (Exception exception)
            {
                message.setText("Неверная ссылка");
                exception.printStackTrace();
            }
        }
        return message;
    }
}
