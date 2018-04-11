package model.translator;

import model.kernel.IMicroprocessor;
import model.kernel.RegisterPairs;
import model.kernel.Registers;
import model.kernel.cmd.*;

public class ComandsFactory {

    private static final ICommand MOV_A_A = new CMD_Intel8080_MOV(Registers.A, Registers.A);
    private static final ICommand MOV_A_B = new CMD_Intel8080_MOV(Registers.A, Registers.B);
    private static final ICommand MOV_A_C = new CMD_Intel8080_MOV(Registers.A, Registers.C);
    private static final ICommand MOV_A_D = new CMD_Intel8080_MOV(Registers.A, Registers.D);
    private static final ICommand MOV_A_E = new CMD_Intel8080_MOV(Registers.A, Registers.E);
    private static final ICommand MOV_A_H = new CMD_Intel8080_MOV(Registers.A, Registers.H);
    private static final ICommand MOV_A_L = new CMD_Intel8080_MOV(Registers.A, Registers.L);
    private static final ICommand MOV_A_M = new CMD_Intel8080_MOV(Registers.A, Registers.M);

    private static final ICommand MOV_B_A = new CMD_Intel8080_MOV(Registers.B, Registers.A);
    private static final ICommand MOV_B_B = new CMD_Intel8080_MOV(Registers.B, Registers.B);
    private static final ICommand MOV_B_C = new CMD_Intel8080_MOV(Registers.B, Registers.C);
    private static final ICommand MOV_B_D = new CMD_Intel8080_MOV(Registers.B, Registers.D);
    private static final ICommand MOV_B_E = new CMD_Intel8080_MOV(Registers.B, Registers.E);
    private static final ICommand MOV_B_H = new CMD_Intel8080_MOV(Registers.B, Registers.H);
    private static final ICommand MOV_B_L = new CMD_Intel8080_MOV(Registers.B, Registers.L);
    private static final ICommand MOV_B_M = new CMD_Intel8080_MOV(Registers.B, Registers.M);

    private static final ICommand MOV_C_A = new CMD_Intel8080_MOV(Registers.C, Registers.A);
    private static final ICommand MOV_C_B = new CMD_Intel8080_MOV(Registers.C, Registers.B);
    private static final ICommand MOV_C_C = new CMD_Intel8080_MOV(Registers.C, Registers.C);
    private static final ICommand MOV_C_D = new CMD_Intel8080_MOV(Registers.C, Registers.D);
    private static final ICommand MOV_C_E = new CMD_Intel8080_MOV(Registers.C, Registers.E);
    private static final ICommand MOV_C_H = new CMD_Intel8080_MOV(Registers.C, Registers.H);
    private static final ICommand MOV_C_L = new CMD_Intel8080_MOV(Registers.C, Registers.L);
    private static final ICommand MOV_C_M = new CMD_Intel8080_MOV(Registers.C, Registers.M);

    private static final ICommand MOV_D_A = new CMD_Intel8080_MOV(Registers.D, Registers.A);
    private static final ICommand MOV_D_B = new CMD_Intel8080_MOV(Registers.D, Registers.B);
    private static final ICommand MOV_D_C = new CMD_Intel8080_MOV(Registers.D, Registers.C);
    private static final ICommand MOV_D_D = new CMD_Intel8080_MOV(Registers.D, Registers.D);
    private static final ICommand MOV_D_E = new CMD_Intel8080_MOV(Registers.D, Registers.E);
    private static final ICommand MOV_D_H = new CMD_Intel8080_MOV(Registers.D, Registers.H);
    private static final ICommand MOV_D_L = new CMD_Intel8080_MOV(Registers.D, Registers.L);
    private static final ICommand MOV_D_M = new CMD_Intel8080_MOV(Registers.D, Registers.M);

    private static final ICommand MOV_E_A = new CMD_Intel8080_MOV(Registers.E, Registers.A);
    private static final ICommand MOV_E_B = new CMD_Intel8080_MOV(Registers.E, Registers.B);
    private static final ICommand MOV_E_C = new CMD_Intel8080_MOV(Registers.E, Registers.C);
    private static final ICommand MOV_E_D = new CMD_Intel8080_MOV(Registers.E, Registers.D);
    private static final ICommand MOV_E_E = new CMD_Intel8080_MOV(Registers.E, Registers.E);
    private static final ICommand MOV_E_H = new CMD_Intel8080_MOV(Registers.E, Registers.H);
    private static final ICommand MOV_E_L = new CMD_Intel8080_MOV(Registers.E, Registers.L);
    private static final ICommand MOV_E_M = new CMD_Intel8080_MOV(Registers.E, Registers.M);

    private static final ICommand MOV_H_A = new CMD_Intel8080_MOV(Registers.H, Registers.A);
    private static final ICommand MOV_H_B = new CMD_Intel8080_MOV(Registers.H, Registers.B);
    private static final ICommand MOV_H_C = new CMD_Intel8080_MOV(Registers.H, Registers.C);
    private static final ICommand MOV_H_D = new CMD_Intel8080_MOV(Registers.H, Registers.D);
    private static final ICommand MOV_H_E = new CMD_Intel8080_MOV(Registers.H, Registers.E);
    private static final ICommand MOV_H_H = new CMD_Intel8080_MOV(Registers.H, Registers.H);
    private static final ICommand MOV_H_L = new CMD_Intel8080_MOV(Registers.H, Registers.L);
    private static final ICommand MOV_H_M = new CMD_Intel8080_MOV(Registers.H, Registers.M);

    private static final ICommand MOV_L_A = new CMD_Intel8080_MOV(Registers.L, Registers.A);
    private static final ICommand MOV_L_B = new CMD_Intel8080_MOV(Registers.L, Registers.B);
    private static final ICommand MOV_L_C = new CMD_Intel8080_MOV(Registers.L, Registers.C);
    private static final ICommand MOV_L_D = new CMD_Intel8080_MOV(Registers.L, Registers.D);
    private static final ICommand MOV_L_E = new CMD_Intel8080_MOV(Registers.L, Registers.E);
    private static final ICommand MOV_L_H = new CMD_Intel8080_MOV(Registers.L, Registers.H);
    private static final ICommand MOV_L_L = new CMD_Intel8080_MOV(Registers.L, Registers.L);
    private static final ICommand MOV_L_M = new CMD_Intel8080_MOV(Registers.L, Registers.M);

    private static final ICommand MOV_M_A = new CMD_Intel8080_MOV(Registers.M, Registers.A);
    private static final ICommand MOV_M_B = new CMD_Intel8080_MOV(Registers.M, Registers.B);
    private static final ICommand MOV_M_C = new CMD_Intel8080_MOV(Registers.M, Registers.C);
    private static final ICommand MOV_M_D = new CMD_Intel8080_MOV(Registers.M, Registers.D);
    private static final ICommand MOV_M_E = new CMD_Intel8080_MOV(Registers.M, Registers.E);
    private static final ICommand MOV_M_H = new CMD_Intel8080_MOV(Registers.M, Registers.H);
    private static final ICommand MOV_M_L = new CMD_Intel8080_MOV(Registers.M, Registers.L);

    private static final ICommand MVI_A = new CMD_Intel8080_MVI(Registers.A);
    private static final ICommand MVI_B = new CMD_Intel8080_MVI(Registers.B);
    private static final ICommand MVI_C = new CMD_Intel8080_MVI(Registers.C);
    private static final ICommand MVI_D = new CMD_Intel8080_MVI(Registers.D);
    private static final ICommand MVI_E = new CMD_Intel8080_MVI(Registers.E);
    private static final ICommand MVI_H = new CMD_Intel8080_MVI(Registers.H);
    private static final ICommand MVI_L = new CMD_Intel8080_MVI(Registers.L);
    private static final ICommand MVI_M = new CMD_Intel8080_MVI(Registers.M);

    private static final ICommand LXI_B = new CMD_Intel8080_LXI(Registers.B);
    private static final ICommand LXI_D = new CMD_Intel8080_LXI(Registers.D);
    private static final ICommand LXI_H = new CMD_Intel8080_LXI(Registers.H);
    private static final ICommand LXI_SP = new CMD_Intel8080_LXI(Registers.SP);

    private static final ICommand LDA = new CMD_Intel8080_LDA();

    private static final ICommand LHLD = new CMD_Intel8080_LHLD();

    private static final ICommand STA = new CMD_Intel8080_STA();

    private static final ICommand SHLD = new CMD_Intel8080_SHLD();

    private static final ICommand LDAX_B = new CMD_Intel8080_LDAX(RegisterPairs.B);
    private static final ICommand LDAX_D = new CMD_Intel8080_LDAX(RegisterPairs.D);

    private static final ICommand STAX_B = new CMD_Intel8080_STAX(RegisterPairs.B);
    private static final ICommand STAX_D = new CMD_Intel8080_STAX(RegisterPairs.D);

    private static final ICommand XCHG = new CMD_Intel8080_XCHG();

    private static final ICommand ADD_A = new CMD_Intel8080_ADD(Registers.A);
    private static final ICommand ADD_B = new CMD_Intel8080_ADD(Registers.B);
    private static final ICommand ADD_C = new CMD_Intel8080_ADD(Registers.C);
    private static final ICommand ADD_D = new CMD_Intel8080_ADD(Registers.D);
    private static final ICommand ADD_E = new CMD_Intel8080_ADD(Registers.E);
    private static final ICommand ADD_H = new CMD_Intel8080_ADD(Registers.H);
    private static final ICommand ADD_L = new CMD_Intel8080_ADD(Registers.L);
    private static final ICommand ADD_M = new CMD_Intel8080_ADD(Registers.M);

    private static final ICommand ADI = new CMD_Intel8080_ADI();

    private static final ICommand ADC_A = new CMD_Intel8080_ADC(Registers.A);
    private static final ICommand ADC_B = new CMD_Intel8080_ADC(Registers.B);
    private static final ICommand ADC_C = new CMD_Intel8080_ADC(Registers.C);
    private static final ICommand ADC_D = new CMD_Intel8080_ADC(Registers.D);
    private static final ICommand ADC_E = new CMD_Intel8080_ADC(Registers.E);
    private static final ICommand ADC_H = new CMD_Intel8080_ADC(Registers.H);
    private static final ICommand ADC_L = new CMD_Intel8080_ADC(Registers.L);
    private static final ICommand ADC_M = new CMD_Intel8080_ADC(Registers.M);

    private static final ICommand ACI = new CMD_Intel8080_ACI();

    private static final ICommand SUB_A = new CMD_Intel8080_SUB(Registers.A);
    private static final ICommand SUB_B = new CMD_Intel8080_SUB(Registers.B);
    private static final ICommand SUB_C = new CMD_Intel8080_SUB(Registers.C);
    private static final ICommand SUB_D = new CMD_Intel8080_SUB(Registers.D);
    private static final ICommand SUB_E = new CMD_Intel8080_SUB(Registers.E);
    private static final ICommand SUB_H = new CMD_Intel8080_SUB(Registers.H);
    private static final ICommand SUB_L = new CMD_Intel8080_SUB(Registers.L);
    private static final ICommand SUB_M = new CMD_Intel8080_SUB(Registers.M);

    private static final ICommand SUI = new CMD_Intel8080_SUI();

    private static final ICommand SBB_A = new CMD_Intel8080_SBB(Registers.A);
    private static final ICommand SBB_B = new CMD_Intel8080_SBB(Registers.B);
    private static final ICommand SBB_C = new CMD_Intel8080_SBB(Registers.C);
    private static final ICommand SBB_D = new CMD_Intel8080_SBB(Registers.D);
    private static final ICommand SBB_E = new CMD_Intel8080_SBB(Registers.E);
    private static final ICommand SBB_H = new CMD_Intel8080_SBB(Registers.H);
    private static final ICommand SBB_L = new CMD_Intel8080_SBB(Registers.L);
    private static final ICommand SBB_M = new CMD_Intel8080_SBB(Registers.M);

    private static final ICommand SBI = new CMD_Intel8080_SBI();

    private static final ICommand INR_A = new CMD_Intel8080_INR(Registers.A);
    private static final ICommand INR_B = new CMD_Intel8080_INR(Registers.B);
    private static final ICommand INR_C = new CMD_Intel8080_INR(Registers.C);
    private static final ICommand INR_D = new CMD_Intel8080_INR(Registers.D);
    private static final ICommand INR_E = new CMD_Intel8080_INR(Registers.E);
    private static final ICommand INR_H = new CMD_Intel8080_INR(Registers.H);
    private static final ICommand INR_L = new CMD_Intel8080_INR(Registers.L);
    private static final ICommand INR_M = new CMD_Intel8080_INR(Registers.M);

    private static final ICommand INX_B = new CMD_Intel8080_INX(RegisterPairs.B);
    private static final ICommand INX_D = new CMD_Intel8080_INX(RegisterPairs.D);
    private static final ICommand INX_H = new CMD_Intel8080_INX(RegisterPairs.H);
    private static final ICommand INX_SP = new CMD_Intel8080_INX(null);

    private static final ICommand DCR_A = new CMD_Intel8080_DCR(Registers.A);
    private static final ICommand DCR_B = new CMD_Intel8080_DCR(Registers.B);
    private static final ICommand DCR_C = new CMD_Intel8080_DCR(Registers.C);
    private static final ICommand DCR_D = new CMD_Intel8080_DCR(Registers.D);
    private static final ICommand DCR_E = new CMD_Intel8080_DCR(Registers.E);
    private static final ICommand DCR_H = new CMD_Intel8080_DCR(Registers.H);
    private static final ICommand DCR_L = new CMD_Intel8080_DCR(Registers.L);
    private static final ICommand DCR_M = new CMD_Intel8080_DCR(Registers.M);

    private static final ICommand DCX_B = new CMD_Intel8080_DCX(RegisterPairs.B);
    private static final ICommand DCX_D = new CMD_Intel8080_DCX(RegisterPairs.D);
    private static final ICommand DCX_H = new CMD_Intel8080_DCX(RegisterPairs.H);
    private static final ICommand DCX_SP = new CMD_Intel8080_DCX(null);

    private static final ICommand DAD_B = new CMD_Intel8080_DAD(RegisterPairs.B);
    private static final ICommand DAD_D = new CMD_Intel8080_DAD(RegisterPairs.D);
    private static final ICommand DAD_H = new CMD_Intel8080_DAD(RegisterPairs.H);
    private static final ICommand DAD_SP = new CMD_Intel8080_DAD(null);

    private static final ICommand ANA_A = new CMD_Intel8080_ANA(Registers.A);
    private static final ICommand ANA_B = new CMD_Intel8080_ANA(Registers.B);
    private static final ICommand ANA_C = new CMD_Intel8080_ANA(Registers.C);
    private static final ICommand ANA_D = new CMD_Intel8080_ANA(Registers.D);
    private static final ICommand ANA_E = new CMD_Intel8080_ANA(Registers.E);
    private static final ICommand ANA_H = new CMD_Intel8080_ANA(Registers.H);
    private static final ICommand ANA_L = new CMD_Intel8080_ANA(Registers.L);
    private static final ICommand ANA_M = new CMD_Intel8080_ANA(Registers.M);

    private static final ICommand ANI = new CMD_Intel8080_ANI();

    private static final ICommand ORA_A = new CMD_Intel8080_ORA(Registers.A);
    private static final ICommand ORA_B = new CMD_Intel8080_ORA(Registers.B);
    private static final ICommand ORA_C = new CMD_Intel8080_ORA(Registers.C);
    private static final ICommand ORA_D = new CMD_Intel8080_ORA(Registers.D);
    private static final ICommand ORA_E = new CMD_Intel8080_ORA(Registers.E);
    private static final ICommand ORA_H = new CMD_Intel8080_ORA(Registers.H);
    private static final ICommand ORA_L = new CMD_Intel8080_ORA(Registers.L);
    private static final ICommand ORA_M = new CMD_Intel8080_ORA(Registers.M);

    private static final ICommand ORI = new CMD_Intel8080_ORI();

    private static final ICommand XRA_A = new CMD_Intel8080_XRA(Registers.A);
    private static final ICommand XRA_B = new CMD_Intel8080_XRA(Registers.B);
    private static final ICommand XRA_C = new CMD_Intel8080_XRA(Registers.C);
    private static final ICommand XRA_D = new CMD_Intel8080_XRA(Registers.D);
    private static final ICommand XRA_E = new CMD_Intel8080_XRA(Registers.E);
    private static final ICommand XRA_H = new CMD_Intel8080_XRA(Registers.H);
    private static final ICommand XRA_L = new CMD_Intel8080_XRA(Registers.L);
    private static final ICommand XRA_M = new CMD_Intel8080_XRA(Registers.M);

    private static final ICommand XRI = new CMD_Intel8080_XRI();

    private static final ICommand CMP_A = new CMD_Intel8080_CMP(Registers.A);
    private static final ICommand CMP_B = new CMD_Intel8080_CMP(Registers.B);
    private static final ICommand CMP_C = new CMD_Intel8080_CMP(Registers.C);
    private static final ICommand CMP_D = new CMD_Intel8080_CMP(Registers.D);
    private static final ICommand CMP_E = new CMD_Intel8080_CMP(Registers.E);
    private static final ICommand CMP_H = new CMD_Intel8080_CMP(Registers.H);
    private static final ICommand CMP_L = new CMD_Intel8080_CMP(Registers.L);
    private static final ICommand CMP_M = new CMD_Intel8080_CMP(Registers.M);

    private static final ICommand CPI = new CMD_Intel8080_CPI();

    private static final ICommand CMA = new CMD_Intel8080_CMA();

    private static final ICommand STC = new CMD_Intel8080_STC();
    private static final ICommand CMC = new CMD_Intel8080_CMC();

    private static final ICommand RLC = new CMD_Intel8080_RLC();
    private static final ICommand RRC = new CMD_Intel8080_RRC();
    private static final ICommand RAL = new CMD_Intel8080_RAL();
    private static final ICommand RAR = new CMD_Intel8080_RAR();

    private static final ICommand PCHL = new CMD_Intel8080_PCHL();
    private static final ICommand SPHL = new CMD_Intel8080_SPHL();

    private static final ICommand CALL = new CMD_Intel8080_CALL();
    private static final ICommand CNZ = new CMD_Intel8080_CNZ();
    private static final ICommand CZ = new CMD_Intel8080_CZ();
    private static final ICommand CNC = new CMD_Intel8080_CNC();
    private static final ICommand CC = new CMD_Intel8080_CC();
    private static final ICommand CPO = new CMD_Intel8080_CPO();
    private static final ICommand CPE = new CMD_Intel8080_CPE();
    private static final ICommand CP = new CMD_Intel8080_CP();
    private static final ICommand CM = new CMD_Intel8080_CM();

    private static final ICommand RET = new CMD_Intel8080_RET();
    private static final ICommand RNZ = new CMD_Intel8080_RNZ();
    private static final ICommand RZ = new CMD_Intel8080_RZ();
    private static final ICommand RNC = new CMD_Intel8080_RNC();
    private static final ICommand RC = new CMD_Intel8080_RC();
    private static final ICommand RPO = new CMD_Intel8080_RPO();
    private static final ICommand RPE = new CMD_Intel8080_RPE();
    private static final ICommand RP = new CMD_Intel8080_RP();
    private static final ICommand RM = new CMD_Intel8080_RM();

    private static final ICommand JMP = new CMD_Intel8080_JMP();
    private static final ICommand JNZ = new CMD_Intel8080_JNZ();
    private static final ICommand JZ = new CMD_Intel8080_JZ();
    private static final ICommand JNC = new CMD_Intel8080_JNC();
    private static final ICommand JC = new CMD_Intel8080_JC();
    private static final ICommand JPO = new CMD_Intel8080_JPO();
    private static final ICommand JPE = new CMD_Intel8080_JPE();
    private static final ICommand JP = new CMD_Intel8080_JP();
    private static final ICommand JM = new CMD_Intel8080_JM();

    private static final ICommand PUSH_B = new CMD_Intel8080_PUSH(Registers.B);
    private static final ICommand PUSH_D = new CMD_Intel8080_PUSH(Registers.D);
    private static final ICommand PUSH_H = new CMD_Intel8080_PUSH(Registers.H);
    private static final ICommand PUSH_PSW = new CMD_Intel8080_PUSH(null);

    private static final ICommand POP_B = new CMD_Intel8080_POP(Registers.B);
    private static final ICommand POP_D = new CMD_Intel8080_POP(Registers.D);
    private static final ICommand POP_H = new CMD_Intel8080_POP(Registers.H);
    private static final ICommand POP_PSW = new CMD_Intel8080_POP(null);

    private static final ICommand XTHL = new CMD_Intel8080_XTHL();

    private static final ICommand IN = new CMD_Intel8080_IN();
    private static final ICommand OUT = new CMD_Intel8080_OUT();

    private static final ICommand NOP = new CMD_Intel8080_NOP();
    private static final ICommand HLT = new CMD_Intel8080_HLT();

    private static final ICommand NULL = new CMD_NULL();

    /**
     * Метод для постоения команды по её коду и аргументам.
     * @param mp микропроцессор, в памяти которого хранятся команды.
     * @param address адрес извлекаемой команды.
     * @return инициализированный и готовый к выполнению класс-команда.
     */
    public static ICommand createCommand(IMicroprocessor mp, int address) {
        int code = mp.getValueFromMemoryByAddress(address);
        switch (code) {
            //================================================================================
            //================================================================================
            case Intel8080CommandsCodes.MOV_A_A: {
                return MOV_A_A;
            }
            case Intel8080CommandsCodes.MOV_A_B: {
                return MOV_A_B;
            }
            case Intel8080CommandsCodes.MOV_A_C: {
                return MOV_A_C;
            }
            case Intel8080CommandsCodes.MOV_A_D: {
                return MOV_A_D;
            }
            case Intel8080CommandsCodes.MOV_A_E: {
                return MOV_A_E;
            }
            case Intel8080CommandsCodes.MOV_A_H: {
                return MOV_A_H;
            }
            case Intel8080CommandsCodes.MOV_A_L: {
                return MOV_A_L;
            }
            case Intel8080CommandsCodes.MOV_A_M: {
                return MOV_A_M;
            }

            case Intel8080CommandsCodes.MOV_B_A: {
                return MOV_B_A;
            }
            case Intel8080CommandsCodes.MOV_B_B: {
                return MOV_B_B;
            }
            case Intel8080CommandsCodes.MOV_B_C: {
                return MOV_B_C;
            }
            case Intel8080CommandsCodes.MOV_B_D: {
                return MOV_B_D;
            }
            case Intel8080CommandsCodes.MOV_B_E: {
                return MOV_B_E;
            }
            case Intel8080CommandsCodes.MOV_B_H: {
                return MOV_B_H;
            }
            case Intel8080CommandsCodes.MOV_B_L: {
                return MOV_B_L;
            }
            case Intel8080CommandsCodes.MOV_B_M: {
                return MOV_B_M;
            }

            case Intel8080CommandsCodes.MOV_C_A: {
                return MOV_C_A;
            }
            case Intel8080CommandsCodes.MOV_C_B: {
                return MOV_C_B;
            }
            case Intel8080CommandsCodes.MOV_C_C: {
                return MOV_C_C;
            }
            case Intel8080CommandsCodes.MOV_C_D: {
                return MOV_C_D;
            }
            case Intel8080CommandsCodes.MOV_C_E: {
                return MOV_C_E;
            }
            case Intel8080CommandsCodes.MOV_C_H: {
                return MOV_C_H;
            }
            case Intel8080CommandsCodes.MOV_C_L: {
                return MOV_C_L;
            }
            case Intel8080CommandsCodes.MOV_C_M: {
                return MOV_C_M;
            }

            case Intel8080CommandsCodes.MOV_D_A: {
                return MOV_D_A;
            }
            case Intel8080CommandsCodes.MOV_D_B: {
                return MOV_D_B;
            }
            case Intel8080CommandsCodes.MOV_D_C: {
                return MOV_D_C;
            }
            case Intel8080CommandsCodes.MOV_D_D: {
                return MOV_D_D;
            }
            case Intel8080CommandsCodes.MOV_D_E: {
                return MOV_D_E;
            }
            case Intel8080CommandsCodes.MOV_D_H: {
                return MOV_D_H;
            }
            case Intel8080CommandsCodes.MOV_D_L: {
                return MOV_D_L;
            }
            case Intel8080CommandsCodes.MOV_D_M: {
                return MOV_D_M;
            }

            case Intel8080CommandsCodes.MOV_E_A: {
                return MOV_E_A;
            }
            case Intel8080CommandsCodes.MOV_E_B: {
                return MOV_E_B;
            }
            case Intel8080CommandsCodes.MOV_E_C: {
                return MOV_E_C;
            }
            case Intel8080CommandsCodes.MOV_E_D: {
                return MOV_E_D;
            }
            case Intel8080CommandsCodes.MOV_E_E: {
                return MOV_E_E;
            }
            case Intel8080CommandsCodes.MOV_E_H: {
                return MOV_E_H;
            }
            case Intel8080CommandsCodes.MOV_E_L: {
                return MOV_E_L;
            }
            case Intel8080CommandsCodes.MOV_E_M: {
                return MOV_E_M;
            }

            case Intel8080CommandsCodes.MOV_H_A: {
                return MOV_H_A;
            }
            case Intel8080CommandsCodes.MOV_H_B: {
                return MOV_H_B;
            }
            case Intel8080CommandsCodes.MOV_H_C: {
                return MOV_H_C;
            }
            case Intel8080CommandsCodes.MOV_H_D: {
                return MOV_H_D;
            }
            case Intel8080CommandsCodes.MOV_H_E: {
                return MOV_H_E;
            }
            case Intel8080CommandsCodes.MOV_H_H: {
                return MOV_H_H;
            }
            case Intel8080CommandsCodes.MOV_H_L: {
                return MOV_H_L;
            }
            case Intel8080CommandsCodes.MOV_H_M: {
                return MOV_H_M;
            }

            case Intel8080CommandsCodes.MOV_L_A: {
                return MOV_L_A;
            }
            case Intel8080CommandsCodes.MOV_L_B: {
                return MOV_L_B;
            }
            case Intel8080CommandsCodes.MOV_L_C: {
                return MOV_L_C;
            }
            case Intel8080CommandsCodes.MOV_L_D: {
                return MOV_L_D;
            }
            case Intel8080CommandsCodes.MOV_L_E: {
                return MOV_L_E;
            }
            case Intel8080CommandsCodes.MOV_L_H: {
                return MOV_L_H;
            }
            case Intel8080CommandsCodes.MOV_L_L: {
                return MOV_L_L;
            }
            case Intel8080CommandsCodes.MOV_L_M: {
                return MOV_L_M;
            }

            case Intel8080CommandsCodes.MOV_M_A: {
                return MOV_M_A;
            }
            case Intel8080CommandsCodes.MOV_M_B: {
                return MOV_M_B;
            }
            case Intel8080CommandsCodes.MOV_M_C: {
                return MOV_M_C;
            }
            case Intel8080CommandsCodes.MOV_M_D: {
                return MOV_M_D;
            }
            case Intel8080CommandsCodes.MOV_M_E: {
                return MOV_M_E;
            }
            case Intel8080CommandsCodes.MOV_M_H: {
                return MOV_M_H;
            }
            case Intel8080CommandsCodes.MOV_M_L: {
                return MOV_M_L;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.MVI_A: {
                String arg = getValueFromNextByte(mp, address);
                MVI_A.setArgument(arg);
                return MVI_A;
            }
            case Intel8080CommandsCodes.MVI_B: {
                String arg = getValueFromNextByte(mp, address);
                MVI_B.setArgument(arg);
                return MVI_B;
            }
            case Intel8080CommandsCodes.MVI_C: {
                String arg = getValueFromNextByte(mp, address);
                MVI_C.setArgument(arg);
                return MVI_C;
            }
            case Intel8080CommandsCodes.MVI_D: {
                String arg = getValueFromNextByte(mp, address);
                MVI_D.setArgument(arg);
                return MVI_D;
            }
            case Intel8080CommandsCodes.MVI_E: {
                String arg = getValueFromNextByte(mp, address);
                MVI_E.setArgument(arg);
                return MVI_E;
            }
            case Intel8080CommandsCodes.MVI_H: {
                String arg = getValueFromNextByte(mp, address);
                MVI_H.setArgument(arg);
                return MVI_H;
            }
            case Intel8080CommandsCodes.MVI_L: {
                String arg = getValueFromNextByte(mp, address);
                MVI_L.setArgument(arg);
                return MVI_L;
            }
            case Intel8080CommandsCodes.MVI_M: {
                String arg = getValueFromNextByte(mp, address);
                MVI_M.setArgument(arg);
                return MVI_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LXI_B_data: {
                String arg = getValueFromNextWord(mp, address);
                LXI_B.setArgument(arg);
                return LXI_B;
            }
            case Intel8080CommandsCodes.LXI_D_data: {
                String arg = getValueFromNextWord(mp, address);
                LXI_D.setArgument(arg);
                return LXI_D;
            }
            case Intel8080CommandsCodes.LXI_H_data: {
                String arg = getValueFromNextWord(mp, address);
                LXI_H.setArgument(arg);
                return LXI_H;
            }
            case Intel8080CommandsCodes.LXI_SP_data: {
                String arg = getValueFromNextWord(mp, address);
                LXI_SP.setArgument(arg);
                return LXI_SP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LDA: {
                String arg = getValueFromNextWord(mp, address);
                LDA.setArgument(arg);
                return LDA;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LHLD: {
                String arg = getValueFromNextWord(mp, address);
                LHLD.setArgument(arg);
                return LHLD;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.STA: {
                String arg = getValueFromNextWord(mp, address);
                STA.setArgument(arg);
                return STA;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SHLD: {
                String arg = getValueFromNextWord(mp, address);
                SHLD.setArgument(arg);
                return SHLD;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.LDAX_B: {
                return LDAX_B;
            }
            case Intel8080CommandsCodes.LDAX_D: {
                return LDAX_D;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.STAX_B: {
                return STAX_B;
            }
            case Intel8080CommandsCodes.STAX_D: {
                return STAX_D;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XCHG: {
                return XCHG;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ADD_A: {
                return ADD_A;
            }
            case Intel8080CommandsCodes.ADD_B: {
                return ADD_B;
            }
            case Intel8080CommandsCodes.ADD_C: {
                return ADD_C;
            }
            case Intel8080CommandsCodes.ADD_D: {
                return ADD_D;
            }
            case Intel8080CommandsCodes.ADD_E: {
                return ADD_E;
            }
            case Intel8080CommandsCodes.ADD_H: {
                return ADD_H;
            }
            case Intel8080CommandsCodes.ADD_L: {
                return ADD_L;
            }
            case Intel8080CommandsCodes.ADD_M: {
                return ADD_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ADI: {
                String arg = getValueFromNextByte(mp, address);
                ADI.setArgument(arg);
                return ADI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ADC_A: {
                return ADC_A;
            }
            case Intel8080CommandsCodes.ADC_B: {
                return ADC_B;
            }
            case Intel8080CommandsCodes.ADC_C: {
                return ADC_C;
            }
            case Intel8080CommandsCodes.ADC_D: {
                return ADC_D;
            }
            case Intel8080CommandsCodes.ADC_E: {
                return ADC_E;
            }
            case Intel8080CommandsCodes.ADC_H: {
                return ADC_H;
            }
            case Intel8080CommandsCodes.ADC_L: {
                return ADC_L;
            }
            case Intel8080CommandsCodes.ADC_M: {
                return ADC_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ACI: {
                String arg = getValueFromNextByte(mp, address);
                ACI.setArgument(arg);
                return ACI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SUB_A: {
                return SUB_A;
            }
            case Intel8080CommandsCodes.SUB_B: {
                return SUB_B;
            }
            case Intel8080CommandsCodes.SUB_C: {
                return SUB_C;
            }
            case Intel8080CommandsCodes.SUB_D: {
                return SUB_D;
            }
            case Intel8080CommandsCodes.SUB_E: {
                return SUB_E;
            }
            case Intel8080CommandsCodes.SUB_H: {
                return SUB_H;
            }
            case Intel8080CommandsCodes.SUB_L: {
                return SUB_L;
            }
            case Intel8080CommandsCodes.SUB_M: {
                return SUB_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SUI: {
                String arg = getValueFromNextByte(mp, address);
                SUI.setArgument(arg);
                return SUI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SBB_A: {
                return SBB_A;
            }
            case Intel8080CommandsCodes.SBB_B: {
                return SBB_B;
            }
            case Intel8080CommandsCodes.SBB_C: {
                return SBB_C;
            }
            case Intel8080CommandsCodes.SBB_D: {
                return SBB_D;
            }
            case Intel8080CommandsCodes.SBB_E: {
                return SBB_E;
            }
            case Intel8080CommandsCodes.SBB_H: {
                return SBB_H;
            }
            case Intel8080CommandsCodes.SBB_L: {
                return SBB_L;
            }
            case Intel8080CommandsCodes.SBB_M: {
                return SBB_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SBI: {
                String arg = getValueFromNextByte(mp, address);
                SBI.setArgument(arg);
                return SBI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.INR_A: {
                return INR_A;
            }
            case Intel8080CommandsCodes.INR_B: {
                return INR_B;
            }
            case Intel8080CommandsCodes.INR_C: {
                return INR_C;
            }
            case Intel8080CommandsCodes.INR_D: {
                return INR_D;
            }
            case Intel8080CommandsCodes.INR_E: {
                return INR_E;
            }
            case Intel8080CommandsCodes.INR_H: {
                return INR_H;
            }
            case Intel8080CommandsCodes.INR_L: {
                return INR_L;
            }
            case Intel8080CommandsCodes.INR_M: {
                return INR_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.INX_B: {
                return INX_B;
            }
            case Intel8080CommandsCodes.INX_D: {
                return INX_D;
            }
            case Intel8080CommandsCodes.INX_H: {
                return INX_H;
            }
            case Intel8080CommandsCodes.INX_SP: {
                return INX_SP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.DCR_A: {
                return DCR_A;
            }
            case Intel8080CommandsCodes.DCR_B: {
                return DCR_B;
            }
            case Intel8080CommandsCodes.DCR_C: {
                return DCR_C;
            }
            case Intel8080CommandsCodes.DCR_D: {
                return DCR_D;
            }
            case Intel8080CommandsCodes.DCR_E: {
                return DCR_E;
            }
            case Intel8080CommandsCodes.DCR_H: {
                return DCR_H;
            }
            case Intel8080CommandsCodes.DCR_L: {
                return DCR_L;
            }
            case Intel8080CommandsCodes.DCR_M: {
                return DCR_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.DCX_B: {
                return DCX_B;
            }
            case Intel8080CommandsCodes.DCX_D: {
                return DCX_D;
            }
            case Intel8080CommandsCodes.DCX_H: {
                return DCX_H;
            }
            case Intel8080CommandsCodes.DCX_SP: {
                return DCX_SP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.DAD_B: {
                return DAD_B;
            }
            case Intel8080CommandsCodes.DAD_D: {
                return DAD_D;
            }
            case Intel8080CommandsCodes.DAD_H: {
                return DAD_H;
            }
            case Intel8080CommandsCodes.DAD_SP: {
                return DAD_SP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ANA_A: {
                return ANA_A;
            }
            case Intel8080CommandsCodes.ANA_B: {
                return ANA_B;
            }
            case Intel8080CommandsCodes.ANA_C: {
                return ANA_C;
            }
            case Intel8080CommandsCodes.ANA_D: {
                return ANA_D;
            }
            case Intel8080CommandsCodes.ANA_E: {
                return ANA_E;
            }
            case Intel8080CommandsCodes.ANA_H: {
                return ANA_H;
            }
            case Intel8080CommandsCodes.ANA_L: {
                return ANA_L;
            }
            case Intel8080CommandsCodes.ANA_M: {
                return ANA_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ANI: {
                String arg = getValueFromNextByte(mp, address);
                ANI.setArgument(arg);
                return ANI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ORA_A: {
                return ORA_A;
            }
            case Intel8080CommandsCodes.ORA_B: {
                return ORA_B;
            }
            case Intel8080CommandsCodes.ORA_C: {
                return ORA_C;
            }
            case Intel8080CommandsCodes.ORA_D: {
                return ORA_D;
            }
            case Intel8080CommandsCodes.ORA_E: {
                return ORA_E;
            }
            case Intel8080CommandsCodes.ORA_H: {
                return ORA_H;
            }
            case Intel8080CommandsCodes.ORA_L: {
                return ORA_L;
            }
            case Intel8080CommandsCodes.ORA_M: {
                return ORA_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.ORI: {
                String arg = getValueFromNextByte(mp, address);
                ORI.setArgument(arg);
                return ORI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XRA_A: {
                return XRA_A;
            }
            case Intel8080CommandsCodes.XRA_B: {
                return XRA_B;
            }
            case Intel8080CommandsCodes.XRA_C: {
                return XRA_C;
            }
            case Intel8080CommandsCodes.XRA_D: {
                return XRA_D;
            }
            case Intel8080CommandsCodes.XRA_E: {
                return XRA_E;
            }
            case Intel8080CommandsCodes.XRA_H: {
                return XRA_H;
            }
            case Intel8080CommandsCodes.XRA_L: {
                return XRA_L;
            }
            case Intel8080CommandsCodes.XRA_M: {
                return XRA_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XRI: {
                String arg = getValueFromNextByte(mp, address);
                XRI.setArgument(arg);
                return XRI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CMP_A: {
                return CMP_A;
            }
            case Intel8080CommandsCodes.CMP_B: {
                return CMP_B;
            }
            case Intel8080CommandsCodes.CMP_C: {
                return CMP_C;
            }
            case Intel8080CommandsCodes.CMP_D: {
                return CMP_D;
            }
            case Intel8080CommandsCodes.CMP_E: {
                return CMP_E;
            }
            case Intel8080CommandsCodes.CMP_H: {
                return CMP_H;
            }
            case Intel8080CommandsCodes.CMP_L: {
                return CMP_L;
            }
            case Intel8080CommandsCodes.CMP_M: {
                return CMP_M;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CPI: {
                String arg = getValueFromNextByte(mp, address);
                CPI.setArgument(arg);
                return CPI;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CMA: {
                return CMA;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.STC: {
                return STC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CMC: {
                return CMC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RLC: {
                return RLC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RRC: {
                return RRC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RAL: {
                return RAL;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RAR: {
                return RAR;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.PCHL: {
                return PCHL;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.SPHL: {
                return SPHL;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CALL: {
                String arg = getValueFromNextWord(mp, address);
                CALL.setArgument(arg);
                return CALL;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CNZ: {
                String arg = getValueFromNextWord(mp, address);
                CNZ.setArgument(arg);
                return CNZ;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CZ: {
                String arg = getValueFromNextWord(mp, address);
                CZ.setArgument(arg);
                return CZ;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CNC: {
                String arg = getValueFromNextWord(mp, address);
                CNC.setArgument(arg);
                return CNC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CC: {
                String arg = getValueFromNextWord(mp, address);
                CC.setArgument(arg);
                return CC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CPO: {
                String arg = getValueFromNextWord(mp, address);
                CPO.setArgument(arg);
                return CPO;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CPE: {
                String arg = getValueFromNextWord(mp, address);
                CPE.setArgument(arg);
                return CPE;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CP: {
                String arg = getValueFromNextWord(mp, address);
                CP.setArgument(arg);
                return CP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.CM: {
                String arg = getValueFromNextWord(mp, address);
                CM.setArgument(arg);
                return CM;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RET: {
                return RET;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RNZ: {
                return RNZ;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RZ: {
                return RZ;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RNC: {
                return RNC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RC: {
                return RC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RPO: {
                return RPO;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RPE: {
                return RPE;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RP: {
                return RP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.RM: {
                return RM;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JMP: {
                String arg = getValueFromNextWord(mp, address);
                JMP.setArgument(arg);
                return JMP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JZ: {
                String arg = getValueFromNextWord(mp, address);
                JZ.setArgument(arg);
                return JZ;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JNZ: {
                String arg = getValueFromNextWord(mp, address);
                JNZ.setArgument(arg);
                return JNZ;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JC: {
                String arg = getValueFromNextWord(mp, address);
                JC.setArgument(arg);
                return JC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JNC: {
                String arg = getValueFromNextWord(mp, address);
                JNC.setArgument(arg);
                return JNC;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JP: {
                String arg = getValueFromNextWord(mp, address);
                JP.setArgument(arg);
                return JP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JM: {
                String arg = getValueFromNextWord(mp, address);
                JM.setArgument(arg);
                return JM;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JPO: {
                String arg = getValueFromNextWord(mp, address);
                JPO.setArgument(arg);
                return JPO;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.JPE: {
                String arg = getValueFromNextWord(mp, address);
                JPE.setArgument(arg);
                return JPE;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.PUSH_B: {
                return PUSH_B;
            }
            case Intel8080CommandsCodes.PUSH_D: {
                return PUSH_D;
            }
            case Intel8080CommandsCodes.PUSH_H: {
                return PUSH_H;
            }
            case Intel8080CommandsCodes.PUSH_PSW: {
                return PUSH_PSW;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.POP_B: {
                return POP_B;
            }
            case Intel8080CommandsCodes.POP_D: {
                return POP_D;
            }
            case Intel8080CommandsCodes.POP_H: {
                return POP_H;
            }
            case Intel8080CommandsCodes.POP_PSW: {
                return POP_PSW;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.XTHL: {
                return XTHL;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.IN: {
                String arg = getValueFromNextByte(mp, address);
                IN.setArgument(arg);
                return IN;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.OUT: {
                String arg = getValueFromNextByte(mp, address);
                OUT.setArgument(arg);
                return OUT;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.NOP: {
                return NOP;
            }

            //================================================================================
            //================================================================================

            case Intel8080CommandsCodes.HLT: {
                return HLT;
            }

            //================================================================================
            //================================================================================

        }

        return NULL;
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