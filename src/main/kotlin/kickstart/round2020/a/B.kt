package kickstart.round2020.a

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, k, p) = readLine()!!.trim().split(' ').map { it.toInt() }
        val arr = ArrayList<IntArray>()
        repeat(n) {
            val res = readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray()
            for (i in 1 until res.size) {
                res[i] += res[i - 1]
            }
            arr.add(res)
        }
        val seen = HashMap<String, Int>()
        fun dfs(index: Int, left: Int): Int {
            if (index !in arr.indices) return 0
            val key = "$index,$left"
            if (key in seen) return seen[key]!!
            var ans = 0
            for (i in 0..minOf(k, left)) {
                ans = maxOf(ans, dfs(index + 1, left - i) + if (i == 0) 0 else arr[index][i - 1])
            }
            return ans.also {
                seen[key] = it
            }
        }

        println("Case #${it + 1}: ${dfs(0, p)}")
    }
}