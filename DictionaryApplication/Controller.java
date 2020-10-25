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
    public TextField tfSearch;

    @FXML
    public ListView<String> lvWords;

    @FXML
    public TextArea taVie;

    @FXML
    public TextField tfAddEn;

    @FXML
    public TextField tfAddVn;

    @FXML
    public TextField tfDe;

    @FXML
    public TextField tfReEn;

    @FXML
    public TextField tfReVn;

    @FXML
    public Button btAdd;

    @FXML
    public Button btDe;

    @FXML
    public Button btRe;

    Map<String, String> dictionary = new HashMap<>();
    Map<String, String> searcher = new HashMap<>();
    public static final String FILENAME = "dictionaries.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.insertFromFile();
        btSearch.setOnMouseClicked(event -> {
            String search = tfSearch.getText();
            Set<String> keys = dictionary.keySet();
            if (search != null && !search.equals("")) {
                for (String key : keys) {
                    if (key.contains(search)) {
                        searcher.put(key, dictionary.get(key));
                    }
                }
            }
            String wordMeaning = dictionary.get(search);
            taVie.setText(wordMeaning);
            lvWords.getItems().clear();
            lvWords.getItems().addAll(searcher.keySet());
        });

        btAdd.setOnMouseClicked(event -> {
            String addEn = tfAddEn.getText();
            String addVn = tfAddVn.getText();
            if (addEn != null && addVn != null && !addEn.equals("") && !addVn.equals("")) {
                dictionary.put(addEn, addVn);
                this.dictionaryExportToFile();
            }
            tfAddEn.clear();
            tfAddVn.clear();
            lvWords.getItems().clear();
            lvWords.getItems().addAll(dictionary.keySet());
        });

        btRe.setOnMouseClicked(event -> {
            String ReEn = tfReEn.getText();
            String ReVn = tfReVn.getText();
            if (ReEn != null && ReVn != null && !ReEn.equals("") && !ReVn.equals("")) {
                dictionary.remove(ReEn);
                dictionary.put(ReEn, ReVn);
                this.dictionaryExportToFile();
            }
            tfReEn.clear();
            tfReVn.clear();
            lvWords.getItems().clear();
            lvWords.getItems().addAll(dictionary.keySet());
        });

        btDe.setOnMouseClicked(event -> {
            String delete = tfDe.getText();
            dictionary.remove(delete);
            this.dictionaryExportToFile();
            tfDe.clear();
            lvWords.getItems().clear();
            lvWords.getItems().addAll(dictionary.keySet());
        });

        lvWords.setOnMouseClicked(event -> {
            String searchedWords = lvWords.getSelectionModel().getSelectedItem();
            if (searchedWords != null && !searchedWords.equals("")) {
                String wordMeaning = dictionary.get(searchedWords);
                taVie.setText(wordMeaning);
            }
        });
    }

    public void insertFromFile() {
        String line;
        String[] wordsArray;
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
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
    }

    public void initializeWordList() {
        this.insertFromFile();
        lvWords.getItems().addAll(dictionary.keySet());
    }

    public void dictionaryExportToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
            Set<String> keys = dictionary.keySet();
            for (String key : keys) {
                bw.write(key + "\t\t" + dictionary.get(key) + "\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}