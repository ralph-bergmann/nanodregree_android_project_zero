package udacty.nanadegree.projectzero

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import udacty.nanadegree.projectzero.adapter.AppsAdapter
import udacty.nanadegree.projectzero.data.App
import udacty.nanadegree.projectzero.utils.OnAppInteractionListener
import udacty.nanadegree.projectzero.utils.toAppList

class MainActivity : AppCompatActivity(), OnAppInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.setHasFixedSize(true)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = AppsAdapter(resources.getStringArray(R.array.apps).toAppList(), this)
    }

    override fun onClick(app: App) =
        toast(getString(R.string.toast_app_launch, app.name))
}
