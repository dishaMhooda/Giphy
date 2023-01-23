package com.disha.giphy.data.db


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.Images
import com.disha.giphy.data.model.Original
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class GiphyDaoTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var dao: GiphyDao
    private lateinit var database: GiphyDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GiphyDatabase::class.java
        ).addTypeConverter(Converter())
            .build()
        dao = database.getGiphyDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveFavouriteGiphy(): Unit = runBlocking {
        var getFavouriteGiphy: Flow<List<FavouriteGiphy>> = dao.getFavouriteGiphy()
        Truth.assertThat(listOf(getFavouriteGiphy)[0].first()).isEmpty()

        val favouriteGiphy = FavouriteGiphy(
            "1",
            Images(Original(url = "https://media2.giphy.com/media/w6fWo7nFQ9slK5D6IB/giphy.gif?cid=50c270aao9yhoshkabvwqb2ux98kt787ddl05ndu9pcfbbum&rid=giphy.gif&ct=g")),
            "New Year Asian GIF by Hello All",
            "2023-01-22 03:00:07",
            "gif",
            "https://giphy.com/gifs/helloall-new-year-chinese-2023-w6fWo7nFQ9slK5D6IB",
            "helloall"
        )
        dao.saveGiphyAsFavourite(favouriteGiphy)

        getFavouriteGiphy = dao.getFavouriteGiphy()
        Truth.assertThat(listOf(getFavouriteGiphy)[0].first()).contains(favouriteGiphy)

    }
}