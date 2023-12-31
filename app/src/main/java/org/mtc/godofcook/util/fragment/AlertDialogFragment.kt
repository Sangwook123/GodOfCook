package org.mtc.godofcook.util.fragment

import android.os.Bundle
import android.view.View
import org.mtc.godofcook.R
import org.mtc.godofcook.databinding.FragmentAlertDialogBinding
import org.mtc.godofcook.util.binding.BindingDialogFragment

class AlertDialogFragment : BindingDialogFragment<FragmentAlertDialogBinding>(R.layout.fragment_alert_dialog) {
    private var handleNegativeButton: (() -> Unit)? = null
    private var handlePositiveButton: (() -> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.setCanceledOnTouchOutside(false)

        val title = arguments?.getString(ARG_TITLE)
        val negativeButtonLabel = arguments?.getString(ARG_NEGATIVE_BUTTON_LABEL)
        val positiveButtonLabel = arguments?.getString(ARG_POSITIVE_BUTTON_LABEL)

        if (title != null && negativeButtonLabel != null && positiveButtonLabel != null) {
            initDialogText(title, positiveButtonLabel, negativeButtonLabel)
        }

        initNegativeButtonClickListener(handleNegativeButton)
        initPositiveButtonClickListener(handlePositiveButton)
    }

    private fun initDialogText(
        title: String,
        positiveButtonLabel: String,
        negativeButtonLabel: String
    ) {
        binding.tvAlertFragmentTitle.text = title
        binding.tvAlertFragmentPositive.text = positiveButtonLabel
        binding.tvAlertFragmentNegative.text = negativeButtonLabel
    }

    private fun initNegativeButtonClickListener(handleNegativeButton: (() -> Unit)?) {
        binding.tvAlertFragmentNegative.setOnClickListener {
            handleNegativeButton?.invoke()
            dismiss()
        }
    }

    private fun initPositiveButtonClickListener(handlePositiveButton: (() -> Unit)?) {
        binding.tvAlertFragmentPositive.setOnClickListener {
            handlePositiveButton?.invoke()
            dismiss()
        }
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_NEGATIVE_BUTTON_LABEL = "negativeButtonLabel"
        private const val ARG_POSITIVE_BUTTON_LABEL = "positiveButtonLabel"

        fun newInstance(
            title: String,
            negativeButtonLabel: String,
            positiveButtonLabel: String,
            handleNegativeButton: () -> Unit,
            handlePositiveButton: () -> Unit
        ): AlertDialogFragment {
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_NEGATIVE_BUTTON_LABEL, negativeButtonLabel)
            args.putString(ARG_POSITIVE_BUTTON_LABEL, positiveButtonLabel)

            val fragment = AlertDialogFragment()
            fragment.arguments = args

            fragment.handleNegativeButton = handleNegativeButton
            fragment.handlePositiveButton = handlePositiveButton

            return fragment
        }
    }
}