package ru.test.points.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import ru.test.points.R
import ru.test.points.base.dagger.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() =
        supportFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_tabs.setupWithViewPager(main_viewpager)
        main_viewpager.adapter = MainViewPagerAdapter(this, supportFragmentManager)
    }

    override fun onDestroy() {
        main_viewpager.adapter = null
        super.onDestroy()
    }
}
