package com.motasem.ziad.volley

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley
import com.facebook.drawee.backends.pipeline.Fresco
import com.motasem.ziad.volley.model.LruBitmapCache

class MySingleton : Application() {
    val TAG = "mzn"
    private var mRequestQueue: RequestQueue? = null
    private var mImageLoader: ImageLoader? = null

    companion object {
        private var mInstance: MySingleton? = null

        fun getInstance(): MySingleton? {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        mInstance = this
    }

    fun getRequestQueue(): RequestQueue? {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return mRequestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String?) {
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        getRequestQueue()!!.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        getRequestQueue()!!.add(req)
    }

    fun cancelPendingRequest(tag: Any?) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    fun getImageLoader(): ImageLoader? {
        getRequestQueue()
        if (mImageLoader == null) {
            mImageLoader = ImageLoader(mRequestQueue, LruBitmapCache())
        }
        return mImageLoader
    }
}