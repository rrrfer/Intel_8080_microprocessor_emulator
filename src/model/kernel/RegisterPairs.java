package model.kernel;

/**
 * Перечисление, описывающее регистровые пары микропроцессора Intel 8080.
 * @author Maxim Rozhkov
 */
public enum RegisterPairs {

    /**
     * Регистровая пара BC.
     */
    B,

    /**
     * Регистровая пара DE.
     */
    D,

    /**
     * Регистровая пара HL.
     */
    H,

    /**
     * Регистрова пара PSW. Регистровая пара PSW состоит из региста A (старший байт),
     * и регистра флагов (младший байт).
     */
    PSW
}