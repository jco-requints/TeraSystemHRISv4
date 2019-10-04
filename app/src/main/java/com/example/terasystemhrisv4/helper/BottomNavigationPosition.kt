package com.example.terasystemhrisv4.helper

import androidx.fragment.app.Fragment
import com.example.terasystemhrisv4.R
import com.example.terasystemhrisv4.model.AccountDetails
import com.example.terasystemhrisv4.ui.ClientsFragment
import com.example.terasystemhrisv4.ui.LeavesFragment
import com.example.terasystemhrisv4.ui.LogsFragment
import com.example.terasystemhrisv4.ui.ProfileFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    LOGS(0, R.id.logs),
    LEAVES(1, R.id.leaves),
    CLIENTS(2, R.id.clients),
    PROFILE(3, R.id.profile);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.LOGS.id -> BottomNavigationPosition.LOGS
    BottomNavigationPosition.LEAVES.id -> BottomNavigationPosition.LEAVES
    BottomNavigationPosition.CLIENTS.id -> BottomNavigationPosition.CLIENTS
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.LOGS
}

fun BottomNavigationPosition.createFragment(accountDetails: AccountDetails): Fragment = when (this) {
    BottomNavigationPosition.LOGS -> LogsFragment.newInstance(accountDetails)
    BottomNavigationPosition.LEAVES -> LeavesFragment.newInstance(accountDetails)
    BottomNavigationPosition.CLIENTS -> ClientsFragment.newInstance(accountDetails)
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance(accountDetails)
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.LOGS -> LogsFragment.TAG
    BottomNavigationPosition.LEAVES -> LeavesFragment.TAG
    BottomNavigationPosition.CLIENTS -> ClientsFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}

