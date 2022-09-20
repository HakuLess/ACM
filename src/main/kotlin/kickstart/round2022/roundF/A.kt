package kickstart.round2022.roundF

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = ArrayList<Triple<String, Int, Int>>()
        repeat(n) {
            val s = readLine()!!.split(" ")
            arr.add(Triple(s[0], s[1].toInt(), s[2].toInt()))
        }
        var ans = 0
        val a = arr.sortedWith(compareBy({ it.first }, { it.third })).map { it.third }
        val b = arr.sortedWith(compareBy({ it.second }, { it.third })).map { it.third }
        for (i in a.indices) {
            if (a[i] == b[i]) ans++
        }
        println("Case #${it + 1}: $ans")
    }
}