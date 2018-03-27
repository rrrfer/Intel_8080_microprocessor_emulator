package translator;

import java.util.ArrayList;
import java.util.HashMap;

public class Intel8080Translator implements ITranslator {

    private StringBuilder statusString;

    private ArrayList<String> commands;
    private HashMap<String, Integer> label2AddressMap;
    private ArrayList<String> commandsTemplateArray;
    private HashMap<String, Integer> commandsCodes;

    private int currentAddress;

    private boolean isCorrect;
    private boolean hasErrors;

    public Intel8080Translator() {
        commands = new ArrayList<>();
        label2AddressMap = new HashMap<>();
        statusString = new StringBuilder();

        commandsTemplateArray = new ArrayList<>();
        initCmdTemplateArray(commandsTemplateArray);

        commandsCodes = new HashMap<>();
        initCommandsCodes(commandsCodes);
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
                                ":set." + Integer.toHexString(data));
                        currentAddress += 1;
                        continue;
                    }

                    hasErrors = true;
                    statusString.append("Ошибка в строке № " + (i + 1) + ". " +
                            "Неизвестная или ошибочная директива: " + dividedProgramLines[i] +
                            System.lineSeparator());

                }

                String labelName;
                if ((labelName = isLabel(dividedProgramLines[i])) != null) {
                    if (!label2AddressMap.containsKey(labelName)) {
                        label2AddressMap.put(labelName, currentAddress);
                        continue;
                    } else {
                        hasErrors = true;
                        statusString.append("Ошибка в строке №" + (i + 1) + ". " +
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
    public boolean getStatusFlag() {
        return hasErrors;
    }

    @Override
    public String getStatusString() {
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
            cmdArr[2] = CommandsCodes.SET;
            cmdArr[1] = 1;
            cmdArr[3] = Integer.parseInt(lex.substring(4), 16);
            return cmdArr;
        }

        if (commandsCodes.containsKey(lex.toUpperCase())) {
            cmdArr[2] = commandsCodes.get(lex.toUpperCase());
            cmdArr[1] = CommandsCodes.countByteInCommand(cmdArr[2]);
            return cmdArr;
        }

        if (lex.contains(",")) {
            String arrCmd[] = lex.split(",");
            if (commandsCodes.containsKey(arrCmd[0].toUpperCase())) {
                cmdArr[2] = commandsCodes.get(arrCmd[0].toUpperCase());
                cmdArr[1] = CommandsCodes.countByteInCommand(cmdArr[2]);
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
            cmdArr[1] = CommandsCodes.countByteInCommand(cmdArr[2]);
            return cmdArr;
        }

        cmdArr[0] = -1;
        return cmdArr;
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
        }

        if (!isCorrect) {
            statusString.append("Ошибка в строке № " + (lineNumber + 1) + ". " +
                    "Неверная команда или несуществующая метка: " + lex +
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

    private void initCommandsCodes(HashMap<String, Integer> hashMap) {

        hashMap.put("NOP", CommandsCodes.NOP);

        hashMap.put("MVI A", CommandsCodes.MVI_A);
        hashMap.put("MVI B", CommandsCodes.MVI_B);
        hashMap.put("MVI C", CommandsCodes.MVI_C);
        hashMap.put("MVI D", CommandsCodes.MVI_D);
        hashMap.put("MVI E", CommandsCodes.MVI_E);
        hashMap.put("MVI H", CommandsCodes.MVI_H);
        hashMap.put("MVI L", CommandsCodes.MVI_L);
        hashMap.put("MVI M", CommandsCodes.MVI_M);

        hashMap.put("LXI B", CommandsCodes.LXI_B_data);
        hashMap.put("LXI D", CommandsCodes.LXI_D_data);
        hashMap.put("LXI H", CommandsCodes.LXI_H_data);
        hashMap.put("LXI PWS", CommandsCodes.LXI_PSW_data);

        hashMap.put("MOV A,A", CommandsCodes.MOV_A_A);
        hashMap.put("MOV A,B", CommandsCodes.MOV_A_B);
        hashMap.put("MOV A,C", CommandsCodes.MOV_A_C);
        hashMap.put("MOV A,D", CommandsCodes.MOV_A_D);
        hashMap.put("MOV A,E", CommandsCodes.MOV_A_E);
        hashMap.put("MOV A,H", CommandsCodes.MOV_A_H);
        hashMap.put("MOV A,L", CommandsCodes.MOV_A_L);
        hashMap.put("MOV A,M", CommandsCodes.MOV_A_M);

        hashMap.put("MOV B,A", CommandsCodes.MOV_B_A);
        hashMap.put("MOV B,B", CommandsCodes.MOV_B_B);
        hashMap.put("MOV B,C", CommandsCodes.MOV_B_C);
        hashMap.put("MOV B,D", CommandsCodes.MOV_B_D);
        hashMap.put("MOV B,E", CommandsCodes.MOV_B_E);
        hashMap.put("MOV B,H", CommandsCodes.MOV_B_H);
        hashMap.put("MOV B,L", CommandsCodes.MOV_B_L);
        hashMap.put("MOV B,M", CommandsCodes.MOV_B_M);

        hashMap.put("MOV C,A", CommandsCodes.MOV_C_A);
        hashMap.put("MOV C,B", CommandsCodes.MOV_C_B);
        hashMap.put("MOV C,C", CommandsCodes.MOV_C_C);
        hashMap.put("MOV C,D", CommandsCodes.MOV_C_D);
        hashMap.put("MOV C,E", CommandsCodes.MOV_C_E);
        hashMap.put("MOV C,H", CommandsCodes.MOV_C_H);
        hashMap.put("MOV C,L", CommandsCodes.MOV_C_L);
        hashMap.put("MOV C,M", CommandsCodes.MOV_C_M);

        hashMap.put("MOV D,A", CommandsCodes.MOV_D_A);
        hashMap.put("MOV D,B", CommandsCodes.MOV_D_B);
        hashMap.put("MOV D,C", CommandsCodes.MOV_D_C);
        hashMap.put("MOV D,D", CommandsCodes.MOV_D_D);
        hashMap.put("MOV D,E", CommandsCodes.MOV_D_E);
        hashMap.put("MOV D,H", CommandsCodes.MOV_D_H);
        hashMap.put("MOV D,L", CommandsCodes.MOV_D_L);
        hashMap.put("MOV D,M", CommandsCodes.MOV_D_M);

        hashMap.put("MOV E,A", CommandsCodes.MOV_E_A);
        hashMap.put("MOV E,B", CommandsCodes.MOV_E_B);
        hashMap.put("MOV E,C", CommandsCodes.MOV_E_C);
        hashMap.put("MOV E,D", CommandsCodes.MOV_E_D);
        hashMap.put("MOV E,E", CommandsCodes.MOV_E_E);
        hashMap.put("MOV E,H", CommandsCodes.MOV_E_H);
        hashMap.put("MOV E,L", CommandsCodes.MOV_E_L);
        hashMap.put("MOV E,M", CommandsCodes.MOV_E_M);

        hashMap.put("MOV H,A", CommandsCodes.MOV_H_A);
        hashMap.put("MOV H,B", CommandsCodes.MOV_H_B);
        hashMap.put("MOV H,C", CommandsCodes.MOV_H_C);
        hashMap.put("MOV H,D", CommandsCodes.MOV_H_D);
        hashMap.put("MOV H,E", CommandsCodes.MOV_H_E);
        hashMap.put("MOV H,H", CommandsCodes.MOV_H_H);
        hashMap.put("MOV H,L", CommandsCodes.MOV_H_L);
        hashMap.put("MOV H,M", CommandsCodes.MOV_H_M);

        hashMap.put("MOV L,A", CommandsCodes.MOV_L_A);
        hashMap.put("MOV L,B", CommandsCodes.MOV_L_B);
        hashMap.put("MOV L,C", CommandsCodes.MOV_L_C);
        hashMap.put("MOV L,D", CommandsCodes.MOV_L_D);
        hashMap.put("MOV L,E", CommandsCodes.MOV_L_E);
        hashMap.put("MOV L,H", CommandsCodes.MOV_L_H);
        hashMap.put("MOV L,L", CommandsCodes.MOV_L_L);
        hashMap.put("MOV L,M", CommandsCodes.MOV_L_M);

        hashMap.put("MOV M,A", CommandsCodes.MOV_M_A);
        hashMap.put("MOV M,B", CommandsCodes.MOV_M_B);
        hashMap.put("MOV M,C", CommandsCodes.MOV_M_C);
        hashMap.put("MOV M,D", CommandsCodes.MOV_M_D);
        hashMap.put("MOV M,E", CommandsCodes.MOV_M_E);
        hashMap.put("MOV M,H", CommandsCodes.MOV_M_H);
        hashMap.put("MOV M,L", CommandsCodes.MOV_M_L);
        hashMap.put("MOV M,M", CommandsCodes.MOV_M_M);

        hashMap.put("ADD A", CommandsCodes.ADD_A);
        hashMap.put("ADD B", CommandsCodes.ADD_B);
        hashMap.put("ADD C", CommandsCodes.ADD_C);
        hashMap.put("ADD D", CommandsCodes.ADD_D);
        hashMap.put("ADD E", CommandsCodes.ADD_E);
        hashMap.put("ADD H", CommandsCodes.ADD_H);
        hashMap.put("ADD L", CommandsCodes.ADD_L);
        hashMap.put("ADD M", CommandsCodes.ADD_M);

        hashMap.put("ADI", CommandsCodes.ADI);

        hashMap.put("ADC A", CommandsCodes.ADC_A);
        hashMap.put("ADC B", CommandsCodes.ADC_B);
        hashMap.put("ADC C", CommandsCodes.ADC_C);
        hashMap.put("ADC D", CommandsCodes.ADC_D);
        hashMap.put("ADC E", CommandsCodes.ADC_E);
        hashMap.put("ADC H", CommandsCodes.ADC_H);
        hashMap.put("ADC L", CommandsCodes.ADC_L);
        hashMap.put("ADC M", CommandsCodes.ADC_M);

        hashMap.put("ACI", CommandsCodes.ACI);

        hashMap.put("SUB A", CommandsCodes.SUB_A);
        hashMap.put("SUB B", CommandsCodes.SUB_B);
        hashMap.put("SUB C", CommandsCodes.SUB_C);
        hashMap.put("SUB D", CommandsCodes.SUB_D);
        hashMap.put("SUB E", CommandsCodes.SUB_E);
        hashMap.put("SUB H", CommandsCodes.SUB_H);
        hashMap.put("SUB L", CommandsCodes.SUB_L);
        hashMap.put("SUB M", CommandsCodes.SUB_M);

        hashMap.put("SUI", CommandsCodes.SUI);

        hashMap.put("SBB A", CommandsCodes.SBB_A);
        hashMap.put("SBB B", CommandsCodes.SBB_B);
        hashMap.put("SBB C", CommandsCodes.SBB_C);
        hashMap.put("SBB D", CommandsCodes.SBB_D);
        hashMap.put("SBB E", CommandsCodes.SBB_E);
        hashMap.put("SBB H", CommandsCodes.SBB_H);
        hashMap.put("SBB L", CommandsCodes.SBB_L);
        hashMap.put("SBB M", CommandsCodes.SBB_M);

        hashMap.put("SBI", CommandsCodes.SBI);

        hashMap.put("INR A", CommandsCodes.INR_A);
        hashMap.put("INR B", CommandsCodes.INR_B);
        hashMap.put("INR C", CommandsCodes.INR_C);
        hashMap.put("INR D", CommandsCodes.INR_D);
        hashMap.put("INR E", CommandsCodes.INR_E);
        hashMap.put("INR H", CommandsCodes.INR_H);
        hashMap.put("INR L", CommandsCodes.INR_L);
        hashMap.put("INR M", CommandsCodes.INR_M);

        hashMap.put("INX B", CommandsCodes.INX_B);
        hashMap.put("INX D", CommandsCodes.INX_D);
        hashMap.put("INX H", CommandsCodes.INX_H);
        hashMap.put("INX PSW", CommandsCodes.INX_PSW);

        hashMap.put("DCR A", CommandsCodes.DCR_A);
        hashMap.put("DCR B", CommandsCodes.DCR_B);
        hashMap.put("DCR C", CommandsCodes.DCR_C);
        hashMap.put("DCR D", CommandsCodes.DCR_D);
        hashMap.put("DCR E", CommandsCodes.DCR_E);
        hashMap.put("DCR H", CommandsCodes.DCR_H);
        hashMap.put("DCR L", CommandsCodes.DCR_L);
        hashMap.put("DCR M", CommandsCodes.DCR_M);

        hashMap.put("DCX B", CommandsCodes.DCX_B);
        hashMap.put("DCX D", CommandsCodes.DCX_D);
        hashMap.put("DCX H", CommandsCodes.DCX_H);
        hashMap.put("DCX PSW", CommandsCodes.DCX_PSW);

        hashMap.put("HLT", CommandsCodes.HLT);

        hashMap.put("JNZ", CommandsCodes.JNZ);
        hashMap.put("JZ", CommandsCodes.JZ);
        hashMap.put("JNC", CommandsCodes.JNC);
        hashMap.put("JC", CommandsCodes.JC);
        hashMap.put("JP", CommandsCodes.JP);
        hashMap.put("JM", CommandsCodes.JM);
        hashMap.put("JMP", CommandsCodes.JMP);
        hashMap.put("JPO", CommandsCodes.JPO);
        hashMap.put("JPE", CommandsCodes.JPE);

        hashMap.put("LDA", CommandsCodes.LDA);

        hashMap.put("STA", CommandsCodes.STA);

        hashMap.put("LHLD", CommandsCodes.LHLD);

        hashMap.put("SHLD", CommandsCodes.SHLD);

        hashMap.put("LDAX B", CommandsCodes.LDAX_B);
        hashMap.put("LDAX D", CommandsCodes.LDAX_D);

        hashMap.put("STAX B", CommandsCodes.STAX_B);
        hashMap.put("STAX D", CommandsCodes.STAX_D);

        hashMap.put("XCHG", CommandsCodes.XCHG);

        hashMap.put("RLC", CommandsCodes.RLC);
        hashMap.put("RRC", CommandsCodes.RRC);

        hashMap.put("RAL", CommandsCodes.RAL);
        hashMap.put("RAR", CommandsCodes.RAR);

        hashMap.put("DAD B", CommandsCodes.DAD_B);
        hashMap.put("DAD D", CommandsCodes.DAD_D);
        hashMap.put("DAD H", CommandsCodes.DAD_H);
        hashMap.put("DAD PSW", CommandsCodes.DAD_PSW);

        hashMap.put("ANA A", CommandsCodes.ANA_A);
        hashMap.put("ANA B", CommandsCodes.ANA_B);
        hashMap.put("ANA C", CommandsCodes.ANA_C);
        hashMap.put("ANA D", CommandsCodes.ANA_D);
        hashMap.put("ANA E", CommandsCodes.ANA_E);
        hashMap.put("ANA H", CommandsCodes.ANA_H);
        hashMap.put("ANA L", CommandsCodes.ANA_L);
        hashMap.put("ANA M", CommandsCodes.ANA_M);

        hashMap.put("ANI", CommandsCodes.ANI);

        hashMap.put("ORA A", CommandsCodes.ORA_A);
        hashMap.put("ORA B", CommandsCodes.ORA_B);
        hashMap.put("ORA C", CommandsCodes.ORA_C);
        hashMap.put("ORA D", CommandsCodes.ORA_D);
        hashMap.put("ORA E", CommandsCodes.ORA_E);
        hashMap.put("ORA H", CommandsCodes.ORA_H);
        hashMap.put("ORA L", CommandsCodes.ORA_L);
        hashMap.put("ORA M", CommandsCodes.ORA_M);

        hashMap.put("ORI", CommandsCodes.ORI);

        hashMap.put("XRA A", CommandsCodes.XRA_A);
        hashMap.put("XRA B", CommandsCodes.XRA_B);
        hashMap.put("XRA C", CommandsCodes.XRA_C);
        hashMap.put("XRA D", CommandsCodes.XRA_D);
        hashMap.put("XRA E", CommandsCodes.XRA_E);
        hashMap.put("XRA H", CommandsCodes.XRA_H);
        hashMap.put("XRA L", CommandsCodes.XRA_L);
        hashMap.put("XRA M", CommandsCodes.XRA_M);

        hashMap.put("XRI", CommandsCodes.XRI);

        hashMap.put("CMP A", CommandsCodes.CMP_A);
        hashMap.put("CMP B", CommandsCodes.CMP_B);
        hashMap.put("CMP C", CommandsCodes.CMP_C);
        hashMap.put("CMP D", CommandsCodes.CMP_D);
        hashMap.put("CMP E", CommandsCodes.CMP_E);
        hashMap.put("CMP H", CommandsCodes.CMP_H);
        hashMap.put("CMP L", CommandsCodes.CMP_L);
        hashMap.put("CMP M", CommandsCodes.CMP_M);

        hashMap.put("CPI", CommandsCodes.CPI);

        hashMap.put("IN", CommandsCodes.IN);
        hashMap.put("OUT", CommandsCodes.OUT);

        hashMap.put("STC", CommandsCodes.STC);
        hashMap.put("CMC", CommandsCodes.CMC);
        hashMap.put("CMA", CommandsCodes.CMA);

        hashMap.put("PCHL", CommandsCodes.PCHL);
        hashMap.put("SPHL", CommandsCodes.SPHL);

        hashMap.put("CALL", CommandsCodes.CALL);
        hashMap.put("RET", CommandsCodes.RET);

        hashMap.put("CNZ", CommandsCodes.CNZ);
        hashMap.put("CZ", CommandsCodes.CZ);
        hashMap.put("CNC", CommandsCodes.CNC);
        hashMap.put("CC", CommandsCodes.CC);
        hashMap.put("CPO", CommandsCodes.CPO);
        hashMap.put("CPE", CommandsCodes.CPE);
        hashMap.put("CP", CommandsCodes.CP);
        hashMap.put("CM", CommandsCodes.CM);

        hashMap.put("RNZ", CommandsCodes.RNZ);
        hashMap.put("RZ", CommandsCodes.RZ);
        hashMap.put("RNC", CommandsCodes.RNC);
        hashMap.put("RC", CommandsCodes.RC);
        hashMap.put("RPO", CommandsCodes.RPO);
        hashMap.put("RPE", CommandsCodes.RPE);
        hashMap.put("RP", CommandsCodes.RP);
        hashMap.put("RM", CommandsCodes.RM);

        hashMap.put("PUSH B", CommandsCodes.PUSH_B);
        hashMap.put("PUSH D", CommandsCodes.PUSH_D);
        hashMap.put("PUSH H", CommandsCodes.PUSH_H);
        hashMap.put("PUSH PSW", CommandsCodes.PUSH_PSW);

        hashMap.put("POP B", CommandsCodes.POP_B);
        hashMap.put("POP D", CommandsCodes.POP_D);
        hashMap.put("POP H", CommandsCodes.POP_H);
        hashMap.put("POP PSW", CommandsCodes.POP_PSW);

        hashMap.put("XTHL", CommandsCodes.XTHL);
    }
}