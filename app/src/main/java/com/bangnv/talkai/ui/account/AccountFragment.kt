package com.bangnv.talkai.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangnv.talkai.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView() and onDestroyView().
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Dữ liệu trong ViewModel sẽ bị mất khi Fragment bị hủy
        val accountViewModel =
            ViewModelProvider(this)[AccountViewModel::class.java]

        // Để chia sẻ ViewModel giữa các Fragment trong cùng một Activity
        // và tránh mất dữ liệu khi điều hướng giữa các Fragment trong BottomNavigation.
//        val accountViewModel =
//            ViewModelProvider(requireActivity())[AccountViewModel::class.java]

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvAccount
        accountViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}