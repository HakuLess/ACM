package leetcode.contest.c274

fun main() {
    val s = SolutionB()

}
class SolutionB {
    fun numberOfBeams(bank: Array<String>): Int {
        val arr = HashMap<Int, HashSet<Int>>()
        for (i in bank.indices) {
            for (j in bank[i].indices) {
                if (bank[i][j] == '1') {
                    arr[i] = arr.getOrDefault(i, hashSetOf())
                    arr[i]!!.add(j)
                }
            }
        }
        var lst = HashSet<Int>()
        var cur = HashSet<Int>()
        var ans = 0
        arr.keys.sorted().forEach {
            lst = cur
            cur = arr[it]!!
            ans += lst.size * cur.size
        }
        return ans
    }
}