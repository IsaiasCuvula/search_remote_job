package com.bersyte.remojob.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job")
data class JobToSave(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val candidateRequiredLocation: String?,
    val category: String?,
    val companyLogoUrl: String?,
    val companyName: String?,
    val description: String?,
    val jobId: Int?,
    val jobType: String?,
    val publicationDate: String?,
    val salary: String?,
    val title: String?,
    val url: String?
)