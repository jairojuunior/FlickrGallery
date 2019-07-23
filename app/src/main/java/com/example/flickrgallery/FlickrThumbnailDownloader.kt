//package br.edu.ufabc.padm.flickrgallery2
//
//import android.content.Intent
//import android.os.AsyncTask
//import android.util.Log
//import com.example.flickrgallery.FlickrPhoto
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//import java.net.HttpURLConnection
//import java.net.MalformedURLException
//import java.net.URL
//
///**
// * AsyncTask to download the thumbnail of a single image
// */
//object FlickrThumbnailDownloader {
//    private val LOGTAG = this::class.java.getSimpleName()
//
//    /**
//     * Download a thumbnail
//     * @param params the thumbnail url
//     * @return the file descriptor of the cached file
//     */
//    fun download(flickrPhoto: FlickrPhoto): FlickrPhoto {
//        val url: URL
//        val conn: HttpURLConnection
//        val responseCode: Int
//
//        try {
//            url = URL(flickrPhoto.thumbUrl)
//            conn = url.openConnection() as HttpURLConnection
//            conn.connect()
//            responseCode = conn.responseCode
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                Log.d(LOGTAG, "Downloading thumbnail id: " + flickrPhoto.id)
//                // TODO: send image to cache directory
//                // TODO: send broadcast with path of cached image
//                File(App.appContext.cacheDir, flickrPhoto.id.toString()).apply {
//                    conn.inputStream.copyTo(FileOutputStream(this))
//                    flickrPhoto.thumbFile = this
//                    Intent(FlickrServiceContract.THUMBNAIL_READY).apply {
//                        putExtra(FlickrServiceContract.THUMBNAIL_READY_EXTRA, flickrPhoto)
//                        App.sendBroadcast(this)
//                    }
//                }
//            } else {
//                sendStatus(R.string.thumbnail_download_error)
//                Log.e(LOGTAG, "Failed to download thumbnail. HTTP code: $responseCode")
//            }
//        } catch (e: MalformedURLException) {
//            sendStatus(R.string.thumbnail_download_error)
//            Log.e(LOGTAG, "Malformed thumbnail URL", e)
//        } catch (e: IOException) {
//            sendStatus(R.string.thumbnail_download_error)
//            Log.e(LOGTAG, "Failed to open thumbnail URL", e)
//        }
//
//        return flickrPhoto
//    }
//
//    private fun sendStatus(messageId : Int) {
//        Intent(FlickrServiceContract.STATUS_BROADCAST).apply {
//            putExtra(FlickrServiceContract.STATUS_BROADCAST_EXTRA,
//                    App.appContext.getString(messageId))
//            App.sendBroadcast(this)
//        }
//    }
//
//}