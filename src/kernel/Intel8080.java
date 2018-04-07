package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

import java.util.HashMap;

public class Intel8080 implements IMicroprocessor {

    // Внутренние состояние микропроцессора
    private int[] registers;
    private int flags;
    private IMemory memory;

    private IInputOutputSystem ioSystem;

    // Вспомогательные переменные
    private HashMap<String, Integer> registerNumberByName;

    public Intel8080(IMemory memory) {
        this.registers = new int[9];
        this.flags = 0;
        this.memory = memory;

        this.registerNumberByName = new HashMap<>();
        registerNumberByName.put("A", 0);
        registerNumberByName.put("B", 1);
        registerNumberByName.put("C", 2);
        registerNumberByName.put("D", 3);
        registerNumberByName.put("E", 4);
        registerNumberByName.put("H", 5);
        registerNumberByName.put("L", 6);
        registerNumberByName.put("PC", 7);
        registerNumberByName.put("SP", 8);
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
        return registers[registerNumberByName.get(registerName)];
    }

    @Override
    public void setValueByRegisterName(String registerName, int value) {
        registers[registerNumberByName.get(registerName)] = value;
    }

    @Override
    public int getValueByFlagName(String flagName) {
        int value = 0;
        switch (flagName) {
            case "S": {
                value = (flags & 0b10000000) > 0 ? 1 : 0;
                break;
            }
            case "Z": {
                value = (flags & 0b01000000) > 0 ? 1 : 0;
                break;
            }
            case "P": {
                value = (flags & 0b00000100) > 0 ? 1 : 0;
                break;
            }
            case "C": {
                value = (flags & 0b00000001) > 0 ? 1 : 0;
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueByFlagName(String flagName, int value) {
        switch (flagName) {
            case "S": {
                if (value > 0) {
                    flags = flags | (1 << 7);
                } else {
                    flags = flags & 0b01111111;
                }
                break;
            }
            case "Z": {
                if (value > 0) {
                    flags = flags | (1 << 6);
                } else {
                    flags = flags & 0b10111111;
                }
                break;
            }
            case "P": {
                if (value > 0) {
                    flags = flags | (1 << 2);
                } else {
                    flags = flags & 0b11111011;
                }
                break;
            }
            case "C": {
                if (value > 0) {
                    flags = flags | 1;
                } else {
                    flags = flags & 0b11111110;
                }
                break;
            }
        }
    }

    @Override
    public int getAllFlags() {
        return flags;
    }

    @Override
    public void setAllFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public void executeCommand(ICommand command) {
        registers[registerNumberByName.get("PC")]
                = (registers[registerNumberByName.get("PC")] + command.getSize()) % memory.getSize();
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
    public void resetRegisters() {
        for (int i = 0; i < registers.length; ++i) {
            registers[i] = 0;
        }

        flags = 0;
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
                value = registers[registerNumberByName.get("B")] * 256;
                value += registers[registerNumberByName.get("C")];
                break;
            }
            case "D": {
                value = registers[registerNumberByName.get("D")] * 256;
                value += registers[registerNumberByName.get("E")];
                break;
            }
            case "H": {
                value = registers[registerNumberByName.get("H")] * 256;
                value += registers[registerNumberByName.get("L")];
                break;
            }case "PSW": {
                value = registers[registerNumberByName.get("A")] * 256;
                value += flags;
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueByRegisterPairName(String registerPairName, int value) {
        switch (registerPairName) {
            case "B": {
                registers[registerNumberByName.get("B")] = value / 256;
                registers[registerNumberByName.get("C")] = value % 256;
                break;
            }
            case "D": {
                registers[registerNumberByName.get("D")] = value / 256;
                registers[registerNumberByName.get("E")] = value % 256;
                break;
            }
            case "H": {
                registers[registerNumberByName.get("H")] = value / 256;
                registers[registerNumberByName.get("L")] = value % 256;
                break;
            }
            case "PSW": {
                registers[registerNumberByName.get("A")] = value / 256;
                flags = value % 256;
                break;
            }
        }
    }

    @Override
    public void setIOSystem(IInputOutputSystem ioSystem) {
        this.ioSystem = ioSystem;
    }

    @Override
    public IInputOutputSystem getIOSystem() {
        return ioSystem;
    }
}