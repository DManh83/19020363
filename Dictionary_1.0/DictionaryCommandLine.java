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
}
