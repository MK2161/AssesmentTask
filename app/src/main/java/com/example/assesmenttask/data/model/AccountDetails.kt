package com.example.assesmenttask.data.model


data class AccountDetails(
    val isSelected: Boolean? = false,
    val index: Int? = null,
    val icon: Int? = null,
    val bankCode: String? = null,
    val bankName: String? = null,
    val accountBalance: String? = null,
)


data class BankDetails(
    val icon: Int? = null,
    val bankName: String? = null,
)