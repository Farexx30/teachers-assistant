package com.example.teachersassistant.constants

enum class Day(val value: Int, val asString: String) {
    MONDAY(1, "Monday"),
    TUESDAY(2,"Tuesday"),
    WEDNESDAY(3, "Wednesday"),
    THURSDAY(4,"Thursday"),
    FRIDAY(5,"Friday"),
    SATURDAY(6,"Saturday"),
    SUNDAY(7,"Sunday");

    companion object {
        fun fromValue(value: Int): Day {
            return entries.first {
                it.value == value
            }
        }

        fun fromString(value: String): Day {
            return entries.first {
                it.asString.equals(value, ignoreCase = true)
            }
        }
    }
}