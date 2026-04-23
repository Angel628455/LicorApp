package edu.ucne.licorapp.data.local.entity
import androidx.room.Embedded
import androidx.room.Relation

data class OrdenarWithDetails(
    @Embedded val order: OrdenarEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pedidoId"
    )
    val details: List<OrdenarDetailEntity>
)