package com.mobileapps.isschallengekotlin.ui

import com.mobileapps.isschallengekotlin.models.IssResponse

interface MainViewInterface
{
    fun displayResponse(issResponse: IssResponse?)
    fun displayError(s : String)
}