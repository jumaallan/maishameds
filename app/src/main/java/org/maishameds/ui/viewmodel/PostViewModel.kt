/*
 * Copyright 2020 MaishaMeds
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.maishameds.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import org.maishameds.core.data.network.PostsResponse
import org.maishameds.core.network.NetworkResult
import org.maishameds.data.model.Post
import org.maishameds.data.repository.PostRepository

class PostViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    suspend fun fetchPosts(): Flow<NetworkResult<List<PostsResponse>>> =
        postRepository.fetchPosts()

    suspend fun savePosts(posts: List<Post>) =
        postRepository.savePosts(posts)

    fun getPosts(): LiveData<List<Post>> =
        postRepository.getPosts().asLiveData()
}