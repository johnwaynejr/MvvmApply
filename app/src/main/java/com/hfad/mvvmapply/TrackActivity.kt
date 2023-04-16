package com.hfad.mvvmapply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hfad.mvvmapply.databinding.ActivityTrackBinding

//package com.hfad.mvvmapply.presentation.track.TrackViewModel

class TrackActivity : ComponentActivity() {
    private val viewModel by viewModels<TrackViewModel> { TrackViewModel.getViewModelFactory(123) }
    // 1
    private lateinit var binding: ActivityTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2
        binding = ActivityTrackBinding.inflate(layoutInflater)
        // 3
        setContentView(binding.root)

        viewModel.getLoadingLiveData().observe(this) { isLoading ->
            changeProgressBarVisibility(isLoading)
        }
    }

    private fun changeProgressBarVisibility(visible: Boolean) {
        // 4
        binding.progressBar.isVisible = visible
    }
}

//В строке //1  указали поле для `Binding`-класса. Обратите внимание на название `Binding`-класса — `ActivityTrackBinding`. Оно получилось из слияния `activity_track` и `Binding`.
//В строке //2  используем метод `inflate(layoutInflater)` с передачей `layoutInflater`, чтобы создать объект класса `ActivityTrackBinding`. Очень похоже на то, как мы получаем `View` через `xml` с помощью того же `layoutInflater`.
//В строке //3 у `Binding` есть поле `root`, чтобы получить корневой контейнер разметки. В нашем случае это `ConstraintLayout`. Именно через `root` мы и получаем `View` для `Activity.setContentView`.
//В строке //4 получаем доступ к `ProgressBar` через `Binding`-класс. Чтобы получить ссылку на `View`, в `Binding`-классе есть поле по значению `id` каждого элемента `View`. У нас `ProgressBar` имеет значение `android:id="@+id/progress_bar"` и поле `progressBar`.


class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 1
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // ...

    class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            // 2
            itemView.findViewById<TextView>(R.id.title).text = item.text
            itemView.findViewById<TextView>(R.id.field).text = item.field
            // ..
        }
    }

}
