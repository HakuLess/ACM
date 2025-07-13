package leetcode.contest.c458

import utils.print

fun main() {
    val s = SolutionC()
    s.processStr("a#b%*", 1).print()
    s.processStr("cd%#*#", 3).print()
    s.processStr("z*#", 0).print()
}

class SolutionC {
    fun processStr(s: String, k: Long): Char {

        data class Op(val type: String, val char: Char, val length: Long)

        val ops = ArrayList<Op>()
        var totalLen = 0L

        for (c in s) {
            when (c) {
                '*' -> {
                    if (totalLen > 0) {
                        totalLen -= 1
                        ops.add(Op("DELETE", '*', totalLen))
                    }
                }
                '#' -> {
                    totalLen *= 2
                    ops.add(Op("COPY", '#', totalLen))
                }
                '%' -> {
                    ops.add(Op("REVERSE", '%', totalLen))
                }
                else -> {
                    totalLen += 1
                    ops.add(Op("ADD", c, totalLen))
                }
            }
        }

        if (k >= totalLen) {
            return '.'
        }

        var pos = k
        for (i in ops.indices.reversed()) {
            val op = ops[i]
            when (op.type) {
                "ADD" -> {
                    if (op.length - 1 == pos) {
                        return op.char
                    }
                }
                "DELETE" -> {
                    if (pos >= op.length) {
                        return '.'
                    }
                }
                "COPY" -> {
                    val half = op.length / 2
                    // 如果是后半段 转移至前半段
                    if (pos >= half) {
                        pos -= half
                    }
                }
                "REVERSE" -> {
                    pos = op.length - 1 - pos
                }
            }
        }

        return '.'
    }
}