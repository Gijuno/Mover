package com.cclean.mover.activity

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.cclean.mover.util.MainViewPagerAdapter
import com.cclean.mover.R
import com.cclean.mover.db.dataMangement
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_store.viewpager_main

class MainActivity : AppCompatActivity(){
    public lateinit var viewPagerAdapter: MainViewPagerAdapter
    private lateinit var dataMangement: dataMangement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)

        viewpager_main.adapter = viewPagerAdapter
        viewpager_main.offscreenPageLimit = 3
        viewpager_main.currentItem = 0

        //권한 체크
        val permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {}

            override fun onPermissionDenied(deniedPermissions: List<String>) {

            }
        }

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("애플리케이션 이용에는 권한이 꼭 필요합니다\n[설정] > [권한] 에서 권한을 허용할 수 있습니다")
                .setPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()

        imgbtn_home.setOnClickListener {
            viewpager_main.currentItem = 0
        }
        imgbtn_book.setOnClickListener {
            viewpager_main.currentItem = 1
        }
        imgbtn_person.setOnClickListener {
            viewpager_main.currentItem = 2
        }

        viewpager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                dataMangement.refresh()
            }
        })
    }

    public fun setDataMangement(dataMangement: dataMangement)
    {
        this.dataMangement = dataMangement
    }
}