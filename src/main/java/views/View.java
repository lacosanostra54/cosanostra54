package views;

import models.Mappers;
import models.user.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

public class View
{
    Mappers mappers;
    Views views;

    View(Mappers newMappers, Views newViews)
    {
        mappers = newMappers;
        views = newViews;
    }

    public String generateInnerMassage(User user)
    {
        return null;
    }
    public ReplyKeyboardMarkup generateMarkup(User user)
    {
        return new ReplyKeyboardMarkup();
    }
    public SendMessage handleUpdate(Update update, User user)
    {
        return null;
    }
}
