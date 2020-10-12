import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandLine extends DictionaryManagement {
    public void dictionaryBasic() {
        DictionaryManagement dic = new DictionaryManagement();
        dic.insertFromCommandline();
        dic.showAllWord();
    }

    public void dictionaryAdvanced() {
        DictionaryManagement dic = new DictionaryManagement();
        dic.showAllWord();
        dic.insertFromfile();
        dic.dictionnaryLookup();
    }

    public void dictionarySearcher() {
        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();
        try{
            BufferedReader buf = new BufferedReader(new FileReader("dictionaries.txt"));
            ArrayList<String> words = new ArrayList<>();
            String line;
            String[] wordsArray;

            while(true){
                line = buf.readLine();
                if(line == null){
                    break;
                }else{
                    line = line.trim();
                    line = line.replaceAll("\\s+", "   ");
                    wordsArray = line.split("\\s", 2);
                    for(int i = 0; i < wordsArray.length; i++) {
                        wordsArray[i] = wordsArray[i].trim();
                        wordsArray[i] = wordsArray[i].replaceAll("\\s+", " ");
                    }
                    for(String each : wordsArray){
                        if(!"".equals(each)){
                            words.add(each);
                        }
                    }
                }
            }
            for (int i = 0; i < words.size(); ++i) {
                if (words.get(2 * i).contains(search)) {
                    System.out.println(words.get(2 * i));
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
