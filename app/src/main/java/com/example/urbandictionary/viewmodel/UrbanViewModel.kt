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
    var definitionData : MutableLiveData<Definition> = MutableLiveData()
    var response: Boolean = false

    fun makeCall(string: String) {
        compositeDisposable.add(getDefinitions("" + string).subscribe { definition ->

            if(definition.isNotEmpty()) {
                    definitionData.value = definition[0]
                    response = true
                    definitionData.postValue(definition.get(0))
            } else {
                response = false
            }
        })
    }

    fun getDefinitions(searchWord: String): Observable<List<Definition>> {
        return urbanFactory.getDefinitions(searchWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

}