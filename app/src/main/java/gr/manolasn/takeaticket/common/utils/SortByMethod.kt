package gr.manolasn.takeaticket.common.utils

import gr.manolasn.takeaticket.R

enum class SortByMethod(val value: String,val displayValue: Int) {
    ORIGINAL_TITLE_ASC("original_title.asc", R.string.original_title_asc),
    ORIGINAL_TITLE_DESC("original_title.desc", R.string.original_title_desc),
    POPULARITY_ASC("popularity.asc", R.string.popularity_asc),
    POPULARITY_DESC("popularity.desc", R.string.popularity_desc),
    REVENUE_ASC("revenue.asc", R.string.revenue_asc),
    REVENUE_DESC("revenue.desc", R.string.revenue_desc),
    PRIMARY_RELEASE_DATE_ASC("primary_release_date.asc", R.string.primary_release_date_asc),
    PRIMARY_RELEASE_DATE_DESC("primary_release_date.desc", R.string.primary_release_date_desc),
    TITLE_ASC("title.asc", R.string.title_asc),
    TITLE_DESC("title.desc", R.string.title_desc),
    VOTE_AVERAGE_ASC("vote_average.asc", R.string.vote_average_asc),
    VOTE_AVERAGE_DESC("vote_average.desc", R.string.vote_average_desc),
    VOTE_COUNT_ASC("vote_count.asc", R.string.vote_count_asc),
    VOTE_COUNT_DESC("vote_count.desc", R.string.vote_count_desc);

    override fun toString(): String {
        return value
    }

    fun toDisplayValue(): Int {
        return displayValue
    }
}