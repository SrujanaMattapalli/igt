package com.test.myapplication;

import org.junit.Test;

import java.util.List;

import json.GameData;
import retrofit2.Call;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private final Call<List<GameData>> mCallListUserResponse;

    public ExampleUnitTest(Call<List<GameData>> mCallListUserResponse) {
        this.mCallListUserResponse = mCallListUserResponse;
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}