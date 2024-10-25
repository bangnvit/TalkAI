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

    private var messages: List<ChatMessage> = emptyList() // Initialize with an empty list

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
        val inflater = LayoutInflater.from(parent.context) // Move LayoutInflater to a variable
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val binding = ItemTalkRightBinding.inflate(inflater, parent, false)
                UserViewHolder(binding)
            }

            VIEW_TYPE_AI -> {
                val binding = ItemTalkLeftBinding.inflate(inflater, parent, false)
                AIViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type") // Ensure all cases are handled
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

    // ViewHolder for user messages
    inner class UserViewHolder(private val binding: ItemTalkRightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            val markwon = Markwon.create(binding.root.context) // Render markdown content
            markwon.setMarkdown(binding.messageTextView, message.content)

            // Add long click event to copy text to clipboard
            binding.messageTextView.setOnLongClickListener {
                GlobalFunction.copyTextToClipboard(binding.root.context, message.content)
                true // Return true to consume the event
            }
        }
    }

    // ViewHolder for AI messages
    inner class AIViewHolder(private val binding: ItemTalkLeftBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            val markwon = Markwon.create(binding.root.context) // Render markdown content
            markwon.setMarkdown(binding.aiMessageTextView, message.content)

            // Add long click event to copy text to clipboard
            binding.aiMessageTextView.setOnLongClickListener {
                GlobalFunction.copyTextToClipboard(binding.root.context, message.content)
                true // Return true to consume the event
            }

            // Set icon based on `aiType`
            val iconResId = when (aiType) {
                1 -> R.drawable.ic_gemini
                else -> R.drawable.ic_chatgpt
            }
            binding.aiIcon.setImageResource(iconResId)
        }
    }
}
