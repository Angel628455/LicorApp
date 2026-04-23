package edu.ucne.licorapp.data.mapper

import edu.ucne.licorapp.data.local.entity.CarritoEntity
import edu.ucne.licorapp.domain.model.CarritoItem

fun CarritoEntity.toDomain(): CarritoItem {
    return CarritoItem(
        mercanciaId = this.mercanciaId,
        nombre = this.nombre,
        precio = this.precio,
        imagenUrl = this.imagenUrl,
        cantidad = this.cantidad
    )
}

fun CarritoItem.toEntity(usuarioId: Int, isSynced: Boolean = false): CarritoEntity {
    return CarritoEntity(
        mercanciaId = this.mercanciaId,
        nombre = this.nombre,
        precio = this.precio,
        imagenUrl = this.imagenUrl,
        cantidad = this.cantidad,
        usuarioId = usuarioId,
        isSynced = isSynced
    )
}