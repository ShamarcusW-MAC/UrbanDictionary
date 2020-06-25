package com.example.urbandictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.urbandictionary.model.Definition
import com.example.urbandictionary.network.UrbanFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UrbanViewModel: ViewModel() {

    val urbanFactory: UrbanFactory = UrbanFactory();
    val compositeDisposable : CompositeDisposable = CompositeDisposable()
    var definitionData : MutableLiveData<List<Definition>> = MutableLiveData()

    fun makeCall(string: String) {
        compositeDisposable.add(getDefinitions("" + string).subscribe { definition ->
            definitionData.value = definition
            definitionData.postValue(definition)
        })
    }

    fun getDefinitions(searchWord: String): Observable<List<Definition>> {
        return urbanFactory.getDefinitions(searchWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}