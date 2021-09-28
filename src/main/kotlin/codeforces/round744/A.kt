package codeforces.round744

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    repeat(n) {
        val s = readLine()!!.trim()
        println(if (s.length % 2 == 0 && s.count { it == 'B' } == s.length / 2) "Yes" else "No")
    }
}