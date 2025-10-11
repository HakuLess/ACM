package leetcode.contest.b167

import utils.SegmentTree
import utils.SegmentTreeGPT

class ExamTracker() {

    val root = SegmentTree<Long>(
        start = 1,
        end = Int.MAX_VALUE / 2,
        value = 0,
    ) { a, b -> a + b }

    fun record(time: Int, score: Int) {
        root.update(root, time, score.toLong())
    }

    fun totalScore(startTime: Int, endTime: Int): Long {
        return root.query(root, startTime, endTime)
    }

}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * var obj = ExamTracker()
 * obj.record(time,score)
 * var param_2 = obj.totalScore(startTime,endTime)
 */