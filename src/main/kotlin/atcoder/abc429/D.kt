package atcoder.abc429

import utils.file.FastScanner
import java.io.BufferedInputStream

fun upperBound(arr: LongArray, from: Int, to: Int, value: Long): Int {
    var l = from
    var r = to // exclusive
    while (l < r) {
        val mid = (l + r) ushr 1
        if (arr[mid] <= value) l = mid + 1 else r = mid
    }
    return l
}

fun main() {
    val fs = FastScanner()
    val n = fs.nextInt()
    val m = fs.nextLong()
    val c = fs.nextInt()

    val a = LongArray(n)
    for (i in 0 until n) a[i] = fs.nextLong()
    a.sort()

    val b = LongArray(2 * n)
    for (i in 0 until n) {
        b[i] = a[i]
        b[i + n] = a[i] + m
    }

    var ans = 0L

    for (i in 0 until n) {
        val j = i + c - 1
        val stopPos = b[j]
        val k = upperBound(b, j, i + n, stopPos).let {
            upperBound(b, 0, 2 * n, stopPos)
        }
        val cntMet = k - i
        val prev = if (i == 0) a[n - 1] - m else a[i - 1]
        val len = a[i] - prev
        ans += len * cntMet
    }

    println(ans)
}