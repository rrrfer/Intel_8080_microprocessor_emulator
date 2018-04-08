package translator;

import kernel.IMicroprocessor;
import kernel.RegisterPairs;
import kernel.Registers;
import kernel.cmd.*;

public class Intel8080CommandsBuilder {
    public static ICommand getCommand(IMicroprocessor mp, int address) {
        int code = mp.getValueFromMemoryByAddress(address);
        switch (code) {
            //================================================================================
            //================================================================================
            case Intel8080CommandsCodes.MOV_A_A: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_A_B: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_A_C: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_A_D: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_A_E: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_A_H: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_A_L: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.L);
            }
            case Intel8080CommandsCodes.MOV_A_M: {
                return new CMD_Intel8080_MOV(Registers.A, Registers.M);
            }

            case Intel8080CommandsCodes.MOV_B_A: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_B_B: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_B_C: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_B_D: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_B_E: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_B_H: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_B_L: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.L);
            }
            case Intel8080CommandsCodes.MOV_B_M: {
                return new CMD_Intel8080_MOV(Registers.B, Registers.M);
            }

            case Intel8080CommandsCodes.MOV_C_A: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_C_B: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_C_C: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_C_D: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_C_E: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_C_H: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_C_L: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.L);
            }
            case Intel8080CommandsCodes.MOV_C_M: {
                return new CMD_Intel8080_MOV(Registers.C, Registers.M);
            }

            case Intel8080CommandsCodes.MOV_D_A: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_D_B: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_D_C: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_D_D: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_D_E: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_D_H: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_D_L: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.L);
            }
            case Intel8080CommandsCodes.MOV_D_M: {
                return new CMD_Intel8080_MOV(Registers.D, Registers.M);
            }

            case Intel8080CommandsCodes.MOV_E_A: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_E_B: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_E_C: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_E_D: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_E_E: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_E_H: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_E_L: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.L);
            }
            case Intel8080CommandsCodes.MOV_E_M: {
                return new CMD_Intel8080_MOV(Registers.E, Registers.M);
            }

            case Intel8080CommandsCodes.MOV_H_A: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_H_B: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_H_C: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_H_D: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_H_E: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_H_H: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_H_L: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.L);
            }
            case Intel8080CommandsCodes.MOV_H_M: {
                return new CMD_Intel8080_MOV(Registers.H, Registers.M);
            }

            case Intel8080CommandsCodes.MOV_L_A: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_L_B: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_L_C: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_L_D: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_L_E: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_L_H: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_L_L: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.L);
            }
            case Intel8080CommandsCodes.MOV_L_M: {
                return new CMD_Intel8080_MOV(Registers.L, Registers.M);
            }

            case Intel8080CommandsCodes.MOV_M_A: {
                return new CMD_Intel8080_MOV(Registers.M, Registers.A);
            }
            case Intel8080CommandsCodes.MOV_M_B: {
                return new CMD_Intel8080_MOV(Registers.M, Registers.B);
            }
            case Intel8080CommandsCodes.MOV_M_C: {
                return new CMD_Intel8080_MOV(Registers.M, Registers.C);
            }
            case Intel8080CommandsCodes.MOV_M_D: {
                return new CMD_Intel8080_MOV(Registers.M, Registers.D);
            }
            case Intel8080CommandsCodes.MOV_M_E: {
                return new CMD_Intel8080_MOV(Registers.M, Registers.E);
            }
            case Intel8080CommandsCodes.MOV_M_H: {
                return new CMD_Intel8080_MOV(Registers.M, Registers.H);
            }
            case Intel8080CommandsCodes.MOV_M_L: {
                return new CMD_Intel8080_MOV(Registers.M, Registers.L);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.MVI_A: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.A, arg);
            }
            case Intel8080CommandsCodes.MVI_B: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.B, arg);
            }
            case Intel8080CommandsCodes.MVI_C: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.C, arg);
            }
            case Intel8080CommandsCodes.MVI_D: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.D, arg);
            }
            case Intel8080CommandsCodes.MVI_E: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.E, arg);
            }
            case Intel8080CommandsCodes.MVI_H: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.H, arg);
            }
            case Intel8080CommandsCodes.MVI_L: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.L, arg);
            }
            case Intel8080CommandsCodes.MVI_M: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_MVI(Registers.M, arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LXI_B_data: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_LXI(Registers.B, arg);
            }
            case Intel8080CommandsCodes.LXI_D_data: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_LXI(Registers.D, arg);
            }
            case Intel8080CommandsCodes.LXI_H_data: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_LXI(Registers.H, arg);
            }
            case Intel8080CommandsCodes.LXI_SP_data: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_LXI(Registers.SP, arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LDA: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_LDA(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LHLD: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_LHLD(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.STA: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_STA(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SHLD: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_SHLD(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LDAX_B: {
                return new CMD_Intel8080_LDAX(RegisterPairs.B);
            }
            case Intel8080CommandsCodes.LDAX_D: {
                return new CMD_Intel8080_LDAX(RegisterPairs.D);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.STAX_B: {
                return new CMD_Intel8080_STAX(RegisterPairs.B);
            }
            case Intel8080CommandsCodes.STAX_D: {
                return new CMD_Intel8080_STAX(RegisterPairs.D);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XCHG: {
                return new CMD_Intel8080_XCHG();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ADD_A: {
                return new CMD_Intel8080_ADD(Registers.A);
            }
            case Intel8080CommandsCodes.ADD_B: {
                return new CMD_Intel8080_ADD(Registers.B);
            }
            case Intel8080CommandsCodes.ADD_C: {
                return new CMD_Intel8080_ADD(Registers.C);
            }
            case Intel8080CommandsCodes.ADD_D: {
                return new CMD_Intel8080_ADD(Registers.D);
            }
            case Intel8080CommandsCodes.ADD_E: {
                return new CMD_Intel8080_ADD(Registers.E);
            }
            case Intel8080CommandsCodes.ADD_H: {
                return new CMD_Intel8080_ADD(Registers.H);
            }
            case Intel8080CommandsCodes.ADD_L: {
                return new CMD_Intel8080_ADD(Registers.L);
            }
            case Intel8080CommandsCodes.ADD_M: {
                return new CMD_Intel8080_ADD(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ADI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_ADI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ADC_A: {
                return new CMD_Intel8080_ADC(Registers.A);
            }
            case Intel8080CommandsCodes.ADC_B: {
                return new CMD_Intel8080_ADC(Registers.B);
            }
            case Intel8080CommandsCodes.ADC_C: {
                return new CMD_Intel8080_ADC(Registers.C);
            }
            case Intel8080CommandsCodes.ADC_D: {
                return new CMD_Intel8080_ADC(Registers.D);
            }
            case Intel8080CommandsCodes.ADC_E: {
                return new CMD_Intel8080_ADC(Registers.E);
            }
            case Intel8080CommandsCodes.ADC_H: {
                return new CMD_Intel8080_ADC(Registers.H);
            }
            case Intel8080CommandsCodes.ADC_L: {
                return new CMD_Intel8080_ADC(Registers.L);
            }
            case Intel8080CommandsCodes.ADC_M: {
                return new CMD_Intel8080_ADC(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ACI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_ACI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SUB_A: {
                return new CMD_Intel8080_SUB(Registers.A);
            }
            case Intel8080CommandsCodes.SUB_B: {
                return new CMD_Intel8080_SUB(Registers.B);
            }
            case Intel8080CommandsCodes.SUB_C: {
                return new CMD_Intel8080_SUB(Registers.C);
            }
            case Intel8080CommandsCodes.SUB_D: {
                return new CMD_Intel8080_SUB(Registers.D);
            }
            case Intel8080CommandsCodes.SUB_E: {
                return new CMD_Intel8080_SUB(Registers.E);
            }
            case Intel8080CommandsCodes.SUB_H: {
                return new CMD_Intel8080_SUB(Registers.H);
            }
            case Intel8080CommandsCodes.SUB_L: {
                return new CMD_Intel8080_SUB(Registers.L);
            }
            case Intel8080CommandsCodes.SUB_M: {
                return new CMD_Intel8080_SUB(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SUI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_SUI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SBB_A: {
                return new CMD_Intel8080_SBB(Registers.A);
            }
            case Intel8080CommandsCodes.SBB_B: {
                return new CMD_Intel8080_SBB(Registers.B);
            }
            case Intel8080CommandsCodes.SBB_C: {
                return new CMD_Intel8080_SBB(Registers.C);
            }
            case Intel8080CommandsCodes.SBB_D: {
                return new CMD_Intel8080_SBB(Registers.D);
            }
            case Intel8080CommandsCodes.SBB_E: {
                return new CMD_Intel8080_SBB(Registers.E);
            }
            case Intel8080CommandsCodes.SBB_H: {
                return new CMD_Intel8080_SBB(Registers.H);
            }
            case Intel8080CommandsCodes.SBB_L: {
                return new CMD_Intel8080_SBB(Registers.L);
            }
            case Intel8080CommandsCodes.SBB_M: {
                return new CMD_Intel8080_SBB(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SBI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_SBI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.INR_A: {
                return new CMD_Intel8080_INR(Registers.A);
            }
            case Intel8080CommandsCodes.INR_B: {
                return new CMD_Intel8080_INR(Registers.B);
            }
            case Intel8080CommandsCodes.INR_C: {
                return new CMD_Intel8080_INR(Registers.C);
            }
            case Intel8080CommandsCodes.INR_D: {
                return new CMD_Intel8080_INR(Registers.D);
            }
            case Intel8080CommandsCodes.INR_E: {
                return new CMD_Intel8080_INR(Registers.E);
            }
            case Intel8080CommandsCodes.INR_H: {
                return new CMD_Intel8080_INR(Registers.H);
            }
            case Intel8080CommandsCodes.INR_L: {
                return new CMD_Intel8080_INR(Registers.L);
            }
            case Intel8080CommandsCodes.INR_M: {
                return new CMD_Intel8080_INR(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.INX_B: {
                return new CMD_Intel8080_INX(RegisterPairs.B);
            }
            case Intel8080CommandsCodes.INX_D: {
                return new CMD_Intel8080_INX(RegisterPairs.D);
            }
            case Intel8080CommandsCodes.INX_H: {
                return new CMD_Intel8080_INX(RegisterPairs.H);
            }
            case Intel8080CommandsCodes.INX_SP: {
                return new CMD_Intel8080_INX(null);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.DCR_A: {
                return new CMD_Intel8080_DCR(Registers.A);
            }
            case Intel8080CommandsCodes.DCR_B: {
                return new CMD_Intel8080_DCR(Registers.B);
            }
            case Intel8080CommandsCodes.DCR_C: {
                return new CMD_Intel8080_DCR(Registers.C);
            }
            case Intel8080CommandsCodes.DCR_D: {
                return new CMD_Intel8080_DCR(Registers.D);
            }
            case Intel8080CommandsCodes.DCR_E: {
                return new CMD_Intel8080_DCR(Registers.E);
            }
            case Intel8080CommandsCodes.DCR_H: {
                return new CMD_Intel8080_DCR(Registers.H);
            }
            case Intel8080CommandsCodes.DCR_L: {
                return new CMD_Intel8080_DCR(Registers.L);
            }
            case Intel8080CommandsCodes.DCR_M: {
                return new CMD_Intel8080_DCR(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.DCX_B: {
                return new CMD_Intel8080_DCX(RegisterPairs.B);
            }
            case Intel8080CommandsCodes.DCX_D: {
                return new CMD_Intel8080_DCX(RegisterPairs.D);
            }
            case Intel8080CommandsCodes.DCX_H: {
                return new CMD_Intel8080_DCX(RegisterPairs.H);
            }
            case Intel8080CommandsCodes.DCX_SP: {
                return new CMD_Intel8080_DCX(null);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.DAD_B: {
                return new CMD_Intel8080_DAD(RegisterPairs.B);
            }
            case Intel8080CommandsCodes.DAD_D: {
                return new CMD_Intel8080_DAD(RegisterPairs.D);
            }
            case Intel8080CommandsCodes.DAD_H: {
                return new CMD_Intel8080_DAD(RegisterPairs.H);
            }
            case Intel8080CommandsCodes.DAD_SP: {
                return new CMD_Intel8080_DAD(null);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ANA_A: {
                return new CMD_Intel8080_ANA(Registers.A);
            }
            case Intel8080CommandsCodes.ANA_B: {
                return new CMD_Intel8080_ANA(Registers.B);
            }
            case Intel8080CommandsCodes.ANA_C: {
                return new CMD_Intel8080_ANA(Registers.C);
            }
            case Intel8080CommandsCodes.ANA_D: {
                return new CMD_Intel8080_ANA(Registers.D);
            }
            case Intel8080CommandsCodes.ANA_E: {
                return new CMD_Intel8080_ANA(Registers.E);
            }
            case Intel8080CommandsCodes.ANA_H: {
                return new CMD_Intel8080_ANA(Registers.H);
            }
            case Intel8080CommandsCodes.ANA_L: {
                return new CMD_Intel8080_ANA(Registers.L);
            }
            case Intel8080CommandsCodes.ANA_M: {
                return new CMD_Intel8080_ANA(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ANI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_ANI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ORA_A: {
                return new CMD_Intel8080_ORA(Registers.A);
            }
            case Intel8080CommandsCodes.ORA_B: {
                return new CMD_Intel8080_ORA(Registers.B);
            }
            case Intel8080CommandsCodes.ORA_C: {
                return new CMD_Intel8080_ORA(Registers.C);
            }
            case Intel8080CommandsCodes.ORA_D: {
                return new CMD_Intel8080_ORA(Registers.D);
            }
            case Intel8080CommandsCodes.ORA_E: {
                return new CMD_Intel8080_ORA(Registers.E);
            }
            case Intel8080CommandsCodes.ORA_H: {
                return new CMD_Intel8080_ORA(Registers.H);
            }
            case Intel8080CommandsCodes.ORA_L: {
                return new CMD_Intel8080_ORA(Registers.L);
            }
            case Intel8080CommandsCodes.ORA_M: {
                return new CMD_Intel8080_ORA(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ORI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_ORI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XRA_A: {
                return new CMD_Intel8080_XRA(Registers.A);
            }
            case Intel8080CommandsCodes.XRA_B: {
                return new CMD_Intel8080_XRA(Registers.B);
            }
            case Intel8080CommandsCodes.XRA_C: {
                return new CMD_Intel8080_XRA(Registers.C);
            }
            case Intel8080CommandsCodes.XRA_D: {
                return new CMD_Intel8080_XRA(Registers.D);
            }
            case Intel8080CommandsCodes.XRA_E: {
                return new CMD_Intel8080_XRA(Registers.E);
            }
            case Intel8080CommandsCodes.XRA_H: {
                return new CMD_Intel8080_XRA(Registers.H);
            }
            case Intel8080CommandsCodes.XRA_L: {
                return new CMD_Intel8080_XRA(Registers.L);
            }
            case Intel8080CommandsCodes.XRA_M: {
                return new CMD_Intel8080_XRA(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XRI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_XRI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CMP_A: {
                return new CMD_Intel8080_CMP(Registers.A);
            }
            case Intel8080CommandsCodes.CMP_B: {
                return new CMD_Intel8080_CMP(Registers.B);
            }
            case Intel8080CommandsCodes.CMP_C: {
                return new CMD_Intel8080_CMP(Registers.C);
            }
            case Intel8080CommandsCodes.CMP_D: {
                return new CMD_Intel8080_CMP(Registers.D);
            }
            case Intel8080CommandsCodes.CMP_E: {
                return new CMD_Intel8080_CMP(Registers.E);
            }
            case Intel8080CommandsCodes.CMP_H: {
                return new CMD_Intel8080_CMP(Registers.H);
            }
            case Intel8080CommandsCodes.CMP_L: {
                return new CMD_Intel8080_CMP(Registers.L);
            }
            case Intel8080CommandsCodes.CMP_M: {
                return new CMD_Intel8080_CMP(Registers.M);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CPI: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_CPI(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CMA: {
                return new CMD_Intel8080_CMA();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.STC: {
                return new CMD_Intel8080_STC();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CMC: {
                return new CMD_Intel8080_CMC();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RLC: {
                return new CMD_Intel8080_RLC();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RRC: {
                return new CMD_Intel8080_RRC();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RAL: {
                return new CMD_Intel8080_RAL();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RAR: {
                return new CMD_Intel8080_RAR();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.PCHL: {
                return new CMD_Intel8080_PCHL();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SPHL: {
                return new CMD_Intel8080_SPHL();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CALL: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CALL(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CNZ: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CNZ(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CZ: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CZ(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CNC: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CNC(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CC: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CC(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CPO: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CPO(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CPE: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CPE(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CP: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CP(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CM: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_CM(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RET: {
                return new CMD_Intel8080_RET();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RNZ: {
                return new CMD_Intel8080_RNZ();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RZ: {
                return new CMD_Intel8080_RZ();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RNC: {
                return new CMD_Intel8080_RNZ();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RC: {
                return new CMD_Intel8080_RC();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RPO: {
                return new CMD_Intel8080_RPO();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RPE: {
                return new CMD_Intel8080_RPE();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RP: {
                return new CMD_Intel8080_RP();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RM: {
                return new CMD_Intel8080_RM();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JMP: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JMP(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JZ: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JZ(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JNZ: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JNZ(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JC: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JC(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JNC: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JNC(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JP: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JP(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JM: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JM(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JPO: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JPO(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JPE: {
                String arg = getValueFromNextWord(mp, address);
                return new CMD_Intel8080_JPE(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.PUSH_B: {
                return new CMD_Intel8080_PUSH(Registers.B);
            }
            case Intel8080CommandsCodes.PUSH_D: {
                return new CMD_Intel8080_PUSH(Registers.D);
            }
            case Intel8080CommandsCodes.PUSH_H: {
                return new CMD_Intel8080_PUSH(Registers.H);
            }
            case Intel8080CommandsCodes.PUSH_PSW: {
                return new CMD_Intel8080_PUSH(null);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.POP_B: {
                return new CMD_Intel8080_POP(Registers.B);
            }
            case Intel8080CommandsCodes.POP_D: {
                return new CMD_Intel8080_POP(Registers.D);
            }
            case Intel8080CommandsCodes.POP_H: {
                return new CMD_Intel8080_POP(Registers.H);
            }
            case Intel8080CommandsCodes.POP_PSW: {
                return new CMD_Intel8080_POP(null);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XTHL: {
                return new CMD_Intel8080_XTHL();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.IN: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_IN(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.OUT: {
                String arg = getValueFromNextByte(mp, address);
                return new CMD_Intel8080_OUT(arg);
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.NOP: {
                return new CMD_Intel8080_NOP();
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.HLT: {
                return new CMD_Intel8080_HLT();
            }

            //================================================================================
            //================================================================================

        }

        return new CMD_NULL();
    }

    /**
     * Метод для формирования аргумента трёхбайтовой команды.
     * @param mp микропроцессор, содержащий в памяти коды команд и аргументов.
     * @param address адрес извлекаемой команды.
     * @return аргумент в формате строки (0x0000).
     */
    private static String getValueFromNextWord(IMicroprocessor mp, int address) {
        address = (address + 1) % mp.getMemorySize();
        int value = mp.getValueFromMemoryByAddress(address) * 256;
        address = (address + 1) % mp.getMemorySize();
        value += mp.getValueFromMemoryByAddress(address);
        return Integer.toString(value, 16);
    }


    /**
     * Метод для формирования аргумента двухбайтовой команды.
     * @param mp микропроцессор, содержащий в памяти коды команд и аргументов.
     * @param address адрес извлекаемой команды.
     * @return аргумент в формате строки (0x00).
     */
    private static String getValueFromNextByte(IMicroprocessor mp, int address) {
        address = (address + 1) % mp.getMemorySize();
        return Integer.toString(mp.getValueFromMemoryByAddress(address), 16);
    }
}