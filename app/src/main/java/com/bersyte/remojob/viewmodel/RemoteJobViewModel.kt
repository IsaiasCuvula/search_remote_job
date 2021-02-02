package com.bersyte.remojob.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.remojob.models.JobToSave
import com.bersyte.remojob.repository.RemoteJobRepository
import kotlinx.coroutines.launch


class RemoteJobViewModel(
    app: Application,
    private val remoteJobRepository: RemoteJobRepository
) : AndroidViewModel(app) {

    fun remoteJobResult() =
        remoteJobRepository.getRemoteJobResponseLiveData()

    fun searchJob(query: String?) =
        remoteJobRepository.searchRemoteJob(query)


    fun searchResult() = remoteJobRepository.getSearchJobResponseLiveData()


    fun insertJob(job: JobToSave) = viewModelScope.launch {
        remoteJobRepository.insertJob(job)
    }

    fun deleteJob(job: JobToSave) = viewModelScope.launch {
        remoteJobRepository.deleteJob(job)
    }

    fun getAllJob() = remoteJobRepository.getAllJobs()

}