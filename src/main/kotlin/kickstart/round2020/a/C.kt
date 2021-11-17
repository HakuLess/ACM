package kickstart.round2020.a

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, k) = readLine()!!.trim().split(' ').map { it.toInt() }
        val arr = readLine()!!.trim().split(' ').map { it.toInt() }.sorted()

        fun check(mid: Int): Boolean {
            var lst = arr[0]
            var c = 0
            for (i in 1 until arr.size) {
                if (arr[i] - lst > mid) {
                    c += (arr[i] - lst - 1) / mid
                }
                lst = arr[i]
            }
            return c <= k
        }

        var left = 1
        var right = Int.MAX_VALUE / 2
        while (left + 1 < right) {
            val mid = (left + right).ushr(1)
            when {
                check(mid) -> right = mid
                else -> left = mid
            }
        }
        val ans = when {
            check(left) -> left
            check(right) -> right
            else -> -1
        }

        println("Case #${it + 1}: $ans")
    }
}