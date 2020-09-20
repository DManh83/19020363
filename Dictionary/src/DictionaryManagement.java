import java.util.Scanner;

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
            System.out.println(i + "\t|" + word.getWord_target() + "\t\t|" + word.getWord_explain());
            i++;
        }
    }
}
