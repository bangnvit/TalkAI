package com.bangnv.talkai.ui.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bangnv.talkai.databinding.FragmentVocabularyBinding

class VocabularyFragment : Fragment() {

    private var _binding: FragmentVocabularyBinding? = null
    private val binding get() = _binding
    // Using viewModels() delegate to obtain ViewModel scoped to the fragment
    private val vocabularyViewModel: VocabularyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVocabularyBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.tvVocabulary
//        vocabularyViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
        binding?.let { // Use `let` to safely access `binding`
            vocabularyViewModel.text.observe(viewLifecycleOwner) { text ->
                it.tvVocabulary.text = text // Safely update TextView
            }
        }

        return _binding?.root ?: throw IllegalStateException("Binding should not be null")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Properly nullify to avoid memory leaks
    }
}