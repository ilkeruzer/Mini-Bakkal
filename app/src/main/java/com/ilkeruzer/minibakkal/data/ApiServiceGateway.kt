package com.ilkeruzer.minibakkal.data

import android.annotation.SuppressLint
import android.util.Log
import com.ilkeruzer.minibakkal.data.local.IResult
import com.ilkeruzer.minibakkal.data.service.IResource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class ApiServiceGateway<T> {
    private val TAG = "ApiServiceGateway"

    private val SUCCESS = 200
    private val FAILED = 500

    private var resourceListener: IResource<T>? = null
    private var resultListener: IResult? = null

    constructor(observable: Observable<Response<T>>) {
        setObservable(observable)
    }

    constructor(observable: Observable<T>,type: String) {
        if (type == "ROOM") {
            setRoomObservable(observable)
        }
    }

    constructor(completable: Completable) {
        setRoomCompletable(completable)
    }

    @SuppressLint("CheckResult")
    private fun setRoomCompletable(completable: Completable) {
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { resultListener?.onSuccess() },
                {
                    handleError(it)
                    resultListener?.onError()
                }
            )
    }


    private fun setRoomObservable(observable: Observable<T>) {
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object : Observer<T> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: T) {
                    resultListener?.onSuccess()
                }

                override fun onError(e: Throwable) {
                    handleError(e)
                    resultListener?.onError()
                }
            }
            )
    }


    private fun setObservable(observable: Observable<Response<T>>) {
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Response<T>?> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: Response<T>) {
                    setGateway(t)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: $e")
                    handleError(e)
                }

                override fun onComplete() {}

            })
    }

    private fun handleError(throwable: Throwable) {
        if (throwable is HttpException) {
            val statusCode = throwable.code()
            // handle different HTTP error codes here (4xx)
            Log.e(TAG, "handleError: $statusCode")
            Log.e(TAG, "handleError: ${throwable.message()}")
        } else if (throwable is SocketTimeoutException) {
            // handle timeout from Retrofit
        } else if (throwable is IOException) {
            // file was not found, do something
        } else {
        }
    }

    private fun setGateway(response: Response<T>) {
        if (response.errorBody() != null) {
            try {
                Log.e(TAG, "errorBody: " + response.errorBody())
                Log.e(TAG, "errorBody: " + response.message())
            } catch (e: Exception) {
                Log.e(TAG, "e: " + e.message)
            }
        }
        when (response.code()) {
            SUCCESS -> response.body()?.let { resourceListener?.onSuccess(it) }
            FAILED -> failed()
        }
    }


    private fun failed() {
        resourceListener?.onFailed()
    }

    fun apiResponse(listener: IResource<T>?) {
        this.resourceListener = listener
    }

    fun localeResponse(listener: IResult) {
        this.resultListener = listener
    }
}
