package com.example.fooddeliveryapp.utils

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

object FirebaseCloudStorageManager {
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    var storageRef: StorageReference = storage.reference

    fun uploadImage(selectedImage: Uri): String {
        val ref = storageRef.child("images/" + UUID.randomUUID().toString())
        val uploadTask = ref.putFile(selectedImage)
        var downloadUri = ""

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            Log.e("downloadUrl", "task is successful = ${task.isSuccessful}")
             downloadUri = if (task.isSuccessful) {
                  task.result.toString()
            } else ""

        }
        Log.e("downloadUrl", "IMAGE URL => $downloadUri")
        return downloadUri

    }
}