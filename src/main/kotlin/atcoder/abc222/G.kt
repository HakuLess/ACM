package atcoder.abc222

fun main(args: Array<String>) {
    fun check(k: Long): Int {
        var cur = 2L
        var step = 1
        val meet = HashSet<Long>()

        while (cur % k != 0L) {
            if (cur % k in meet) return -1
            meet.add(cur % k)
            cur = (cur * 10 + 2) % k
            step++
        }
        return step
    }

    val n = readLine()!!.trim().toInt()
    repeat(n) {
        val k = readLine()!!.trim().toInt()
        println(check(k.toLong()))
    }
}