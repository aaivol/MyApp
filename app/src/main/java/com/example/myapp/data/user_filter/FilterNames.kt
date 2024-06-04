package com.example.myapp.data.user_filter

enum class FilterNames(val filterName: String) {
    DIABETES("DIABETES"),
    ALLERGY("ALLERGY"),
    FAT("FAT"),
    GASTRITIS("GASTRITIS"),
    NO_MEAT("NO_MEAT"),
    VEGAN("VEGAN"),
    NO_MILK("NO_MILK"),
    PREGNANT("PREGNANT"),
    LACTATION("LACTATION")
}

val enFilterToRu = mapOf(
    FilterNames.DIABETES.name to "Диабет",
    FilterNames.ALLERGY.name to "Аллергия",
    FilterNames.FAT.name to "Ожирение",
    FilterNames.GASTRITIS.name to "Гастрит",
    FilterNames.NO_MEAT.name to "Без мяса",
    FilterNames.VEGAN.name to "Веган",
    FilterNames.NO_MILK.name to "Без молока",
    FilterNames.PREGNANT.name to "Жду ребенка",
    FilterNames.LACTATION.name to "Кормление",
)