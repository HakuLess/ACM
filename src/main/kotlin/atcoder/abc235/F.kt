package atcoder.abc235

// 计算1..n中，包含所有c的数字的和
// 数位DP，难度够高
fun main() {
    val mod = 998244353L
    val n = readLine()!!.map { it - '0' }
    readLine()
    val c = readLine()!!.split(' ').map { it.toInt() }
    val size = 1024

    var mask = 0
    for (i in c) {
        mask += 1 shl i
    }

    // dpc[i][j]表示，遍历到第i位时，状态j的数量
    // dps[i][j]表示，遍历到第i位时，状态j的总和
    // 将二维优化至一维
    var dpc = LongArray(size)
    var dps = LongArray(size)

    // 前缀数值
    var pre = 0L
    // 前缀状态
    var preStatus = 0

    // 处理第一位数
    for (d in 1 until n[0]) {
        val key = 1 shl d
        dpc[key]++
        dps[key] += d.toLong()
    }
    pre = n[0].toLong()
    preStatus = preStatus or (1 shl n[0])

    for (i in 1 until n.size) {
        val newDpc = LongArray(size)
        val newDps = LongArray(size)

        // 前面选择小于pre值
        for (j in 0 until size) {
            // 第i位选择d
            for (d in 0..9) {
                val key = j or (1 shl d)
                newDpc[key] = (newDpc[key] + dpc[j]) % mod
                newDps[key] = (newDps[key] + dps[j] * 10 + d * dpc[j]) % mod
            }
        }

        // 前面选择都是0，则d需要从1开始
        for (d in 1..9) {
            val key = 1 shl d
            newDpc[key] = (newDpc[key] + 1) % mod
            newDps[key] = (newDps[key] + d) % mod
        }

        // 前边选择与目标值刚好相同
        // 这里不用计算相等的场景，完全相等只用额外处理一次，若只是前部分位相等，之后逻辑可以归入逻辑1中
        for (d in 0 until n[i]) {
            val key = preStatus or (1 shl d)
            newDpc[key] = (newDpc[key] + 1) % mod
            newDps[key] = (newDps[key] + pre * 10 + d) % mod
        }

        pre = (pre * 10 + n[i]) % mod
        preStatus = preStatus or (1 shl n[i])

        dpc = newDpc
        dps = newDps
    }

    var ans = 0L
    for (j in 0 until size) {
        if ((j and mask) == mask) {
            ans = (ans + dps[j]) % mod
        }
    }

    if ((preStatus and mask) == mask) {
        ans = (ans + pre) % mod
    }
    println(ans)
}