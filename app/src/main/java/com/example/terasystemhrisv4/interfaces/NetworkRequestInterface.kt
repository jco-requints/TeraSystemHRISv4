package com.example.terasystemhrisv4.interfaces

interface NetworkRequestInterface
{
    fun beforeNetworkCall()
    fun afterNetworkCall(result: String?)
}