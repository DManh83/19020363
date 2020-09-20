public class DictionaryCommandLine extends DictionaryManagement {
    public void dictionaryBasic() {
        DictionaryManagement dic = new DictionaryManagement();
        dic.insertFromCommandline();
        dic.showAllWord();
    }
}
