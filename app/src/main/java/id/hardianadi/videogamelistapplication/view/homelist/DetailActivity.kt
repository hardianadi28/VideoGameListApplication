package id.hardianadi.videogamelistapplication.view.homelist

import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import id.hardianadi.videogamelistapplication.R
import id.hardianadi.videogamelistapplication.core.data.Resource
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.ui.GenreListAdapter
import id.hardianadi.videogamelistapplication.core.ui.PlatformListAdapter
import id.hardianadi.videogamelistapplication.viewmodel.DetailViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail_game.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModel()
    private var gameId = 0
    private val mCompositeDisposable = CompositeDisposable()
    private val mAdapterPlatform = PlatformListAdapter()
    private val mAdapterGenre = GenreListAdapter()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        gameId = intent.getIntExtra(EXTRA_DATA, 0)

        prepareRecyclerView()
        loadUI()

    }

    private fun loadUI() {
        mCompositeDisposable.add(viewModel.getGameDetail(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> progressBarDetail.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBarDetail.visibility = View.GONE
                            showGameDetail(game.data)
                        }
                        is Resource.Error -> {
                            progressBarDetail.visibility = View.GONE
                        }
                    }
                }
            })
    }

    private fun showGameDetail(game: Game?) {
        game?.let {
            supportActionBar?.title = game.name
            tvDetailDescription.text = game.description
            Glide.with(this@DetailActivity)
                .load(game.backgroundImage)
                .into(textDetailImage)

            var statusFavorite = game.isFavorite

            setStatusFavorite(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteGame(game, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

            mAdapterPlatform.setData(game.platform)
            mAdapterGenre.setData(game.genre)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            tvDetailDescription.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }
    }

    private fun prepareRecyclerView() {
        with(rvPlatform) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mAdapterPlatform
        }

        with(rvGenre) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mAdapterGenre
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite))
        }
    }


    override fun onPause() {
        super.onPause()
        mCompositeDisposable.clear()
        Log.d("DetailActivity", "onPause: mCompositeDisposable cleared")
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
        Log.d("DetailActivity", "onDestroy: mCompositeDisposable disposed")
    }
}