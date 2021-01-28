package com.example.testproject.core.shared_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.testproject.R
import com.example.testproject.databinding.ReviewAppBinding
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory

class ReviewAppWidget() : DialogFragment() {
    private lateinit var binding: ReviewAppBinding
    var value = 0
    lateinit var manager: ReviewManager
    var reviewInfo: ReviewInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.review_app, container, false)
        initReviews()
        binding.sendReview.setOnClickListener {
            askForReview()
            dismiss()
        }
        return binding.root
    }


        private fun initReviews() {
            manager = ReviewManagerFactory.create(requireContext())
            manager.requestReviewFlow().addOnCompleteListener { request ->
                if (request.isSuccessful) {
                    reviewInfo = request.result
                } else {
                    // Log error
                }
            }
        }

    private fun askForReview() {
        if (reviewInfo != null) {
            manager.launchReviewFlow(requireActivity(), reviewInfo!!).addOnFailureListener {
                // Log error and continue with the flow
            }.addOnCompleteListener { _ ->
                // Log success and continue with the flow
            }
        }
    }
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

}