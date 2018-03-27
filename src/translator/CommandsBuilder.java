package translator;

import kernel.IMemory;
import kernel.cmd.CMD_Intel8080_ADD;
import kernel.cmd.CMD_Intel8080_MVI;
import kernel.cmd.CMD_Intel8080_NOP;
import kernel.cmd.ICommand;

public class CommandsBuilder {
    public static ICommand getCommand(IMemory memory, int address) {
        int code = memory.getValueByIndex(address);
        switch (code) {

            case CommandsCodes.NOP: {
                return new CMD_Intel8080_NOP();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.MVI_A: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("A", arg);
            }
            case CommandsCodes.MVI_B: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("B", arg);
            }
            case CommandsCodes.MVI_C: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("C", arg);
            }
            case CommandsCodes.MVI_D: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("D", arg);
            }
            case CommandsCodes.MVI_E: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("E", arg);
            }
            case CommandsCodes.MVI_H: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("H", arg);
            }
            case CommandsCodes.MVI_L: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("L", arg);
            }
            case CommandsCodes.MVI_M: {
                String arg = Integer.toString(memory.getValueByIndex(address + 1), 16);
                return new CMD_Intel8080_MVI("M", arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.ADD_A: {
                return new CMD_Intel8080_ADD("A");
            }
            case CommandsCodes.ADD_B: {
                return new CMD_Intel8080_ADD("B");
            }
            case CommandsCodes.ADD_C: {
                return new CMD_Intel8080_ADD("C");
            }
            case CommandsCodes.ADD_D: {
                return new CMD_Intel8080_ADD("D");
            }
            case CommandsCodes.ADD_E: {
                return new CMD_Intel8080_ADD("E");
            }
            case CommandsCodes.ADD_H: {
                return new CMD_Intel8080_ADD("H");
            }
            case CommandsCodes.ADD_L: {
                return new CMD_Intel8080_ADD("L");
            }
            case CommandsCodes.ADD_M: {
                return new CMD_Intel8080_ADD("M");
            }
        }
        return null;
    }
}