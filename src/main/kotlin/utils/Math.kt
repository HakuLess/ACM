package utils

import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayList

/**
 * 数学相关操作
 *
 * 排列组合数
 * 快速幂
 * 素数
 * 全排列
 * 二分查找
 * 回文字符串（Manacher算法）
 * 表达式计算
 * */

// 阶乘
// 乘法逆元计算
fun fac(n: Int, mod: Long = 1000000007L): Pair<LongArray, LongArray> {
    val fac = LongArray(n + 1)
    fac[0] = 1
    val invFac = LongArray(n + 1)
    invFac[0] = 1
    for (i in 1..n) {
        fac[i] = fac[i - 1] * i % mod
        invFac[i] = inv(fac[i], mod)
    }
    // first: 阶乘数
    // second: 乘法逆元
    // 乘以a的阶乘，使用*fac[a]
    // 除以a的阶乘，使用*invFac[a]
    return Pair(fac, invFac)
}

fun fexp(x: Long, y: Long, mod: Long): Long {
    var x: Long = x
    var y: Long = y
    var ans: Long = 1
    while (y != 0L) {
        if (y and 1 != 0L) ans = ans * x % mod
        x = x * x % mod
        y = y shr 1
    }
    return ans
}

fun inv(x: Long, mod: Long = 1000000007L): Long {
    return fexp(x, mod - 2, mod)
}

// 组合
fun C(n: Int, m: Int, mod: Long = 1000000007L): Long {
    return fac(n, mod).let {
        it.first[n] * it.second[m] % mod
    }
}


// 线性筛出法
// 获取0..N的素数的个数
fun Int.countPrime(): Int {
    val primes = ArrayList<Int>()
    val isPrime = IntArray(this) { 1 }
    for (i in 2 until this) {
        if (isPrime[i] == 1) {
            primes.add(i)
        }
        var j = 0
        while (j < primes.size && i * primes[j] < this) {
            isPrime[i * primes[j]] = 0
            if (i % primes[j] == 0) {
                break
            }
            ++j
        }
    }
    return primes.size
}

/**
 * 获取minPrime列表
 * prime[k] == n 代表 k因式分解最小因子为n，若n==1代表k为质数
 *
 * @param k 要求的质数的最大值
 * */
fun getPrime(k: Int): IntArray {
    val minPrime = IntArray(k + 1) { 1 }
    var p = 2
    while (p <= k) {
        var i = p
        while (p <= k / i) {
            if (minPrime[i * p] == 1) {
                minPrime[i * p] = p
            }
            i++
        }
        p++
        while (p <= k) {
            if (minPrime[p] == 1)
                break
            p++
        }
    }
    return minPrime
}

/**
 * 快速幂
 * 计算 base 的 pow 次方，并且求其 %m 后的结果
 * */
fun quickPower(base: Long, pow: Long, m: Long = 1000000007L): Long {
    var res = 1L
    var a = base
    var b = pow
    while (b > 0) {
        if (b and 1L != 0L)
            res = res * a % m
        a = a * a % m
        b = b shr 1
    }
    return res
}

fun quickPower(base: BigInteger, pow: BigInteger, m: Long = 1000000007L): BigInteger {
    var res = 1L.toBigInteger()
    var a = base
    var b = pow
    while (b > 0.toBigInteger()) {
        if (b and 1L.toBigInteger() != 0L.toBigInteger()) {
            if (m == -1L) {
                res = (res * a)
            } else {
                res = (res * a).mod(m.toBigInteger())
            }
        }
        if (m == -1L) {
            a = (a * a)
        } else {
            a = (a * a).mod(m.toBigInteger())
        }
        b = b shr 1
    }
    return res
}

/**
 * GCD 最大公约数
 * */
tailrec fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

tailrec fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

tailrec fun gcd(a: BigInteger, b: BigInteger): BigInteger {
    return if (b == BigInteger.ZERO) a else gcd(b, a % b)
}

/**
 * LCM 最小公倍数
 * */
fun lcm(a: Long, b: Long): Long {
    return a / gcd(a, b) * b
}

fun lcm(a: BigInteger, b: BigInteger): BigInteger {
    return a / gcd(a, b) * b
}

// 无重复数组，全排列
fun CharArray.permute(): List<List<Char>> {
    val ans = ArrayList<List<Char>>()
    fun backtrack(nums: CharArray, tracker: ArrayList<Char>) {
        if (tracker.size == nums.size) {
            ans.add(ArrayList(tracker))
            return
        }
        for (num in nums) {
            tracker.add(num)
            backtrack(nums, tracker)
            tracker.remove(num)
        }
    }
    backtrack(this, arrayListOf())
    return ans
}

// 无重复数组，全排列
fun IntArray.permute(): List<List<Int>> {
    val ans = ArrayList<List<Int>>()
    fun backtrack(nums: IntArray, tracker: ArrayList<Int>) {
        if (tracker.size == nums.size) {
            ans.add(ArrayList(tracker))
            return
        }
        for (num in nums) {
            if (tracker.contains(num)) {
                continue
            }
            tracker.add(num)
            backtrack(nums, tracker)
            tracker.remove(num)
        }
    }
    backtrack(this, arrayListOf())
    return ans
}

/**
 * 二分查找，找到第一个满足条件的Index
 * 若整个列表都没有满足的，返回-1
 * */
fun <T> ArrayList<T>.biFirstIndexOf(func: (T) -> Boolean): Int {
    if (this.isEmpty()) return -1
    var left = 0
    var right = this.lastIndex
    while (left + 1 < right) {
        val mid = (left + right).ushr(1)
        when {
            func(this[mid]) -> right = mid
            else -> left = mid
        }
    }
//    println("$left $right")
    return when {
        func(this[left]) -> left
        func(this[right]) -> right
        else -> -1
    }
}

/**
 * 二分查找，找到最后一个满足条件的Index
 * 若整个列表都没有满足的，返回-1
 * */
fun <T> ArrayList<T>.biLastIndexOf(func: (T) -> Boolean): Int {
    if (this.isEmpty()) return -1
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

fun IntArray.biFirstIndexOf(func: (Int) -> Boolean): Int {
    var left = 0
    var right = this.lastIndex
    while (left + 1 < right) {
        val mid = (left + right).ushr(1)
        when {
            func(this[mid]) -> right = mid
            else -> left = mid
        }
    }
    return when {
        func(this[left]) -> left
        func(this[right]) -> right
        else -> -1
    }
}

// 马拉车算法，O(n)获取回文最大半径
fun manacher(s: String): IntArray {
    val n = s.length
    var i = 0
    var j = -1
    var mx = -1
    // 以i为中心的回文最大长度，存放的是半径值
    val len = IntArray(n)
    while (i < n) {
        if (i > mx)
            len[i] = 0
        else
            len[i] = minOf(len[2 * j - i], mx - i)
        // 中心扩展
        while (i - len[i] - 1 >= 0 && i + len[i] + 1 < n && s[i - len[i] - 1] == s[i + len[i] + 1])
            ++len[i]
        if (i + len[i] > mx) {
            mx = i + len[i]
            j = i
        }
        i++
    }
    return len
}

// 离散化
fun IntArray.discretization(): IntArray {
    val map = HashMap<Int, Int>()
    var i = 1
    this.sorted().distinct().forEach {
        map[it] = i
        i++
    }
    return this.map { map[it]!! }.toIntArray()
}

// 表达式计算
// 支持 +-*/ 并符合运算规律，先计算*/再计算+-
// todo 增加括号的支持
// todo 支持非单位数（10以内）
fun eval(expression: String): Int {
    val stn = Stack<Int>()
    val op = Stack<Char>()
    expression.forEach {
        if (it in '0'..'9') {
            stn.push(it - '0')
            if (op.isNotEmpty() && op.peek() in arrayOf('*', '/')) {
                val a = stn.pop()
                val b = stn.pop()
                when (op.pop()) {
                    '*' -> stn.push(a * b)
                    '/' -> stn.push(b / a)
                }
            }
        } else {
            op.push(it)
        }
    }
    while (op.isNotEmpty()) {
        val a = stn.pop()
        val b = stn.pop()
        when (op.pop()) {
            '+' -> stn.push(a + b)
            '-' -> stn.push(b - a)
        }
    }
    return stn.pop()
}

/**
 * 全组合下的全排列
 * */
inline fun <reified T : Comparable<T>> Array<T>.permutations(func: (Array<T>) -> Unit) {
    val n = this.size
    for (mask in (1 shl n) - 1 downTo 1) {
        val arr = ArrayList<T>()
        for (i in 0 until n) {
            if (mask and (1 shl i) != 0) {
                arr.add(this[i])
            }
        }
        val permute = arr.toTypedArray()
        do {
            func(permute)
        } while (permute.nextPermutation())
    }
}

fun <T : Comparable<T>> Array<T>.nextPermutation(): Boolean {
    for (i in this.size - 2 downTo 0) {
        for (j in this.size - 1 downTo i + 1) {
            if (this[j] > this[i]) {
                val temp = this[i]
                this[i] = this[j]
                this[j] = temp
                this.sort(i + 1, this.size)
                return true
            }
        }
    }
    // 若已为最大，返回false，若要继续最小，则直接reverse即可
//    this.reverse()
    return false
}