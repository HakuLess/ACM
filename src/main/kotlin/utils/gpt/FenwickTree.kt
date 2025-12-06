package utils.gpt


class FenwickTree(private val n: Int) {

    private val tree = LongArray(n + 1)

    fun update(i: Int, delta: Long) {
        var x = i
        while (x <= n) {
            tree[x] += delta
            x += x and -x
        }
    }

    fun query(i: Int): Long {
        var x = i
        var res = 0L
        while (x > 0) {
            res += tree[x]
            x -= x and -x
        }
        return res
    }

    fun query(l: Int, r: Int): Long {
        if (l > r) return 0L
        return query(r) - query(l - 1)
    }

    fun countLE(x: Int): Long = query(x)
    fun countLT(x: Int): Long = query(x - 1)
    fun countGT(x: Int, windowSize: Int): Long = windowSize - query(x)
}