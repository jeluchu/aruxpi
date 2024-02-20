package com.jeluchu.aruxpi.models.schedule

data class Week(
    /**
     * All current season entries scheduled for Monday.
     */
    val monday: List<AnimesInDay>,

    /**
     * All current season entries scheduled for Tuesday.
     */
    val tuesday: List<AnimesInDay>,

    /**
     * All current season entries scheduled for Wednesday.
     */
    val wednesday: List<AnimesInDay>,

    /**
     * All current season entries scheduled for Thursday.
     */
    val thursday: List<AnimesInDay>,

    /**
     * All current season entries scheduled for Friday.
     */
    val friday: List<AnimesInDay>,

    /**
     * All current season entries scheduled for Saturday.
     */
    val saturday: List<AnimesInDay>,

    /**
     * All current season entries scheduled for Sunday.
     */
    val sunday: List<AnimesInDay>
)