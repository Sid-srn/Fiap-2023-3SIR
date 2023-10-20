package br.com.fiap.ex3rickmorty.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.ex3rickmorty.model.Contants.ITEM_RM
import br.com.fiap.ex3rickmorty.databinding.ActivityRickMortyDetailBinding
import br.com.fiap.ex3rickmorty.model.ResultModel
import com.squareup.picasso.Picasso

class RickMortyDetailActivity : AppCompatActivity() {

    lateinit var databind: ActivityRickMortyDetailBinding
    var itemDetail : ResultModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databind = ActivityRickMortyDetailBinding.inflate(layoutInflater)
        setContentView(databind.root)

        itemDetail = intent.getParcelableExtra(ITEM_RM)

        if (itemDetail!= null){
            Picasso.with(this).load(itemDetail?.image).into(databind.imgDetail)
            databind.txtName.text = itemDetail?.name
            databind.txtStatus.text = itemDetail?.status
            databind.txtSpecie.text = itemDetail?.species
            databind.txtGender.text = itemDetail?.gender
            databind.txtCreated.text = itemDetail?.created
        }

    }
}