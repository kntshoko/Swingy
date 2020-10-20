package swingy.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class NewHero {

    @NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be white spaces")
    @Length(min = 3, max = 15, message = "name length must be between 3 and 15")
    private String heroName;

    @Max(value = 3, message = "Value must be between 0 and 3")
    @Min(value = 0, message = "Value must be between 0 and 3")
    private int c;

    public NewHero(String heroName, int c) {
        this.heroName = heroName;
        this.c = c;
    }

    public String getName() {
        return heroName;
    }

    public void Save() {
        try {
            File stats = new File("src/main/java/swingy/control/stats.txt");
            File nStats = new File("src/main/java/swingy/control/nStats.txt");

            if (!stats.createNewFile()) {

            }
            try {
                int i = 0;
                Scanner myReader = new Scanner(stats);
                PrintWriter myWriter = new PrintWriter(nStats);
                String data = "";

                while (myReader.hasNextLine()) {
                    String s = myReader.nextLine();
                    data = data + s + "\n";
                    if (s.contains(heroName)) {
                        i = 1;
                    }
                }
                if (i == 0) {
                    String cc;
                    if (c == 0)
                        cc = "pawn" + ",1,0,1,1,1";
                    else if (c == 1)
                        cc = "bishop" + ",1,0,2,1,3";
                    else if (c == 2)
                        cc = "knight" + ",1,0,1,3,2";
                    else
                        cc = "rook" + ",1,0,3,2,2";
                    myWriter.println(data + heroName + "," + cc);
                    myWriter.close();
                    myReader.close();
                    if (!stats.delete()) {

                    }
                    if (!nStats.renameTo(stats)) {

                    }
                } else {
                    myWriter.close();
                    myReader.close();
                    nStats.delete();
                }
            } catch (FileNotFoundException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
