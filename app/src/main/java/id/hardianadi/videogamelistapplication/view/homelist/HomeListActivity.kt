package id.hardianadi.videogamelistapplication.view.homelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.hardianadi.videogamelistapplication.R
import id.hardianadi.videogamelistapplication.core.data.Resource
import id.hardianadi.videogamelistapplication.core.ui.GameListAdapter
import id.hardianadi.videogamelistapplication.viewmodel.HomeListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home_list.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeListActivity : AppCompatActivity() {

    private val viewModel: HomeListViewModel by viewModel()
    private val mAdapter = GameListAdapter()
    private val mCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_list)

        prepareRecyclerView()

        loadUI()
    }

    private fun loadUI() {
        mCompositeDisposable.add(
            viewModel.getGameList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { game ->
                    if (game != null) {
                        when (game) {
                            is Resource.Loading -> progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                progressBar.visibility = View.GONE
                                mAdapter.setData(game.data)
                            }
                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                viewError.visibility = View.VISIBLE
                                tvError.text = game.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                }
        )
    }

    private fun prepareRecyclerView() {
        with(rvGame) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
            mAdapter.onItemClick = { selectedData ->
                val intent = Intent(this@HomeListActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.gameId)
                startActivity(intent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mCompositeDisposable.clear()
        Log.d("HomeListActivity", "onPause: mCompositeDisposable cleared")
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
        Log.d("HomeListActivity", "onDestroy: mCompositeDisposable disposed")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                val uri = Uri.parse("videogamelistapplication://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return true
    }
}