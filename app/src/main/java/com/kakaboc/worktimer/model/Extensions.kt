package com.kakaboc.worktimer.model

fun Long.millisToSeconds(): Long {
    return this / 1000
}

fun Long.secondsToMinutes(): Long {
    return this / 60
}

fun Long.minutesToHours(): Long {
    return this / 60
}

fun Long.hoursToMinutes(): Long {
    return this * 60
}

fun Long.minutesToSeconds(): Long {
    return this * 60
}