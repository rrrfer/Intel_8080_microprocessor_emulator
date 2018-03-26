package translator;

public class CommandsCodes {

    static final int NOP = 0b0000_0000;

    static final int MVI_B = 0b0000_0110;
    static final int MVI_C = 0b0000_1110;
    static final int MVI_D = 0b0001_0110;
    static final int MVI_E = 0b0001_1110;
    static final int MVI_H = 0b0010_0110;
    static final int MVI_L = 0b0010_1110;
    static final int MVI_M = 0b0011_0110;
    static final int MVI_A = 0b0011_1110;

    static final int MOV_B_B = 0b0100_0000;
    static final int MOV_B_C = 0b0100_0001;
    static final int MOV_B_D = 0b0100_0010;
    static final int MOV_B_E = 0b0100_0011;
    static final int MOV_B_H = 0b0100_0100;
    static final int MOV_B_L = 0b0100_0101;
    static final int MOV_B_M = 0b0100_0110;
    static final int MOV_B_A = 0b0100_0111;

    static final int MOV_C_B = 0b0100_1000;
    static final int MOV_C_C = 0b0100_1001;
    static final int MOV_C_D = 0b0100_1010;
    static final int MOV_C_E = 0b0100_1011;
    static final int MOV_C_H = 0b0100_1100;
    static final int MOV_C_L = 0b0100_1101;
    static final int MOV_C_M = 0b0100_1110;
    static final int MOV_C_A = 0b0100_1111;

    static final int MOV_D_B = 0b0101_0000;
    static final int MOV_D_C = 0b0101_0001;
    static final int MOV_D_D = 0b0101_0010;
    static final int MOV_D_E = 0b0101_0011;
    static final int MOV_D_H = 0b0101_0100;
    static final int MOV_D_L = 0b0101_0101;
    static final int MOV_D_M = 0b0101_0110;
    static final int MOV_D_A = 0b0101_0111;

    static final int MOV_E_B = 0b0101_1000;
    static final int MOV_E_C = 0b0101_1001;
    static final int MOV_E_D = 0b0101_1010;
    static final int MOV_E_E = 0b0101_1011;
    static final int MOV_E_H = 0b0101_1100;
    static final int MOV_E_L = 0b0101_1101;
    static final int MOV_E_M = 0b0101_1110;
    static final int MOV_E_A = 0b0101_1111;

    static final int MOV_H_B = 0b0110_0000;
    static final int MOV_H_C = 0b0110_0001;
    static final int MOV_H_D = 0b0110_0010;
    static final int MOV_H_E = 0b0110_0011;
    static final int MOV_H_H = 0b0110_0100;
    static final int MOV_H_L = 0b0110_0101;
    static final int MOV_H_M = 0b0110_0110;
    static final int MOV_H_A = 0b0110_0111;

    static final int MOV_L_B = 0b0110_1000;
    static final int MOV_L_C = 0b0110_1001;
    static final int MOV_L_D = 0b0110_1010;
    static final int MOV_L_E = 0b0110_1011;
    static final int MOV_L_H = 0b0110_1100;
    static final int MOV_L_L = 0b0110_1101;
    static final int MOV_L_M = 0b0110_1110;
    static final int MOV_L_A = 0b0110_1111;

    static final int MOV_M_B = 0b0111_0000;
    static final int MOV_M_C = 0b0111_0001;
    static final int MOV_M_D = 0b0111_0010;
    static final int MOV_M_E = 0b0111_0011;
    static final int MOV_M_H = 0b0111_0100;
    static final int MOV_M_L = 0b0111_0101;
    static final int MOV_M_M = 0b0111_0110;
    static final int MOV_M_A = 0b0111_0111;

    static final int MOV_A_B = 0b0111_1000;
    static final int MOV_A_C = 0b0111_1001;
    static final int MOV_A_D = 0b0111_1010;
    static final int MOV_A_E = 0b0111_1011;
    static final int MOV_A_H = 0b0111_1100;
    static final int MOV_A_L = 0b0111_1101;
    static final int MOV_A_M = 0b0111_1110;
    static final int MOV_A_A = 0b0111_1111;

    static final int LXI_B_data = 0b0000_0001;
    static final int LXI_D_data = 0b0001_0001;
    static final int LXI_H_data = 0b0010_0001;
    static final int LXI_PSW_data = 0b0011_0001;

    static final int ADD_B = 0b1000_0000;
    static final int ADD_C = 0b1000_0001;
    static final int ADD_D = 0b1000_0010;
    static final int ADD_E = 0b1000_0011;
    static final int ADD_H = 0b1000_0100;
    static final int ADD_L = 0b1000_0101;
    static final int ADD_M = 0b1000_0110;
    static final int ADD_A = 0b1000_0111;

    static final int ADI = 0b1100_0110;

    static final int ADC_B = 0b1000_1000;
    static final int ADC_C = 0b1000_1001;
    static final int ADC_D = 0b1000_1010;
    static final int ADC_E = 0b1000_1011;
    static final int ADC_H = 0b1000_1100;
    static final int ADC_L = 0b1000_1101;
    static final int ADC_M = 0b1000_1110;
    static final int ADC_A = 0b1000_1111;

    static final int ACI = 0b1100_1110;

    static final int SUB_B = 0b1001_0000;
    static final int SUB_C = 0b1001_0001;
    static final int SUB_D = 0b1001_0010;
    static final int SUB_E = 0b1001_0011;
    static final int SUB_H = 0b1001_0100;
    static final int SUB_L = 0b1001_0101;
    static final int SUB_M = 0b1001_0110;
    static final int SUB_A = 0b1001_0111;

    static final int SUI = 0b1101_0110;

    static final int SBB_B = 0b1001_1000;
    static final int SBB_C = 0b1001_1001;
    static final int SBB_D = 0b1001_1010;
    static final int SBB_E = 0b1001_1011;
    static final int SBB_H = 0b1001_1100;
    static final int SBB_L = 0b1001_1101;
    static final int SBB_M = 0b1001_1110;
    static final int SBB_A = 0b1001_1111;

    static final int SBI = 0b1101_1110;

    static final int INR_B = 0b0000_0100;
    static final int INR_C = 0b0000_1100;
    static final int INR_D = 0b0001_0100;
    static final int INR_E = 0b0001_1100;
    static final int INR_H = 0b0010_0100;
    static final int INR_L = 0b0010_1100;
    static final int INR_M = 0b0011_0100;
    static final int INR_A = 0b0011_1100;

    static final int INX_B = 0b0000_0011;
    static final int INX_D = 0b0001_0011;
    static final int INX_H = 0b0010_0011;
    static final int INX_PSW = 0b0011_0011;

    static final int DCR_B = 0b0000_0101;
    static final int DCR_C = 0b0000_1101;
    static final int DCR_D = 0b0001_0101;
    static final int DCR_E = 0b0001_1101;
    static final int DCR_H = 0b0010_0101;
    static final int DCR_L = 0b0010_1101;
    static final int DCR_M = 0b0011_0101;
    static final int DCR_A = 0b0011_1101;

    static final int DCX_B = 0b0000_1011;
    static final int DCX_D = 0b0001_1011;
    static final int DCX_H = 0b0010_1011;
    static final int DCX_PSW = 0b0011_1011;

    static final int HLT = 0b0111_0110;

    static final int JNZ = 0b1100_0010;
    static final int JZ = 0b1100_1010;
    static final int JNC = 0b1101_0010;
    static final int JC = 0b1101_1010;
    static final int JP = 0b1110_0010;
    static final int JPO = 0b1110_1010;
    static final int JPE = 0b1111_0010;
    static final int JM = 0b1111_1010;
    static final int JMP = 0b1100_0011;

    static final int LDA = 0b0011_1010;

    static final int STA = 0b0011_0010;

    static final int LHLD = 0b001_01010;

    static final int SHLD = 0b0010_0010;

    static final int LDAX_B = 0b0000_1010;
    static final int LDAX_D = 0b0001_1010;

    static final int STAX_B = 0b0000_0010;
    static final int STAX_D = 0b0001_0010;

    static final int XCHG = 0b1110_1011;

    static final int RLC = 0b00000111;
    static final int RRC = 0b00001111;

    static final int RAL = 0b0001_0111;
    static final int RAR = 0b0001_1111;

    static final int DAD_B = 0b00001001;
    static final int DAD_D = 0b00011001;
    static final int DAD_H = 0b00101001;
    static final int DAD_PSW = 0b00111001;

    static final int ANI = 0b11100110;

    static final int ANA_B = 0b1010_0000;
    static final int ANA_C = 0b1010_0001;
    static final int ANA_D = 0b1010_0010;
    static final int ANA_E = 0b1010_0011;
    static final int ANA_H = 0b1010_0100;
    static final int ANA_L = 0b1010_0101;
    static final int ANA_M = 0b1010_0110;
    static final int ANA_A = 0b1010_0111;

    static final int ORI = 0b1111_0110;

    static final int ORA_B = 0b1011_0000;
    static final int ORA_C = 0b1011_0001;
    static final int ORA_D = 0b1011_0010;
    static final int ORA_E = 0b1011_0011;
    static final int ORA_H = 0b1011_0100;
    static final int ORA_L = 0b1011_0101;
    static final int ORA_M = 0b1011_0110;
    static final int ORA_A = 0b1011_0111;


    static final int XRA_B = 0b1010_1000;
    static final int XRA_C = 0b1010_1001;
    static final int XRA_D = 0b1010_1010;
    static final int XRA_E = 0b1010_1011;
    static final int XRA_H = 0b1010_1100;
    static final int XRA_L = 0b1010_1101;
    static final int XRA_M = 0b1010_1110;
    static final int XRA_A = 0b1010_1111;

    static final int XRI = 0b1110_1110;

    static final int CMP_B = 0b1011_1000;
    static final int CMP_C = 0b1011_1001;
    static final int CMP_D = 0b1011_1010;
    static final int CMP_E = 0b1011_1011;
    static final int CMP_H = 0b1011_1100;
    static final int CMP_L = 0b1011_1101;
    static final int CMP_M = 0b1011_1110;
    static final int CMP_A = 0b1011_1111;

    static final int CPI = 0b1111_1110;

    static final int IN = 0b1101_1011;
    static final int OUT = 0b1101_0011;

    static final int STC = 0b0011_0111;

    static final int CMC = 0b0011_1111;

    static final int CMA = 0b0010_1111;

    static final int PCHL = 0b1110_1001;
    static final int SPHL = 0b1111_1001;

    static final int CALL = 0b1100_1101;
    static final int RET = 0b1100_1001;

    static final int CNZ = 0b1100_0100;
    static final int CZ = 0b1100_1100;
    static final int CNC = 0b1101_0100;
    static final int CC = 0b1101_1100;
    static final int CPO = 0b1110_0100;
    static final int CPE = 0b1110_1100;
    static final int CP = 0b1111_0100;
    static final int CM = 0b1111_1100;

    static final int RNZ = 0b1100_0000;
    static final int RZ = 0b1100_1000;
    static final int RNC = 0b1101_0000;
    static final int RC = 0b1101_1000;
    static final int RPO = 0b1110_0000;
    static final int RPE = 0b1110_1000;
    static final int RP = 0b1111_0000;
    static final int RM = 0b1111_1000;

    static final int PUSH_B = 0b1100_0101;
    static final int PUSH_D = 0b1101_0101;
    static final int PUSH_H = 0b1110_0101;
    static final int PUSH_PSW = 0b1111_0101;

    static final int POP_B = 0b1100_0001;
    static final int POP_D = 0b1101_0001;
    static final int POP_H = 0b1110_0001;
    static final int POP_PSW = 0b1111_0001;

    static final int XTHL = 0b1110_0011;

    static final int SET = 256;

    public static int countByteInCommand(int cmd) {
        if (cmd == JNZ || cmd == JZ || cmd == JNC || cmd == JC ||
                cmd == JP || cmd == JM || cmd == JMP || cmd == JPO || cmd == JPE ||
                cmd == LDA || cmd == STA ||  cmd == LXI_B_data ||
                cmd == LXI_D_data || cmd == LXI_H_data || cmd == LXI_PSW_data ||
                cmd == CALL || cmd == CNZ || cmd == CZ || cmd == CNC || cmd == CC ||
                cmd == CPO || cmd == CPE || cmd == CP || cmd == CM) {
            return 3;
        } else if (cmd == MVI_A || cmd == MVI_B || cmd == MVI_C ||
                cmd == MVI_D || cmd == MVI_E || cmd == MVI_H ||
                cmd == MVI_L || cmd == MVI_M || cmd == ANI || cmd == ORI ||
                cmd == CPI || cmd == ADI || cmd == SUI || cmd == XRI ||
                cmd == IN || cmd == OUT || cmd == SBI ||
                cmd == LHLD || cmd == SHLD) {
            return 2;
        } else  {
            return 1;
        }
    }
}
