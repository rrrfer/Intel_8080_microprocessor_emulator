package translator;

import kernel.IReadOnlyMemory;
import kernel.cmd.*;

public class CommandsBuilder {
    public static ICommand getCommand(IReadOnlyMemory memory, int address) {
        int code = memory.getValueByIndex(address);
        switch (code) {

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
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_LXI("B", arg);
            }
            case CommandsCodes.LXI_D_data: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_LXI("D", arg);
            }
            case CommandsCodes.LXI_H_data: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_LXI("H", arg);
            }
            case CommandsCodes.LXI_SP_data: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_LXI("SP", arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.LDA: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_LDA(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.LHLD: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_LHLD(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.STA: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_STA(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.SHLD: {
                String arg = getValueFromNextWord(memory, address);
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

            case CommandsCodes.ADC_A: {
                return new CMD_Intel8080_ADC("A");
            }
            case CommandsCodes.ADC_B: {
                return new CMD_Intel8080_ADC("B");
            }
            case CommandsCodes.ADC_C: {
                return new CMD_Intel8080_ADC("C");
            }
            case CommandsCodes.ADC_D: {
                return new CMD_Intel8080_ADC("D");
            }
            case CommandsCodes.ADC_E: {
                return new CMD_Intel8080_ADC("E");
            }
            case CommandsCodes.ADC_H: {
                return new CMD_Intel8080_ADC("H");
            }
            case CommandsCodes.ADC_L: {
                return new CMD_Intel8080_ADC("L");
            }
            case CommandsCodes.ADC_M: {
                return new CMD_Intel8080_ADC("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.ACI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_ACI(arg);
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

            case CommandsCodes.SUI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_SUI(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.SBB_A: {
                return new CMD_Intel8080_SBB("A");
            }
            case CommandsCodes.SBB_B: {
                return new CMD_Intel8080_SBB("B");
            }
            case CommandsCodes.SBB_C: {
                return new CMD_Intel8080_SBB("C");
            }
            case CommandsCodes.SBB_D: {
                return new CMD_Intel8080_SBB("D");
            }
            case CommandsCodes.SBB_E: {
                return new CMD_Intel8080_SBB("E");
            }
            case CommandsCodes.SBB_H: {
                return new CMD_Intel8080_SBB("H");
            }
            case CommandsCodes.SBB_L: {
                return new CMD_Intel8080_SBB("L");
            }
            case CommandsCodes.SBB_M: {
                return new CMD_Intel8080_SBB("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.SBI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_SBI(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.INR_A: {
                return new CMD_Intel8080_INR("A");
            }
            case CommandsCodes.INR_B: {
                return new CMD_Intel8080_INR("B");
            }
            case CommandsCodes.INR_C: {
                return new CMD_Intel8080_INR("C");
            }
            case CommandsCodes.INR_D: {
                return new CMD_Intel8080_INR("D");
            }
            case CommandsCodes.INR_E: {
                return new CMD_Intel8080_INR("E");
            }
            case CommandsCodes.INR_H: {
                return new CMD_Intel8080_INR("H");
            }
            case CommandsCodes.INR_L: {
                return new CMD_Intel8080_INR("L");
            }
            case CommandsCodes.INR_M: {
                return new CMD_Intel8080_INR("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.INX_B: {
                return new CMD_Intel8080_INX("B");
            }
            case CommandsCodes.INX_D: {
                return new CMD_Intel8080_INX("D");
            }
            case CommandsCodes.INX_H: {
                return new CMD_Intel8080_INX("H");
            }
            case CommandsCodes.INX_SP: {
                return new CMD_Intel8080_INX("SP");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.DCR_A: {
                return new CMD_Intel8080_DCR("A");
            }
            case CommandsCodes.DCR_B: {
                return new CMD_Intel8080_DCR("B");
            }
            case CommandsCodes.DCR_C: {
                return new CMD_Intel8080_DCR("C");
            }
            case CommandsCodes.DCR_D: {
                return new CMD_Intel8080_DCR("D");
            }
            case CommandsCodes.DCR_E: {
                return new CMD_Intel8080_DCR("E");
            }
            case CommandsCodes.DCR_H: {
                return new CMD_Intel8080_DCR("H");
            }
            case CommandsCodes.DCR_L: {
                return new CMD_Intel8080_DCR("L");
            }
            case CommandsCodes.DCR_M: {
                return new CMD_Intel8080_DCR("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.DCX_B: {
                return new CMD_Intel8080_DCX("B");
            }
            case CommandsCodes.DCX_D: {
                return new CMD_Intel8080_DCX("D");
            }
            case CommandsCodes.DCX_H: {
                return new CMD_Intel8080_DCX("H");
            }
            case CommandsCodes.DCX_SP: {
                return new CMD_Intel8080_DCX("SP");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.DAD_B: {
                return new CMD_Intel8080_DAD("B");
            }
            case CommandsCodes.DAD_D: {
                return new CMD_Intel8080_DAD("D");
            }
            case CommandsCodes.DAD_H: {
                return new CMD_Intel8080_DAD("H");
            }
            case CommandsCodes.DAD_SP: {
                return new CMD_Intel8080_DAD("SP");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.ANA_A: {
                return new CMD_Intel8080_ANA("A");
            }
            case CommandsCodes.ANA_B: {
                return new CMD_Intel8080_ANA("B");
            }
            case CommandsCodes.ANA_C: {
                return new CMD_Intel8080_ANA("C");
            }
            case CommandsCodes.ANA_D: {
                return new CMD_Intel8080_ANA("D");
            }
            case CommandsCodes.ANA_E: {
                return new CMD_Intel8080_ANA("E");
            }
            case CommandsCodes.ANA_H: {
                return new CMD_Intel8080_ANA("H");
            }
            case CommandsCodes.ANA_L: {
                return new CMD_Intel8080_ANA("L");
            }
            case CommandsCodes.ANA_M: {
                return new CMD_Intel8080_ANA("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.ANI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_ANI(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.ORA_A: {
                return new CMD_Intel8080_ORA("A");
            }
            case CommandsCodes.ORA_B: {
                return new CMD_Intel8080_ORA("B");
            }
            case CommandsCodes.ORA_C: {
                return new CMD_Intel8080_ORA("C");
            }
            case CommandsCodes.ORA_D: {
                return new CMD_Intel8080_ORA("D");
            }
            case CommandsCodes.ORA_E: {
                return new CMD_Intel8080_ORA("E");
            }
            case CommandsCodes.ORA_H: {
                return new CMD_Intel8080_ORA("H");
            }
            case CommandsCodes.ORA_L: {
                return new CMD_Intel8080_ORA("L");
            }
            case CommandsCodes.ORA_M: {
                return new CMD_Intel8080_ORA("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.ORI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_ORI(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.XRA_A: {
                return new CMD_Intel8080_XRA("A");
            }
            case CommandsCodes.XRA_B: {
                return new CMD_Intel8080_XRA("B");
            }
            case CommandsCodes.XRA_C: {
                return new CMD_Intel8080_XRA("C");
            }
            case CommandsCodes.XRA_D: {
                return new CMD_Intel8080_XRA("D");
            }
            case CommandsCodes.XRA_E: {
                return new CMD_Intel8080_XRA("E");
            }
            case CommandsCodes.XRA_H: {
                return new CMD_Intel8080_XRA("H");
            }
            case CommandsCodes.XRA_L: {
                return new CMD_Intel8080_XRA("L");
            }
            case CommandsCodes.XRA_M: {
                return new CMD_Intel8080_XRA("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.XRI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_XRI(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CMP_A: {
                return new CMD_Intel8080_CMP("A");
            }
            case CommandsCodes.CMP_B: {
                return new CMD_Intel8080_CMP("B");
            }
            case CommandsCodes.CMP_C: {
                return new CMD_Intel8080_CMP("C");
            }
            case CommandsCodes.CMP_D: {
                return new CMD_Intel8080_CMP("D");
            }
            case CommandsCodes.CMP_E: {
                return new CMD_Intel8080_CMP("E");
            }
            case CommandsCodes.CMP_H: {
                return new CMD_Intel8080_CMP("H");
            }
            case CommandsCodes.CMP_L: {
                return new CMD_Intel8080_CMP("L");
            }
            case CommandsCodes.CMP_M: {
                return new CMD_Intel8080_CMP("M");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CPI: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_CPI(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CMA: {
                return new CMD_Intel8080_CMA();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.STC: {
                return new CMD_Intel8080_STC();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CMC: {
                return new CMD_Intel8080_CMC();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RLC: {
                return new CMD_Intel8080_RLC();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RRC: {
                return new CMD_Intel8080_RRC();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RAL: {
                return new CMD_Intel8080_RAL();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RAR: {
                return new CMD_Intel8080_RAR();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.PCHL: {
                return new CMD_Intel8080_PCHL();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.SPHL: {
                return new CMD_Intel8080_SPHL();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CALL: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CALL(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CNZ: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CNZ(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CZ: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CZ(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CNC: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CNC(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CC: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CC(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CPO: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CPO(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CPE: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CPE(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CP: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CP(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.CM: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_CM(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RET: {
                return new CMD_Intel8080_RET();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RNZ: {
                return new CMD_Intel8080_RNZ();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RZ: {
                return new CMD_Intel8080_RZ();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RNC: {
                return new CMD_Intel8080_RNZ();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RC: {
                return new CMD_Intel8080_RC();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RPO: {
                return new CMD_Intel8080_RPO();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RPE: {
                return new CMD_Intel8080_RPE();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RP: {
                return new CMD_Intel8080_RP();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.RM: {
                return new CMD_Intel8080_RM();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JMP: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JMP(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JZ: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JZ(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JNZ: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JNZ(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JC: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JC(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JNC: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JNC(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JP: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JP(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JM: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JM(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JPO: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JPO(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.JPE: {
                String arg = getValueFromNextWord(memory, address);
                return new CMD_Intel8080_JPE(arg);
            }

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
            case CommandsCodes.PUSH_PSW: {
                return new CMD_Intel8080_PUSH("PSW");
            }

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
            case CommandsCodes.POP_PSW: {
                return new CMD_Intel8080_POP("PSW");
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.XTHL: {
                return new CMD_Intel8080_XTHL();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.IN: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_IN(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.OUT: {
                String arg = getValueFromNextByte(memory, address);
                return new CMD_Intel8080_OUT(arg);
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.NOP: {
                return new CMD_Intel8080_NOP();
            }

            //================================================================================
            //================================================================================

            case CommandsCodes.HLT: {
                return new CMD_Intel8080_HLT();
            }

            //================================================================================
            //================================================================================

        }

        return new CMD_NULL(Integer.toString(code, 16));
    }

    private static String getValueFromNextWord(IReadOnlyMemory memory, int address) {
        int value = memory.getValueByIndex(address + 1) * 256;
        value += memory.getValueByIndex(address + 2);
        return Integer.toString(value, 16);
    }

    private static String getValueFromNextByte(IReadOnlyMemory memory, int address) {
        return Integer.toString(memory.getValueByIndex(address + 1), 16);
    }
}