package views;

import helpers.instagram.Instagram;
import models.Mappers;
import models.user.User;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class AddInstagramId extends FieldReader
{

    private Instagram instagram;

    AddInstagramId(Mappers newMappers, Views newViews, Instagram newInstagram)
    {
        super(newMappers, newViews);
        instagram = newInstagram;
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
            Long ans = instagram.getId(messageText);
            if (ans == null)
            {
                message.setText("Неверное имя");
                user.setViewId(views.getMyProfileId());
            }
            else
            {
                user.setInstagramId(ans);
                message.setText(views.getMyProfile().generateInnerMassage(user));
                user.setViewId(views.getMyProfileId());
            }
        }
        return message;
    }
}
