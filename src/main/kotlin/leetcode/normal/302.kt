package leetcode.normal

class Solution302 {
    fun minArea(image: Array<CharArray>, x: Int, y: Int): Int {
        var left = y
        var right = y
        var top = x
        var bottom = x
        for (i in image.indices) {
            for (j in image[0].indices) {
                if (image[i][j] == '1') {
                    left = minOf(left, j)
                    right = maxOf(right, j)
                    top = minOf(top, i)
                    bottom = maxOf(bottom, i)
                }
            }
        }
        return (bottom - top + 1) * (right - left + 1)
    }
}