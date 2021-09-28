package codeforces.round744

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }
        val ans = ArrayList<Int>()
        arr.forEach {
            if (ans.isEmpty() || it < ans.first()) {
                ans.add(0, it)
            } else {
                ans.add(it)
            }
        }
        println(ans.joinToString(" "))
    }
}