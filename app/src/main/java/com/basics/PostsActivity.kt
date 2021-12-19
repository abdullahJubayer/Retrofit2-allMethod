package com.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.noob.coroutineretrofit.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsActivity : AppCompatActivity() {

    private lateinit var textView:TextView
    private lateinit var apiService:Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        textView=findViewById(R.id.text_view)
        apiService=RetrofitBuilder.apiService

//        getSpecificOrderPost()
//        getSpecificOrderWithArrayParameterPost()
//        getPostWithMap()
//        getPostWithURL()
        getCommentsWithFullURL()
    }

    private fun getPost(){
        apiService.getPosts().enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (post in response.body()!!)
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }
    private fun getSpecificPost(){
        apiService.getPosts(2).enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (post in response.body()!!)
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }
    private fun getSpecificOrderPost(){
        apiService.getPosts(2,"id","desc").enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (post in response.body()!!)
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }
    private fun getSpecificOrderWithArrayParameterPost(){
        apiService.getPosts(arrayOf(1,2,3),"id","desc").enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (post in response.body()!!)
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }

    private fun getPostWithMap(){
        val map=HashMap<String,String>()
        map["userId"] = "1"
        map["_sort"] = "id"
        map["_order"] = "desc"
        apiService.getPosts(map).enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (post in response.body()!!)
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }

    private fun getPostWithURL(){
        apiService.getPosts("posts").enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (post in response.body()!!)
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }

    private fun getComments(){
        apiService.getComments(2).enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (comment in response.body()!!)
                        textView.append("userId : ${comment.postId} \n" +
                                "id : ${comment.id} \n"  +
                                "title : ${comment.name} \n"  +
                                "body : ${comment.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }
    private fun getCommentsWithFullURL(){
        apiService.getComments("https://jsonplaceholder.typicode.com/comments?postId=1").enqueue(object : Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful && response.body()  != null){
                    for (comment in response.body()!!)
                        textView.append("userId : ${comment.postId} \n" +
                                "id : ${comment.id} \n"  +
                                "title : ${comment.name} \n"  +
                                "body : ${comment.body} \n\n")
                }
            }
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }
}