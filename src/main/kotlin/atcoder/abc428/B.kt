package atcoder.abc428

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val s = readLine()!!

    val map = HashMap<String, Int>()

    for (i in 0..n - k) {
        val sub = s.substring(i, i + k)
        map[sub] = (map[sub] ?: 0) + 1
    }

    val max = map.values.maxOrNull() ?: 0
    val strs = map.filterValues { it == max }.keys.sorted()

    println(max)
    println(strs.joinToString(" "))
}