package leetcode.contest.b155

class SolutionA {
    fun findCommonResponse(responses: List<List<String>>): String {

        val freq = HashMap<String, Int>()
        for (day in responses) {
            val set = day.toHashSet()
            for (resp in set) {
                freq[resp] = freq.getOrDefault(resp, 0) + 1
            }
        }

        var ans = ""
        var maxCnt = -1
        for ((cur, cnt) in freq) {
            if (cnt > maxCnt || (cnt == maxCnt && cur < ans)) {
                ans = cur
                maxCnt = cnt
            }
        }
        return ans
    }
}