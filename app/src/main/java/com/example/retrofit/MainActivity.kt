package com.example.retrofit
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope // 1. 核心修复：添加lifecycleScope导入
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.api.RetrofitClient
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.util.PostAdapter
import com.example.retrofit.data.Post // 2. 补充Post数据类导入（避免后续隐式报错）
import kotlinx.coroutines.launch
import retrofit2.Response // 3. 显式指定泛型，避免类型推断问题

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchPosts()
    }

    private fun fetchPosts() {
        // 此时launch因导入正确，IDE能识别协程作用域
        lifecycleScope.launch {
            try {
                // 4. 显式指定Response泛型，让IDE明确类型（可选但推荐）
                val response: Response<List<Post>> = RetrofitClient.apiService.getPosts()
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    binding.recyclerView.adapter = PostAdapter(posts)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "请求失败: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "网络异常: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}