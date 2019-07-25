package com.mobileapps.isschallengekotlin.ui

import android.util.Log
import com.mobileapps.isschallengekotlin.models.IssResponse
import com.mobileapps.isschallengekotlin.network.NetworkClient
import com.mobileapps.isschallengekotlin.network.NetworkInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter (internal var mvi: MainViewInterface, val lat : String, val long : String) : MainPresenterInterface
{

    val TAG = "MainPresenter"

    //Observable
    val observable: Observable<IssResponse> get() =
        NetworkClient.getRetrofit()?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.getIssResponse(lat,long)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())!!

    //Observer
    val observer: DisposableObserver<IssResponse>
        get() = object : DisposableObserver<IssResponse>() {
            override fun onNext(issResponse: IssResponse) {
                mvi.displayResponse(issResponse)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "Error$e")
                e.printStackTrace()
                mvi.displayError("Error fetching Movie Data")
            }

            override fun onComplete() {
                Log.d(TAG, "Completed")
            }
        }




    override fun getResponse()
    {
        observable.subscribeWith(observer)
    }
}