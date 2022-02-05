package leetcode.contest.b71

class SolutionA {
    fun minimumSum(num: Int): Int {
        val arr = ArrayList<Int>()
        for (i in num.toString()) {
            arr.add(i - '0')
        }
        arr.sort()
        var a = 0
        var b = 0
        var add = true
        for (i in arr.indices) {
            if (add) {
                a *= 10
                a += arr[i]
            } else {
                b *= 10
                b += arr[i]
            }
            add = !add
        }
        return a + b
    }
}