package com.jeluchu.aruxpi.core.utils

/**
 * Ratcliff/Obershelp pattern recognition
 * The Ratcliff/Obershelp algorithm computes the similarity of two strings a
 * the doubled number of matching characters divided by the total number of
 * characters in the two strings. Matching characters are those in the longest
 * common subsequence plus, recursively, matching characters in the unmatched
 * region on either side of the longest common subsequence.
 * The Ratcliff/Obershelp distance is computed as 1 - Ratcliff/Obershelp
 * similarity.
 *
 * @author Ligi https://github.com/dxpux (as a patch for fuzzystring)
 * Ported to java from .net by denmase
 */
class RatcliffObershelp : StringSimilarity, StringDistance {
    /**
     * Compute the Ratcliff-Obershelp similarity between strings.
     *
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return The RatcliffObershelp similarity in the range [0, 1]
     * @throws NullPointerException if s1 or s2 is null.
     */
    override fun similarity(s1: String?, s2: String?): Double {
        if (s1 == null) {
            throw NullPointerException("s1 must not be null")
        }
        if (s2 == null) {
            throw NullPointerException("s2 must not be null")
        }
        if (s1 == s2) {
            return 1.0
        }
        val matches = getMatchList(s1, s2)
        var sumOfMatches = 0
        for (match in matches) {
            sumOfMatches += match.length
        }
        return 2.0 * sumOfMatches / (s1.length + s2.length)
    }

    /**
     * Return 1 - similarity.
     *
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return 1 - similarity
     * @throws NullPointerException if s1 or s2 is null.
     */
    override fun distance(s1: String?, s2: String?): Double {
        return 1.0 - similarity(s1, s2)
    }

    companion object {
        private fun getMatchList(s1: String, s2: String): List<String> {
            val list: MutableList<String> = ArrayList()
            val match = frontMaxMatch(s1, s2)
            if (match.isNotEmpty()) {
                val frontsource = s1.substring(0, s1.indexOf(match))
                val fronttarget = s2.substring(0, s2.indexOf(match))
                val frontqueue = getMatchList(frontsource, fronttarget)
                val endsource = s1.substring(s1.indexOf(match) + match.length)
                val endtarget = s2.substring(s2.indexOf(match) + match.length)
                val endqueue = getMatchList(endsource, endtarget)
                list.add(match)
                list.addAll(frontqueue)
                list.addAll(endqueue)
            }
            return list
        }

        private fun frontMaxMatch(s1: String, s2: String): String {
            var longest = 0
            var longestsubstring = ""
            for (i in s1.indices) {
                for (j in i + 1..s1.length) {
                    val substring = s1.substring(i, j)
                    if (s2.contains(substring) && substring.length > longest) {
                        longest = substring.length
                        longestsubstring = substring
                    }
                }
            }
            return longestsubstring
        }
    }
}