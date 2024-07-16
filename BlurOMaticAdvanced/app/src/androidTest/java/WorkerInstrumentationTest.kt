import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.workers.BlurWorker
import com.example.bluromatic.workers.CleanupWorker
import com.example.bluromatic.workers.SaveImageToFileWorker
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WorkerInstrumentationTest {
	
	private lateinit var context: Context
	private val mockImageUri = KEY_IMAGE_URI to
			"android.resource://com.example.bluromatic/drawable/android_cupcake"
	
	@Before
	fun setup() {
		context = ApplicationProvider.getApplicationContext()
	}
	
	@Test
	fun cleanupWorker_doWork_resultSuccess() {
		val worker = TestListenableWorkerBuilder<CleanupWorker>(context).build()
		
		runBlocking {
			val result = worker.doWork()
			
			assertTrue(result is ListenableWorker.Result.Success)
		}
	}
	
	@Test
	fun blurWorker_doWork_resultSuccessReturnUri() {
		val worker = TestListenableWorkerBuilder<BlurWorker>(context)
			.setInputData(workDataOf(mockImageUri))
			.build()
		
		runBlocking {
			val result = worker.doWork()
			val uri = result.outputData.getString(KEY_IMAGE_URI)
			
			assertTrue(result is ListenableWorker.Result.Success)
			assertTrue(result.outputData.keyValueMap.containsKey(KEY_IMAGE_URI))
			assertTrue(
				uri != null &&
				uri.startsWith(
					"file:///data/user/0/com.example.bluromatic/files/blur_filter_outputs/blur-filter-output-"
				)
			)
		}
	}
	
	@Test
	fun saveImageToFileWorker_doWork_resultSuccessReturnUri() {
		val worker = TestListenableWorkerBuilder<SaveImageToFileWorker>(context)
			.setInputData(workDataOf(mockImageUri))
			.build()
		
		runBlocking {
			val result = worker.doWork()
			val uri = result.outputData.getString(KEY_IMAGE_URI)
			
			assertTrue(result is ListenableWorker.Result.Success)
			assertTrue(result.outputData.keyValueMap.containsKey(KEY_IMAGE_URI))
			assertTrue(
				uri != null &&
						uri.startsWith(
							"content://media/external/images/media/"
						)
			)
		}
	}
}