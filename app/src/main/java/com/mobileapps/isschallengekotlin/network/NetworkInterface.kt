package com.mobileapps.isschallengekotlin.network

import com.mobileapps.isschallengekotlin.models.IssResponse
import com.mobileapps.isschallengekotlin.network.NetworkHelper.ISS_RESPONSE_PATH
import com.mobileapps.isschallengekotlin.network.NetworkHelper.QUERY_LAT
import com.mobileapps.isschallengekotlin.network.NetworkHelper.QUERY_LONG
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface NetworkInterface
{
    @GET(ISS_RESPONSE_PATH)
    fun getIssResponse(
            @Query(QUERY_LAT) lat: String,
            @Query(QUERY_LONG) long: String): Observable<IssResponse>
}