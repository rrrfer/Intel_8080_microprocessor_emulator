package kernel;

import emulator.IInputOutputSystem;
import kernel.cmd.ICommand;

public class Intel8080 implements IMicroprocessor {

    // Внутренние состояние микропроцессора
    private int[] registers;
    private int flags;
    private IMemory memory;

    private IInputOutputSystem ioSystem;

    // Вспомогательные переменные
    private IMicroprocessorAdapterForCommands microprocessorCommandsAdapter;

    public Intel8080(IMemory memory) {
        this.microprocessorCommandsAdapter = new MicroprocessorAdapterForCommands(this);

        this.registers = new int[9];
        this.flags = 0;
        this.memory = memory;
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
    public int getValueFromRegister(Intel8080Registers register) {
        return registers[register.ordinal()];
    }

    @Override
    public void setValueInRegister(Intel8080Registers register, int value) {
        registers[register.ordinal()] = value;
    }

    @Override
    public int getValueFromFlag(Intel8080Flags flag) {
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
    public void setValueInFlag(Intel8080Flags flag, int value) {
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
        int PC = getValueFromRegister(Intel8080Registers.PC);
        PC = PC + command.getSize() % memory.getSize();
        setValueInRegister(Intel8080Registers.PC, PC);
        command.execute(microprocessorCommandsAdapter);
    }

    @Override
    public void checkValueForSetFlags(int value) {
        if (value % 256 == 0) {
            setValueInFlag(Intel8080Flags.Z, 1);
        } else {
            setValueInFlag(Intel8080Flags.Z, 0);
        }

        if (value < 0) {
            setValueInFlag(Intel8080Flags.S, 1);
        } else {
            setValueInFlag(Intel8080Flags.S, 0);
        }

        if (value > 255 || value < 0) {
            setValueInFlag(Intel8080Flags.C, 1);
        } else {
            setValueInFlag(Intel8080Flags.C, 0);
        }

        if (value < 0) value += 256;

        int counter = 0;
        for (int i = 0; i < 8; ++i) {
            counter += value % 2;
            value = value >> 1;
        }

        setValueInFlag(Intel8080Flags.P, (counter + 1) % 2);
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
    public int getValueFromRegisterPair(Intel8080RegisterPairs registerPair) {
        int value = 0;
        switch (registerPair) {
            case B: {
                value = getValueFromRegister(Intel8080Registers.B) * 256;
                value += getValueFromRegister(Intel8080Registers.C);
                break;
            }
            case D: {
                value = getValueFromRegister(Intel8080Registers.D) * 256;
                value += getValueFromRegister(Intel8080Registers.E);
                break;
            }
            case H: {
                value = getValueFromRegister(Intel8080Registers.H) * 256;
                value += getValueFromRegister(Intel8080Registers.L);
                break;
            }case PSW: {
                value = getValueFromRegister(Intel8080Registers.A) * 256;
                value += flags;
                break;
            }
        }
        return value;
    }

    @Override
    public void setValueInRegisterPair(Intel8080RegisterPairs registerPair, int value) {
        switch (registerPair) {
            case B: {
                setValueInRegister(Intel8080Registers.B, value / 256);
                setValueInRegister(Intel8080Registers.C, value % 256);
                break;
            }
            case D: {
                setValueInRegister(Intel8080Registers.D, value / 256);
                setValueInRegister(Intel8080Registers.E, value % 256);
                break;
            }
            case H: {
                setValueInRegister(Intel8080Registers.H, value / 256);
                setValueInRegister(Intel8080Registers.L, value % 256);
                break;
            }
            case PSW: {
                setValueInRegister(Intel8080Registers.A, value / 256);
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