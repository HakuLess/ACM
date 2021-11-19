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
        val sb = StringBuilder()
        var i = 0
        var j = 0
        var preJ = -1
        while (j in 0 until cols) {
            if (i == 0) preJ = j
            sb.append(encodedText[j + i * cols])
            i++
            j++
            if (i !in 0 until rows) {
                i = 0
                j = preJ + 1
            }
        }
        return sb.toString().trimEnd()
    }
}