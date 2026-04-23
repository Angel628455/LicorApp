package edu.ucne.licorapp.data.mapper

import edu.ucne.licorapp.data.local.entity.CategoriaEntity
import edu.ucne.licorapp.data.remote.dto.categoria.CategoriaDto
import edu.ucne.licorapp.domain.model.Categoria

fun CategoriaDto.toEntity(): CategoriaEntity {
    return CategoriaEntity(
        id = this.id,
        nombre = this.nombre ?: "General",
        imagenIconoUrl = this.imagenIconoUrl
    )
}

fun CategoriaEntity.toDomain(): Categoria {
    return Categoria(
        id = this.id,
        nombre = this.nombre,
        imagenIconoUrl = this.imagenIconoUrl
    )
}