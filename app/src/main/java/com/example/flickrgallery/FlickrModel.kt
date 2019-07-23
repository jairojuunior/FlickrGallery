package com.example.flickrgallery

import java.io.File
import java.io.Serializable

class FlickrPhotoList: Serializable{
    val photos = ArrayList<FlickrPhoto>()

    fun add(photo: FlickrPhoto) = photos.add(photo)

    fun getPhotoAt(i: Int) = photos[i]

    fun addAll(photoList: FlickrPhotoList) = photos.addAll(photoList.photos)

    fun size() = photos.size

}

data class FlickrPhoto(
    var id: Long = 0L,
    var title: String = "",
    var thumbUrl: String = "",
    var thumbFile: File = File("")) : Serializable{

    fun hasThumbUrl() = thumbUrl.trim().isNotEmpty()
    fun hasThumbFile() = thumbFile.name.trim().isNotEmpty()
}