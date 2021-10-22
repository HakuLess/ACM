package kickstart.round2021.b

// 等差数列
// 可改变一个元素，最长连续等差数列
fun main() {
    val t = readLine()!!.trim().toInt()

    fun getMax(arr: LongArray): Int {
        var i = 1
        var lst = 0
        var skip = false
        var diff: Long? = null
        var cur = 0
        var ans = 0
        var rec = Pair<Int, Long>(0, 0)
        while (i in arr.indices) {
//            println("$i: $diff, ${arr[i] - arr[i - 1]}")
            when (diff) {
                null -> {
                    diff = arr[i] - arr[i - 1]
                    cur = 1
                }
                arr[i] - arr[i - 1] -> {
                    // 相等，长度增加
                    cur++
                }
                else -> {
                    // 不相等，回退到上次变更位置
                    if (skip) {
                        i = lst
                        diff = null
                        skip = false
                        cur = 0
                        arr[rec.first] = rec.second
                        continue
                    } else {
                        lst = i
                        skip = true
                        cur++
                        rec = Pair(i, arr[i])
//                        println("改变$i from ${arr[i]} to ${arr[i - 1] + diff}")
                        arr[i] = arr[i - 1] + diff
                    }
                }
            }
            ans = maxOf(ans, cur)
            i++
        }
        return ans
    }
    repeat(t) {
        readLine()
        val arr = readLine()!!.trim().split(" ").map { it.toLong() }.toLongArray()

        println("Case #${it + 1}: ${maxOf(getMax(arr.clone()), getMax(arr.reversedArray().clone())) + 1}")
    }
}