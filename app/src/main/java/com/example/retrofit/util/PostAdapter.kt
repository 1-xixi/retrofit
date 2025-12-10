package com.example.retrofit.util

// 1. 补充缺失的导入（核心修复）
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.data.Post

// 2. 修正类声明语法：把 { 改为 ()（关键！）
class PostAdapter(private val posts: List<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    // 创建 ViewHolder：加载 item 布局
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    // 绑定数据到 ViewHolder
    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        val post = posts[position]
        holder.id.text = post.id.toString()
        holder.userId.text = post.userId.toString()
        holder.title.text = post.title
        holder.body.text = post.body
    }

    // 返回数据总数
    override fun getItemCount(): Int = posts.size // 简化写法

    // ViewHolder：缓存 item 中的控件
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 3. 可选优化：用 !! 或 ?. 处理空安全（避免 findViewById 为空崩溃）
        val id: TextView = itemView.findViewById(R.id.tvId)!!
        val userId: TextView = itemView.findViewById(R.id.tvUserId)
        val title: TextView = itemView.findViewById(R.id.tvTitle)!!
        val body: TextView = itemView.findViewById(R.id.tvBody)!!
    }
}