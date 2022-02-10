package utils

/**
 * 位运算工具类
 * */
// n位二进制
class Bits(val n: Int) {

    /**
     * 遍历n位二进制所有值
     * */
    fun eachMask(func: (Int) -> Unit) {
        for (mask in 0 until (1 shl n)) {
            func(mask)
        }
    }

    /**
     * 完整遍历n位二进制包含整数
     *
     * @param mask 当前进行运算的值
     * @param func 执行回调，从右至左遍历（低位到高位）
     *
     * 100 则调用(0, false) (1, false) (2, true)
     * */
    fun eachBit(mask: Int, func: (Int, Boolean) -> Unit) {
        for (i in 0 until n) {
            func(i, mask and (1 shl i) != 0)
        }
    }
}

/**
 * 将第offset位改变
 * 如 10010改变第1位（从低到高），则变为10000
 *
 * @param offset 偏移位
 * @return 改变后的值
 * */
fun Int.changeBit(offset: Int): Int {
    return this xor (1 shl offset)
}

/**
 * 从低位到高位数，第offset位是否为1
 *
 * @param offset 偏移index
 * @return true 该位为1，false 该位不为1
 * */
fun Int.bitOne(offset: Int): Boolean {
    return (this shr offset) and 1 == 1
}

fun Int.countOne(): Int {
    return Integer.bitCount(this)
}