package by.lifetech.test

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.lifetech.test.controller.TestController
import by.lifetech.test.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    private var controller: TestController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecycler()

        observeData()

        getData()
    }

    private fun getData() {
        viewModel.getData()
    }

    private fun setUpRecycler() {
        controller = TestController()
        val manager = LinearLayoutManager(this)
        binding.recycler.layoutManager = manager
        binding.recycler.adapter = controller?.adapter
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.getDataChannel()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    controller?.setList(it)
                }
        }
    }
}