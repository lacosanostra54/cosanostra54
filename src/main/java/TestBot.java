import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.*;

public class TestBot extends TelegramLongPollingBot
{
    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.getCallbackQuery() != null)
        {
            CallbackQuery cq = update.getCallbackQuery();
            System.out.println(cq.getData());
        }
        else
        {
            SendMessage sm = new SendMessage();
            sm.setText("text");
            sm.setChatId(update.getMessage().getChat().getId());
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("hello");
            inlineKeyboardButton.setCallbackData("123");
            List<InlineKeyboardButton> lst = new ArrayList<>();
            lst.add(inlineKeyboardButton);
            List<List<InlineKeyboardButton>> llst = new ArrayList<>();
            llst.add(lst);
            inlineKeyboardMarkup.setKeyboard(llst);
            sm.setReplyMarkup(inlineKeyboardMarkup);
            try
            {
                execute(sm);
            }
            catch (TelegramApiException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername()
    {
        return "teleUPadminbot";
    }

    @Override
    public String getBotToken()
    {
        return "417715812:AAGkndYDP6d1k_UIVIkhiKpVflTyKMeTHcc";
    }
}
