package translator;

import kernel.IMemory;
import kernel.cmd.*;

public class CommandsBuilder {
    public static ICommand getCommand(IMemory memory, int address) {
        int code = memory.getValueByIndex(address);
        switch (code) {

            case CommandsCodes.NOP: {
                return new CMD_Intel8080_NOP();
            }

            case CommandsCodes.HLT: {
                return new CMD_Intel8080_HLT();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.MOV_A_A: {
                return new CMD_Intel8080_MOV("A", "A");
            }
            case CommandsCodes.MOV_A_B: {
                return new CMD_Intel8080_MOV("A", "B");
            }
            case CommandsCodes.MOV_A_C: {
                return new CMD_Intel8080_MOV("A", "C");
            }
            case CommandsCodes.MOV_A_D: {
                return new CMD_Intel8080_MOV("A", "D");
            }
            case CommandsCodes.MOV_A_E: {
                return new CMD_Intel8080_MOV("A", "E");
            }
            case CommandsCodes.MOV_A_H: {
                return new CMD_Intel8080_MOV("A", "H");
            }
            case CommandsCodes.MOV_A_L: {
                return new CMD_Intel8080_MOV("A", "L");
            }
            case CommandsCodes.MOV_A_M: {
                return new CMD_Intel8080_MOV("A", "M");
            }

            case CommandsCodes.MOV_B_A: {
                return new CMD_Intel8080_MOV("B", "A");
            }
            case CommandsCodes.MOV_B_B: {
                return new CMD_Intel8080_MOV("B", "B");
            }
            case CommandsCodes.MOV_B_C: {
                return new CMD_Intel8080_MOV("B", "C");
            }
            case CommandsCodes.MOV_B_D: {
                return new CMD_Intel8080_MOV("B", "D");
            }
            case CommandsCodes.MOV_B_E: {
                return new CMD_Intel8080_MOV("B", "E");
            }
            case CommandsCodes.MOV_B_H: {
                return new CMD_Intel8080_MOV("B", "H");
            }
            case CommandsCodes.MOV_B_L: {
                return new CMD_Intel8080_MOV("B", "L");
            }
            case CommandsCodes.MOV_B_M: {
                return new CMD_Intel8080_MOV("B", "M");
            }

            case CommandsCodes.MOV_C_A: {
                return new CMD_Intel8080_MOV("C", "A");
            }
            case CommandsCodes.MOV_C_B: {
                return new CMD_Intel8080_MOV("C", "B");
            }
            case CommandsCodes.MOV_C_C: {
                return new CMD_Intel8080_MOV("C", "C");
            }
            case CommandsCodes.MOV_C_D: {
                return new CMD_Intel8080_MOV("C", "D");
            }
            case CommandsCodes.MOV_C_E: {
                return new CMD_Intel8080_MOV("C", "E");
            }
            case CommandsCodes.MOV_C_H: {
                return new CMD_Intel8080_MOV("C", "H");
            }
            case CommandsCodes.MOV_C_L: {
                return new CMD_Intel8080_MOV("C", "L");
            }
            case CommandsCodes.MOV_C_M: {
                return new CMD_Intel8080_MOV("C", "M");
            }

            case CommandsCodes.MOV_D_A: {
                return new CMD_Intel8080_MOV("D", "A");
            }
            case CommandsCodes.MOV_D_B: {
                return new CMD_Intel8080_MOV("D", "B");
            }
            case CommandsCodes.MOV_D_C: {
                return new CMD_Intel8080_MOV("D", "C");
            }
            case CommandsCodes.MOV_D_D: {
                return new CMD_Intel8080_MOV("D", "D");
            }
            case CommandsCodes.MOV_D_E: {
                return new CMD_Intel8080_MOV("D", "E");
            }
            case CommandsCodes.MOV_D_H: {
                return new CMD_Intel8080_MOV("D", "H");
            }
            case CommandsCodes.MOV_D_L: {
                return new CMD_Intel8080_MOV("D", "L");
            }
            case CommandsCodes.MOV_D_M: {
                return new CMD_Intel8080_MOV("D", "M");
            }

            case CommandsCodes.MOV_E_A: {
                return new CMD_Intel8080_MOV("E", "A");
            }
            case CommandsCodes.MOV_E_B: {
                return new CMD_Intel8080_MOV("E", "B");
            }
            case CommandsCodes.MOV_E_C: {
                return new CMD_Intel8080_MOV("E", "C");
            }
            case CommandsCodes.MOV_E_D: {
                return new CMD_Intel8080_MOV("E", "D");
            }
            case CommandsCodes.MOV_E_E: {
                return new CMD_Intel8080_MOV("E", "E");
            }
            case CommandsCodes.MOV_E_H: {
                return new CMD_Intel8080_MOV("E", "H");
            }
            case CommandsCodes.MOV_E_L: {
                return new CMD_Intel8080_MOV("E", "L");
            }
            case CommandsCodes.MOV_E_M: {
                return new CMD_Intel8080_MOV("E", "M");
            }

            case CommandsCodes.MOV_H_A: {
                return new CMD_Intel8080_MOV("H", "A");
            }
            case CommandsCodes.MOV_H_B: {
                return new CMD_Intel8080_MOV("H", "B");
            }
            case CommandsCodes.MOV_H_C: {
                return new CMD_Intel8080_MOV("H", "C");
            }
            case CommandsCodes.MOV_H_D: {
                return new CMD_Intel8080_MOV("H", "D");
            }
            case CommandsCodes.MOV_H_E: {
                return new CMD_Intel8080_MOV("H", "E");
            }
            case CommandsCodes.MOV_H_H: {
                return new CMD_Intel8080_MOV("H", "H");
            }
            case CommandsCodes.MOV_H_L: {
                return new CMD_Intel8080_MOV("H", "L");
            }
            case CommandsCodes.MOV_H_M: {
                return new CMD_Intel8080_MOV("H", "M");
            }

            case CommandsCodes.MOV_L_A: {
                return new CMD_Intel8080_MOV("L", "A");
            }
            case CommandsCodes.MOV_L_B: {
                return new CMD_Intel8080_MOV("L", "B");
            }
            case CommandsCodes.MOV_L_C: {
                return new CMD_Intel8080_MOV("L", "C");
            }
            case CommandsCodes.MOV_L_D: {
                return new CMD_Intel8080_MOV("L", "D");
            }
            case CommandsCodes.MOV_L_E: {
                return new CMD_Intel8080_MOV("L", "E");
            }
            case CommandsCodes.MOV_L_H: {
                return new CMD_Intel8080_MOV("L", "H");
            }
            case CommandsCodes.MOV_L_L: {
                return new CMD_Intel8080_MOV("L", "L");
            }
            case CommandsCodes.MOV_L_M: {
                return new CMD_Intel8080_MOV("L", "M");
            }

            case CommandsCodes.MOV_M_A: {
                return new CMD_Intel8080_MOV("M", "A");
            }
            case CommandsCodes.MOV_M_B: {
                return new CMD_Intel8080_MOV("M", "B");
            }
            case CommandsCodes.MOV_M_C: {
                return new CMD_Intel8080_MOV("M", "C");
            }
            case CommandsCodes.MOV_M_D: {
                return new CMD_Intel8080_MOV("M", "D");
            }
            case CommandsCodes.MOV_M_E: {
                return new CMD_Intel8080_MOV("M", "E");
            }
            case CommandsCodes.MOV_M_H: {
                return new CMD_Intel8080_MOV("M", "H");
            }
            case CommandsCodes.MOV_M_L: {
                return new CMD_Intel8080_MOV("M", "L");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.MVI_A: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("A", arg);
            }
            case CommandsCodes.MVI_B: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("B", arg);
            }
            case CommandsCodes.MVI_C: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("C", arg);
            }
            case CommandsCodes.MVI_D: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("D", arg);
            }
            case CommandsCodes.MVI_E: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("E", arg);
            }
            case CommandsCodes.MVI_H: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("H", arg);
            }
            case CommandsCodes.MVI_L: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("L", arg);
            }
            case CommandsCodes.MVI_M: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_MVI("M", arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.LXI_B_data: {
                String arg = getValueFromNext2Bytes(memory, address);
                return new CMD_Intel8080_LXI("B", arg);
            }
            case CommandsCodes.LXI_D_data: {
                String arg = getValueFromNext2Bytes(memory, address);
                return new CMD_Intel8080_LXI("D", arg);
            }
            case CommandsCodes.LXI_H_data: {
                String arg = getValueFromNext2Bytes(memory, address);
                return new CMD_Intel8080_LXI("H", arg);
            }
            //case CommandsCodes.LXI_PSW_data: {
            //    int value = memory.getValueByIndex(address + 1) * 256;
            //    value += memory.getValueByIndex(address + 2);
            //    String arg = Integer.toString(value, 16);
            //    return new CMD_Intel8080_LXI("B", arg);
            //}

            //================================================================================
            //================================================================================

            case CommandsCodes.LDA: {
                String arg = getValueFromNext2Bytes(memory, address);
                return new CMD_Intel8080_LDA(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.LHLD: {
                String arg = getValueFromNext2Bytes(memory, address);
                return new CMD_Intel8080_LHLD(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.STA: {
                String arg = getValueFromNext2Bytes(memory, address);
                return new CMD_Intel8080_STA(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.SHLD: {
                String arg = getValueFromNext2Bytes(memory, address);
                return new CMD_Intel8080_SHLD(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.LDAX_B: {
                return new CMD_Intel8080_LDAX("B");
            }
            case CommandsCodes.LDAX_D: {
                return new CMD_Intel8080_LDAX("D");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.STAX_B: {
                return new CMD_Intel8080_STAX("B");
            }
            case CommandsCodes.STAX_D: {
                return new CMD_Intel8080_STAX("D");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.XCHG: {
                return new CMD_Intel8080_XCHG();
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

            //================================================================================
            //================================================================================

            case CommandsCodes.ADI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_ADI(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.SUB_A: {
                return new CMD_Intel8080_SUB("A");
            }
            case CommandsCodes.SUB_B: {
                return new CMD_Intel8080_SUB("B");
            }
            case CommandsCodes.SUB_C: {
                return new CMD_Intel8080_SUB("C");
            }
            case CommandsCodes.SUB_D: {
                return new CMD_Intel8080_SUB("D");
            }
            case CommandsCodes.SUB_E: {
                return new CMD_Intel8080_SUB("E");
            }
            case CommandsCodes.SUB_H: {
                return new CMD_Intel8080_SUB("H");
            }
            case CommandsCodes.SUB_L: {
                return new CMD_Intel8080_SUB("L");
            }
            case CommandsCodes.SUB_M: {
                return new CMD_Intel8080_SUB("M");
            }

            //================================================================================
            //================================================================================

            //================================================================================
            //================================================================================

            case CommandsCodes.PUSH_B: {
                return new CMD_Intel8080_PUSH("B");
            }
            case CommandsCodes.PUSH_D: {
                return new CMD_Intel8080_PUSH("D");
            }
            case CommandsCodes.PUSH_H: {
                return new CMD_Intel8080_PUSH("H");
            }
            //case CommandsCodes.PUSH_PSW: {
            //    return new CMD_Intel8080_PUSH("B");
            //}

            //================================================================================
            //================================================================================

            case CommandsCodes.POP_B: {
                return new CMD_Intel8080_POP("B");
            }
            case CommandsCodes.POP_D: {
                return new CMD_Intel8080_POP("D");
            }
            case CommandsCodes.POP_H: {
                return new CMD_Intel8080_POP("H");
            }
            //case CommandsCodes.POP_PSW: {
            //    return new CMD_Intel8080_POP("B");
            //}
        }
        return null;
    }

    private static String getValueFromNext2Bytes(IMemory memory, int address) {
        int value = memory.getValueByIndex(address + 1) * 256;
        value += memory.getValueByIndex(address + 2);
        return Integer.toString(value, 16);
    }

    private static String getValueFromNextByte(IMemory memory, int address) {
        return Integer.toString(memory.getValueByIndex(address + 1), 16);
    }
}