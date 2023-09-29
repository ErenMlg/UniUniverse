package com.softcross.uniuniverse.ui.register

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.softcross.uniuniverse.R
import com.softcross.uniuniverse.common.util.navigate
import com.softcross.uniuniverse.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding
    private var pickedPhoto: Uri? = null
    private var pickedBitMap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivBack.setOnClickListener {
                Navigation.navigate(it, R.id.NavRegisterToLogin)
            }
            edProfilePictureRegister.setOnClickListener {
                selectPhoto()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPhoto = data.data
            if (pickedPhoto != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source =
                        ImageDecoder.createSource(requireContext().contentResolver, pickedPhoto!!)
                    pickedBitMap = resizePhoto(ImageDecoder.decodeBitmap(source))
                    binding.ivProfile.scaleType = ImageView.ScaleType.CENTER_CROP
                    binding.ivProfile.setImageBitmap(pickedBitMap!!)
                } else {
                    pickedBitMap = resizePhoto(
                        MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            pickedPhoto
                        )
                    )
                    binding.ivProfile.scaleType = ImageView.ScaleType.CENTER_CROP
                    binding.ivProfile.setImageBitmap(pickedBitMap!!)
                }
            }
        }
    }

    private fun resizePhoto(bitmap: Bitmap): Bitmap {
        var width = bitmap.width
        var height = bitmap.height

        val bitmapRatio: Float = width.toFloat() / height.toFloat()

        if (bitmapRatio > 1) {
            width = 600
            height = (width / bitmapRatio).toInt()
        } else {
            height = 600
            width = (height * bitmapRatio).toInt()
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 2)
    }

}