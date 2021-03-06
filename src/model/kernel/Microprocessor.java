package model.kernel;

import model.emulator.IIntraProgramIOEventsListener;
import model.kernel.cmd.ICommand;

import java.util.ArrayList;

/**
 * Класс микропроцессора Intel 8080.
 * Данный класс описывает внутреннюю реализацию микропроцессора Intel 8080.
 * @author Maxim Rozhkov
 */
public class Microprocessor implements IMicroprocessor {

    private int[] memory;
    private int[] registers;
    private int flags;

    private ArrayList<Integer> executionLevel;

    private IIntraProgramIOEventsListener ioSystem;

    private IExecutableCommandEventsListener commandsExecuteListener;

    public Microprocessor(int memorySize) {
        this.commandsExecuteListener = new ExecutableCommandEventsListener(this);
        this.flags = 0;
        this.registers = new int[Registers.SIZE.ordinal()];
        this.memory = new int[memorySize];
        this.executionLevel = new ArrayList<>();
    }

    @Override
    public int getValueFromRegister(Registers register) {
        return registers[register.ordinal()];
    }

    @Override
    public void setValueInRegister(Registers register, int value) {
        registers[register.ordinal()] = value;
    }

    @Override
    public int getValueFromFlag(Flags flag) {
        int value = 0;
        switch (flag) {
            case S: {
                value = (flags & 0b10000000) > 0 ? 1 : 0;
                break;
            }
            case Z: {
                value = (flags & 0b01000000) > 0 ? 1 : 0;
                break;
            }
            case P: {
                value = (flags & 0b00000100) > 0 ? 1 : 0;
                break;
            }
            case C: {
                value = (flags & 0b00000001) > 0 ? 1 : 0;
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueInFlag(Flags flag, int value) {
        switch (flag) {
            case S: {
                if (value > 0) {
                    flags = flags | (1 << 7);
                } else {
                    flags = flags & 0b01111111;
                }
                break;
            }
            case Z: {
                if (value > 0) {
                    flags = flags | (1 << 6);
                } else {
                    flags = flags & 0b10111111;
                }
                break;
            }
            case P: {
                if (value > 0) {
                    flags = flags | (1 << 2);
                } else {
                    flags = flags & 0b11111011;
                }
                break;
            }
            case C: {
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
        int PC = getValueFromRegister(Registers.PC);
        PC = (PC + command.getSize()) % memory.length;
        setValueInRegister(Registers.PC, PC);
        command.execute(commandsExecuteListener);
    }

    @Override
    public void interrupt(ICommand command) {
        command.execute(commandsExecuteListener);
    }

    @Override
    public void checkValueForSetFlags(int value) {
        if (value % 256 == 0) {
            setValueInFlag(Flags.Z, 1);
        } else {
            setValueInFlag(Flags.Z, 0);
        }

        if (value < 0) {
            setValueInFlag(Flags.S, 1);
        } else {
            setValueInFlag(Flags.S, 0);
        }

        if (value > 255 || value < 0) {
            setValueInFlag(Flags.C, 1);
        } else {
            setValueInFlag(Flags.C, 0);
        }

        if (value < 0) value += 256;

        int counter = 0;
        for (int i = 0; i < 8; ++i) {
            counter += value % 2;
            value = value >> 1;
        }

        setValueInFlag(Flags.P, (counter + 1) % 2);
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
        for (int i = 0; i < memory.length; ++i) {
            memory[i] = 0;
        }
    }

    @Override
    public int getValueFromRegisterPair(RegisterPairs registerPair) {
        int value = 0;
        switch (registerPair) {
            case B: {
                value = getValueFromRegister(Registers.B) * 256;
                value += getValueFromRegister(Registers.C);
                break;
            }
            case D: {
                value = getValueFromRegister(Registers.D) * 256;
                value += getValueFromRegister(Registers.E);
                break;
            }
            case H: {
                value = getValueFromRegister(Registers.H) * 256;
                value += getValueFromRegister(Registers.L);
                break;
            }case PSW: {
                value = getValueFromRegister(Registers.A) * 256;
                value += flags;
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueInRegisterPair(RegisterPairs registerPair, int value) {
        switch (registerPair) {
            case B: {
                setValueInRegister(Registers.B, value / 256);
                setValueInRegister(Registers.C, value % 256);
                break;
            }
            case D: {
                setValueInRegister(Registers.D, value / 256);
                setValueInRegister(Registers.E, value % 256);
                break;
            }
            case H: {
                setValueInRegister(Registers.H, value / 256);
                setValueInRegister(Registers.L, value % 256);
                break;
            }
            case PSW: {
                setValueInRegister(Registers.A, value / 256);
                flags = value % 256;
                break;
            }
        }
    }

    @Override
    public void setIntraProgramIOEventsListener(IIntraProgramIOEventsListener ioSystem) {
        this.ioSystem = ioSystem;
    }

    @Override
    public IIntraProgramIOEventsListener getIntraProgramIOEventsListener() {
        return ioSystem;
    }

    @Override
    public int getValueFromMemoryByAddress(int address) {
        return memory[address];
    }

    @Override
    public void setValueInMemoryByAddress(int address, int value) {
        memory[address] = value;
    }

    @Override
    public int getMemorySize() {
        return memory.length;
    }

    @Override
    public void setExecutionLevel(int level) {
        executionLevel.add(level);
    }

    @Override
    public int getExecutionLevel() {
        if (executionLevel.size() > 0) {
            return executionLevel.get(executionLevel.size() - 1);
        } else {
            return 8;
        }
    }

    @Override
    public void returnFromInterrupt() {
        if (executionLevel.size() != 0) {
            executionLevel.remove(executionLevel.size() - 1);
        }
    }
}