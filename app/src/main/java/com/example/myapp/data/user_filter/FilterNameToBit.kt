package com.example.myapp.data.user_filter

import com.example.myapp.data.user.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val nameToBit = mapOf(
    FilterNames.DIABETES.name to FilterBits.DIABETES_BIT.bit,
    FilterNames.ALLERGY.name to FilterBits.ALLERGY_BIT.bit,
    FilterNames.FAT.name to FilterBits.FAT_BIT.bit,
    FilterNames.GASTRITIS.name to FilterBits.GASTRITIS_BIT.bit,
    FilterNames.NO_MEAT.name to FilterBits.NO_MEAT_BIT.bit,
    FilterNames.VEGAN.name to FilterBits.VEGAN_BIT.bit,
    FilterNames.NO_MILK.name to FilterBits.NO_MILK_BIT.bit,
    FilterNames.PREGNANT.name to FilterBits.PREGNANT_BIT.bit,
    FilterNames.LACTATION.name to FilterBits.LACTATION_BIT.bit,
)

fun Int.setBit(bit: Int, value: Int): Int {
    return if (value == 1) {
        this or (1 shl bit)
    } else {
        this and (1 shl bit).inv()
    }
}

fun Int.getBit(bit: Int): Int {
    return if (this and (1 shl bit) == 0) 0 else 1
}

suspend fun getNewFilters(currentValue: Int, filterName: FilterNames, value: Int): Int {
    return withContext(Dispatchers.IO) {
        currentValue.setBit(nameToBit[filterName.name]!!, value)
    }
}