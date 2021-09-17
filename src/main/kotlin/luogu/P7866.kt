package luogu

fun main(args: Array<String>) {
    val t = readLine()!!.toInt()
    val map = HashMap<Char, IntArray>()
    for (i in 0 until t) {
        val cur = readLine()!!.toCharArray()
        map[cur[1]] = map.getOrDefault(cur[1], IntArray(4))
        val index = when (cur[0]) {
            'S' -> 0
            'H' -> 1
            'C' -> 2
            else -> 3
        }
        map[cur[1]]!![index]++
    }

    var ans = 0
    map.forEach { t, u ->
        u.sortDescending()
        while (u[0] >= 2 && (u[1] > 0 || u[2] > 0 || u[3] > 0)) {
            ans++
            u[0] -= 2
            for (i in 3 downTo 1) {
                if (u[i] > 0) {
                    u[i]--
                    break
                }
            }
            u.sortDescending()
        }
    }
    println(ans)
}