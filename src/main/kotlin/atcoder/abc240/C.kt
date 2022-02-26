package atcoder.abc240

fun main(args: Array<String>) {
    val (n, x) = readLine()!!.trim().split(" ").map { it.toInt() }
    var cur = HashSet<Int>()
    cur.add(0)

    repeat(n) {
        val (a, b) = readLine()!!.trim().split(" ").map { it.toInt() }
        val next = HashSet<Int>()
        cur.forEach {
            if (a + it <= x)
                next.add(a + it)
            if (b + it <= x)
                next.add(b + it)
        }
        cur = next
    }
    println(if (x in cur) "Yes" else "No")
}