package com.example.jetpackwithjunit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.jetpackwithjunit.model.Repos
import com.example.jetpackwithjunit.retrofit.ApiService
import com.example.jetpackwithjunit.utils.LiveDataResult
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.mock
import java.net.SocketException

@RunWith(JUnit4::class)
class LoginFragmentViewModelTest {
        @get:Rule
        val instantTaskExecutorRule = InstantTaskExecutorRule()

        @Mock
        lateinit var userService: ApiService

        lateinit var mainViewModel: LoginFragmentViewModel

        @Before
        fun setUp() {
            MockitoAnnotations.initMocks(this)
            this.mainViewModel = LoginFragmentViewModel()
        }

        @Test
        fun fetchRepositories_positiveResponse() {
            Mockito.`when`(this.userService.getRepositories(ArgumentMatchers.anyString()))
                .thenAnswer {
                    return@thenAnswer Maybe.just(ArgumentMatchers.anyList<LiveDataResult<Repos>>())
                }

            val observer = mock(Observer::class.java) as Observer<LiveDataResult<List<Repos>>>
            this.mainViewModel.repositoriesLiveData.observeForever(observer)

            this.mainViewModel.fetchUserRepositories(ArgumentMatchers.anyString())

            assertNotNull(this.mainViewModel.repositoriesLiveData.value)
            assertEquals(
                LiveDataResult.Status.SUCCESS,
                this.mainViewModel.repositoriesLiveData.value?.status
            )
        }

        @Test
        fun fetchRepositories_error() {
            Mockito.`when`(this.userService.getRepositories(ArgumentMatchers.anyString()))
                .thenAnswer {
                    return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
                }

            val observer = mock(Observer::class.java) as Observer<LiveDataResult<List<Repos>>>
            this.mainViewModel.repositoriesLiveData.observeForever(observer)

            this.mainViewModel.fetchUserRepositories(ArgumentMatchers.anyString())

            assertNotNull(this.mainViewModel.repositoriesLiveData.value)
            assertEquals(
                LiveDataResult.Status.ERROR,
                this.mainViewModel.repositoriesLiveData.value?.status
            )
            assert(this.mainViewModel.repositoriesLiveData.value?.err is Throwable)
        }

        @Test
        fun setLoadingVisibility_onSuccess() {
            Mockito.`when`(this.userService.getRepositories(com.nhaarman.mockitokotlin2.any()))
                .thenAnswer {
                    return@thenAnswer Maybe.just(listOf<Repos>())
                }

            val spiedViewModel = com.nhaarman.mockitokotlin2.spy(this.mainViewModel)

            spiedViewModel.fetchUserRepositories(ArgumentMatchers.anyString())
            Mockito.verify(spiedViewModel, Mockito.times(2))
                .setLoadingVisibility(ArgumentMatchers.anyBoolean())
        }

        @Test
        fun setLoadingVisibility_onError() {
            Mockito.`when`(this.userService.getRepositories(com.nhaarman.mockitokotlin2.any()))
                .thenAnswer {
                    return@thenAnswer Maybe.error<SocketException>(SocketException())
                }

            val spiedViewModel = com.nhaarman.mockitokotlin2.spy(this.mainViewModel)

            spiedViewModel.fetchUserRepositories(ArgumentMatchers.anyString())
            Mockito.verify(spiedViewModel, Mockito.times(2))
                .setLoadingVisibility(ArgumentMatchers.anyBoolean())
        }

        @Test
        fun setLoadingVisibility_onNoData() {
            Mockito.`when`(this.userService.getRepositories(com.nhaarman.mockitokotlin2.any()))
                .thenReturn(Maybe.create {
                    it.onComplete()
                })

            val spiedViewModel = com.nhaarman.mockitokotlin2.spy(this.mainViewModel)

            spiedViewModel.fetchUserRepositories(ArgumentMatchers.anyString())
            Mockito.verify(spiedViewModel, Mockito.times(2))
                .setLoadingVisibility(ArgumentMatchers.anyBoolean())
        }

    }