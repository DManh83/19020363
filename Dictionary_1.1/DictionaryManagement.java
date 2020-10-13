import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary{
    public static final String FILENAME = "dictionaries.txt";
    public static final Scanner sc = new Scanner(System.in);

    public void insertFromCommandline() {
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
            BufferedReader br = new BufferedReader(new FileReader(new File(FILENAME)));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                System.out.println(i + "\t| " + line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWord() {
        String more = sc.nextLine();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(FILENAME).getAbsoluteFile(), true));
            bw.write(more + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteWord() {
        List<String> line = new ArrayList<>();
        String l;
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            while ((l = br.readLine()) != null) {
                line.add(l);
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
            String r = sc.nextLine();
            for(String i : line) {
                if (!i.contains(r)) {
                    bw.write(i + "\n");
                }
            }
            bw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void dictionnaryLookup() {
        String commandline = sc.nextLine();
        System.out.println("enter command line(search):");
        if(commandline.equals("search")) {
            String search = sc.nextLine();
            try{
                BufferedReader buf = new BufferedReader(new FileReader(FILENAME));
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
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FILENAME)));
            String line;
            List<String> lines = new ArrayList<>();
            int i = 0;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

            File file = new File("file.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String s : lines) {
                bw.write(s + "\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
