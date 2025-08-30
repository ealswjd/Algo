// https://www.acmicpc.net/problem/3085

fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0
    val n = readLine().toInt() // 보드의 크기

    // 다음 N개 줄에는 보드에 채워져 있는 사탕의 색상이 주어진다.
    val map: Array<CharArray> = Array(n) {
        readLine().trim().toCharArray()
    }
    val visited: Array<BooleanArray> = Array(n) {
        BooleanArray(n)
    }

    fun checkLine(idx: Int, isRow: Boolean): Int {
        var maxLen = 1
        var cnt = 1

        if (isRow) {
            for (c in 1 until n) {
                if(map[idx][c] == map[idx][c-1]) {
                    cnt++
                    maxLen = maxOf(maxLen, cnt)
                } else {
                    cnt = 1
                }
            }
        } else {
            for (r in 1 until n) {
                if(map[r][idx] == map[r-1][idx]) {
                    cnt++
                    maxLen = maxOf(maxLen, cnt)
                } else {
                    cnt = 1
                }
            }
        }

        return maxLen
    }

    fun swap(r1: Int, c1: Int, r2: Int, c2: Int) {
        val tmp = map[r1][c1]

        map[r1][c1] = map[r2][c2]
        map[r2][c2] = tmp
    }

    // 초기 상태에서 최댓값 계산
    for (i in 0 until n) {
        answer = maxOf(answer, checkLine(i, true))
        answer = maxOf(answer, checkLine(i, false))
    }

    // 모든 인접 칸 교환 시도
    for (r in 0 until n) {
        for (c in 0 until n) {
            // 오른쪽이랑 교환
            if (c+1 < n && check(map[r][c], map[r][c+1])) {
                swap(r, c, r, c+1)
                answer = maxOf(
                    answer,
                    checkLine(r, true),
                    checkLine(c, false),
                    checkLine(c+1, false)
                )
                swap(r, c, r, c+1)
            }

            // 아래랑 교환
            if (r+1 < n && check(map[r][c], map[r+1][c])) {
                swap(r, c, r+1, c)
                answer = maxOf(
                    answer,
                    checkLine(r, true),
                    checkLine(r+1, true),
                    checkLine(c, false)
                )
                swap(r, c, r+1, c)
            }
        }
    }

    // 최댓값 출력
    print(answer)
}

fun check(color1: Char, color2: Char): Boolean {
    return color1 != color2
}
