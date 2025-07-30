package ru.easycode.zerotoheroandroidtdd

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentDeleteBinding

class DeleteBottomSheetFragment : BottomSheetDialogFragment(R.layout.fragment_delete) {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DeleteViewModel
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        viewModel = (activity as ProvideViewModel).viewModel(DeleteViewModel::class.java)

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.comeback()
                dismiss()
            }
        }
        (dialog as BottomSheetDialog).onBackPressedDispatcher.addCallback(onBackPressedCallback)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDeleteBinding.bind(view)

        val itemId = requireArguments().getLong(KEY)
        viewModel.init(itemId) // загружаем текст элемента по ID

        // обновим UI, когда текст будет получен из репозитория
        viewModel.liveData.observe(viewLifecycleOwner) { text ->
            binding.itemTitleTextView.text = text
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete(itemId)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
        _binding = null
    }

    companion object {
        fun newInstance(itemId: Long): DeleteBottomSheetFragment {
            val fragment = DeleteBottomSheetFragment()
            fragment.arguments = Bundle().apply {
                putLong(KEY, itemId)
            }
            return fragment
        }
        private const val KEY = "itemIdToDelete"
        const val TAG = "DeleteBottomSheet"
    }
}
