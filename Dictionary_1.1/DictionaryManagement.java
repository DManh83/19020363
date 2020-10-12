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

    /*
    public void repairFileDictionary() {
        Scanner sc = new Scanner(System.in);
        String repair = sc.nextLine();
        if (repair.equals("re") || repair.equals("de") || repair.equals("more")) {
            try{
                File f = new File("dictionaries.txt");

            }
        }
    }
    */
    public void dictionnaryLookup() {
        Scanner sc = new Scanner(System.in);
        String commandline = sc.nextLine();
        system.out.println("enter command line (search):");
        if(commandline.equals("search")) {
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
                    }
                    else{
                        line = line.trim();
                        line = line.replaceAll("\\s+", "   ");
                        wordsArray = line.split("\\s", 2);
                        for(int i = 0; i < wordsArray.length; i++) {
                            wordsArray[i] = wordsArray[i].trim();
                            wordsArray[i] = wordsArray[i].replaceAll("\\s+", " ");
                        }
                        for(String each : wordsArray) {
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
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else System.out.println();
    }

    public void dictionaryExportToFile() {
        
    }
}
