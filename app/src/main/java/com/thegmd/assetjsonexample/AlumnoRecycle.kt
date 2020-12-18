package com.thegmd.assetjsonexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

/**
 * StudentAdapter is the class for the Student Recycler View
 */
class AlumnoRecycle(private val mainCallBack: AlumnoCallbackListener) :
    RecyclerView.Adapter<AlumnoRecycle.MainViewHolder>(), ActualizarRecycle {

    var alumnos: List<Alumno> by Delegates.observable(emptyList()) { _, viejo, nuevo ->
        autoNotify(viejo, nuevo) { o, n -> o.codigo == n.codigo }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder(
            parent.inflate(R.layout.student_item)
        )

    override fun getItemCount() = alumnos.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mainCallBack, alumnos[position])
    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            albumCallbackListener: AlumnoCallbackListener,
            alumno: Alumno
        ) {
            itemView.setOnClickListener {
                albumCallbackListener.onItemClicked(alumno.codigo)
            }
            val textView: TextView = itemView.findViewById(R.id.textViewStudentName)
            textView.text = alumno.nombre
        }
    }

    interface AlumnoCallbackListener {
        fun onItemClicked(code: Int)
    }

}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}