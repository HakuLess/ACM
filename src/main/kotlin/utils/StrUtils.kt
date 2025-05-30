package utils

/**
 * 字符串操作相关工具类
 *
 * 下一个排列
 *
 * 字符串Hash
 * */


fun kmpSearch(pat: String, txt: String, func: (Int) -> Unit) {
    val m = pat.length
    val n = txt.length

    // create lps[] that will hold the longest prefix suffix values for pattern
    val lps = IntArray(m)
    var j = 0 // index for pat[]

    // Preprocess the pattern (calculate lps[] array)
    computeLPSArray(pat, m, lps)

    var i = 0 // index for txt[]
    while (i < n) {
        if (pat[j] == txt[i]) {
            j++
            i++
        }
        if (j == m) {
//            println("Found pattern at index " + (i - j))
            func(i - j)
            j = lps[j - 1]
        } else if (i < n && pat[j] != txt[i]) {
            // Do not match lps[0..lps[j-1]] characters, they will match anyway
            if (j != 0)
                j = lps[j - 1]
            else
                i += 1
        }// mismatch after j matches
    }
}

fun computeLPSArray(pat: String, m: Int, lps: IntArray) {
    // length of the previous longest prefix suffix
    var len = 0
    var i = 1
    lps[0] = 0 // lps[0] is always 0

    // the loop calculates lps[i] for i = 1 to m-1
    while (i < m) {
        if (pat[i] == pat[len]) {
            len++
            lps[i] = len
            i++
        } else {
            if (len != 0) {
                len = lps[len - 1]
            } else {
                lps[i] = len
                i++
            }
        }
    }
}

/**
 * 下一个更大的排列
 * */
fun CharArray.nextPermutation(): Boolean {
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

fun computePrefixHash(s: String, base: Int, mod: Int): Pair<LongArray, LongArray> {
    val n = s.length
    val hashValues = LongArray(n + 1)  // 用来存储前缀哈希
    val powerValues = LongArray(n + 1) // 存储 base 的幂次值
    hashValues[0] = 0
    powerValues[0] = 1

    for (i in 1..n) {
        hashValues[i] = (hashValues[i - 1] * base + (s[i - 1] - 'a' + 1)) % mod
        powerValues[i] = (powerValues[i - 1] * base) % mod
    }

    return Pair(hashValues, powerValues)
}

/**
 * 字符串Hash
 * */
fun String.hash(l: Int = 0, r: Int = this.length - 1, base: Int = 131, mod: Int = 1000000007): StringHash {
    val (hashValues1, powerValues1) = computePrefixHash(this, base, mod)

    return StringHash(this.length, mod = mod).apply {
        hashValues = hashValues1
        powerValues = powerValues1
    }
}


class StringHash(n: Int, val mod: Int) {
    val length = n
    var hashValues = LongArray(n + 1)  // 用来存储前缀哈希
    var powerValues = LongArray(n + 1) // 存储 base 的幂次值

    fun hash(l: Int = 0, r: Int = this.length - 1): Long {
        // 计算子串 [l, r] 的哈希值
        return (hashValues[r + 1] - (hashValues[l] * powerValues[r - l + 1]) % mod + mod) % mod
    }
}