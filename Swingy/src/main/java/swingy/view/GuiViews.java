package swingy.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.File;
import java.util.Scanner;
import java.awt.*;

public class GuiViews {

    JPanel titlePanel, optionPanel, instructionPanel, map;
    JPanel[] mapPanels;
    JLabel titleLabel, instructionLabel, em;
    JButton button1 = new JButton("START");
    JButton button2 = new JButton("SELECT HERO");
    JButton button3 = new JButton("CREATE NEW HERO");
    JButton button4 = new JButton("SELECT");
    JButton button5 = new JButton("SAVE");
    JButton button6 = new JButton("play");
    JButton button7 = new JButton("NORTH");
    JButton button8 = new JButton("WEST");
    JButton button9 = new JButton("EAST");
    JButton button10 = new JButton("SOUTH");
    JButton button11 = new JButton("FIGHT");
    JButton button12 = new JButton("RUN");
    JButton button13 = new JButton("continue");

    JTextField textField;
    JComboBox<String> box;
    JComboBox<String> type;
    JFrame window;
    JTextArea heroTextArea, villainTextArea;

    Container con;
    Font f = new Font("Times New Romans", Font.PLAIN, 60), f2 = new Font("Times New Romans", Font.PLAIN, 40);
    Font f4 = new Font("Times New Romans", Font.PLAIN, 11), f3 = new Font("Times New Romans", Font.PLAIN, 18);
    Color c1 = Color.GREEN, c2 = Color.BLACK, c3 = Color.BLUE;

    private int num = 0;
    private String view, message;
    private int typ = 0;

    public GuiViews() {
        window = new JFrame();
        window.setSize(900, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 0, 600, 50);
        titleLabel = new JLabel("HERO VS VILLAINS");
        titleLabel.setFont(f3);
        titlePanel.add(titleLabel);

        con.add(titlePanel);

        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        optionPanel = new JPanel();
        optionPanel.setBounds(100, 500, 600, 100);

        instructionLabel = new JLabel("CLICK ON THE BUTTON TO START");
        instructionLabel.setFont(f2);

        button1.setFont(f3);
        button1.setFocusPainted(false);
        instructionPanel.add(instructionLabel);
        optionPanel.add(button1);

        con.add(instructionPanel);
        con.add(optionPanel);
    }

    public void setMessage(String msg) {
        message = msg;
    }

    public int getNum() {
        num = box.getSelectedIndex();
        return num;
    }

    public int getType() {
        typ = type.getSelectedIndex();
        return typ;
    }

    public String getView() {
        view = textField.getText();
        return view;
    }

    public void Landing() {

        instructionPanel.setVisible(false);
        optionPanel.setVisible(false);

        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        optionPanel = new JPanel();
        optionPanel.setBounds(100, 500, 600, 100);

        instructionLabel = new JLabel("WHAT DO YOU WANT TO DO?");
        instructionLabel.setFont(f2);

        button3.setFont(f3);
        button3.setFocusPainted(false);
        button3.setVisible(true);
        button2.setFont(f3);
        button2.setFocusPainted(false);
        button2.setVisible(true);

        instructionPanel.add(instructionLabel);
        optionPanel.add(button3);
        optionPanel.add(button2);

        con.add(instructionPanel);
        con.add(optionPanel);

    }

    public void Choose() {

        instructionPanel.setVisible(false);
        optionPanel.setVisible(false);

        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        optionPanel = new JPanel();
        optionPanel.setBounds(100, 500, 600, 100);
        instructionLabel = new JLabel("SELECT HERO FROM THE LIST");
        instructionLabel.setFont(f2);

        String st = "";
        try {
            File stats = new File("src/main/java/swingy/control/stats.txt");

            if (stats.createNewFile()) {


            }
            Scanner myReader = new Scanner(stats);

            int i = 0;
            while (myReader.hasNextLine()) {
                String s = myReader.nextLine();
                String ss[] = s.split(",");
                if (i == 0)
                    st = ss[0];
                else
                    st = st + "," + ss[0];
                i++;
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (st.equals("")) {
            Create();
        }
        else {
        String ss[] = st.split(",");
        box = new JComboBox<>(ss);
        box.setVisible(true);
        box.setFont(f3);

        button4.setFont(f3);
        button4.setFocusPainted(false);
        button4.setVisible(true);

        instructionPanel.add(instructionLabel);
        instructionPanel.add(box);
        optionPanel.add(button4);

        con.add(instructionPanel);
        con.add(optionPanel);}

    }

    public void Create() {

        instructionPanel.setVisible(false);
        optionPanel.setVisible(false);


        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        optionPanel = new JPanel();
        optionPanel.setBounds(100, 500, 600, 100);

        instructionLabel = new JLabel("ENTER HERO NAME");
        instructionLabel.setFont(f2);

        em = new JLabel(message);
        em.setFont(f4);

        button5.setFont(f3);
        button5.setFocusPainted(false);
        button5.setVisible(true);
        textField = new JTextField(10);
        textField.setFont(f3);

        String ss[] = { "pawn", "bishop", "knight", "rook" };
        type = new JComboBox<>(ss);
        type.setVisible(true);
        type.setFont(f3);

        instructionPanel.add(instructionLabel);
        instructionPanel.add(textField);
        instructionPanel.add(type);
        instructionPanel.add(em);
        optionPanel.add(button5);

        con.add(instructionPanel);
        con.add(optionPanel);
    }

    public void Stats(String hero) {

        instructionPanel.setVisible(false);
        optionPanel.setVisible(false);

        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        instructionPanel.setLayout(new FlowLayout());
        optionPanel = new JPanel();
        optionPanel.setBounds(100, 500, 600, 100);

        instructionLabel = new JLabel("HERO STATS");
        instructionLabel.setFont(f2);

        heroTextArea = new JTextArea(hero);
        heroTextArea.setSize(600, 300);
        heroTextArea.setFont(f3);
        heroTextArea.setEditable(false);

        button6.setFont(f3);
        button6.setFocusPainted(false);
        button6.setVisible(true);

        instructionPanel.add(heroTextArea);
        optionPanel.add(button6);

        con.add(instructionPanel);
        con.add(optionPanel);
    }

    public void Play(int x, int y, int e) {

        instructionPanel.setVisible(false);
        optionPanel.setVisible(false);

        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        optionPanel = new JPanel();
        optionPanel.setBounds(300, 500, 200, 200);
        instructionLabel = new JLabel("CLICK A BUTTON TO MOVE\n");
        instructionLabel.setFont(f2);
        map = new JPanel(new GridLayout(e, e, 2, 2));

        mapPanels = new JPanel[e * e];

        int i = e * e;
        for (int j = 0; j < i; j++) {

            mapPanels[j] = new JPanel();
            map.add(mapPanels[j]);

            if (j % 2 == 0)
                mapPanels[j].setBackground(c1);
            else
                mapPanels[j].setBackground(c2);
            if (j == (((y - 1) * e) + x - 1))
                mapPanels[j].setBackground(c3);
        }

        button7.setFont(f3);
        button7.setFocusPainted(false);
        button7.setVisible(true);

        button8.setFont(f3);
        button8.setFocusPainted(false);
        button8.setVisible(true);

        button9.setFont(f3);
        button9.setFocusPainted(false);
        button9.setVisible(true);

        button10.setFont(f3);
        button10.setFocusPainted(false);
        button10.setVisible(true);

        instructionPanel.add(instructionLabel);
        instructionPanel.add(map);
        optionPanel.add(button7);
        optionPanel.add(button8);
        optionPanel.add(button9);
        optionPanel.add(button10);

        con.add(instructionPanel);
        con.add(optionPanel);

    }

    public void Fight(int x, int y, int e) {
        instructionPanel.setVisible(false);
        optionPanel.setVisible(false);

        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        optionPanel = new JPanel();
        optionPanel.setBounds(100, 500, 600, 100);

        instructionLabel = new JLabel("YOU HAVE ENCOUNTERED A VILLAIN, WHAT DO YOU WANT TO DO?");
        instructionLabel.setFont(f3);

        map = new JPanel(new GridLayout(e, e, 2, 2));

        mapPanels = new JPanel[e * e];

        int i = e * e;
        for (int j = 0; j < i; j++) {

            mapPanels[j] = new JPanel();
            map.add(mapPanels[j]);

            if (j % 2 == 0)
                mapPanels[j].setBackground(c1);
            else
                mapPanels[j].setBackground(c2);
            if (j == (((y - 1) * e) + x - 1))
                mapPanels[j].setBackground(Color.red);
        }

        button11.setFont(f3);
        button11.setFocusPainted(false);
        button11.setVisible(true);

        button12.setFont(f3);
        button12.setFocusPainted(false);
        button12.setVisible(true);

        instructionPanel.add(instructionLabel);
        instructionPanel.add(map);
        optionPanel.add(button11);
        optionPanel.add(button12);

        con.add(instructionPanel);
        con.add(optionPanel);
    }

    public void Results(String hero, String simulate, String villain) {
        instructionPanel.setVisible(false);
        optionPanel.setVisible(false);

        instructionPanel = new JPanel();
        instructionPanel.setBounds(100, 50, 700, 450);
        optionPanel = new JPanel();
        optionPanel.setBounds(100, 500, 600, 100);

        instructionLabel = new JLabel(simulate.toUpperCase());
        instructionLabel.setFont(f3);
        instructionLabel.setBounds(600, 300, 0, 0);

        heroTextArea = new JTextArea(hero);
        heroTextArea.setBounds(600, 300, 0, 0);
        heroTextArea.setFont(f3);
        heroTextArea.setEditable(false);

        villainTextArea = new JTextArea(villain);
        villainTextArea.setBounds(600, 300, 0, 0);
        villainTextArea.setFont(f3);
        villainTextArea.setEditable(false);

        button13.setFont(f3);
        button13.setFocusPainted(false);
        button13.setVisible(true);

        instructionPanel.add(instructionLabel);
        instructionPanel.add(heroTextArea);
        instructionPanel.add(villainTextArea);
        optionPanel.add(button13);

        con.add(instructionPanel);
        con.add(optionPanel);
    }

    public JButton B1() {
        return button1;
    }

    public JButton B2() {
        return button2;
    }

    public JButton B3() {
        return button3;
    }

    public JButton B4() {
        return button4;
    }

    public JButton B5() {
        return button5;
    }

    public JButton B6() {
        return button6;
    }

    public JButton B7() {
        return button7;
    }

    public JButton B8() {
        return button8;
    }

    public JButton B9() {
        return button9;
    }

    public JButton B10() {
        return button10;
    }

    public JButton B11() {
        return button11;
    }

    public JButton B12() {
        return button12;
    }

    public JButton B13() {
        return button13;
    }
}
