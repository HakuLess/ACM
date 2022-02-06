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

    val z0 = HashSet<Int>()
    val z1 = HashSet<Int>()
    var flip = false

    fun fix(idx: Int) {
        if (!flip) {
            z1.add(idx)
            z0.remove(idx)
        } else {
            z1.remove(idx)
            z0.add(idx)
        }
    }

    fun unfix(idx: Int) {
        if (!flip) {
            z0.add(idx)
            z1.remove(idx)
        } else {
            z0.remove(idx)
            z1.add(idx)
        }
    }

    fun flip() {
        flip = !flip
    }

    fun all(): Boolean {
        if (flip) {
            return z1.isEmpty()
        } else {
            return z1.size == size
        }
    }

    fun one(): Boolean {
        println("$flip ${z0} ${z1}")
        if (flip) {
            return z1.size != size
        } else {
            return z1.isNotEmpty()
        }
    }

    fun count(): Int {
        if (!flip) {
            return z1.size
        } else {
            return size - z1.size
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until size) {
            if (!flip) {
                if (i in z0) sb.append('0')
                else if (i in z1) sb.append('1')
                else sb.append('0')
            } else {
                if (i in z0) sb.append('1')
                else if (i in z1) sb.append('0')
                else sb.append('1')
            }
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