package udacty.nanadegree.projectzero.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import udacty.nanadegree.projectzero.BR
import udacty.nanadegree.projectzero.R
import udacty.nanadegree.projectzero.data.App
import udacty.nanadegree.projectzero.utils.OnAppInteractionListener
import udacty.nanadegree.projectzero.utils.layoutInflater
import java.lang.ref.WeakReference

const val VIEW_TYPE_HEADER = 0
const val VIEW_TYPE_ITEM_APP = 1

class AppsAdapter(val apps: List<App>,
                  listener: OnAppInteractionListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val clickHandler = ClickHandler(listener)

    override fun getItemViewType(position: Int): Int =
        if (position == 0)
            VIEW_TYPE_HEADER
        else
            VIEW_TYPE_ITEM_APP

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == VIEW_TYPE_HEADER)
            HeaderViewHolder(parent.layoutInflater().inflate(R.layout.cell_header, parent, false))
        else
            AppsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cell_app, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AppsViewHolder) {
            holder.binder.setVariable(BR.app, apps[position - 1]) // -1 for header
            holder.binder.setVariable(BR.clickHandler, clickHandler)
        }
    }

    override fun getItemCount(): Int = apps.size + 1 // +1 for header


    class ClickHandler(listener: OnAppInteractionListener) {

        private val ref = WeakReference<OnAppInteractionListener>(listener)

        fun onClick(app: App) =
            ref.get()?.let { it.onClick(app) }
    }
}

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class AppsViewHolder(val binder: ViewDataBinding) : RecyclerView.ViewHolder(binder.root)
