package leetcode.contest.b100

class SolutionA {
    fun distMoney(money: Int, children: Int): Int {
        if (money < children) return -1
        // 先每人1
        val arr = IntArray(children) { 1 }
        var i = 0
        while (arr.sum() < money) {
            arr[i]++
            if (arr[i] == 8 && i != arr.lastIndex) {
                i++
            }
        }
        return arr.count { it == 8 } - if (arr.last() == 4) 1 else 0
    }
}