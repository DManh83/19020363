package DictionaryApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.*;
import java.net.URL;

public class Controller implements Initializable {
    @FXML
    public Button btSearch;

    @FXML
    public TextField tfSearchedWord;

    @FXML
    public ListView<String> lvWords;

    @FXML
    public TextArea taVie;

    Map<String, String> dictionary = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btSearch.setOnMouseClicked(event -> {
            String searchedWord = tfSearchedWord.getText();
            if (searchedWord != null && !searchedWord.equals("")) {
                String wordMeaning = dictionary.get(searchedWord);
                taVie.setText(wordMeaning);
            }
        });
        this.initializeWordList();
        lvWords.setOnMouseClicked(event -> {
            String searchedWord = lvWords.getSelectionModel().getSelectedItem();
            if (searchedWord != null && !searchedWord.equals("")) {
                String wordMeaning = dictionary.get(searchedWord);
                taVie.setText(wordMeaning);
            }
        });
    }

    public void initializeWordList() {
        String line;
        String[] wordsArray;
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("dictionaries.txt"));
            while ((line = br.readLine()) != null) {
                line = line.trim();
                line = line.replaceAll("\\s+", "   ");
                wordsArray = line.split("\\s", 2);
                for (int i = 0; i < wordsArray.length; i++) {
                    wordsArray[i] = wordsArray[i].trim();
                    wordsArray[i] = wordsArray[i].replaceAll("\\s+", " ");
                    words.add(wordsArray[i]);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < words.size(); i++) {
            if (i % 2 == 0) {
                dictionary.put(words.get(i), words.get(i + 1));
            }
        }
        lvWords.getItems().addAll(dictionary.keySet());
    }
}