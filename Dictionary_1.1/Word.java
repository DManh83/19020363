
public class Word {
    private String word_target;
    private String word_explain;

    public Word(String wt, String we) {
        this.word_target = wt;
        this.word_explain = we;
    }
    public Word() {
        //Todo:
    }

    public String getWord_target() { return word_target; }

    public String getWord_explain() { return word_explain; }

    public void setWord_target(String wt) { this.word_target = wt; }

    public void setWord_explain(String we) { this.word_explain = we; }

}
