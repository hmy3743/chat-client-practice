package com.myhan.chat_client_practice.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.myhan.chat_client_practice.FragmentFriendViewModel
import com.myhan.chat_client_practice.R
import com.myhan.chat_client_practice.databinding.FragmentFriendBinding
import kotlinx.android.synthetic.main.fragment_friend.*
import org.koin.android.viewmodel.ext.android.viewModel


class FragmentFriend : Fragment() {

    companion object {
        fun newInstance() = FragmentFriend()
    }

    private val viewModel: FragmentFriendViewModel by viewModel()
    private lateinit var binding: FragmentFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_friend, container, false)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.fragment = this

        return binding.root
    }

    fun onCardSend() {
        val animSet = AnimatorSet()

        val disappearTranslation = ObjectAnimator
            .ofFloat(constraintLayoutCard, View.TRANSLATION_Y, 0f, -10f)
            .setDuration(500)

        val disappearAlpha = ObjectAnimator
            .ofFloat(constraintLayoutCard, View.ALPHA, 1f, 0f)
            .setDuration(500)

        val disappear = AnimatorSet().apply{
            playTogether(disappearTranslation, disappearAlpha)
            addListener(object: Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    editTextCardContent.text.clear()
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationStart(animation: Animator?) {}
            })
        }

        val appearTranslation = ObjectAnimator
            .ofFloat(constraintLayoutCard, View.TRANSLATION_Y, 0f, 0f)
            .setDuration(0)

        val appearAlpha = ObjectAnimator
            .ofFloat(constraintLayoutCard, View.ALPHA, 0f, 1f)
            .setDuration(500)

        val appear = AnimatorSet().apply{ playTogether(appearTranslation, appearAlpha) }

        animSet.playSequentially(disappear, appear)
        animSet.start()
    }

}
