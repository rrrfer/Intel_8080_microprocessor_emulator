import kernel.*;
import translator.ITranslator;
import translator.Intel8080Translator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        ITranslator translator = new Intel8080Translator();
        String[] commands
                = translator.getLexemes(loadProgramTextFromFile("test.i8080"));

        System.out.println("Has errors: " + translator.getStatusFlag());
        System.out.println("Result: " + translator.getStatusString());
        System.out.println();

        if (commands != null) {
            for (String cmd : commands) {
                System.out.println(cmd);
            }
        }
    }

    private static String loadProgramTextFromFile(String path) {
        try {
            BufferedReader bufferedReader
                    = new BufferedReader(new FileReader(new File(path)));
            StringBuilder program = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                program.append(line).append(System.lineSeparator());
            }
            return program.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void viewIntel8080State(IMicroprocessor microprocessor) {
        System.out.println("= = = State intel8080 = = =");
        System.out.println("*GPR*");
        System.out.println("A: " + microprocessor.getValueByRegisterName("A"));
        System.out.println("B: " + microprocessor.getValueByRegisterName("B"));
        System.out.println("C: " + microprocessor.getValueByRegisterName("C"));
        System.out.println("D: " + microprocessor.getValueByRegisterName("D"));
        System.out.println("E: " + microprocessor.getValueByRegisterName("E"));
        System.out.println("H: " + microprocessor.getValueByRegisterName("H"));
        System.out.println("L: " + microprocessor.getValueByRegisterName("L"));
        System.out.println("SP: " + microprocessor.getValueByRegisterName("SP"));
        System.out.println("PC: " + microprocessor.getValueByRegisterName("PC"));
        System.out.println("*Flags*");
        System.out.println("Z: " + microprocessor.getValueByFlagName("Z"));
        System.out.println("C: " + microprocessor.getValueByFlagName("C"));
        System.out.println("S: " + microprocessor.getValueByFlagName("S"));
        System.out.println("P: " + microprocessor.getValueByFlagName("P"));
        System.out.println();
    }
}

// Читаем из файла листинг программы
// Парсим из файла все лексемы
// Переводим символьные метки в физические адреса
// Переводим список лексем в hex код
// Загружаем каждую команду по соответствующему адресу
// По hex коду строим нужный объект команды (отображаем/выполняем)