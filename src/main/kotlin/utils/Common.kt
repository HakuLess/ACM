package utils

fun String.toIntArray(): IntArray {
    return this.trim { it == '[' || it == ']' }.split(',').map {
        it.toInt()
    }.toIntArray()
}

fun String.toGrid(): Array<IntArray> {
    return this.trim(']').split("],[").map {
        it.toIntArray()
    }.toTypedArray()
}

/**
 * 单调递增子序列长度
 *
 * @param strict 是否严格递增 true 严格递增 false 可以相等
 * */
fun IntArray.lis(strict: Boolean = true): Int {
    var len = 1
    val n: Int = this.size
    if (n == 0) {
        return 0
    }
    val d = IntArray(n + 1)
    d[len] = this[0]
    for (i in 1 until n) {
        if (this[i] > d[len] || (!strict && this[i] == d[len])) {
            d[++len] = this[i]
        } else {
            var l = 1
            var r = len
            var pos = 0
            while (l <= r) {
                val mid = l + r shr 1
                if (d[mid] < this[i] || (!strict && d[mid] == this[i])) {
                    pos = mid
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
            d[pos + 1] = this[i]
        }
    }
    return len
}

/**
 * Longest Common SubSequence
 * */
fun <T> lcs(v1: List<T>, v2: List<T>, m: Int = v1.size, n: Int = v2.size): Int {
    val dp = Array(m + 1) { IntArray(n + 1) }

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
    for (i in 0..m) {
        for (j in 0..n) {
            if (i == 0 || j == 0)
                dp[i][j] = 0
            else if (v1[i - 1] == v2[j - 1])
                dp[i][j] = dp[i - 1][j - 1] + 1
            else
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
        }
    }
    return dp[m][n]
}

/**
 * @param s Sub
 * @param t Target
 * */
fun isSubsequence(s: String, t: String): Boolean {
    var x = 0
    var y = 0
    while (x < s.length && y < t.length) {
        if (s[x] == t[y]) {
            x++
            y++
        } else {
            y++
        }
    }
    return x == s.length
}

/**
 * Current String is a SubSequence Of Target String
 *
 * @param target Target String
 * */
fun String.isSubSeqOf(target: String): Boolean {
    var x = 0
    var y = 0
    while (x < this.length && y < target.length) {
        if (this[x] == target[y]) {
            x++
            y++
        } else {
            y++
        }
    }
    return x == this.length
}