package edu.ucne.licorapp.data.mapper

import android.R.attr.id
import edu.ucne.licorapp.data.local.entity.MercanciaEntity
import edu.ucne.licorapp.data.remote.dto.mercancia.MercanciaDto
import edu.ucne.licorapp.domain.model.Mercancia

fun MercanciaDto.toEntity(): MercanciaEntity {
    return MercanciaEntity(
        id = id,
        nombre = nombre,
        marca = marca ?: "",
        descripcion = descripcion ?: "",
        precio = precio,
        precioOferta = precioOferta,
        stock = stock ?: 0,
        imagenUrl = imagenUrl ?: "",
        categoria = nombreCategoria ?: "",
        categoriaId = categoriaId ?: 0,
        procesador = procesador ?: "N/A",
        ram = ram ?: "N/A",
        almacenamiento = almacenamiento ?: "N/A",
        calificacion = calificacion ?: 0.0,
        galeriaString = galeria?.joinToString(",") ?: "",
        isSynced = true
    )
}

fun MercanciaEntity.toDomain(): Mercancia {
    return Mercancia(
        id = id,
        nombre = nombre,
        marca = marca ?: "",
        descripcion = descripcion ?: "",
        precio = precio,
        precioOferta = precioOferta,
        stock = stock,
        imagenUrl = imagenUrl ?: "",
        categoria = categoria ?: "",
        categoriaId = categoriaId,
        procesador = procesador ?: "N/A",
        ram = ram ?: "N/A",
        almacenamiento = almacenamiento ?: "N/A",
        calificacion = calificacion ?: 0.0,
        galeriaString = galeriaString
        //galeriaUrls = if (galeriaString.isNotEmpty()) galeriaString.split(",") else emptyList()
    )
}

//fun Mercancia.toDto(): MercanciaDto {
//    return MercanciaDto(
//        id = id,
//        nombre = nombre,
//        marca = marca,
//        descripcion = descripcion,
//        precio = precio,
//        precioOferta = precioOferta,
//        stock = stock,
//        imagenUrl = imagenUrl,
//        nombreCategoria = categoria,
//        categoriaId = categoriaId,
//        procesador = procesador,
//        ram = ram,
//        almacenamiento = almacenamiento,
//        calificacion = calificacion,
//        galeria = galeriaUrls
//    )
