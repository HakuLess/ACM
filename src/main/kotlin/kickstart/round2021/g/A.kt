package kickstart.round2021.g

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        var (n, d, c, m) = readLine()!!.trim().split(' ').map { it.toLong() }
        val s = readLine()!!.trim().toString()
        for (i in s.indices) {
            if (s[i] == 'C') {
                c--
            } else {
                d--
                c += m
            }
            if (c < 0 || d < 0) {
                if (d < 0) {
                    println("Case #${it + 1}: NO")
                    return@repeat
                } else {
                    if ((i..s.lastIndex).any { s[it] == 'D' }) {
                        println("Case #${it + 1}: NO")
                        return@repeat
                    } else {
                        println("Case #${it + 1}: YES")
                        return@repeat
                    }
                }
            }
        }
        println("Case #${it + 1}: YES")
    }
}