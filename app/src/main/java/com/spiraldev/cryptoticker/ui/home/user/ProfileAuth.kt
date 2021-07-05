package com.spiraldev.cryptoticker.ui.home.user

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils.firebaseUser
import com.spiraldev.cryptoticker.FireBase.FirebaseUtils.firedatabase
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.data.FireBase.Infomation
import com.spiraldev.cryptoticker.util.ImageLoader
import kotlinx.android.synthetic.main.activity_profile_auth.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class ProfileAuth : AppCompatActivity() {

    private lateinit var imgUrl: Uri

    private val PICK_IMAGE_REQUEST = 100

    override fun onStart() {
        super.onStart()
        UIProfile()

    }

    fun UIProfile(){
        nameProfileauth.text = FirebaseUtils.firebaseUser?.email
        ImageLoader.loadImage(imgProfileauth,
            FirebaseUtils.firebaseUser?.photoUrl?.toString() ?: "")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_auth)

        imgProfileauth.setOnClickListener {
            intentUploadPhotoAuth()
        }

        textVip.setOnClickListener {
            updateInfo()
        }
    }

    fun intentUploadPhotoAuth(){
//        Intent(MediaStore.ACTION_REVIEW).also { pictureIntent ->
//            pictureIntent.resolveActivity(packageManager!!)?.also {
//                startActivityForResult(pictureIntent, PICK_IMAGE_REQUEST)
//            }
//        }

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(
            intent,
            PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST){
//            val imgBitmap = data?.extras?.get("data") as Bitmap
//            uploadimgUrl(imgBitmap)

            if (data!= null) {
                val contentURI: Uri? = data.getData()
                try {
                    val FixBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,
                        contentURI)
                    uploadimgUrl(FixBitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun updateInfo(){
        try {
            val userId: String = firebaseUser?.getUid()!!
            val database: DatabaseReference = firedatabase.getReference().child("users").child(
                userId)
            database.child("vip").setValue("0")
            database.child("timer").setValue(0)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun uploadimgUrl(bitmap: Bitmap){
        val baos = ByteArrayOutputStream()
        val storageRef = FirebaseStorage.getInstance().reference.child("pics/${FirebaseUtils.firebaseUser?.uid}")

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val img = baos.toByteArray()

        val upload = storageRef.putBytes(img)

        upload.addOnCompleteListener{ uploadtask->
            if (uploadtask.isSuccessful){
                getUrlPhotoAuth(storageRef)
            }else{
                uploadtask.exception?.let {
                    Toast.makeText(this, it.message!!, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun getUrlPhotoAuth(storageRef: StorageReference){
        storageRef.downloadUrl.addOnCompleteListener { urlTask->
            urlTask.result?.let {
                imgUrl = it
                ImageLoader.loadImage(imgProfileauth, imgUrl.toString() ?: "")
                updateUriPhotoUrl(imgUrl)
            }
        }
    }

    fun updateUriPhotoUrl(uri: Uri){
        val request: UserProfileChangeRequest = UserProfileChangeRequest.Builder().setPhotoUri(uri).build()

        firebaseUser?.updateProfile(request)
            ?.addOnCompleteListener(OnCompleteListener<Void?> { task ->
                if (task.isSuccessful) {
                    UIProfile()
                }
            })
    }
}