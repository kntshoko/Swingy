package swingy.view;

public class ConsoleViews {

    public void intstruction1() {
        System.out.println("   +++++++++++++++++++++++");
        System.out.println("   +   WHAT DO YOU WANT  +");
        System.out.println("   +         TO DO?      +");
        System.out.println("   +      ENTER A KEY    +");
        System.out.println("   +                     +");
        System.out.println("   + CREATE A HERO  -- 1 +");
        System.out.println("   + CHOOSE A HERO  -- 2 +");
        System.out.println("   +++++++++++++++++++++++");
    }

    public void intstruction2() {
        System.out.println("\n\n   +++++++++++++++++++++++");
        System.out.println("   +  ENTER THE NEW HERO +");
        System.out.println("   +         NAME        +");
        System.out.println("   +++++++++++++++++++++++");
    }

    public void intstruction3() {
        System.out.println("\n\n   +++++++++++++++++++++++");
        System.out.println("   +    ENTER THE HERO   +");
        System.out.println("   +       NUMBER        +");
        System.out.println("   +++++++++++++++++++++++");
    }

    public void intstruction4() {
        System.out.println("\n\n   +++++++++++++++++++++++");
        System.out.println("   + ENTER A KEY TO MOVE +");
        System.out.println("   +   NORTH ------ n    +");
        System.out.println("   +   SOUTH ------ s    +");
        System.out.println("   +   WEST  ------ w    +");
        System.out.println("   +   EAST  ------ e    +");
        System.out.println("   +++++++++++++++++++++++\n");
    }

    public void intstruction5() {
        System.out.println("\n\n   +++++++++++++++++++++++++++");
        System.out.println("   +      DO YOU WANT TO     +");
        System.out.println("   +      PLAY THE MAP?      +");
        System.out.println("   +      ENTER A KEY        +");
        System.out.println("   +                         +");
        System.out.println("   +  PLAY -- 1              +");
        System.out.println("   +  EXIT -- ANY OTHER KEY  +");
        System.out.println("   +++++++++++++++++++++++++++");
    }

    public void intstruction6(String s) {

        String ss[] = s.split(",");
        System.out.println("\n  +++++++++++++++++++++++");
        for (int i = 0; i < ss.length; i++) {
            System.out.println("    " + ss[i] + " ---  " + i);
        }
        System.out.println("   +++++++++++++++++++++++");
    }

    public void intstruction7(String s) {

        System.out.println(s);
    }

    public void intstruction8() {
        System.out.println("\n\n   +++++++++++++++++++++++");
        System.out.println("   +   HERO ENCOUNTERD   +");
        System.out.println("   +      A VILLAIN!     +");
        System.out.println("   +      ENTER KEY?      +");
        System.out.println("   +                     +");
        System.out.println("   + FIGHT - 1           +");
        System.out.println("   + RUN - ANY OTHER KEY +");
        System.out.println("   +++++++++++++++++++++++\n");
    }

    public void intstruction9() {

        System.out.println("\n\n   +++++++++++++++++++++++");
        System.out.println("   +   SELECT HERO TYPE  +");
        System.out.println("   +   PAWN   ----- 0    +");
        System.out.println("   +   BISHOP ----- 1    +");
        System.out.println("   +   KHIGHT ----- 2    +");
        System.out.println("   +   ROOK   ----- 3    +");
        System.out.println("   +++++++++++++++++++++++\n");

    }
}
