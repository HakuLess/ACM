package kickstart.round2022.roundC

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, x, y) = readLine()!!.split(' ').map { it.toInt() }
        val sum = n * (n + 1) / 2
        if (sum % (x + y) == 0) {
            println("Case #${it + 1}: POSSIBLE")
            val ans = arrayListOf<Int>()
            var left = sum / (x + y) * x
            var cur = n
            while (left != 0) {
                if (left >= cur) {
                    left -= cur
                    ans.add(cur)
                } else {
                    ans.add(left)
                    left = 0
                }
                cur--
            }
            println(ans.size)
            println(ans.joinToString(" "))
        } else {
            println("Case #${it + 1}: IMPOSSIBLE")
        }
    }
}