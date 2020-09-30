import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary{
    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String eng, vie;
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            eng = sc.nextLine();
            vie = sc.nextLine();
            Word word = new Word(eng, vie);
            words.add(word);
        }
    }
    public void showAllWord() {
        System.out.println("No\t| English\t\t| Vietnamese");
        int i = 1;
        for (Word word : words) {
            System.out.println(i + "\t| " + word.getWord_target() + "\t\t| " + word.getWord_explain());
            i++;
        }
    }

    public void insertFromfile() {
        try {
            File f = new File("dictionaries.txt");
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                System.out.println(i + "\t| " + line);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dictionnaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type lookup word:");
        String search = sc.nextLine();
        try{
            BufferedReader buf = new BufferedReader(new FileReader("dictionaries.txt"));
            ArrayList<String> words = new ArrayList<>();
            String lineJustFetched;
            String[] wordsArray;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    wordsArray = lineJustFetched.split("\\s", 4);
                    for(String each : wordsArray){
                        if(!"".equals(each)){
                            words.add(each);
                        }
                    }
                }
            }

            for (int i = 0; i < words.size(); i++) {
                if (search.equals(words.get(i))) {
                    System.out.println(words.get(i+1));
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
