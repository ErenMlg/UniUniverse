package com.softcross.uniuniverse.presentation.register

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.softcross.uniuniverse.R
import com.softcross.uniuniverse.common.util.createCustomToast
import com.softcross.uniuniverse.common.util.gone
import com.softcross.uniuniverse.common.util.launchAndCollectIn
import com.softcross.uniuniverse.common.util.navigate
import com.softcross.uniuniverse.common.util.visible
import com.softcross.uniuniverse.databinding.FragmentRegisterBinding
import com.softcross.uniuniverse.presentation.register.RegisterViewModel.RegisterState
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
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.launchAndCollectIn(viewLifecycleOwner) { state ->
            when (state) {
                is RegisterState.Success -> {
                    //binding.lottieAnimation.gone()
                    requireContext().createCustomToast(getString(R.string.strUserAddSuccess))
                    Navigation.navigate(binding.ivBack, R.id.NavRegisterToLogin)
                }

                is RegisterState.Failed -> {
                    requireContext().createCustomToast(state.errorMessage)
                }

                is RegisterState.Controlling -> {
                    //binding.lottieAnimation.visible()
                }
            }
        }
        binding.apply {
            ivBack.setOnClickListener {
                Navigation.navigate(it, R.id.NavRegisterToLogin)
            }
            edProfilePictureRegister.setOnClickListener {
                selectPhoto()
            }
            btnRegisterRegister.setOnClickListener {
                if (pickedBitMap != null) {
                    viewModel.checkInputs(
                        edFirstNameRegister.text.trim().toString(),
                        edLastNameRegister.text.trim().toString(),
                        pickedBitMap!!
                    )
                } else {
                    MaterialAlertDialogBuilder(requireContext())
                        .apply {
                            setMessage(getString(R.string.strContinueWithoutPic))
                            setTitle(getString(R.string.strNotAddedPic))
                            setIcon(R.drawable.icon_question)
                            setCancelable(false)
                            setPositiveButton(getString(R.string.strYes)) { _, _ ->
                                viewModel.checkInputs(
                                    edFirstNameRegister.text.trim().toString(),
                                    edLastNameRegister.text.trim().toString(),
                                    getBitmapFromVectorDrawable(
                                        requireContext(),
                                        R.drawable.icon_profile
                                    )
                                )
                            }
                            setNegativeButton(getString(R.string.strNo)) { dialogInterface, i ->
                                dialogInterface.dismiss()
                                requireContext().createCustomToast(getString(R.string.strPleaseAddPic))
                            }
                            setBackground(
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.background_introduction_buttons,
                                    null
                                )
                            )
                            create().show()
                        }
                }
            }
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPhoto = data.data
            if (pickedPhoto != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source =
                        ImageDecoder.createSource(
                            requireContext().contentResolver,
                            pickedPhoto!!
                        )
                    pickedBitMap = resizePhoto(ImageDecoder.decodeBitmap(source))
                    binding.ivProfile.scaleType = ImageView.ScaleType.CENTER_CROP
                    binding.ivProfile.setColorFilter(requireContext().getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color))
                    binding.ivProfile.setImageBitmap(pickedBitMap!!)
                } else {
                    pickedBitMap = resizePhoto(
                        MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            pickedPhoto
                        )
                    )
                    binding.ivProfile.scaleType = ImageView.ScaleType.CENTER_CROP
                    binding.ivProfile.setColorFilter(requireContext().getColor(com.google.android.material.R.color.mtrl_btn_transparent_bg_color))
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

    fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}