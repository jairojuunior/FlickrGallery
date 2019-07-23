package com.example.flickrgallery

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.edu.ufabc.padm.flickrgallery2.FlickrFetcher


object FlickrServiceContract {
    // commands

    const val RECENTS_DOWNLOAD_ACTION = "photoList"

    // broadcasts
    const val RECENTS_LIST_READY = "recentsListReady"
    const val RECENTS_LIST_READY_EXTRA = "recentsListReadyExtra"
}

class FlickrService : IntentService("FlickrService") {


    override fun onHandleIntent(intent: Intent?) {
        when(intent?.action){
            FlickrServiceContract.RECENTS_DOWNLOAD_ACTION -> downloadRecents()
        }

    }

    private fun downloadRecents(){

        Intent(FlickrServiceContract.RECENTS_LIST_READY).apply {
            putExtra(FlickrServiceContract.RECENTS_LIST_READY_EXTRA, FlickrFetcher.fetchRecentPhotos())
            App.sendBroadcast(this)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
