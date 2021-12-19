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
//        getCommentsWithFullURL()
//        createPostWithBodyAnnotation()
//        createPostWithFieldAnnotation()
//        createPostWithFieldMapAnnotation()
//        updatePost()
//        updatePostField()
        deletePost()
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


    private fun createPostWithBodyAnnotation(){
        val post=Post(userId = 1000,id=null,title = "MyPost",body = "I Love You")
        apiService.createPost(post).enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful && response.body()  != null){
                    val post=response.body()!!
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }

    //TODO NOT WORK
//    private fun createPostWithBodyAnnotation(){
//        val post1=Post(userId = 1000,id=null,title = "MyPost",body = "I Love You")
//        val post2=Post(userId = 1002,id=null,title = "MyPost",body = "I Love You")
//        apiService.createPost(listOf(post1,post2)).enqueue(object : Callback<List<Post>>{
//            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
//                if (response.isSuccessful && response.body()  != null){
//                    for (post in response.body()!!)
//                        textView.append("userId : ${post.userId} \n" +
//                                "id : ${post.id} \n"  +
//                                "title : ${post.title} \n"  +
//                                "body : ${post.body} \n\n")
//                }
//            }
//            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
//                textView.text = "Request Failure : ${t.message}"
//            }
//
//        })
//    }

    private fun createPostWithFieldAnnotation(){
        apiService.createPost(1000,"MyPost","I Love You 2").enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful && response.body()  != null){
                    val post=response.body()!!
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }
    private fun createPostWithFieldMapAnnotation(){
        val map=HashMap<String,String>()
        map["userId"] = "1"
        map["title"] = "MyPost"
        map["body"] = "I Love You 3"
        apiService.createPost(map).enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful && response.body()  != null){
                    val post=response.body()!!
                        textView.append("userId : ${post.userId} \n" +
                                "id : ${post.id} \n"  +
                                "title : ${post.title} \n"  +
                                "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }


    private fun updatePost(){
        val post=Post(userId = 1000,id = null,title = "MyPost",body = "I Love You55")
        apiService.updatePost(2,post).enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful && response.body()  != null){
                    val post=response.body()!!
                    textView.append("userId : ${post.userId} \n" +
                            "id : ${post.id} \n"  +
                            "title : ${post.title} \n"  +
                            "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }
    private fun updatePostField(){
        apiService.updatePostField(2,"Update Post").enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful && response.body()  != null){
                    val post=response.body()!!
                    textView.append("userId : ${post.userId} \n" +
                            "id : ${post.id} \n"  +
                            "title : ${post.title} \n"  +
                            "body : ${post.body} \n\n")
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                textView.text = "Request Failure : ${t.message}"
            }

        })
    }

    private fun deletePost(){
        apiService.deletePost(2).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    textView.text="Code : ${response.code()}"
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

        })
    }

}