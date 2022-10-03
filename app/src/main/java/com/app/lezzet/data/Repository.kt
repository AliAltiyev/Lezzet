package com.app.lezzet.data

import com.app.lezzet.data.database.LocalDataSource
import com.app.lezzet.data.network.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    val remoteDataSource = remoteDataSource
    val localDataSource = localDataSource
}