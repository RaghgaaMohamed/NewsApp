package com.example.newsapplication.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.*
import com.example.newsapplication.databinding.ActivityHomePageBinding
import com.google.android.material.tabs.TabLayout
import com.route.base.BaseActivity

class HomePage : BaseActivity<ActivityHomePageBinding,HomeViewModel>() ,TabLayout.OnTabSelectedListener ,Navigator{

    lateinit var adapter: NewsAdapter

    override fun getlayoutId(): Int {
        return R.layout.activity_home_page
    }

    override fun initializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm=viewModel
        setUpnews()
        viewModel.getSources()
        ObserveLiveData()
        viewModel.navigator=this
    }

     fun ObserveLiveData() {
        viewModel.messageLiveData.observe(this, Observer { message ->
            showDialoge(message = message, posActionName = "ok",
                posAction = DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })

        })
        viewModel.showprogressLiveData.observe(this, Observer { show ->
            if (show)
               viewDataBinding.progressBar.visibility = View.VISIBLE
            else
                viewDataBinding.progressBar.visibility = View.GONE
        })



        viewModel.sourcesLiveData.observe(this, Observer { sourcesList ->
            showSourcesinTabLayout(sourcesList)
        })
        viewModel.showNewsLiveData.observe(this, Observer { ArticlesItem ->
            showNewsinRecyclerView(ArticlesItem)
        })
    }

     fun setUpnews() {
        adapter = NewsAdapter(null)
        viewDataBinding.recyclerView.adapter = adapter
    }

    private fun showSourcesinTabLayout(sources: List<SourcesItem?>?) {
        sources?.forEach {
            val tab = viewDataBinding.tabLayout.newTab()
            tab.setText(it?.name)
            tab.tag = it
            viewDataBinding.tabLayout.addTab(tab)
        }
        viewDataBinding.tabLayout.addOnTabSelectedListener(this)
        viewDataBinding.tabLayout.getTabAt(0)?.select()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        viewModel.getEverythingNews(item.id)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        viewModel.getEverythingNews(item.id)
    }


    private fun showNewsinRecyclerView(newsList: List<ArticlesItem?>?) {
        adapter.changeData(newsList)

    }


}