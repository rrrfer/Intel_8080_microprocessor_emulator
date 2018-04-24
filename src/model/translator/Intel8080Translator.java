package model.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Intel8080Translator implements ITranslator {

    private StringBuilder statusString;

    private ArrayList<String> commands;
    private LinkedHashMap<String, Integer> label2AddressMap;
    private ArrayList<String> label2AddressList;
    private ArrayList<String> commandsTemplateArray;
    private HashMap<String, Integer> commandsCodes;

    private int currentAddress;

    private boolean isCorrect;
    private boolean hasErrors;

    public Intel8080Translator() {
        commands = new ArrayList<>();
        label2AddressMap = new LinkedHashMap<>();
        label2AddressList = new ArrayList<>();
        statusString = new StringBuilder();

        commandsTemplateArray = new ArrayList<>();
        initCmdTemplateArray(commandsTemplateArray);

        commandsCodes = new HashMap<>();
        initCommandsCodes(commandsCodes);
    }

    @Override
    public String[] getLexemes(String programText) {

        commands.clear();
        currentAddress = 0;
        hasErrors = false;
        label2AddressMap.clear();
        label2AddressList.clear();
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
                                ":set." + Integer.toHexString(data));
                        currentAddress += 1;
                        continue;
                    }

                    hasErrors = true;
                }

                String labelName;
                if ((labelName = isLabel(dividedProgramLines[i])) != null) {
                    if (!label2AddressMap.containsKey(labelName)) {
                        label2AddressMap.put(labelName, currentAddress);
                        label2AddressList.add(labelName);
                        label2AddressList.add(Integer.toString(currentAddress, 16));
                        continue;
                    } else {
                        hasErrors = true;
                        statusString.append("Ошибка в строке № " + (i + 1) + " " +
                                "Повторное использование метки: " + dividedProgramLines[i]+
                                System.lineSeparator());
                    }
                }

                String cmdName;
                if((cmdName = isCommand(dividedProgramLines[i].split(" ")[0])) != null) {
                    shiftCurrentAddress(cmdName);
                }
            }

        }

        currentAddress = 0;

        // Разбор и трансляция команд
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
                        currentAddress += 1;
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
    public String getTranslationStatusString() {
        return statusString.toString();
    }

    @Override
    public int[] getCode(String lex) {

        int[] cmdArr = new int[4];
        cmdArr[0] = Integer.valueOf(lex.split(":")[0], 16);
        cmdArr[2] = -1;
        cmdArr[3] = -1;

        lex = lex.split(":")[1];

        if (lex.contains("set.")) {
            cmdArr[2] = Intel8080CommandsCodes.SET;
            cmdArr[1] = 1;
            cmdArr[3] = Integer.parseInt(lex.substring(4), 16);
            return cmdArr;
        }

        if (commandsCodes.containsKey(lex.toUpperCase())) {
            cmdArr[2] = commandsCodes.get(lex.toUpperCase());
            cmdArr[1] = Intel8080CommandsCodes.countByteInCommand(cmdArr[2]);
            return cmdArr;
        }

        if (lex.contains(",")) {
            String arrCmd[] = lex.split(",");
            if (commandsCodes.containsKey(arrCmd[0].toUpperCase())) {
                cmdArr[2] = commandsCodes.get(arrCmd[0].toUpperCase());
                cmdArr[1] = Intel8080CommandsCodes.countByteInCommand(cmdArr[2]);
                try {
                    cmdArr[3] = Integer.parseInt(arrCmd[1], 16);
                } catch (NumberFormatException e) {
                    cmdArr[0] = -1;
                    return cmdArr;
                }
                return cmdArr;
            }
        }

        String arrCmd[] = lex.split(" ");
        if (commandsCodes.containsKey(arrCmd[0].toUpperCase())) {
            cmdArr[2] = commandsCodes.get(arrCmd[0].toUpperCase());
            cmdArr[3] = Integer.parseInt(arrCmd[1], 16);
            cmdArr[1] = Intel8080CommandsCodes.countByteInCommand(cmdArr[2]);
            return cmdArr;
        }

        cmdArr[0] = -1;
        return cmdArr;
    }

    @Override
    public boolean hasTranslationErrors() {
        return hasErrors;
    }

    @Override
    public ArrayList<String> getLabel2AddressList() {
        return label2AddressList;
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
        if (lex.length() > 0) {
            if (lex.charAt(0) == ' ') {
                lex = lex.substring(1);
            }
        }
        if (lex.length() > 0) {
            if (lex.charAt(lex.length() - 1) == ' ') {
                lex = lex.substring(0, lex.length() - 1);
            }
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
                cmd.equals("push") || cmd.equals("pop")) {
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
        isCorrect = false;
        if ((cmdName = isCommand(lex.split(" ")[0])) != null) {
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
                        if (argument.length() == 1 || argument.equals("sp") || argument.equals("psw")) {
                            char a = argument.charAt(0);
                            if (cmdName.equals("ldax") || cmdName.equals("stax")) {
                                if (a != 'b' && a != 'd') {
                                    break;
                                }
                            } else if (cmdName.equals("dad") || cmdName.equals("inx") || cmdName.equals("dcx")) {
                                if (a != 'b' && a != 'd' && a != 'h' && !argument.equals("sp")) {
                                    break;
                                }
                            } else if (cmdName.equals("push") || cmdName.equals("pop")) {
                                if (a != 'b' && a != 'd' && a != 'h' && !argument.equals("psw")) {
                                    break;
                                }
                            } else {
                                if (a != 'a' && a != 'b' && a != 'c' && a != 'd' &&
                                        a != 'e' && a != 'h' && a != 'l' && a != 'm') {
                                    break;
                                }
                            }

                            if (a == 'a' || a == 'b' || a == 'c' || a == 'd' ||
                                    a == 'e' || a == 'h' || a == 'l' || a == 'm' || argument.equals("sp")
                                    || argument.equals("psw")) {
                                commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + argument);
                                currentAddress += 1;
                                isCorrect = true;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    if (lex.split(" ", 2).length == 2) {
                        String argument[] = lex.split(" ", 2)[1].split(",");
                        if (argument.length == 2) {
                            if (argument[0].length() == 1) {
                                char a = argument[0].charAt(0);
                                if (a == 'a' || a == 'b' || a == 'c' || a == 'd' || a == 'e' ||
                                        a == 'h' || a == 'l' || a == 'm') {
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
                        String argument[] = lex.split(" ", 2)[1].split(",");
                        if (argument.length == 2) {
                            if (argument[0].length() == 1 || argument[0].equals("sp")) {
                                char a = argument[0].charAt(0);
                                if (a == 'b' || a == 'd' || a == 'h' || argument[0].equals("sp")) {
                                    try {
                                        int numberArg = otherRadix2Dec(argument[1]);
                                        if (numberArg >= 0 && numberArg <= 65535) {

                                            commands.add(Integer.toHexString(currentAddress) + ":" + cmdName + " " + argument[0] + "," + Integer.toHexString(numberArg));
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
                    String argument[] = lex.split(" ", 2);
                    if (argument.length == 2) {
                        argument = argument[1].split(",");
                    }
                    if (argument.length == 2) {
                        if (argument[0].length() == 1 && argument[1].length() == 1) {
                            char a = argument[0].charAt(0);
                            if (a == 'a' || a == 'b' || a == 'c' || a == 'd' || a == 'e' ||
                                    a == 'h' || a == 'l' || a == 'm') {
                                a = argument[1].charAt(0);
                                if (a == 'a' || a == 'b' || a == 'c' || a == 'd' || a == 'e' ||
                                        a == 'h' || a == 'l' || a == 'm') {

                                    if (argument[0].charAt(0) == 'm') {
                                        if (argument[1].charAt(0) == 'm') {
                                            break;
                                        }
                                    }

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
        }

        if (!isCorrect) {
            statusString.append("Ошибка в строке № " + (lineNumber + 1) + " " +
                    "Неверная команда, аргумент, директива или несуществующая метка: " + lex +
                    System.lineSeparator());
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
        arrayList.add("jm");
        arrayList.add("jpe");
        arrayList.add("jpo");
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
        arrayList.add("xri");
        arrayList.add("xthl");
    }

    private void initCommandsCodes(HashMap<String, Integer> hashMap) {

        hashMap.put("NOP", Intel8080CommandsCodes.NOP);

        hashMap.put("MVI A", Intel8080CommandsCodes.MVI_A);
        hashMap.put("MVI B", Intel8080CommandsCodes.MVI_B);
        hashMap.put("MVI C", Intel8080CommandsCodes.MVI_C);
        hashMap.put("MVI D", Intel8080CommandsCodes.MVI_D);
        hashMap.put("MVI E", Intel8080CommandsCodes.MVI_E);
        hashMap.put("MVI H", Intel8080CommandsCodes.MVI_H);
        hashMap.put("MVI L", Intel8080CommandsCodes.MVI_L);
        hashMap.put("MVI M", Intel8080CommandsCodes.MVI_M);

        hashMap.put("LXI B", Intel8080CommandsCodes.LXI_B_data);
        hashMap.put("LXI D", Intel8080CommandsCodes.LXI_D_data);
        hashMap.put("LXI H", Intel8080CommandsCodes.LXI_H_data);
        hashMap.put("LXI SP", Intel8080CommandsCodes.LXI_SP_data);

        hashMap.put("MOV A,A", Intel8080CommandsCodes.MOV_A_A);
        hashMap.put("MOV A,B", Intel8080CommandsCodes.MOV_A_B);
        hashMap.put("MOV A,C", Intel8080CommandsCodes.MOV_A_C);
        hashMap.put("MOV A,D", Intel8080CommandsCodes.MOV_A_D);
        hashMap.put("MOV A,E", Intel8080CommandsCodes.MOV_A_E);
        hashMap.put("MOV A,H", Intel8080CommandsCodes.MOV_A_H);
        hashMap.put("MOV A,L", Intel8080CommandsCodes.MOV_A_L);
        hashMap.put("MOV A,M", Intel8080CommandsCodes.MOV_A_M);

        hashMap.put("MOV B,A", Intel8080CommandsCodes.MOV_B_A);
        hashMap.put("MOV B,B", Intel8080CommandsCodes.MOV_B_B);
        hashMap.put("MOV B,C", Intel8080CommandsCodes.MOV_B_C);
        hashMap.put("MOV B,D", Intel8080CommandsCodes.MOV_B_D);
        hashMap.put("MOV B,E", Intel8080CommandsCodes.MOV_B_E);
        hashMap.put("MOV B,H", Intel8080CommandsCodes.MOV_B_H);
        hashMap.put("MOV B,L", Intel8080CommandsCodes.MOV_B_L);
        hashMap.put("MOV B,M", Intel8080CommandsCodes.MOV_B_M);

        hashMap.put("MOV C,A", Intel8080CommandsCodes.MOV_C_A);
        hashMap.put("MOV C,B", Intel8080CommandsCodes.MOV_C_B);
        hashMap.put("MOV C,C", Intel8080CommandsCodes.MOV_C_C);
        hashMap.put("MOV C,D", Intel8080CommandsCodes.MOV_C_D);
        hashMap.put("MOV C,E", Intel8080CommandsCodes.MOV_C_E);
        hashMap.put("MOV C,H", Intel8080CommandsCodes.MOV_C_H);
        hashMap.put("MOV C,L", Intel8080CommandsCodes.MOV_C_L);
        hashMap.put("MOV C,M", Intel8080CommandsCodes.MOV_C_M);

        hashMap.put("MOV D,A", Intel8080CommandsCodes.MOV_D_A);
        hashMap.put("MOV D,B", Intel8080CommandsCodes.MOV_D_B);
        hashMap.put("MOV D,C", Intel8080CommandsCodes.MOV_D_C);
        hashMap.put("MOV D,D", Intel8080CommandsCodes.MOV_D_D);
        hashMap.put("MOV D,E", Intel8080CommandsCodes.MOV_D_E);
        hashMap.put("MOV D,H", Intel8080CommandsCodes.MOV_D_H);
        hashMap.put("MOV D,L", Intel8080CommandsCodes.MOV_D_L);
        hashMap.put("MOV D,M", Intel8080CommandsCodes.MOV_D_M);

        hashMap.put("MOV E,A", Intel8080CommandsCodes.MOV_E_A);
        hashMap.put("MOV E,B", Intel8080CommandsCodes.MOV_E_B);
        hashMap.put("MOV E,C", Intel8080CommandsCodes.MOV_E_C);
        hashMap.put("MOV E,D", Intel8080CommandsCodes.MOV_E_D);
        hashMap.put("MOV E,E", Intel8080CommandsCodes.MOV_E_E);
        hashMap.put("MOV E,H", Intel8080CommandsCodes.MOV_E_H);
        hashMap.put("MOV E,L", Intel8080CommandsCodes.MOV_E_L);
        hashMap.put("MOV E,M", Intel8080CommandsCodes.MOV_E_M);

        hashMap.put("MOV H,A", Intel8080CommandsCodes.MOV_H_A);
        hashMap.put("MOV H,B", Intel8080CommandsCodes.MOV_H_B);
        hashMap.put("MOV H,C", Intel8080CommandsCodes.MOV_H_C);
        hashMap.put("MOV H,D", Intel8080CommandsCodes.MOV_H_D);
        hashMap.put("MOV H,E", Intel8080CommandsCodes.MOV_H_E);
        hashMap.put("MOV H,H", Intel8080CommandsCodes.MOV_H_H);
        hashMap.put("MOV H,L", Intel8080CommandsCodes.MOV_H_L);
        hashMap.put("MOV H,M", Intel8080CommandsCodes.MOV_H_M);

        hashMap.put("MOV L,A", Intel8080CommandsCodes.MOV_L_A);
        hashMap.put("MOV L,B", Intel8080CommandsCodes.MOV_L_B);
        hashMap.put("MOV L,C", Intel8080CommandsCodes.MOV_L_C);
        hashMap.put("MOV L,D", Intel8080CommandsCodes.MOV_L_D);
        hashMap.put("MOV L,E", Intel8080CommandsCodes.MOV_L_E);
        hashMap.put("MOV L,H", Intel8080CommandsCodes.MOV_L_H);
        hashMap.put("MOV L,L", Intel8080CommandsCodes.MOV_L_L);
        hashMap.put("MOV L,M", Intel8080CommandsCodes.MOV_L_M);

        hashMap.put("MOV M,A", Intel8080CommandsCodes.MOV_M_A);
        hashMap.put("MOV M,B", Intel8080CommandsCodes.MOV_M_B);
        hashMap.put("MOV M,C", Intel8080CommandsCodes.MOV_M_C);
        hashMap.put("MOV M,D", Intel8080CommandsCodes.MOV_M_D);
        hashMap.put("MOV M,E", Intel8080CommandsCodes.MOV_M_E);
        hashMap.put("MOV M,H", Intel8080CommandsCodes.MOV_M_H);
        hashMap.put("MOV M,L", Intel8080CommandsCodes.MOV_M_L);

        hashMap.put("ADD A", Intel8080CommandsCodes.ADD_A);
        hashMap.put("ADD B", Intel8080CommandsCodes.ADD_B);
        hashMap.put("ADD C", Intel8080CommandsCodes.ADD_C);
        hashMap.put("ADD D", Intel8080CommandsCodes.ADD_D);
        hashMap.put("ADD E", Intel8080CommandsCodes.ADD_E);
        hashMap.put("ADD H", Intel8080CommandsCodes.ADD_H);
        hashMap.put("ADD L", Intel8080CommandsCodes.ADD_L);
        hashMap.put("ADD M", Intel8080CommandsCodes.ADD_M);

        hashMap.put("ADI", Intel8080CommandsCodes.ADI);

        hashMap.put("ADC A", Intel8080CommandsCodes.ADC_A);
        hashMap.put("ADC B", Intel8080CommandsCodes.ADC_B);
        hashMap.put("ADC C", Intel8080CommandsCodes.ADC_C);
        hashMap.put("ADC D", Intel8080CommandsCodes.ADC_D);
        hashMap.put("ADC E", Intel8080CommandsCodes.ADC_E);
        hashMap.put("ADC H", Intel8080CommandsCodes.ADC_H);
        hashMap.put("ADC L", Intel8080CommandsCodes.ADC_L);
        hashMap.put("ADC M", Intel8080CommandsCodes.ADC_M);

        hashMap.put("ACI", Intel8080CommandsCodes.ACI);

        hashMap.put("SUB A", Intel8080CommandsCodes.SUB_A);
        hashMap.put("SUB B", Intel8080CommandsCodes.SUB_B);
        hashMap.put("SUB C", Intel8080CommandsCodes.SUB_C);
        hashMap.put("SUB D", Intel8080CommandsCodes.SUB_D);
        hashMap.put("SUB E", Intel8080CommandsCodes.SUB_E);
        hashMap.put("SUB H", Intel8080CommandsCodes.SUB_H);
        hashMap.put("SUB L", Intel8080CommandsCodes.SUB_L);
        hashMap.put("SUB M", Intel8080CommandsCodes.SUB_M);

        hashMap.put("SUI", Intel8080CommandsCodes.SUI);

        hashMap.put("SBB A", Intel8080CommandsCodes.SBB_A);
        hashMap.put("SBB B", Intel8080CommandsCodes.SBB_B);
        hashMap.put("SBB C", Intel8080CommandsCodes.SBB_C);
        hashMap.put("SBB D", Intel8080CommandsCodes.SBB_D);
        hashMap.put("SBB E", Intel8080CommandsCodes.SBB_E);
        hashMap.put("SBB H", Intel8080CommandsCodes.SBB_H);
        hashMap.put("SBB L", Intel8080CommandsCodes.SBB_L);
        hashMap.put("SBB M", Intel8080CommandsCodes.SBB_M);

        hashMap.put("SBI", Intel8080CommandsCodes.SBI);

        hashMap.put("INR A", Intel8080CommandsCodes.INR_A);
        hashMap.put("INR B", Intel8080CommandsCodes.INR_B);
        hashMap.put("INR C", Intel8080CommandsCodes.INR_C);
        hashMap.put("INR D", Intel8080CommandsCodes.INR_D);
        hashMap.put("INR E", Intel8080CommandsCodes.INR_E);
        hashMap.put("INR H", Intel8080CommandsCodes.INR_H);
        hashMap.put("INR L", Intel8080CommandsCodes.INR_L);
        hashMap.put("INR M", Intel8080CommandsCodes.INR_M);

        hashMap.put("INX B", Intel8080CommandsCodes.INX_B);
        hashMap.put("INX D", Intel8080CommandsCodes.INX_D);
        hashMap.put("INX H", Intel8080CommandsCodes.INX_H);
        hashMap.put("INX SP", Intel8080CommandsCodes.INX_SP);

        hashMap.put("DCR A", Intel8080CommandsCodes.DCR_A);
        hashMap.put("DCR B", Intel8080CommandsCodes.DCR_B);
        hashMap.put("DCR C", Intel8080CommandsCodes.DCR_C);
        hashMap.put("DCR D", Intel8080CommandsCodes.DCR_D);
        hashMap.put("DCR E", Intel8080CommandsCodes.DCR_E);
        hashMap.put("DCR H", Intel8080CommandsCodes.DCR_H);
        hashMap.put("DCR L", Intel8080CommandsCodes.DCR_L);
        hashMap.put("DCR M", Intel8080CommandsCodes.DCR_M);

        hashMap.put("DCX B", Intel8080CommandsCodes.DCX_B);
        hashMap.put("DCX D", Intel8080CommandsCodes.DCX_D);
        hashMap.put("DCX H", Intel8080CommandsCodes.DCX_H);
        hashMap.put("DCX SP", Intel8080CommandsCodes.DCX_SP);

        hashMap.put("HLT", Intel8080CommandsCodes.HLT);

        hashMap.put("JNZ", Intel8080CommandsCodes.JNZ);
        hashMap.put("JZ", Intel8080CommandsCodes.JZ);
        hashMap.put("JNC", Intel8080CommandsCodes.JNC);
        hashMap.put("JC", Intel8080CommandsCodes.JC);
        hashMap.put("JP", Intel8080CommandsCodes.JP);
        hashMap.put("JM", Intel8080CommandsCodes.JM);
        hashMap.put("JMP", Intel8080CommandsCodes.JMP);
        hashMap.put("JPO", Intel8080CommandsCodes.JPO);
        hashMap.put("JPE", Intel8080CommandsCodes.JPE);

        hashMap.put("LDA", Intel8080CommandsCodes.LDA);

        hashMap.put("STA", Intel8080CommandsCodes.STA);

        hashMap.put("LHLD", Intel8080CommandsCodes.LHLD);

        hashMap.put("SHLD", Intel8080CommandsCodes.SHLD);

        hashMap.put("LDAX B", Intel8080CommandsCodes.LDAX_B);
        hashMap.put("LDAX D", Intel8080CommandsCodes.LDAX_D);

        hashMap.put("STAX B", Intel8080CommandsCodes.STAX_B);
        hashMap.put("STAX D", Intel8080CommandsCodes.STAX_D);

        hashMap.put("XCHG", Intel8080CommandsCodes.XCHG);

        hashMap.put("RLC", Intel8080CommandsCodes.RLC);
        hashMap.put("RRC", Intel8080CommandsCodes.RRC);

        hashMap.put("RAL", Intel8080CommandsCodes.RAL);
        hashMap.put("RAR", Intel8080CommandsCodes.RAR);

        hashMap.put("DAD B", Intel8080CommandsCodes.DAD_B);
        hashMap.put("DAD D", Intel8080CommandsCodes.DAD_D);
        hashMap.put("DAD H", Intel8080CommandsCodes.DAD_H);
        hashMap.put("DAD SP", Intel8080CommandsCodes.DAD_SP);

        hashMap.put("ANA A", Intel8080CommandsCodes.ANA_A);
        hashMap.put("ANA B", Intel8080CommandsCodes.ANA_B);
        hashMap.put("ANA C", Intel8080CommandsCodes.ANA_C);
        hashMap.put("ANA D", Intel8080CommandsCodes.ANA_D);
        hashMap.put("ANA E", Intel8080CommandsCodes.ANA_E);
        hashMap.put("ANA H", Intel8080CommandsCodes.ANA_H);
        hashMap.put("ANA L", Intel8080CommandsCodes.ANA_L);
        hashMap.put("ANA M", Intel8080CommandsCodes.ANA_M);

        hashMap.put("ANI", Intel8080CommandsCodes.ANI);

        hashMap.put("ORA A", Intel8080CommandsCodes.ORA_A);
        hashMap.put("ORA B", Intel8080CommandsCodes.ORA_B);
        hashMap.put("ORA C", Intel8080CommandsCodes.ORA_C);
        hashMap.put("ORA D", Intel8080CommandsCodes.ORA_D);
        hashMap.put("ORA E", Intel8080CommandsCodes.ORA_E);
        hashMap.put("ORA H", Intel8080CommandsCodes.ORA_H);
        hashMap.put("ORA L", Intel8080CommandsCodes.ORA_L);
        hashMap.put("ORA M", Intel8080CommandsCodes.ORA_M);

        hashMap.put("ORI", Intel8080CommandsCodes.ORI);

        hashMap.put("XRA A", Intel8080CommandsCodes.XRA_A);
        hashMap.put("XRA B", Intel8080CommandsCodes.XRA_B);
        hashMap.put("XRA C", Intel8080CommandsCodes.XRA_C);
        hashMap.put("XRA D", Intel8080CommandsCodes.XRA_D);
        hashMap.put("XRA E", Intel8080CommandsCodes.XRA_E);
        hashMap.put("XRA H", Intel8080CommandsCodes.XRA_H);
        hashMap.put("XRA L", Intel8080CommandsCodes.XRA_L);
        hashMap.put("XRA M", Intel8080CommandsCodes.XRA_M);

        hashMap.put("XRI", Intel8080CommandsCodes.XRI);

        hashMap.put("CMP A", Intel8080CommandsCodes.CMP_A);
        hashMap.put("CMP B", Intel8080CommandsCodes.CMP_B);
        hashMap.put("CMP C", Intel8080CommandsCodes.CMP_C);
        hashMap.put("CMP D", Intel8080CommandsCodes.CMP_D);
        hashMap.put("CMP E", Intel8080CommandsCodes.CMP_E);
        hashMap.put("CMP H", Intel8080CommandsCodes.CMP_H);
        hashMap.put("CMP L", Intel8080CommandsCodes.CMP_L);
        hashMap.put("CMP M", Intel8080CommandsCodes.CMP_M);

        hashMap.put("CPI", Intel8080CommandsCodes.CPI);

        hashMap.put("IN", Intel8080CommandsCodes.IN);
        hashMap.put("OUT", Intel8080CommandsCodes.OUT);

        hashMap.put("STC", Intel8080CommandsCodes.STC);
        hashMap.put("CMC", Intel8080CommandsCodes.CMC);
        hashMap.put("CMA", Intel8080CommandsCodes.CMA);

        hashMap.put("PCHL", Intel8080CommandsCodes.PCHL);
        hashMap.put("SPHL", Intel8080CommandsCodes.SPHL);

        hashMap.put("CALL", Intel8080CommandsCodes.CALL);
        hashMap.put("RET", Intel8080CommandsCodes.RET);

        hashMap.put("CNZ", Intel8080CommandsCodes.CNZ);
        hashMap.put("CZ", Intel8080CommandsCodes.CZ);
        hashMap.put("CNC", Intel8080CommandsCodes.CNC);
        hashMap.put("CC", Intel8080CommandsCodes.CC);
        hashMap.put("CPO", Intel8080CommandsCodes.CPO);
        hashMap.put("CPE", Intel8080CommandsCodes.CPE);
        hashMap.put("CP", Intel8080CommandsCodes.CP);
        hashMap.put("CM", Intel8080CommandsCodes.CM);

        hashMap.put("RNZ", Intel8080CommandsCodes.RNZ);
        hashMap.put("RZ", Intel8080CommandsCodes.RZ);
        hashMap.put("RNC", Intel8080CommandsCodes.RNC);
        hashMap.put("RC", Intel8080CommandsCodes.RC);
        hashMap.put("RPO", Intel8080CommandsCodes.RPO);
        hashMap.put("RPE", Intel8080CommandsCodes.RPE);
        hashMap.put("RP", Intel8080CommandsCodes.RP);
        hashMap.put("RM", Intel8080CommandsCodes.RM);

        hashMap.put("PUSH B", Intel8080CommandsCodes.PUSH_B);
        hashMap.put("PUSH D", Intel8080CommandsCodes.PUSH_D);
        hashMap.put("PUSH H", Intel8080CommandsCodes.PUSH_H);
        hashMap.put("PUSH PSW", Intel8080CommandsCodes.PUSH_PSW);

        hashMap.put("POP B", Intel8080CommandsCodes.POP_B);
        hashMap.put("POP D", Intel8080CommandsCodes.POP_D);
        hashMap.put("POP H", Intel8080CommandsCodes.POP_H);
        hashMap.put("POP PSW", Intel8080CommandsCodes.POP_PSW);

        hashMap.put("XTHL", Intel8080CommandsCodes.XTHL);
    }
}

