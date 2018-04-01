package kernel;

import emulator.IIOSystem;
import kernel.cmd.ICommand;

import java.util.HashMap;

public class Intel8080 implements IMicroprocessor {

    // Внутренние состояние микропроцессора
    private int[] registers;
    private int[] flags; // FIXME: 31.03.18 Изменить представление флагов на int для реаизации регистра PSW
    private IMemory memory;

    private IIOSystem ioSystem;

    // Вспомогательные переменные
    private HashMap<String, Integer> registerByName;
    private HashMap<String, Integer> flagByName;

    public Intel8080(IMemory memory) {
        this.registers = new int[9];
        this.flags = new int[4];
        this.memory = memory;

        this.registerByName = new HashMap<>();
        registerByName.put("A", 0);
        registerByName.put("B", 1);
        registerByName.put("C", 2);
        registerByName.put("D", 3);
        registerByName.put("E", 4);
        registerByName.put("H", 5);
        registerByName.put("L", 6);
        registerByName.put("PC", 7);
        registerByName.put("SP", 8);

        this.flagByName = new HashMap<>();
        flagByName.put("Z", 0);
        flagByName.put("C", 1);
        flagByName.put("S", 2);
        flagByName.put("P", 3);
    }

    @Override
    public IReadOnlyMemory getReadOnlyMemory() {
        return memory;
    }

    @Override
    public IMemory getMemory() {
        return memory;
    }

    @Override
    public int getValueByRegisterName(String registerName) {
        return registers[registerByName.get(registerName)];
    }

    @Override
    public void setValueByRegisterName(String registerName, int value) {
        registers[registerByName.get(registerName)] = value;
    }

    @Override
    public int getValueByFlagName(String flagName) {
        return flags[flagByName.get(flagName)];
    }

    @Override
    public void setValueByFlagName(String flagName, int value) {
        flags[flagByName.get(flagName)] = value;
    }

    @Override
    public void executeCommand(ICommand command) {
        registers[registerByName.get("PC")]
                = (registers[registerByName.get("PC")] + command.getSize()) % memory.getSize();
        command.execute(this);
    }

    @Override
    public void checkByteForSetFlags(int value) {
        if (value % 256 == 0) {
            setValueByFlagName("Z", 1);
        } else {
            setValueByFlagName("Z", 0);
        }

        if (value < 0) {
            setValueByFlagName("S", 1);
        } else {
            setValueByFlagName("S", 0);
        }

        if (value > 255 || value < 0) {
            setValueByFlagName("C", 1);
        } else {
            setValueByFlagName("C", 0);
        }

        if (value < 0) value += 256;

        int counter = 0;
        for (int i = 0; i < 8; ++i) {
            counter += value % 2;
            value = value >> 1;
        }

        setValueByFlagName("P", (counter + 1) % 2);
    }

    @Override
    public void checkWordForSetFlags(int value) {
        if (value % 65536 == 0) {
            setValueByFlagName("Z", 1);
        } else {
            setValueByFlagName("Z", 0);
        }

        if (value < 0) {
            setValueByFlagName("S", 1);
        } else {
            setValueByFlagName("S", 0);
        }

        if (value > 65535 || value < 0) {
            setValueByFlagName("C", 1);
        } else {
            setValueByFlagName("C", 0);
        }

        if (value < 0) value += 65536;

        int counter = 0;
        for (int i = 0; i < 16; ++i) {
            counter += value % 2;
            value = value >> 1;
        }

        setValueByFlagName("P", (counter + 1) % 2);
    }

    @Override
    public int getRoundedByte(int value) {
        return (value + 256) % 256;
    }

    @Override
    public int getRoundedWord(int value) {
        return (value + 65536) % 65536;
    }

    @Override
    public void resetRegisters() {
        for (int i = 0; i < registers.length; ++i) {
            registers[i] = 0;
        }

        for (int i = 0; i < flags.length; ++i) {
            flags[i] = 0;
        }
    }

    @Override
    public void resetMemory() {
        memory.reset();
    }

    @Override
    public int getValueByRegisterPairName(String registerPairName) {
        int value = 0;
        switch (registerPairName) {
            case "B": {
                value = registers[registerByName.get("B")] * 256;
                value += registers[registerByName.get("C")];
                break;
            }
            case "D": {
                value = registers[registerByName.get("D")] * 256;
                value += registers[registerByName.get("E")];
                break;
            }
            case "H": {
                value = registers[registerByName.get("H")] * 256;
                value += registers[registerByName.get("L")];
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueByRegisterPairName(String registerPairName, int value) {
        switch (registerPairName) {
            case "B": {
                registers[registerByName.get("B")] = value / 256;
                registers[registerByName.get("C")] = value % 256;
                break;
            }
            case "D": {
                registers[registerByName.get("D")] = value / 256;
                registers[registerByName.get("E")] = value % 256;
                break;
            }
            case "H": {
                registers[registerByName.get("H")] = value / 256;
                registers[registerByName.get("L")] = value % 256;
                break;
            }
        }
    }

    @Override
    public void push(int value) {
        int address = getValueByRegisterName("SP");
        memory.setValueByIndex(address, value % 256);
        address = getRoundedWord(address - 1);
        memory.setValueByIndex(address, value / 256);
        address = getRoundedWord(address - 1);
        setValueByRegisterName("SP", address);
    }

    @Override
    public int pop() {
        int address = getValueByRegisterName("SP");
        address = getRoundedWord(address + 1);
        int value = memory.getValueByIndex(address) * 256;
        address = getRoundedWord(address + 1);
        value += memory.getValueByIndex(address);
        setValueByRegisterName("SP", address);
        return value;
    }

    @Override
    public void setIOSystem(IIOSystem ioSystem) {
        this.ioSystem = ioSystem;
    }

    @Override
    public IIOSystem getIOSystem() {
        return ioSystem;
    }
}