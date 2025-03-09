package leetcode.contest.c440

class SolutionC {
    fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
        val n = baskets.size
        val tree = IntArray(4 * n)

        fun build(idx: Int, l: Int, r: Int) {
            if (l == r) {
                tree[idx] = baskets[l]
            } else {
                val mid = (l + r) / 2
                build(idx * 2, l, mid)
                build(idx * 2 + 1, mid + 1, r)
                tree[idx] = maxOf(tree[idx * 2], tree[idx * 2 + 1])
            }
        }

        fun update(idx: Int, l: Int, r: Int, pos: Int, value: Int) {
            if (l == r) {
                tree[idx] = value
            } else {
                val mid = (l + r) / 2
                if (pos <= mid) {
                    update(idx * 2, l, mid, pos, value)
                } else {
                    update(idx * 2 + 1, mid + 1, r, pos, value)
                }
                tree[idx] = maxOf(tree[idx * 2], tree[idx * 2 + 1])
            }
        }

        fun query(idx: Int, l: Int, r: Int, f: Int): Int {
            if (tree[idx] < f) return -1
            if (l == r) return l
            val mid = (l + r) / 2
            return if (tree[idx * 2] >= f) {
                query(idx * 2, l, mid, f)
            } else {
                query(idx * 2 + 1, mid + 1, r, f)
            }
        }

        build(1, 0, n - 1)

        var ans = 0
        for (fruit in fruits) {
            val basketIndex = query(1, 0, n - 1, fruit)
            if (basketIndex == -1) {
                ans++
            } else {
                update(1, 0, n - 1, basketIndex, 0)
            }
        }
        return ans
    }
}