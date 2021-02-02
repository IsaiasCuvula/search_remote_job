package com.bersyte.remojob.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bersyte.remojob.MainActivity
import com.bersyte.remojob.R
import com.bersyte.remojob.databinding.FragmentJobDetailsBinding
import com.bersyte.remojob.models.Job
import com.bersyte.remojob.models.JobToSave
import com.bersyte.remojob.viewmodel.RemoteJobViewModel
import com.google.android.material.snackbar.Snackbar


class JobDetailsFragment : Fragment(R.layout.fragment_job_details) {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: JobDetailsFragmentArgs by navArgs()
    private lateinit var currentJob: Job
    private lateinit var viewModel: RemoteJobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJobDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        currentJob = args.job!!
        setUpWebView()
        binding.fabAddFavorite.setOnClickListener {
            addJobToFavorite(view)
        }

    }

    private fun setUpWebView(){
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob.url?.let { loadUrl(it) }
        }

        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setSupportZoom(false)
        settings.builtInZoomControls = false
        settings.displayZoomControls = false
        settings.textZoom = 100
        settings.blockNetworkImage = false
        settings.loadsImagesAutomatically = true
    }

    private fun addJobToFavorite(view: View) {
        val job = JobToSave(
            0,
            currentJob.candidateRequiredLocation, currentJob.category,
            currentJob.companyLogoUrl, currentJob.companyName,
            currentJob.description, currentJob.id, currentJob.jobType,
            currentJob.publicationDate, currentJob.salary, currentJob.title, currentJob.url
        )

        viewModel.insertJob(job)
        Snackbar.make(view, "Job saved successfully", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}