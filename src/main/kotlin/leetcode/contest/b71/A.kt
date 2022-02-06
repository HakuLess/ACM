package leetcode.contest.b71

class SolutionA {
    fun minimumSum(num: Int): Int {
        val arr = ArrayList<Int>()
        for (i in num.toString()) {
            arr.add(i - '0')
        }
        arr.sort()
        return (arr[0] + arr[1]) * 10 + arr[2] + arr[3]
    }
}