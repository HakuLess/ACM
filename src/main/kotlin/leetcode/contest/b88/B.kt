package leetcode.contest.b88

class LUPrefix(n: Int) {

    val cur = BooleanArray(n)

    var ans = 0

    fun upload(video: Int) {
        cur[video - 1] = true
        while (ans in cur.indices && cur[ans]) {
            ans++
        }
    }

    fun longest(): Int {
        return ans
    }

}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * var obj = LUPrefix(n)
 * obj.upload(video)
 * var param_2 = obj.longest()
 */