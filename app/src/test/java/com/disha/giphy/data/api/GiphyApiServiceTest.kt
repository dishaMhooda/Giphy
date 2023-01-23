package com.disha.giphy.data.api

import com.disha.giphy.BuildConfig
import com.disha.giphy.data.model.GIF
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class GiphyApiServiceTest {

    private lateinit var service: GiphyApiService
    private lateinit var server : MockWebServer

    @Before
    fun setUp(){
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GiphyApiService::class.java)
    }
    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }
    @Test
    fun getTrendingGiphy_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("trending_response.json")
            val responseBody = service.getTrendingGifs(1,BuildConfig.API_KEY,10).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v1/gifs/trending?offset=1&api_key=nMWrdyDb1WvurO9bOk8wdTrY7B1B5Tau&limit=10")
        }
    }

    @Test
    fun getTrendingGiphy_receivedResponse_correctOffset(){
        runBlocking {
            enqueueMockResponse("trending_response.json")
            val responseBody = service.getTrendingGifs(1,BuildConfig.API_KEY,25).body()
            val dataList = responseBody!!.data
            assertThat(dataList.size).isEqualTo(25)
        }
    }

    @Test
    fun getTrendingGiphy_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("trending_response.json")
            val responseBody = service.getTrendingGifs(1,BuildConfig.API_KEY,20).body()
            val dataList = responseBody!!.data
            val data = dataList[0]
            assertThat(data.title).isEqualTo("New Year Asian GIF by Hello All")
            assertThat(data.url).isEqualTo("https://giphy.com/gifs/helloall-new-year-chinese-2023-w6fWo7nFQ9slK5D6IB")
            assertThat(data.trending_datetime).isEqualTo("2023-01-22 03:00:07")
        }
    }

    @Test
    fun getTrendingGiphy_receivedResponse_4XX_Error(){
        runBlocking {
            val expectedResponse = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
            server.enqueue(expectedResponse)

            val actualResponse = service.getTrendingGifsForTesting(1, "", 20)
            assertThat(actualResponse.code()).isEqualTo(HttpURLConnection.HTTP_UNAUTHORIZED)
        }
    }


    @After
    fun tearDown() {
        server.shutdown()
    }
}