package fhj.swengb.course.android.hello

import android.app.Activity
import android.os.Bundle
import android.util.Log


/**
 * A simple (the simplest?) Android app.
 */
class HelloWorld extends Activity {
  val TAG = "HelloWorld"
  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)

    Log.i(TAG, "called onCreate")
    setContentView(R.layout.helloworld)
  }
}