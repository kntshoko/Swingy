package swingy.model;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class update {

    @NotNull
    private int i;
    @NotNull
    private String field;
    @NotNull
    private int value;

    public update(int i, String field, int value) {
        this.i = i;
        this.field = field;
        this.value = value;
        Modify();

    }

    private void Modify() {
        try {
            File stats = new File("src/main/java/swingy/control/stats.txt");
            File nStats = new File("src/main/java/swingy/control/nStats.txt");
            Scanner myReader = new Scanner(stats);
            String nstring = "";
            PrintWriter myWriter = new PrintWriter(nStats);
            int c = 0;

            while (myReader.hasNextLine()) {
                String s = myReader.nextLine();
                if (c == i) {
                    String d[] = s.split(",");
                    s = "";
                    if (field.equals("level")) {
                        int g = 1 + value;
                        d[2] = Integer.toString(g);
                    } else if (field.equals("weapon")) {
                        int g = Integer.parseInt(d[4]) + value;
                        d[4] = Integer.toString(g);
                    } else if (field.equals("armor")) {
                        int g = Integer.parseInt(d[5]) + value;
                        d[5] = Integer.toString(g);
                    } else if (field.equals("helm")) {
                        int g = Integer.parseInt(d[6]) + value;
                        d[6] = Integer.toString(g);
                    } else {
                        int t = value * 1000 + ((value - 1) * (value - 1)) * 450;
                        int g = t + Integer.parseInt(d[3]);
                        d[3] = Integer.toString(g);
                    }
                    for (int j = 0; j < d.length; j++) {
                        if (j < 6)
                            s = s + d[j] + ",";
                        else
                            s = s + d[j];
                    }
                }
                nstring = nstring + s + "\n";
                c++;
            }
            myWriter.print(nstring);
            myWriter.close();
            myReader.close();
            if (!stats.delete()) {

            }
            if (!nStats.renameTo(stats)) {

            }
        } catch (Exception e) {

        }
    }
}