import emulator.EmulatorIntel8080;
import emulator.IEmulator;
import kernel.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        IEmulator emulator = new EmulatorIntel8080();
        emulator.loadProgram(loadProgramTextFromFile("test.i8080"));
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

