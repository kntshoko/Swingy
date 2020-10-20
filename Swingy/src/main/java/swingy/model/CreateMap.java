package swingy.model;

import javax.validation.constraints.*;

public class CreateMap {

    @NotNull
    @Min(1)
    private int level;
    private int i;

    public CreateMap(int level) {
        this.level = level;
    }

    public int getMap() {
        i = (level - 1) * 5 + 10 - (level % 2);
        return i;
    }
}
