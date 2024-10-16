package com.bangnv.talkai.ui.talk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangnv.talkai.R
import com.bangnv.talkai.data.adapter.TalkAdapter
import com.bangnv.talkai.data.repositories.ChatGPTRepository
import com.bangnv.talkai.data.repositories.GeminiRepository
import com.bangnv.talkai.data.repositories.factories.TalkViewModelFactory
import com.bangnv.talkai.databinding.FragmentTalkBinding
import com.bangnv.talkai.utils.GlobalFunction
import kotlinx.coroutines.launch

class TalkFragment : Fragment() {

    private var _binding: FragmentTalkBinding? = null
    private lateinit var talkViewModel: TalkViewModel
    private lateinit var adapter: TalkAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val chatGPTRepository = ChatGPTRepository()
        val geminiRepository = GeminiRepository()
        val viewModelFactory = TalkViewModelFactory(chatGPTRepository, geminiRepository)
        talkViewModel = ViewModelProvider(this, viewModelFactory).get(TalkViewModel::class.java)

        _binding = FragmentTalkBinding.inflate(inflater, container, false)

        // Lấy giá trị aiType từ RadioGroup
        val aiType = if (binding.rgAiSelection.checkedRadioButtonId == R.id.rb_gemini) 1 else 2
        adapter = TalkAdapter(aiType)

        setupRecyclerView()
        observeMessages()
        setupListeners()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun observeMessages() {
        talkViewModel.messages.observe(viewLifecycleOwner) { messages ->
            adapter.submitList(messages)
            binding.recyclerView.scrollToPosition(messages.size - 1)
        }
    }

    private fun setupListeners() {
        binding.btnSend.setOnClickListener {
            GlobalFunction.hideSoftKeyboard(requireActivity())
            val message = binding.edtMessage.text.toString()
            val useGemini = binding.rgAiSelection.checkedRadioButtonId == R.id.rb_gemini

            if (message.isNotEmpty()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    talkViewModel.sendMessage(message, useGemini)
                }
                binding.edtMessage.text?.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

