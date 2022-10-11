package com.erciyes.spellingBee;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.*;

public class Puzzle {
    private ListView listView1;
    private List<String> listt = new ArrayList<>();
    private List<String> pangrams = new ArrayList<>();

    public ListView getListView1() {
        return listView1;
    }

    public void setListView1(ListView listView1) {
        this.listView1 = listView1;
    }

    public List<String> getListt() {
        return listt;
    }

    public void setListt(List<String> listt) {
        this.listt = listt;
    }

    public List<String> getPangrams() {
        return pangrams;
    }

    public void setPangrams(List<String> pangrams) {
        this.pangrams = pangrams;
    }

    public Puzzle(ListView listView1) {
        this.listView1 = listView1;
    }

    public void pangram(){

        HashSet<Character> ss = new HashSet<Character>();

        for (String word : listt) {
            for(int i = 0; i < word.length(); i++)
            {
                ss.add(word.charAt(i));
            }
            if(ss.size() == 7){
                if(!pangrams.contains(word)){
                    pangrams.add(word);
                }
            }
            ss.clear();
        }
    }
    public void createPuzzle() throws IOException {

        listView1.setVisible(false);
        String path = "src/com/erciyes/spellingBee/dictionary.txt";

        HashSet<String> wordList = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;

        while ((line = reader.readLine()) != null) {
            listt.add(line.toUpperCase());
            listView1.getItems().add(line.toUpperCase());
        }
        reader.close();
        pangram();
    }

    public boolean isWordExist(ListView listView, TextField textField) {
        String item = textField.getText();
        boolean situation = false;
        ObservableList<String> words = listView1.getItems(); // Only here to clarify the code
        if (words.contains(item)) {
            situation = true;
        } else {
            situation = false;
        }
        return situation;
    }

    public boolean checkText(TextField textField, Button btnYellow) {
        String text = textField.getText();
        boolean bool = false;
        String btnText = btnYellow.getText();
        char[] btnChar = btnText.toCharArray();
        char[] arr = text.toCharArray();
        for (char letter : arr) {
            for (char btchr : btnChar) {
                if (letter == btchr) {
                    bool = true;
                    break;
                } else {
                    bool = false;
                }
            }
            if (bool == true) {
                break;
            }
        }
        return bool;
    }
}
