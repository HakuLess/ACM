package luogu

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = ArrayList<Int>()
    repeat(n) {
        arr.add(readLine()!!.trim().toInt())
    }
    val seen = IntArray(n * n)
    fun dfs(l: Int, r: Int): Int {
        val key = l + r * n
        if (seen[key] != 0) return seen[key]
        val multi = n - (r - l)
        if (l == r) return arr[l] * multi
        val ans = maxOf(
            dfs(l + 1, r) + arr[l] * multi,
            dfs(l, r - 1) + arr[r] * multi
        )
        return ans.also {
            seen[key] = it
        }
    }
    println(dfs(0, arr.lastIndex))
}