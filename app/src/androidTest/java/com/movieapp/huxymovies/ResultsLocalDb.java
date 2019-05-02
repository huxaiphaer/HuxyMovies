package com.movieapp.huxymovies;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.movieapp.huxymovies.data.local.ResultsDao;
import com.movieapp.huxymovies.data.local.ResultsDatabase;
import com.movieapp.huxymovies.model.Result;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ResultsLocalDb {

    private ResultsDatabase mDatabase;
    private ResultsDao resultsDao;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ResultsDatabase.class)
                .allowMainThreadQueries()
                .build();

        resultsDao = mDatabase.resultsDao();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void onFetchingNotes_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {

        List<Result> noteList = LiveDataTestUtil.INSTANCE.getValue(resultsDao.getAllMovies());
        assertTrue(noteList.isEmpty());

    }

    @Test
    public void onInsertingMovies_checkIf_RowCountIsCorrect() throws InterruptedException {


        final List<Result> resultList = new ArrayList<>();
        resultList.add(new Result(1L, "Great Movie",
                "https://image.com", "Movie 1", 4.3));
        resultList.forEach(result -> {
            resultsDao.insertAllMovies(resultList);
        });
        assertEquals(1, LiveDataTestUtil.INSTANCE.getValue(resultsDao.getAllMovies()).size());
    }

    @Test
    public void onResultDeletion_CheckIf_NoteIsDeletedFromTable() throws InterruptedException {
        List<Result> resultList = new ArrayList<>();

        resultList.add(new Result(1L, "Great Movie",
                "https://image.com", "Movie 1", 4.3));

        resultList.forEach(result -> {
            resultsDao.insertAllMovies(resultList);
        });

        resultsDao.deleteAllMovies();
        assertEquals(0, LiveDataTestUtil.INSTANCE.getValue(resultsDao.getAllMovies()).size());
    }
}
