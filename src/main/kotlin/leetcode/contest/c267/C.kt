package leetcode.contest.c267

import utils.print

fun main() {
    val s = SolutionC()
    s.decodeCiphertext("ch   ie   pr", 3).print()
    s.decodeCiphertext("iveo    eed   l te   olc", 4).print()
}

class SolutionC {
    fun decodeCiphertext(encodedText: String, rows: Int): String {
        val cols = encodedText.length / rows
        val m = Array<CharArray>(rows) { CharArray(cols) }
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                m[i][j] = encodedText[j + i * cols]
            }
        }

        m.print()

        val sb = StringBuilder()
        var i = 0
        var j = 0
        var preJ = -1
        while (j in m[0].indices) {
            if (i == 0) preJ = j
            sb.append(m[i][j])
            i++
            j++
            if (i !in m.indices) {
                i = 0
                j = preJ + 1
            }
        }
        return sb.toString().trimEnd()
    }
}