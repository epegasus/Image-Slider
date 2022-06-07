package dev.pegasus.imageslider

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import dev.pegasus.imageslider.adapter.HomeSliderPagerAdapter
import dev.pegasus.imageslider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewPagerAdapter: HomeSliderPagerAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val dots by lazy {
        arrayOfNulls<ImageView>(viewPagerAdapter.currentList.size)
    }

    private fun initializations() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable { changePage() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializations()
        initViewPager()
    }

    private fun initViewPager() {
        viewPagerAdapter = HomeSliderPagerAdapter()
        binding.vpImagesMain.adapter = viewPagerAdapter
        viewPagerAdapter.submitList(getHomeSliderList())
        createDots()
        binding.vpImagesMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                selectDotAndStyle()
            }
        })
    }

    private fun getHomeSliderList(): ArrayList<Int> {
        val imageList = ArrayList<Int>()
        imageList.add(R.drawable.dummy)
        imageList.add(R.drawable.dummy)
        imageList.add(R.drawable.dummy)
        return imageList
    }

    private fun createDots() {
        for (i in 0 until viewPagerAdapter.currentList.size) {
            dots[i] = ImageView(this)
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive_transparent))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            binding.llDotsHome.addView(dots[i], params)
        }
        selectDotAndStyle()
    }

    private fun selectDotAndStyle() {
        val currentItem = binding.vpImagesMain.currentItem
        val totalSize = viewPagerAdapter.currentList.size
        for (i in 0 until totalSize) {
            val drawableId: Int = if (i == currentItem) R.drawable.dot_active else R.drawable.dot_inactive_transparent
            dots[i]?.setImageResource(drawableId)
        }
        if ((currentItem + 1) < totalSize) {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 2600)
        } else {
            handler.postDelayed({
                binding.vpImagesMain.setCurrentItem(0, true)
            }, 2600)
        }
    }

    private fun changePage() {
        binding.vpImagesMain.setCurrentItem(binding.vpImagesMain.currentItem + 1, true)
    }
}