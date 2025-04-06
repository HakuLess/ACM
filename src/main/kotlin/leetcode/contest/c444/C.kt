package leetcode.contest.c444

import utils.print

fun main() {
    val s = SolutionC()
    s.maxProduct(intArrayOf(1, 2, 3), 2, 10).print()
}

class SolutionC {
    fun maxProduct(nums: IntArray, k: Int, limit: Int): Int {

        val sum = nums.sum()
        if (k < -sum || k > sum) return -1
        val bitLen = sum * 2 + 1
        val len = (bitLen + 63) / 64

        val dpEven = Array(limit + 1) { Bitset(bitLen, len) }
        val dpOdd = Array(limit + 1) { Bitset(bitLen, len) }

        for (num in nums) {
            if (num <= limit) {
                dpOdd[num].set(sum + num)
            }

            for (p in limit downTo 1) {
                if (!dpOdd[p].isEmpty()) {
                    val np = p * num
                    if (np <= limit) {
                        val shifted = dpOdd[p].shiftRight(num)
                        dpEven[np].or(shifted)
                    }
                }
                if (!dpEven[p].isEmpty()) {
                    val np = p * num
                    if (np <= limit) {
                        val shifted = dpEven[p].shiftLeft(num)
                        dpOdd[np].or(shifted)
                    }
                }
            }
        }

        val target = sum + k
        if (target < 0 || target >= bitLen) return -1
        for (p in limit downTo 1) {
            if (dpEven[p].get(target) || dpOdd[p].get(target)) {
                return p
            }
        }
        return -1
    }
}

class Bitset(val size: Int, val len: Int) {
    private val bits = LongArray(len)
    fun set(idx: Int) {
        bits[idx ushr 6] = bits[idx ushr 6] or (1L shl (idx and 63))
    }

    fun get(idx: Int) = ((bits[idx ushr 6] ushr (idx and 63)) and 1L) != 0L
    fun or(other: Bitset) {
        for (i in bits.indices) bits[i] = bits[i] or other.bits[i]
    }

    fun isEmpty() = bits.all { it == 0L }

    // 向左移 shift 位
    fun shiftLeft(shift: Int): Bitset {
        val res = Bitset(size, len)
        val ws = shift ushr 6
        val bs = shift and 63
        for (i in bits.indices.reversed()) {
            var w = 0L
            val src = i - ws
            if (src >= 0) {
                w = bits[src] shl bs
                if (bs != 0 && src - 1 >= 0) {
                    w = w or (bits[src - 1] ushr (64 - bs))
                }
            }
            res.bits[i] = w
        }
        // 清除最后一组多余的位
        val rem = size and 63
        if (rem != 0) {
            val mask = (1L shl rem) - 1
            res.bits[bits.size - 1] = res.bits[bits.size - 1] and mask
        }
        return res
    }

    // 向右移 shift 位
    fun shiftRight(shift: Int): Bitset {
        val res = Bitset(size, len)
        val ws = shift ushr 6
        val bs = shift and 63
        for (i in bits.indices) {
            var w = 0L
            val src = i + ws
            if (src < bits.size) {
                w = bits[src] ushr bs
                if (bs != 0 && src + 1 < bits.size) {
                    w = w or (bits[src + 1] shl (64 - bs))
                }
            }
            res.bits[i] = w
        }
        return res
    }
}
