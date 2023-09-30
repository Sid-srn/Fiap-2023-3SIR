package com.example.apideckgame.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apideckgame.model.IDeckGame
import com.example.apideckgame.NetworkUtils
import com.example.apideckgame.R
import com.example.apideckgame.databinding.ActivityMainBinding
import com.example.apideckgame.model.CardModel
import com.example.apideckgame.model.DeckModel
import com.example.apideckgame.viewModel.DeckViewModel
import com.example.apideckgame.viewModel.DeckViewModelFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding
    var deck: DeckModel? = null

    val deckFactory = DeckViewModelFactory()
    lateinit var viewModel: DeckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, deckFactory)[DeckViewModel::class.java]

        setupObservers()

        viewModel.getDeck()
        bind.progressBar.visibility = View.VISIBLE
        //getDeck()

        bind.btnGetCard.setOnClickListener {
            //getCard()
            viewModel.getCard(1)
            bind.progressBar.visibility = View.VISIBLE
        }
    }

    fun setupObservers() {
        viewModel.liveDeck.observe(
            this,
            Observer(::setDeckId)
        )
        viewModel.liveCard.observe(
            this,
            Observer(::setCard)
        )
        viewModel.liveError.observe(
            this,
            Observer(::showErrorMessage)
        )
    }

    private fun setDeckId(deckModel: DeckModel?) {
        bind.txtDeckName.text = deckModel?.deck_id
        bind.progressBar.visibility = View.GONE
    }

    private fun setCard(cardModel: CardModel) {
        bind.txtCardName.text = cardModel.cards.get(0).code
        cardModel?.cards?.get(0)?.image?.let { it1 -> loadCardImage(it1) }
        bind.progressBar.visibility = View.GONE
    }

    private fun showErrorMessage(errorMessage: String) {
        var erroDialog = AlertDialog.Builder(this)
        erroDialog.setTitle("Erro")
        erroDialog.setMessage(errorMessage)
        erroDialog.setPositiveButton("ok"){dialog, _ ->
            dialog.cancel()
        }
        erroDialog.show()
        bind.progressBar.visibility = View.GONE
    }

    fun getDeck() {
        val endPoint = getEndPoint()
        val callBack = endPoint.getDeck(1)
        callBack.enqueue(object : Callback<DeckModel> {
            override fun onResponse(call: Call<DeckModel>, resp: Response<DeckModel>) {
                deck = resp.body()
                bind.txtDeckName.text = deck?.deck_id
            }

            override fun onFailure(call: Call<DeckModel>, t: Throwable) {
                bind.txtDeckName.text = t.message
            }

        })
    }

    fun getCard() {
        val endPoint = getEndPoint()
        deck?.let {
            val callBack = endPoint.getDeckCards(it.deck_id, 1)
            callBack.enqueue(object : Callback<CardModel> {
                override fun onResponse(call: Call<CardModel>, response: Response<CardModel>) {
                    val card = response.body()
                    bind.txtCardName.text = card?.cards?.get(0)?.code
                    card?.cards?.get(0)?.image?.let { it1 -> loadCardImage(it1) }
                }

                override fun onFailure(call: Call<CardModel>, t: Throwable) {

                    TODO("Not yet implemented")
                }

            })
        }

    }

    fun loadCardImage(imgUrl: String) {
        Picasso
            .with(this)
            .load(imgUrl)
            .into(bind.imgCard)
    }

    fun getEndPoint(): IDeckGame {
        val retrofitClient =
            NetworkUtils.getRetrofitInstance("https://deckofcardsapi.com/api/deck/")
        return retrofitClient.create(IDeckGame::class.java)
    }

}