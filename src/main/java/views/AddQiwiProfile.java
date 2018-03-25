package views;

import models.Mappers;
import models.user.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class AddQiwiProfile extends FieldReader
{

    AddQiwiProfile(Mappers newMappers, Views newViews)
    {
        super(newMappers, newViews);
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
            user.setQiwiPhoneNumber(messageText);
            message.setText(views.getMyProfile().generateInnerMassage(user));
            user.setViewId(views.getMyProfileId());
        }
        return message;
    }
}
