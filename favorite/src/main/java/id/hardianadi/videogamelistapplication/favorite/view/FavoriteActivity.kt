package id.hardianadi.videogamelistapplication.favorite.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.hardianadi.videogamelistapplication.core.ui.GameListAdapter
import id.hardianadi.videogamelistapplication.favorite.R
import id.hardianadi.videogamelistapplication.favorite.di.favoriteModule
import id.hardianadi.videogamelistapplication.favorite.viewmodel.FavoriteViewModel
import id.hardianadi.videogamelistapplication.view.homelist.DetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import timber.log.Timber

class FavoriteActivity : AppCompatActivity() {

    private val viewModel: FavoriteViewModel by viewModel()
    private val mAdapter = GameListAdapter()
    private val mCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteModule)
        prepareRecyclerView()

        loadUI()
    }

    private fun loadUI() {
        mCompositeDisposable.add(
            viewModel.getFavoriteGames()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { game ->
                    if (game != null) {
                        mAdapter.setData(game)
                        viewEmpty.visibility = if (game.isNotEmpty()) View.GONE else View.VISIBLE
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
                val intent = Intent(
                    this@FavoriteActivity,
                    Class.forName("id.hardianadi.videogamelistapplication.view.homelist.DetailActivity")
                )
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData.gameId)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
        Timber.d("onDestroy: mCompositeDisposable disposed")
    }
}