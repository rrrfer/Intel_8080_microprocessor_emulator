package kernel;

import kernel.cmd.ICommand;

import java.util.HashMap;

public class Intel8080 implements IMicroprocessor {

    // Внутренние состояние микропроцессора
    private int[] registers;
    private int[] flags;
    private IMemory memory;

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
        System.out.println("Exec: " + command.getName());
        registers[registerByName.get("PC")]
                = (registers[registerByName.get("PC")] + command.getSize()) % memory.getSize();
        command.execute(this);
    }

    @Override
    public void checkValueForSetFlags(int value) {
        if (value == 0) {
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

        // TODO Чётность единиц в результате
    }

    @Override
    public int getRoundedValue(int value) {
        return value % 255;
    }
}