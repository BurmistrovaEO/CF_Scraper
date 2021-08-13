import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codeforces_parsed.R
import com.example.codeforces_parsed.database.ContestList

class ContestListAdapter : ListAdapter<ContestList, ContestListAdapter.ContestViewHolder>(ContestsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        return ContestViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name,current.duration,current.startTime)
    }

    class ContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameItemView: TextView = itemView.findViewById(R.id.nameView)
        private val durationItemView : TextView = itemView.findViewById(R.id.durView)
        private val startTimeItemView : TextView = itemView.findViewById(R.id.startView)

        fun bind(n_text: String?, d_text: String?, s_text: String?) {
            nameItemView.text = n_text
            durationItemView.text = d_text
            startTimeItemView.text = s_text
        }

        companion object {
            fun create(parent: ViewGroup): ContestViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ContestViewHolder(view)
            }
        }
    }

    class ContestsComparator : DiffUtil.ItemCallback<ContestList>() {
        override fun areItemsTheSame(oldItem: ContestList, newItem: ContestList): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ContestList, newItem: ContestList): Boolean {
            return (oldItem.name == newItem.name && oldItem.duration == newItem.duration &&
                    oldItem.startTime == newItem.startTime)
        }
    }
}