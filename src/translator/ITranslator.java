package translator;

public interface ITranslator {
    String[] getLexemes(String programText);
    int[] getCode(String lex);
    boolean getStatusFlag();
    String getStatusString();
}