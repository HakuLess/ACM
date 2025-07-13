package utils

import java.math.BigInteger
import java.text.DecimalFormat
import java.util.*


object L {
    operator fun <T> get(vararg a: T) = listOf(*a)
}

fun Array<Suffix>.printSuffix() {
    this.forEachIndexed { index, suffix ->
        println("$index: ${suffix.index}")
    }
}

fun Array<LongArray>.print() {
    this.forEach {
        println(it.joinToString(", "))
    }
    println()
}

fun Array<CharArray>.print() {
    this.forEach {
        println(it.joinToString(", "))
    }
    println()
}

fun Array<DoubleArray>.print() {
    this.forEach {
        println(it.joinToString(", "))
    }
    println()
}

fun Stack<Char>.print() {
    println(this.joinToString(", "))
}

fun Map<Int, Int>.printInt() {
    this.forEach {
        println("${it.key} : ${it.value}")
    }
    println()
}

fun Map<Int, Long>.printLong() {
    this.forEach {
        println("${it.key} : ${it.value}")
    }
    println()
}

fun Map<String, Int>.print() {
    this.forEach {
        println("${it.key} : ${it.value}")
    }
    println()
}

fun Map<Char, Int>.printCharInt() {
    this.forEach {
        println("${it.key} : ${it.value}")
    }
    println()
}

fun IntArray.fits(grid: Array<IntArray>, func: (Int, Int) -> Unit) {
    val (x, y) = this
    if (x in grid.indices && y in grid[0].indices) {
        func(x, y)
    }
}

fun Array<IntArray>.print() {
    this.forEach {
        println(it.joinToString(", "))
    }
    println()
}

fun Array<BooleanArray>.print() {
    this.forEach {
        println(it.joinToString(", "))
    }
    println()
}

fun ArrayList<Pair<Int, Int>>.print() {
    this.forEach {
        println("${it.first}, ${it.second}")
    }
    println()
}

fun <T> ArrayList<T>.swap(a: Int, b: Int) {
    val tmpA = this[a]
    val tmpB = this[b]
    this[b] = tmpA
    this[a] = tmpB
}

fun Char.print() {
    println("Char is $this")
}

fun String.print() {
    println("String is $this")
}

fun Int.print() {
    println("Int is $this")
}

fun BigInteger.print() {
    println("BigInteger is $this")
}

fun Int.toBigInteger(): BigInteger {
    return BigInteger.valueOf(this.toLong())
}

fun Long.toBigInteger(): BigInteger {
    return BigInteger.valueOf(this)
}

fun IntArray.toArrayList(): ArrayList<Int> {
    val ans = ArrayList<Int>()
    this.forEach {
        ans.add(it)
    }
    return ans
}

fun IntArray.sumMod(mod: Int): Int {
    var ans = 0
    this.forEach {
        ans += it
        ans %= mod
    }
    return ans
}

fun PriorityQueue<Int>.print() {
    val other = ArrayList<Int>()
    while (this.isNotEmpty()) {
        val it = this.poll()
        print("$it, ")
        other.add(it)
    }
    println()
    this.addAll(other)
}

fun PriorityQueue<Int>.toArrayList(): ArrayList<Int> {
    val other = ArrayList<Int>()
    while (this.isNotEmpty()) {
        val it = this.poll()
        other.add(it)
    }
    this.addAll(other)
    return other
}

fun Long.print() {
    println("Long is $this")
}

fun Double.print() {
    println("Long is $this")
}

//fun TNode.print() {
//    println("level: ${this.level}, value: ${this.`val`}")
//    for (child in this.child) {
//        child.print()
//    }
//}

fun List<List<Int>>.print() {
    this.forEach {
        println(it.joinToString(", "))
    }
}

fun LongArray.print() {
    println(this.joinToString(", "))
}

fun IntArray.print() {
    println(this.joinToString(", "))
}

fun IntArray.swap(index0: Int, index1: Int) {
    val tmp = this[index0]
    this[index0] = this[index1]
    this[index1] = tmp
}

// 第0位为0，第1位为arr[0]，第2位为arr[0] + arr[1]
// 前缀和
fun IntArray.preSumArray(needZero: Boolean = true): LongArray {
    val n = this.size
    val preSum: LongArray
    if (!needZero) {
        preSum = LongArray(n)
        this.foldIndexed(0L) { index, acc, e ->
            preSum[index] = acc + e
            acc + e
        }
    } else {
        preSum = LongArray(this.size + 1)
        this.foldIndexed(0L) { index, acc, e ->
            preSum[index + 1] = acc + e
            acc + e
        }
    }
    return preSum
}

// 第0位为0，第1位为arr[0]，第2位为arr[0] + arr[1]
// 前缀和
fun LongArray.preSumArray(needZero: Boolean = true): LongArray {
    val n = this.size
    val preSum: LongArray
    if (!needZero) {
        preSum = LongArray(n)
        this.foldIndexed(0L) { index, acc, e ->
            preSum[index] = acc + e
            acc + e
        }
    } else {
        preSum = LongArray(this.size + 1)
        this.foldIndexed(0L) { index, acc, e ->
            preSum[index + 1] = acc + e
            acc + e
        }
    }
    return preSum
}

/**
 * 获取作为最小值的范围
 *
 * 右侧到绝对小于当前值
 * 左侧到小于等于当前值
 * */
fun IntArray.minBound(): Array<IntArray> {
    val n = this.size
    val ans = Array(n) { intArrayOf(-1, n) }
    val st = Stack<Int>()
    for (i in this.indices) {
        while (st.isNotEmpty() && this[st.peek()] > this[i]) {
            ans[st.pop()][1] = i
        }
        st.push(i)
    }

    st.clear()
    for (i in this.indices.reversed()) {
        while (st.isNotEmpty() && this[st.peek()] >= this[i]) {
            ans[st.pop()][0] = i
        }
        st.push(i)
    }
    return ans
}

fun IntArray.maxBound() {

}

fun IntArray.biLastIndexOf(func: (Int) -> Boolean): Int {
    var left = 0
    var right = this.lastIndex
    while (left + 1 < right) {
        val mid = (left + right).ushr(1)
        when {
            func(this[mid]) -> left = mid
            else -> right = mid
        }
    }
    return when {
        func(this[right]) -> right
        func(this[left]) -> left
        else -> -1
    }
}

fun DoubleArray.print() {
    println(this.joinToString(", "))
}

fun TreeSet<Int>.print() {
    this.joinToString(", ").also {
        println("TreeSet is $it")
    }
}

fun BooleanArray.print() {
    println(this.joinToString(", "))
}

fun Boolean.print() {
    println(this)
}

/**
 * 倍增法，后缀数组
 * */
class Suffix(
    var index: Int,
    var rank: IntArray
)

class SuffixArray(private val str: String) {
    private val n = str.length
    private var suffixes = Array(n) { Suffix(0, IntArray(2)) }

    private val compare = compareBy<Suffix>({ it.rank[0] }, { it.rank[1] })

    init {
        for (i in 0 until n) {
            suffixes[i].rank[0] = str[i] - 'a'
            suffixes[i].rank[1] = if (i < n - 1) str[i + 1] - 'a' else -1
            suffixes[i].index = i
        }

        suffixes = suffixes.sortedWith(compare).toTypedArray()

        val ind = IntArray(n)

        var k = 4
        while (k < 2 * n) {
            var rank = 0
            var preRank = suffixes[0].rank[0]
            suffixes[0].rank[0] = rank
            ind[suffixes[0].index] = 0

            for (i in 1 until n) {
                if (suffixes[i].rank[0] == preRank && suffixes[i].rank[1] == suffixes[i - 1].rank[1]) {
                    preRank = suffixes[i].rank[0]
                    suffixes[i].rank[0] = rank
                } else {
                    preRank = suffixes[i].rank[0]
                    suffixes[i].rank[0] = ++rank
                }
                ind[suffixes[i].index] = i
            }

            for (i in 0 until n) {
                val nextIndex = suffixes[i].index + k / 2
                suffixes[i].rank[1] = if (nextIndex < n) {
                    suffixes[ind[nextIndex]].rank[0]
                } else {
                    -1
                }
            }

            suffixes = suffixes.sortedWith(compare).toTypedArray()
            k *= 2
        }
    }

    fun getSuffixArray(): Array<Suffix> {
        return suffixes
    }

    fun kasai(): Array<Int> {
        val lcp = Array(n) { 0 }
        val invSuff = Array(n) { 0 }

        for (i in 0 until n) {
            invSuff[suffixes[i].index] = i
        }

        var k = 0
        for (i in 0 until n) {
            if (invSuff[i] == n - 1) {
                k = 0
                continue
            }

            val j = suffixes[invSuff[i] + 1].index

            while (i + k < n && j + k < n && str[i + k] == str[j + k]) {
                k++
            }

            lcp[invSuff[i]] = k

            if (k > 0) {
                k--
            }
        }

        return lcp
    }
}

/**
 * 最长公共前缀
 * */
fun lcp(strs: Array<String>): String {
    if (strs.isEmpty()) {
        return ""
    }
    var prefix = strs[0]
    for (i in 1 until strs.size) {
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) {
                return ""
            }
        }
    }
    return prefix
}

fun String.multi(count: Int): String {
    val sb = StringBuilder()
    repeat(count) {
        sb.append(this)
    }
    return sb.toString()
}

/**
 * 获取所有subSet的可能的和
 * */
fun IntArray.toAllSubSet(): HashSet<Int> {
    val set = hashSetOf<Int>()
    val n = this.size
    for (i in 0..(1 shl n)) {
        var tmp = 0
        for (j in 0 until n) {
            if (i and (1 shl j) != 0) tmp += this[j]
        }
        set.add(tmp)
    }
    return set
}

fun String.isPalindrome(): Boolean {
    var l = 0
    var r = this.lastIndex
    while (l < r) {
        if (this[l] != this[r]) return false
        l++
        r--
    }
    return true
}

fun MutableList<Char>.isPalindrome(): Boolean {
    var l = 0
    var r = this.lastIndex
    while (l < r) {
        if (this[l] != this[r]) return false
        l++
        r--
    }
    return true
}

// 乘法逆元计算
fun getFac(n: Int, mod: Long = 1000000007L): Pair<LongArray, LongArray> {
    val fac = LongArray(n + 1)
    fac[0] = 1
    val invFac = LongArray(n + 1)
    invFac[0] = 1
    for (i in 1..n) {
        fac[i] = fac[i - 1] * i % mod
        invFac[i] = inv(fac[i], mod)
    }
    return Pair(fac, invFac)
}

/**
 * 单调栈 Monotonic Stack
 *
 * @param reversed 是否逆序
 * @param isMin 单调递增 or 递减
 * @param strict 严格递增递减
 *
 * @return 获取该数组对应Index，左侧 or 右侧 第一个大于 or 小于当前值的Index
 */
fun IntArray.getStackIndex(reversed: Boolean, isMin: Boolean, strict: Boolean): IntArray {
    val n = this.size
    val ans = IntArray(n)
    val d: Deque<Int> = ArrayDeque()
    fun func(i: Int) {
        while (!d.isEmpty() && if (isMin) {
                if (!strict) {
                    this[d.peekLast()] >= this[i]
                } else {
                    this[d.peekLast()] > this[i]
                }
            } else {
                if (!strict) {
                    this[d.peekLast()] <= this[i]
                } else {
                    this[d.peekLast()] < this[i]
                }
            }
        )
            d.pollLast()
        ans[i] = if (d.isEmpty()) {
            if (reversed) n else -1
        } else d.peekLast()
        d.addLast(i)
    }
    if (reversed) {
        for (i in n - 1 downTo 0) {
            func(i)
        }
    } else {
        for (i in 0 until n) {
            func(i)
        }
    }
    return ans
}

// Double保留几位小数
fun Double.format(len: Int): String {
    val pattern = "0." + "#".multi(len)
    val format = DecimalFormat(pattern)
    //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
    return format.format(this)
}