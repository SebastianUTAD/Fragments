package com.thegmd.assetjsonexample

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Actualiza RecycleView
 */
interface ActualizarRecycle {

    fun <T> RecyclerView.Adapter<*>.autoNotify(
        viejo: List<T>,
        nuevo: List<T>,
        compara: (T, T) -> Boolean
    ) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compara(viejo[oldItemPosition], nuevo[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return viejo[oldItemPosition] == nuevo[newItemPosition]
            }

            override fun getOldListSize() = viejo.size

            override fun getNewListSize() = nuevo.size
        })

        diff.dispatchUpdatesTo(this)
    }
}