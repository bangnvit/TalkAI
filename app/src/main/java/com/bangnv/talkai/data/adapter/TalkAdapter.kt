package com.bangnv.talkai.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangnv.talkai.R
import com.bangnv.talkai.databinding.ItemTalkLeftBinding
import com.bangnv.talkai.databinding.ItemTalkRightBinding
import com.bangnv.talkai.models.offline.ChatMessage
import com.bangnv.talkai.utils.GlobalFunction
import io.noties.markwon.Markwon

class TalkAdapter(private val aiType: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages = listOf<ChatMessage>()

    companion object {
        private const val VIEW_TYPE_USER = 0
        private const val VIEW_TYPE_AI = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newMessages: List<ChatMessage>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val binding =
                    ItemTalkRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserViewHolder(binding)
            }

            else -> {
                val binding =
                    ItemTalkLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AIViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is UserViewHolder -> holder.bind(message)
            is AIViewHolder -> holder.bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isUser) VIEW_TYPE_USER else VIEW_TYPE_AI
    }

    inner class UserViewHolder(private val binding: ItemTalkRightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            // Sử dụng Markwon để render Markdown
            val markwon = Markwon.create(binding.root.context)
            markwon.setMarkdown(binding.messageTextView, message.content)

            // Thêm sự kiện click để sao chép văn bản
            binding.messageTextView.setOnLongClickListener {
                GlobalFunction.copyTextToClipboard(
                    binding.root.context,
                    message.content
                ) // Sao chép văn bản vào clipboard
                true // Trả về true để ngừng sự kiện click
            }
        }
    }

    inner class AIViewHolder(private val binding: ItemTalkLeftBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            // Sử dụng Markwon để render Markdown
            val markwon = Markwon.create(binding.root.context)
            markwon.setMarkdown(binding.aiMessageTextView, message.content)

            // Thêm sự kiện click để sao chép văn bản
            binding.aiMessageTextView.setOnLongClickListener {
                GlobalFunction.copyTextToClipboard(
                    binding.root.context,
                    message.content
                ) // Sao chép văn bản vào clipboard
                true // Trả về true để ngừng sự kiện click
            }

            // Thay đổi icon dựa trên `aiType`
            val icon = if (aiType == 1) R.drawable.ic_gemini else R.drawable.ic_chatgpt
            binding.aiIcon.setImageResource(icon)
        }
    }
}
