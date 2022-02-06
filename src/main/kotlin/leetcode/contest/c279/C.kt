package leetcode.contest.c279

import utils.print

fun main() {
    val s = Bitset(45)
    s.all().print()
    s.flip()
    s.count().print()
    s.all().print()
    s.one().print()
}

class Bitset(val size: Int) {

    val b = BooleanArray(size) { false }
    var flip = false
    var sum = 0

    fun fix(idx: Int) {
        if (!(flip xor b[idx])) {
            b[idx] = !b[idx]
            sum++
        }
    }

    fun unfix(idx: Int) {
        if (flip xor b[idx]) {
            b[idx] = !b[idx]
            sum--
        }
    }

    fun flip() {
        flip = !flip
        sum = size - sum
    }

    fun all(): Boolean {
        return sum == size
    }

    fun one(): Boolean {
        return sum > 0
    }

    fun count(): Int {
        return sum
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until size) {
            sb.append(if (b[i] xor flip) '1' else '0')
        }
        return sb.toString()
    }

}

/**
 * Your Bitset object will be instantiated and called as such:
 * var obj = Bitset(size)
 * obj.fix(idx)
 * obj.unfix(idx)
 * obj.flip()
 * var param_4 = obj.all()
 * var param_5 = obj.one()
 * var param_6 = obj.count()
 * var param_7 = obj.toString()
 */