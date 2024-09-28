package net.nomia.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val organizationName: String?,
    val legalAddress: String?,
    val postAddress: String?,
    val postalCode: String?,
    val postAddressCoincidence: Boolean,
    val vatin: String?,
    val kpp: String?,
    val ogrn: String?,
    val bankAccount: String?,
    val currency: String,
    val zoneId: String?,
    val type: String,
    val catalogId: UUID,
    val organizationId: UUID
)