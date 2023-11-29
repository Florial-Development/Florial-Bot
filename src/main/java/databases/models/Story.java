package databases.models;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Story {

    @Getter
    @Setter
    private int id;
    @Getter @Setter private int requiredLevel;
    @Getter @Setter private String name;
    @Getter @Setter private String part_1;
    @Getter @Setter private String part_2;
    @Getter @Setter private String part_3;
    @Getter @Setter private String image_1;
    @Getter @Setter private String image_2;
    @Getter @Setter private String image_3;


    public Story(int id, int requiredLevel, String name, String part_1, String part_2, String part_3, String image_1, String image_2, String image_3) {
        this.id = id;
        this.requiredLevel = requiredLevel;
        this.name = name;
        this.part_1 = part_1;
        this.part_2 = part_2;
        this.part_3 = part_3;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.image_3 = image_3;

    }
}
