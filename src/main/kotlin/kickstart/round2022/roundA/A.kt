package kickstart.round2022.roundA

import utils.isSubSeqOf

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val i = readLine().toString()
        val p = readLine().toString()
        if (i.isSubSeqOf(p)) {
            println("Case #${it + 1}: ${p.length - i.length}")
        } else {
            println("Case #${it + 1}: IMPOSSIBLE")
        }
    }
}