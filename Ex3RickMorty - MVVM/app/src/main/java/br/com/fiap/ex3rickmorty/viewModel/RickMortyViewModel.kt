package br.com.fiap.ex3rickmorty.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.ex3rickmorty.RetrofitFactory
import br.com.fiap.ex3rickmorty.model.IRickMortyApi
import br.com.fiap.ex3rickmorty.model.RickMortyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RickMortyViewModel(private val retroFitClient: Retrofit) : ViewModel() {

    private val _rmiewState = MutableLiveData<RickMortyViewState>()
    val rickMortyViewState: LiveData<RickMortyViewState> get() = _rmiewState

    //val liveCharatersList = MutableLiveData<RickMortyModel>()
    //val liveError = MutableLiveData<String>()

    fun getCharacters() {
        _rmiewState.value = RickMortyViewState.Loading
        val endPoint = getRickMortyEndPoint()
        val callBack = endPoint.getCharacters()
        callBack.enqueue(object : Callback<RickMortyModel> {
            override fun onResponse(
                call: Call<RickMortyModel>,
                response: Response<RickMortyModel>
            ) {
                _rmiewState.value = RickMortyViewState.Success(response.body()?.results)
            }

            override fun onFailure(call: Call<RickMortyModel>, t: Throwable) {
                _rmiewState.value = RickMortyViewState.Error(t.message)
            }

        })
    }

    private fun getRickMortyEndPoint(): IRickMortyApi {
        return retroFitClient.create(IRickMortyApi::class.java)
    }
}