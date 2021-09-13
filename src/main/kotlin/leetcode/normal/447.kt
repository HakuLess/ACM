package leetcode.normal

class Solution447 {
    fun numberOfBoomerangs(points: Array<IntArray>): Int {
        fun dis(x: IntArray, y: IntArray): Int {
            return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1])
        }

        var ans = 0
        for (i in points.indices) {
            val map = HashMap<Int, Int>()
            for (j in points.indices) {
                if (i == j) continue
                val dis = dis(points[i], points[j])
                map[dis] = map.getOrDefault(dis, 0) + 1
            }
            map.forEach { (_, value) ->
                ans += value * (value - 1)
            }
        }
        return ans
    }
}