package com.example.flickrgallery

import org.json.JSONObject

class FlickrJSONSerializer (jsonStr: String) {
    private object FlickrJSONContract{
        const val PHOTO_OBJECT = "photos"
        const val PHOTO_ARRAY = "photo"
        const val PHOTO_ID = "id"
        const val PHOTO_THUMB_URL = "url_s"
        const val PHOTO_TITLE = "title"
        const val STATUS = "stat"
        const val STATUS_OK = "ok"
    }

    private var root : JSONObject

    init{
        root = JSONObject(jsonStr)
    }

    fun deserializeRecentPhotos() : FlickrPhotoList{
        val photos = FlickrPhotoList()

        var photosArray = root.getJSONObject(FlickrJSONContract.PHOTO_OBJECT)
            .getJSONArray(FlickrJSONContract.PHOTO_ARRAY)

        for(i in 0 until photosArray.length()){
            val photoObj = photosArray.get(i) as JSONObject
            val photo = FlickrPhoto()

            photo.id = photoObj.getString(FlickrJSONContract.PHOTO_ID).toLong()
            photo.title = photoObj.getString(FlickrJSONContract.PHOTO_TITLE)
            if (photoObj.has(FlickrJSONContract.PHOTO_THUMB_URL))
                photo.thumbUrl = photoObj.getString(FlickrJSONContract.PHOTO_THUMB_URL)

            photos.add(photo)
        }

        return photos
    }

    fun isStatusOk() =root.getString(FlickrJSONContract.STATUS) == FlickrJSONContract.STATUS_OK

}