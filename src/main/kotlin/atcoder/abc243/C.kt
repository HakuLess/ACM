package atcoder.abc243

fun main() {
    val n = readLine()!!.toInt()
    val list = ArrayList<Pair<Int, Int>>()
    repeat(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() }
        list.add(Pair(x, y))
    }
    val s = readLine()!!
    val map = HashMap<Int, ArrayList<Pair<Int, Int>>>()
    for (i in s.indices) {
        val (x, y) = list[i]
        map[y] = map.getOrDefault(y, arrayListOf())
        map[y]!!.add(Pair(x, if (s[i] == 'L') -1 else 1))
    }

    for (key in map.keys) {
        val u = map[key]!!
        u.sortBy { it.first }
        var meet = false
        for (i in u.indices) {
            if (u[i].second == 1) {
                meet = true
            } else if (meet) {
                println("Yes")
                return
            }
        }
    }
    println("No")
}