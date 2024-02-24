package org.example.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
public class tgbot extends TelegramLongPollingBot{
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            if ("/start".equals(text)) {
                sendReplyKeyboard(chatId, "команды", "/botInfo","/minecraft","/howmakepotions");
            } else if ("/команды".equals(text)) {
                sendReplyKeyboard(chatId, "Доступные команды бота", "/botinfo","/botnoinfo","/start");
            } else if ("/minecraft".equals(text)) {
                sendReplyKeyboard(chatId, "инфо проо майнкрафт", "/майнкрафтэто","/ссылканафорумпомайнкрафт","/start");
            } else if ("/botInfo".equals(text)) {
                sendTextMessage(chatId, "кто я");
            } else if ("/botnoinfo".equals(text)) {
                sendTextMessage(chatId, "не скажу");
            }else if ("/майнкрафтэто".equals(text)) {
                sendTextMessage(chatId, "майнкрафт это ну короче эээ пиксели.");
            } else if ("/ссылканафорумпомайнкрафт".equals(text)) {
                sendTextMessage(chatId, "Ссылка на сайт","https://minecraft-inside.ru/forum/?ysclid=lsp1if5ufk708708413");
            } else if ("/howmakepotions".equals(text)) {
                sendReplyKeyboard(chatId, "зелья","/регенерация","/слабость","/огнеупорность","/start");
            } else if ("/регенерация".equals(text)) {
                sendTextMessage(chatId, "Чтобы сделать зелье регенерации в Minecraft, понадобятся:\n" +
                        "\n" +
                        "слеза гаста;\n" +
                        "мутное зелье;\n" +
                        "зельеварка.\n" +
                        "Порядок действий:\n" +
                        "\n" +
                        "Установите варочную стойку.\n" +
                        "Кликните по ней правой кнопкой мыши.\n" +
                        "Разместите ингредиенты согласно рисунку.\n" +
                        "В стойке одновременно можно приготовить 3 бутылочки зелья регенерации.");
            } else if ("/слабость".equals(text)) {
                sendTextMessage(chatId, "Для создания взрывного зелья слабости в Minecraft понадобятся:\n" +
                        "\n" +
                        "Приготовленный паучий глаз. Он делается из паучьего глаза, сахара и гриба.\n" +
                        "Колба с водой. Чтобы получить колбу с водой, нужно скрафтить обычные колбы и набрать в них воду.\n" +
                        "Порох. Он добывается с криперов и гастов.\n" +
                        "После того как вы сварите приготовленный паучий глаз, вы получите зелье слабости. Добавьте к нему порох и дождитесь приготовления.\n" +
                        "\n" +
                        "В основном зелье слабости используется для создания жителей.");
            }else if ("/огнеупорность".equals(text)) {
                sendTextMessage(chatId, "Рецепт зелья огнестойкости в Minecraft:\n" +
                        "\n" +
                        "В варочную ёмкость добавьте следующие ингредиенты:\n" +
                        "сгусток магмы (делается из сгустка слизи и огненного порошка);\n" +
                        "неловкое зелье (из адского нароста и пузырька воды).\n" +
                        "Эффект зелья — полный иммунитет к огню и лаве. Время действия — 3 минуты.\n" +
                        "\n" +
                        "Для усиления зелья огнестойкости можно добавить красную пыль.\n" +
                        "\n" +
                        "Ингредиенты:\n" +
                        "\n" +
                        "красная пыль (при разрушении блока красной руды);\n" +
                        "зелье огнестойкости.\n" +
                        "Эффект усиленного зелья — полный иммунитет к огню и лаве. Время действия — 8 минут.");
            }
        }else{
            long chatId = update.getMessage().getChatId();
            sendReplyKeyboard(chatId, "hola", "/start");
        }
    }
    private void sendReplyKeyboard(long chatId, String text, String... buttonLabel1) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        KeyboardRow row = new KeyboardRow();
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (String buttonLabel : buttonLabel1) {
            KeyboardButton button = new KeyboardButton(buttonLabel);
            row.add(button);
        }
        keyboard.add(row);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void sendInlineKeyboard(long chatId, String text, String... buttons) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (String buttonLabel : buttons) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonLabel);
            button.setCallbackData(buttonLabel);
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(button);
            rowList.add(keyboardButtonsRow);
        }

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(rowList);

        sendMessage.setReplyMarkup(markupKeyboard);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendTextMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendTextMessage(long chatId, String text, String url) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setUrl(url);

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(rowList);

        sendMessage.setReplyMarkup(markupKeyboard);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "RyanGocling228Bot";
    }
    @Override
    public String getBotToken() {
        return "6440171815:AAEwMvSrBWxa34l4m56JshzAC7HvtXV4iIc";
    }
}