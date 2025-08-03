fun main() = with(System.`in`.bufferedReader()) {
    val x = readLine().toInt()
    val dp = IntArray(x + 1)

    for (i in 2..x) {
        dp[i] = dp[i-1] + 1
        if(i % 2 == 0) dp[i] = minOf(dp[i], dp[i/2] + 1)
        if(i % 3 == 0) dp[i] = minOf(dp[i], dp[i/3] + 1)
    }

    println(dp[x])
}