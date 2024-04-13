package com.example.myapp.data.user_filter

enum class FilterBits(val bit: Int) {
    DIABETES_BIT(0),
    ALLERGY_BIT(1),
    FAT_BIT(2),
    GASTRITIS_BIT(3),
    NO_MEAT_BIT(4),
    VEGAN_BIT(5),
    NO_MILK_BIT(6),
    PREGNANT_BIT(7),
    LACTATION_BIT(8)
}