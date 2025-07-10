package com.sifuentes.vania.lab13

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sifuentes.vania.lab13.databinding.ListItemImgBinding
import java.io.File

/**
 * Adaptador para mostrar imágenes en el ViewPager2 de la galería
    Array de archivos de imágenes a mostrar
 */
class GalleryAdapter(private val fileArray: Array<File>) : 
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    /**
     * ViewHolder que contiene la vista de cada imagen individual
     */
    class ViewHolder(private val binding: ListItemImgBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        /**
         * Vincula los datos del archivo con la vista
         * Archivo de imagen a mostrar
         */
        fun bind(file: File) {
            // Usar Glide para cargar la imagen de manera eficiente
            Glide.with(binding.root)
                .load(file)
                .into(binding.localImg)
        }
    }

    /**
     * Crea un nuevo ViewHolder cuando es necesario
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ListItemImgBinding.inflate(layoutInflater, parent, false)
        )
    }

    /**
     * Vincula los datos en la posición especificada con el ViewHolder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fileArray[position])
    }

    /**
     * Retorna el número total de elementos en el adaptador
     */
    override fun getItemCount(): Int {
        return fileArray.size
    }
} 