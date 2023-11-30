package quests;

import lombok.Getter;

import java.util.Random;

public enum QuestType {
    ANIMAL_IMAGE_QUEST(1,"Send an image of your pet in the Animals channel! Don't have one? Feel free to post any random cute animal pic!", 1023840490845650995L, 1),
    MEMES_IMAGE_QUEST(2,"Send a meme in our memes channel of your choosing, just make sure it's not offensive/NSFW!", 825153621611511819L, 1),
    ARTWORK_IMAGE_QUEST(3,"Send a picture of your artwork in our artwork channel! Not an artist? Feel free to send any picture of any artwork you like, just be sure to give credit!", 814301616764747816L, 1),

    SEND_MSG_QUEST_10(4,"Send 10 messages today in any channel.", 0, 10),
    SEND_MSG_QUEST_15(5,"Send 15 messages today in any channel.", 0, 15),
    SEND_MSG_QUEST_20(6,"Send 20 messages today in any channel.", 0, 20),

    ASK_TO_DM_QUEST(7,"Send a message in ask-to-dm. DMS closed? Specify it there!", 943028902828322826L, 1),
    VENT_QUEST(8, "Send a message in our vent channel. Vent, or simply wish others well.", 938257050364968981L, 1);


    @Getter private final String description;
    @Getter private final int id;

    @Getter private final long specificChannelId;

    @Getter private final int amountNeeded;

    QuestType(int id, String description, long specificChannelId, int amountNeeded) {
        this.description = description;
        this.id = id;
        this.specificChannelId = specificChannelId;
        this.amountNeeded = amountNeeded;
    }

    public static QuestType findQuestTypeById(int id) {
        for (QuestType questType : values()) {
            if (questType.getId() == id) {
                return questType;
            }
        }
        return null;
    }

    public static QuestType getRandomQuest() {return values()[new Random().nextInt(values().length)];}
}
