package kickstart.round2022.roundC

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine().toString()
        val old = readLine().toString()
        val ans = StringBuilder(old)
        if (('a'..'z').all { it !in old }) {
            ans.append('a')
        }
        if (('0'..'9').all { it !in old }) {
            ans.append('0')
        }
        if (('A'..'Z').all { it !in old }) {
            ans.append('A')
        }
        if (arrayOf('#', '@', '*', '&').all { it !in old }) {
            ans.append('#')
        }
        println("Case #${it + 1}: ${ans.padEnd(7, '1')}")
    }
}