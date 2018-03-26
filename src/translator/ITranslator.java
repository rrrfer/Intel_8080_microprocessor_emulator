package translator;

public interface ITranslator {
    String[] getLexemes(String programText);
    boolean getStatusFlag();
    String getStatusString();
}