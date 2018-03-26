package translator;

import java.util.ArrayList;
import java.util.HashMap;

public class Intel8080Translator implements ITranslator {

    private StringBuilder statusString;

    private ArrayList<String> commands;
    private HashMap<String, Integer> label2AddressMap;
    private ArrayList<String> commandsTemplateArray;
    private int currentAddress;

    private boolean isCorrect;
    private boolean hasErrors;

    public Intel8080Translator() {
        commands = new ArrayList<>();
        label2AddressMap = new HashMap<>();
        commandsTemplateArray = new ArrayList<>();
        initCmdTemplateArray(commandsTemplateArray);
        statusString = new StringBuilder();
    }

    @Override
    public String[] getLexemes(String programText) {

        currentAddress = 0;
        hasErrors = false;
        label2AddressMap.clear();
        statusString.delete(0, statusString.length());

        String[] dividedProgramLines = programText.split(System.lineSeparator());

        // Обработка исходного кода программы
        for (int i = 0; i < dividedProgramLines.length; ++i) {
            dividedProgramLines[i] = dividedProgramLines[i].toLowerCase();
            dividedProgramLines[i] = cutComment(dividedProgramLines[i]);
            dividedProgramLines[i] = removeTabAndDoubleSpace(dividedProgramLines[i]);
        }

        // Сбор меток и отображение метки в адрес
        for (int i = 0; i < dividedProgramLines.length; ++i) {
            if (!dividedProgramLines[i].isEmpty()) {
                if (isDirective(dividedProgramLines[i])) {
                    int newAddress = isAddressSet(dividedProgramLines[i]);
                    if (newAddress >= 0) {
                        currentAddress = newAddress;
                        continue;
                    }

                    int data = isDataSet(dividedProgramLines[i]);
                    if (data >= 0) {
                        commands.add(Integer.toHexString(currentAddress) +
                                ":" + Integer.toHexString(data));
                        currentAddress += 1;
                        continue;
                    }

                    hasErrors = true;
                    statusString.append("Ошибка в строке № " + (i + 1) + ". " +
                            "Неизвестная или ошибочная директива: " + dividedProgramLines[i]);

                }

                String labelName;
                if ((labelName = isLabel(dividedProgramLines[i])) != null) {
                    if (!label2AddressMap.containsKey(labelName)) {
                        label2AddressMap.put(labelName, currentAddress);
                        continue;
                    } else {
                        hasErrors = true;
                        statusString.append("Ошибка в строке №" + (i + 1) + ". " +
                                "Повторное использование метки: " + dividedProgramLines[i]);
                    }
                }

                String cmdName;
                if((cmdName = isCommand(dividedProgramLines[i].split(" ")[0])) != null) {
                    shiftCurrentAddress(cmdName);
                }
            }

        }

        // Разбор и трансляция команд
        for (int i = 0; i < dividedProgramLines.length; ++i) {
            if (!dividedProgramLines[i].isEmpty()) {
                if (isDirective(dividedProgramLines[i])) {
                    int newAddress = isAddressSet(dividedProgramLines[i]);
                    if (newAddress >= 0) {
                        currentAddress = newAddress;
                        continue;
                    }
                }

                if (isLabel(dividedProgramLines[i]) != null) {
                    continue;
                }

                translateCommands(dividedProgramLines[i], i);

                if (isCorrect) {
                    continue;
                }

                hasErrors = true;
            }
        }

        String[] outputCommands = new String[commands.size()];
        for (int i = 0; i < outputCommands.length; ++i) {
            outputCommands[i] = commands.get(i);
        }

        if (!hasErrors) {
            statusString.append("OK");
            return outputCommands;
        } else {
            return null;
        }
    }

    @Override
    public boolean getStatusFlag() {
        return hasErrors;
    }

    @Override
    public String getStatusString() {
        return statusString.toString();
    }

    private String cutComment(String lex) {
        return lex.split(";").length == 0 ? "" : lex.split(";")[0];
    }

    private String removeTabAndDoubleSpace(String lex) {
        String tab = new String(Character.toChars(9));
        lex = lex.replace(tab, " ");
        while (lex.contains("  ")) {
            lex = lex.replace("  ", " ");
        }
        lex = lex.replace(" ,", ",");
        lex = lex.replace(", ", ",");
        if (lex.length() != 0) {
            lex = removeSpaceInBeginAndEndString(lex);
        }
        return lex;
    }

    private String removeSpaceInBeginAndEndString(String lex) {
        if (lex.charAt(0) == ' ') {
            lex = lex.substring(1);
        }
        if (lex.charAt(lex.length() - 1) == ' ') {
            lex = lex.substring(0, lex.length() - 1);
        }
        return lex;
    }

    private boolean isDirective(String lex) {
        return lex.charAt(0) == '.';
    }

    private int isAddressSet(String lex) {
        if (lex.split(":").length == 2) {
            if (lex.split(":")[0].equals(".adr")) {
                int address = otherRadix2Dec(lex.split(":")[1]);
                if (address >= 0 && address <= 65535) {
                    return address;
                }
            }
        }
        return -1;
    }

    private int isDataSet(String lex) {
        if (lex.split(":").length == 2) {
            if (lex.split(":")[0].equals(".set")) {
                int data = otherRadix2Dec(lex.split(":")[1]);
                if (data >= 0 && data < 256) {
                    return data;
                }
            }
        }
        return -1;
    }

    private String isLabel(String lex) {
        if (lex.contains(" ")) {
            return null;
        }
        if (lex.split(":").length == 1) {
            if (lex.charAt(lex.length() - 1) == ':') {
                return lex.substring(0, lex.length() - 1);
            }
        }
        return null;
    }

    private int otherRadix2Dec(String number) {
        int address;
        if (number.charAt(0) == '0' && number.length() > 1) {
            if (number.charAt(1) == 'x') {
                try {
                    address = Integer.parseInt(number.substring(2), 16);
                    return address;
                } catch (NumberFormatException e) {
                    return -1;
                }
            } else if (number.charAt(1) == 'b') {
                try {
                    address = Integer.parseInt(number.substring(2), 2);
                    return address;
                } catch (NumberFormatException e) {
                    return -1;
                }
            } else {
                try {
                    address = Integer.parseInt(number);
                    return address;
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        } else {
            try {
                address = Integer.parseInt(number);
                return address;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    private int typeCMD(String cmd) {
        if (cmd.equals("cma") || cmd.equals("stc") || cmd.equals("cmc") ||
                cmd.equals("rlc") || cmd.equals("rrc") || cmd.equals("ral") ||
                cmd.equals("rar") || cmd.equals("ret") || cmd.equals("pchl") ||
                cmd.equals("sphl") || cmd.equals("ret") || cmd.equals("rnz") ||
                cmd.equals("rz") || cmd.equals("rnc") || cmd.equals("rc") ||
                cmd.equals("rpo") || cmd.equals("rpe") || cmd.equals("rp") ||
                cmd.equals("rm") || cmd.equals("xthl") || cmd.equals("nop") ||
                cmd.equals("hlt") || cmd.equals("xchg")) {
            return 0;
        } else if (cmd.equals("ldax") || cmd.equals("stax") || cmd.equals("add") ||
                cmd.equals("adc") || cmd.equals("sub") || cmd.equals("sbb") ||
                cmd.equals("inr") || cmd.equals("inx") || cmd.equals("dcr") ||
                cmd.equals("dcx") || cmd.equals("dad") || cmd.equals("ana") ||
                cmd.equals("ora") || cmd.equals("xra") || cmd.equals("cmp") ||
                cmd.equals("") || cmd.equals("push") || cmd.equals("pop")) {
            return 1;
        } else if (cmd.equals("mvi")) {
            return 2;
        } else if (cmd.equals("lxi")) {
            return 3;
        } else if (cmd.equals("mov")) {
            return 4;
        } else if (cmd.equals("adi") || cmd.equals("aci") || cmd.equals("sui") ||
                cmd.equals("sbi") || cmd.equals("ani") || cmd.equals("ori") ||
                cmd.equals("xri") || cmd.equals("cpi") || cmd.equals("in") ||
                cmd.equals("out")) {
            return 5;
        } else if (cmd.equals("lda") || cmd.equals("lhld") ||
                cmd.equals("sta") || cmd.equals("shld")) {
            return 6;
        } else {
            return 7;
        }
    }

    private String isCommand(String cmd) {
        for (String currentTemplate : commandsTemplateArray) {
            if (cmd.equals(currentTemplate)) {
                return currentTemplate;
            }
        }
        return null;
    }

    private void shiftCurrentAddress(String cmdName) {
        switch (typeCMD(cmdName)) {
            case 0: {
                currentAddress += 1;
                break;
            }
            case 1: {
                currentAddress += 1;
                break;
            }
            case 2: {
                currentAddress += 2;
                break;
            }
            case 3: {
                currentAddress += 3;
                break;
            }
            case 4: {
                currentAddress += 1;
                break;
            }
            case 5: {
                currentAddress += 2;
                break;
            }
            case 6: {
                currentAddress += 3;
                break;
            }
            case 7: {
                currentAddress += 3;
                break;
            }
        }
    }

    private void translateCommands(String lex, int lineNumber) {
        String cmdName;
        if ((cmdName = isCommand(lex.split(" ")[0])) != null) {
            isCorrect = false;
            switch (typeCMD(cmdName)) {
                case 0: {
                    if (lex.split(" ").length == 1) {
                        commands.add(Integer.toHexString(currentAddress) + ":" + cmdName);
                        currentAddress += 1;
                        isCorrect = true;
                    }
                    break;
                }
                case 1: {
                    if (lex.split(" ").length == 2) {
                        String argument = lex.split(" ")[1];
                        if (argument.length() == 1) {
                            char a = argument.charAt(0);
                            if (cmdName.equals("ldax") || cmdName.equals("stax")) {
                                if (a != 'b' && a != 'd') {
                                    break;
                                }
                            }
                            if (cmdName.equals("push") || cmdName.equals("pop") || cmdName.equals("dad") ||
                                    cmdName.equals("inx") || cmdName.equals("dcx")) {
                                if (a != 'b' && a != 'd' && a != 'h') {
                                    break;
                                }
                            }
                            if (a == 'a' || a == 'b' || a == 'c' || a == 'd' ||
                                    a == 'e' || a == 'h' || a == 'l') {
                                commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + a);
                                currentAddress += 1;
                                isCorrect = true;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    if (lex.split(" ", 2).length == 2) {
                        String argument[] = lex.split(" ", 2)[1].replace(" ", "")
                                .split(",");
                        if (argument.length == 2) {
                            if (argument[0].length() == 1) {
                                char a = argument[0].charAt(0);
                                if (a == 'a' || a == 'b' || a == 'c' || a == 'd' || a == 'e' ||
                                        a == 'h' || a == 'l') {
                                    try {
                                        int numberArg = otherRadix2Dec(argument[1]);
                                        if (numberArg >= 0 && numberArg <= 255) {

                                            commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + a + "," + Integer.toHexString(numberArg));
                                            currentAddress += 2;
                                            isCorrect = true;
                                        }
                                    } catch (NumberFormatException e) {

                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    if (lex.split(" ", 2).length == 2) {
                        String argument[] = lex.split(" ", 2)[1].replace(" ", "")
                                .split(",");
                        if (argument.length == 2) {
                            if (argument[0].length() == 1) {
                                char a = argument[0].charAt(0);
                                if (a == 'b' || a == 'd' || a == 'h') {
                                    try {
                                        int numberArg = otherRadix2Dec(argument[1]);
                                        if (numberArg >= 0 && numberArg <= 65535) {

                                            commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + a + "," + Integer.toHexString(numberArg));
                                            currentAddress += 3;
                                            isCorrect = true;
                                        }
                                    } catch (NumberFormatException e) {

                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    String argument[] = lex.split(" ", 2)[1]
                            .replace(" ", "")
                            .split(",");
                    if (argument.length == 2) {
                        if (argument[0].length() == 1 && argument[1].length() == 1) {
                            char a = argument[0].charAt(0);
                            if (a == 'a' || a == 'b' || a == 'c' || a == 'd' || a == 'e' ||
                                    a == 'h' || a == 'l') {
                                a = argument[1].charAt(0);
                                if (a == 'a' || a == 'b' || a == 'c' || a == 'd' || a == 'e' ||
                                        a == 'h' || a == 'l') {

                                    commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + argument[0] + "," + argument[1]);
                                    currentAddress += 1;
                                    isCorrect = true;
                                }
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    if (lex.split(" ").length == 2) {
                        String argument = lex.split(" ", 2)[1];
                        int numberArg = otherRadix2Dec(argument);
                        if (numberArg >= 0 && numberArg <= 255) {

                            commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + Integer.toHexString(numberArg));
                            currentAddress += 2;
                            isCorrect = true;
                        }
                    }
                    break;
                }
                case 6: {
                    if (lex.split(" ").length == 2) {
                        String argument = lex.split(" ", 2)[1];
                        int numberArg = otherRadix2Dec(argument);
                        if (numberArg >= 0 && numberArg <= 65535) {

                            commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + Integer.toHexString(numberArg));
                            currentAddress += 3;
                            isCorrect = true;
                        }
                    }
                    break;
                }
                case 7: {
                    if (lex.split(" ").length == 2) {
                        String label = lex.split(" ")[1];
                        int labelAddress = -1;
                        if (label2AddressMap.containsKey(label)) {
                            labelAddress = label2AddressMap.get(label);
                        }

                        if (labelAddress >= 0) {
                            commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + Integer.toHexString(labelAddress));
                            currentAddress += 3;
                            isCorrect = true;
                        }
                    }
                    break;
                }
            }

            if (!isCorrect) {
                statusString.append("Ошибка в строке № " + (lineNumber + 1) + ". " +
                        "Неверная команда или несуществующая метка: " + lex);
            }
        }
    }

    private void initCmdTemplateArray(ArrayList<String> arrayList) {
        arrayList.add("aci");
        arrayList.add("add");
        arrayList.add("adc");
        arrayList.add("adi");
        arrayList.add("ana");
        arrayList.add("ani");
        arrayList.add("call");
        arrayList.add("cc");
        arrayList.add("cm");
        arrayList.add("cma");
        arrayList.add("cmc");
        arrayList.add("cmp");
        arrayList.add("cnc");
        arrayList.add("cnz");
        arrayList.add("cp");
        arrayList.add("cpe");
        arrayList.add("cpi");
        arrayList.add("cpo");
        arrayList.add("cz");
        arrayList.add("dad");
        arrayList.add("daa");
        arrayList.add("dcr");
        arrayList.add("dcx");
        arrayList.add("di");
        arrayList.add("ei");
        arrayList.add("hlt");
        arrayList.add("in");
        arrayList.add("inr");
        arrayList.add("inx");
        arrayList.add("jc");
        arrayList.add("jmp");
        arrayList.add("jnc");
        arrayList.add("jnz");
        arrayList.add("jp");
        arrayList.add("jpe");
        arrayList.add("jz");
        arrayList.add("lda");
        arrayList.add("ldax");
        arrayList.add("lhld");
        arrayList.add("lxi");
        arrayList.add("mov");
        arrayList.add("mvi");
        arrayList.add("nop");
        arrayList.add("ora");
        arrayList.add("ori");
        arrayList.add("out");
        arrayList.add("pchl");
        arrayList.add("pop");
        arrayList.add("push");
        arrayList.add("ral");
        arrayList.add("rar");
        arrayList.add("rc");
        arrayList.add("ret");
        arrayList.add("rlc");
        arrayList.add("rm");
        arrayList.add("rnc");
        arrayList.add("rnz");
        arrayList.add("rp");
        arrayList.add("rpe");
        arrayList.add("rpo");
        arrayList.add("rrc");
        arrayList.add("rst");
        arrayList.add("rz");
        arrayList.add("rz");
        arrayList.add("sbb");
        arrayList.add("sbi");
        arrayList.add("shld");
        arrayList.add("sphl");
        arrayList.add("sta");
        arrayList.add("stax");
        arrayList.add("stc");
        arrayList.add("sub");
        arrayList.add("sui");
        arrayList.add("xchg");
        arrayList.add("xra");
        arrayList.add("xhtl");
    }
}